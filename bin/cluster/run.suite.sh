#!/usr/bin/env bash
set -ex

COOLDOWN=${COOLDOWN:-10s}

# If no arguments passed use IGA_PROBLEM_SIZE as a variable and 12 as a single size to run
[ "$#" == "0" ] && set -- IGA_PROBLEM_SIZE 12

# Input parameters
PERF_VARIABLE_NAME="${1}"

# Get the directories
SCRIPTPATH="$( cd "$(dirname "$0")" || exit ; pwd -P )"
RUN_SCRIPT="${SCRIPTPATH}/run.parameterized.sh"
DOWNLOAD_LOGS_SCRIPT="${SCRIPTPATH}/download.logs.sh"

# Naming
TIMESTAMP=$(date +%s)

for ((i=2;i<=$#;i++)); do
  VARIABLE_VALUE="${!i}"
  export "${PERF_VARIABLE_NAME}"="${VARIABLE_VALUE}"
  OUTPUT_FILE="suite-${TIMESTAMP}-${PERF_VARIABLE_NAME}-${VARIABLE_VALUE}.txt"
  "${RUN_SCRIPT}" |& tee  "${OUTPUT_FILE}"
  sleep "${COOLDOWN}"

  echo "Storing application state"
  APP_ID=$(yarn application -list -appStates ALL | grep -m 1 -oe "application_[0-9]*_[0-9]*")
  if [[ -z "${APP_ID}" ]]; then
    echo "Could not locate application" 1>&2
    exit 1
  fi
  APP_STATE=$(yarn application -status "${APP_ID}" | grep -oe "Final-State : .*$" | grep -oe "[A-Z]*$")
  touch "${OUTPUT_FILE}.${APP_ID}.${APP_STATE}"

  echo "Downloading logs for ${APP_ID}"
  "${DOWNLOAD_LOGS_SCRIPT}" "${APP_ID}"

  echo "Copying suite file to the log dir"
  cp "${OUTPUT_FILE}"* "logs/${APP_ID}/"
done
