#!/usr/bin/env bash
set -ex

SUITE_DIR="$( cd "$(dirname "$0")" || exit ; pwd -P )"
RUN_SCRIPT="${SUITE_DIR}/../run.suite.sh"

. "${SUITE_DIR}/env/n1-standard-96.sh"

 function execute {
   problemSize=$1
   workers=$2
   workerCores=$3
   run=$4
      export IGA_PROBLEM_SIZE=${problemSize} \
        && export IGA_WORKER_CORES=${workerCores} \
        && export SUITE_NAME="explicit-config-${problemSize}-on-${workers}w-${workerCores}c-${run}r" \
        && ${RUN_SCRIPT} IGA_WORKERS "$workers"
 }

echo "Going to run ${RUNS} times"

for run in $(seq 1 1 "${RUNS}");
do
  execute 1536 1 1 "$run"
  execute 1536 4 16 "$run"
  execute 3072 1 1 "$run"
  execute 3072 4 16 "$run"
  execute 6144 1 1 "$run"
  execute 6144 4 16 "$run"
done;
