#!/usr/bin/env bash
set -ex

SUITE_DIR="$( cd "$(dirname "$0")" || exit ; pwd -P )"
RUN_SCRIPT="${SUITE_DIR}/../run.suite.sh"

. "${SUITE_DIR}/env/current.sh"

${RUN_SCRIPT} IGA_PROBLEM_SIZE 49152 24576 12288 6144 3072 1536 768
