#!/usr/bin/env bash
set -ex
trap "exit" INT TERM
trap "kill 0" EXIT

COOLDOWN=${COOLDOWN:-30s}

# If no arguments passed use IGA_PROBLEM_SIZE as a variable and 12 as a single size to run
[ "$#" == "0" ] && set -- IGA_PROBLEM_SIZE 12

# Input parameters
SUITE_NAME=${SUITE_NAME:-DEFAULT}
PERF_VARIABLE_NAME="${1}"

# Get the directories
SCRIPTPATH="$( cd "$(dirname "$0")" || exit ; pwd -P )"
RUN_SCRIPT="${SCRIPTPATH}/run.parameterized.sh"
DOWNLOAD_LOGS_SCRIPT="${SCRIPTPATH}/download.logs.sh"
RECORD_CPU_USAGE_SCRIPT="${SCRIPTPATH}/record.cpu.usage.sh"
RECORD_MEMORY_USAGE_SCRIPT="${SCRIPTPATH}/record.memory.usage.sh"
PRINT_SYSTEM_INFO="${SCRIPTPATH}/print.sys.info.sh"

# Naming
TIMESTAMP=$(date +%s)

# Make sure recordings write to this directory
export OUTPUT_DIR="$(pwd)"

for ((i=2;i<=$#;i++)); do
  VARIABLE_VALUE="${!i}"
  export "${PERF_VARIABLE_NAME}"="${VARIABLE_VALUE}"
  OUTPUT_FILE="suite-${TIMESTAMP}-${PERF_VARIABLE_NAME}-${VARIABLE_VALUE}.txt"

  echo "Printing system info"
  bash -c "$PRINT_SYSTEM_INFO $SUITE_NAME"
  echo "Starting CPU usage recording"
  bash -c "$RECORD_CPU_USAGE_SCRIPT $SUITE_NAME" &
  echo "Starting memory usage recording"
  bash -c "$RECORD_MEMORY_USAGE_SCRIPT $SUITE_NAME" &
  time("$RUN_SCRIPT") |& tee  "${OUTPUT_FILE}"
  echo "Stopping recordings"
  jobs -p | xargs kill
  sleep "${COOLDOWN}"

  echo "Storing application state"
  APP_ID=$(yarn application -list -appStates ALL | sort -r | grep -m 1 -oe "application_[0-9]*_[0-9]*")
  if [[ -z "${APP_ID}" ]]; then
    echo "Could not locate application" 1>&2
    exit 1
  fi
  APP_STATE=$(yarn application -status "${APP_ID}" | grep -oe "Final-State : .*$" | grep -oe "[A-Z]*$")
  touch "${OUTPUT_FILE}.${APP_ID}.${APP_STATE}"

  echo "Downloading logs for ${APP_ID}"
  "${DOWNLOAD_LOGS_SCRIPT}" "${APP_ID}"

  echo "Copying suite files to the log dir"
  cp "${OUTPUT_FILE}"* "logs/${APP_ID}/"
  cp "${SUITE_NAME}"*.txt "logs/${APP_ID}/"

  echo "Copying parameters to the log dir"
  printenv | grep "IGA_" | sed -e 's/^/export /' | cat - > "logs/${APP_ID}/parameters.sh"

  echo "Renaming dir to match suite"
  mv "logs/${APP_ID}" "logs/suite-${SUITE_NAME}-${APP_ID}"
done
