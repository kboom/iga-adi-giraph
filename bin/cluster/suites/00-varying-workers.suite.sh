#!/usr/bin/env bash
set -ex

SUITE_DIR="$( cd "$(dirname "$0")" || exit ; pwd -P )"
RUN_SCRIPT="${SUITE_DIR}/../run.suite.sh"

. "${SUITE_DIR}/env/current.sh"

for problemSize in 24576;
do
  export IGA_PROBLEM_SIZE=${problemSize} && export SUITE_NAME="strong-scalability-${problemSize}" \
    && ${RUN_SCRIPT} IGA_WORKERS 4
done;
