#!/usr/bin/env bash
LOG_DIR=$(pwd)/logs/"${1}" && rm -rf "${LOG_DIR}" && yarn logs -applicationId "${1}" -out "${LOG_DIR}"