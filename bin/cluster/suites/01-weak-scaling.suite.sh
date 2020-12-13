#!/usr/bin/env bash
set -ex

SUITE_DIR="$( cd "$(dirname "$0")" || exit ; pwd -P )"
RUN_SCRIPT="${SUITE_DIR}/../run.suite.sh"

. "${SUITE_DIR}/env/n2-standard-16.sh"

bash "${RUN_SCRIPT} IGA_WORKERS 4 3 2 1"
