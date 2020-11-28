#!/usr/bin/env bash
set -ex

SUITE_DIR="$( cd "$(dirname "$0")" || exit ; pwd -P )"
RUN_SCRIPT="${SUITE_DIR}/../run.suite.sh"

. "${SUITE_DIR}/env/current.sh"

 function execute {
   problemSize=$1
   workers=$2
   run=$3
      export IGA_PROBLEM_SIZE=${problemSize} \
        && export SUITE_NAME="fixed-problem-${problemSize}-on-${workers}w-${run}r" \
        && ${RUN_SCRIPT} IGA_WORKERS $workers
 }

for run in 1..${RUNS};
do
  execute 768 2 "$run"
  execute 1536 2 "$run"
done;
