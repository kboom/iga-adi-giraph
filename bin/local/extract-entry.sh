#!/usr/bin/env bash

INIT_SUPERSTEP=${INIT_SUPERSTEP:-51}

INPUT_R='^.*input superstep: Took ([0-9]*\.[0-9]*) seconds'
INIT_R="^.*superstep ${INIT_SUPERSTEP}: Took ([0-9]*\.[0-9]*) seconds"
TOTAL_R='^.*total: Took ([0-9]*\.[0-9]*)'
SHUTDOWN_R='^.*shutdown: Took ([0-9]*\.[0-9]*) seconds'

INPUT_TIME=0.0
INIT_TIME=0.0
TOTAL_TIME=0.0
SHUTDOWN_TIME=0.0

while read -r line;
do
  if [[ "$line" =~ $INPUT_R ]]; then
    INPUT_TIME="${BASH_REMATCH[1]}"
  elif [[ "$line" =~ $INIT_R ]]; then
    INIT_TIME="${BASH_REMATCH[1]}"
  elif [[ "$line" =~ $SHUTDOWN_R ]]; then
    SHUTDOWN_TIME="${BASH_REMATCH[1]}"
  elif [[ "$line" =~ $TOTAL_R ]]; then
    TOTAL_TIME="${BASH_REMATCH[1]}"
  fi
done < "${1:-/dev/stdin}"

echo "${INPUT_TIME},${INIT_TIME},$(echo "${TOTAL_TIME} - ${SHUTDOWN_TIME}" | bc)"