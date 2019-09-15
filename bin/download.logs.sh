#!/usr/bin/env bash
LOG_DIR=$(pwd)/logs && rm -rf ${LOG_DIR} && yarn logs -applicationId ${1} -out ${LOG_DIR}