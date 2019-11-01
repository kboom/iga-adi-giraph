#!/usr/bin/env bash
set -ex

# If no arguments passed use IGA_PROBLEM_SIZE as a variable and 12 as a single size to run
[ "$#" == "0" ] && set -- IGA_PROBLEM_SIZE 12

# Input parameters
PERF_VARIABLE_NAME="${1}"

# Get the directories
SCRIPTPATH="$( cd "$(dirname "$0")" || exit ; pwd -P )"
RUN_SCRIPT="${SCRIPTPATH}/run.parameterized.sh"

# Naming
TIMESTAMP=$(date +%s)

for ((i=2;i<=$#;i++)); do
  VARIABLE_VALUE="${!i}"
  export "${PERF_VARIABLE_NAME}"="${VARIABLE_VALUE}"
  "${RUN_SCRIPT}" |& tee  "suite-${TIMESTAMP}-${PERF_VARIABLE_NAME}-${VARIABLE_VALUE}.txt"
done
