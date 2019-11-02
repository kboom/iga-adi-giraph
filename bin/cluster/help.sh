#!/usr/bin/env bash
SEPARATOR="------------------------------------------------------"

echo ${SEPARATOR}
echo "To run the test suite"
echo "source .env && ./run.suite.sh <PARAMETER> [VALUES]"

echo ${SEPARATOR}
echo "To list the available containers"
echo "yarn logs -applicationId <APP_ID> -show_container_log_info"

echo ${SEPARATOR}
echo "To download all logs for application"
echo "./download.logs.sh <APP_ID>"

echo ${SEPARATOR}
echo "See the time breakdown. The container with those logs is 2 (master)."
echo "yarn logs -containerId <APP_ID> | grep Took"