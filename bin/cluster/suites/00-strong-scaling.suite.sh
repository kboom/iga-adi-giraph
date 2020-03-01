#!/usr/bin/env bash
set -ex

SUITE_DIR="$( cd "$(dirname "$0")" || exit ; pwd -P )"
RUN_SCRIPT="${SUITE_DIR}/../run.suite.sh"

. "${SUITE_DIR}/env/current.sh"

for problemSize in 12288 6144 3072 1536 768 384 192;
do
  export IGA_PROBLEM_SIZE=${problemSize} && export SUITE_NAME="strong-scalability-${problemSize}" \
    && ${RUN_SCRIPT} IGA_WORKER_CORES 4 2 1
done;