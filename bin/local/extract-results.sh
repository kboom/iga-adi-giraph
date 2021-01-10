#!/usr/bin/env bash

SCRIPTPATH="$( cd "$(dirname "$0")" || exit ; pwd -P )"

if [[ -z "${1}" ]]; then
  echo "Required directory name"
  exit 1
else
  RESULTS_DIR="${1}"
fi

for dir in "${RESULTS_DIR}"/*
do
    dir=${dir%*/}
    . "$dir/parameters.sh"
    RESULT_FILE=$(find "${dir}" -name "container_*_000002")
    RUN_DESC="${IGA_PROBLEM_SIZE},${IGA_WORKERS},$((IGA_WORKER_CORES * IGA_WORKERS)),${IGA_STEPS}"
    RUN_TIMES=$("${SCRIPTPATH}/extract-entry.sh" < "${RESULT_FILE}")
    printf "%s,%s\n" "${RUN_DESC}" "${RUN_TIMES}"
done

