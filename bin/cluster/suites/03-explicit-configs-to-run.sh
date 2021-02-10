#!/usr/bin/env bash
set -ex

SUITE_DIR="$( cd "$(dirname "$0")" || exit ; pwd -P )"
RUN_SCRIPT="${SUITE_DIR}/../run.suite.sh"
CONFIG_TO_LOAD="$1"

. "$CONFIG_TO_LOAD"

 function execute {
   problemSize=$1
   workers=$2
   workerCores=$3
   run=$4
   workerMemory=$((IGA_TOTAL_MEMORY / (workers+2)))

      export IGA_PROBLEM_SIZE=${problemSize} \
        && export IGA_WORKER_CORES=${workerCores} \
        && export IGA_WORKER_MEMORY=${workerMemory} \
        && export SUITE_NAME="explicit-config-${problemSize}-on-${workers}w-${workerCores}c-${workerMemory}m-${run}r" \
        && ${RUN_SCRIPT} IGA_WORKERS "$workers"
 }

echo "Going to run ${RUNS} times"

for run in $(seq 1 1 "${RUNS}");
do
  execute 6144 1 64 "$run"
  execute 6144 2 32 "$run"
  execute 6144 4 16 "$run"
  execute 6144 8 8 "$run"
  execute 6144 16 4 "$run"
done;
