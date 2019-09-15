#!/usr/bin/env bash
SEPARATOR="------------------------------------------------------"

echo ${SEPARATOR}
echo "To list the available containers"
echo "yarn logs -applicationId \${1} -show_container_log_info"

echo ${SEPARATOR}
echo "To show the logs of a specific container"
echo "yarn logs -containerId \${1}"

echo ${SEPARATOR}
echo "To download all logs for application"
echo "LOG_DIR=$(pwd)/logs && rm -rf \${LOG_DIR} && yarn logs -applicationId ${1} -out \${LOG_DIR}"

echo ${SEPARATOR}
echo "See the time breakdown. The container with those logs is 2 (master)."
echo "yarn logs -containerId \${1} | grep Took"