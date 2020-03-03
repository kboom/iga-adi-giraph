#!/usr/bin/env bash

INIT_SUPERSTEP=${INIT_SUPERSTEP:-51}

INPUT_R='^.*input superstep: Took ([0-9]*\.[0-9]*) seconds'
SUPERSTEP_R="^.*superstep ([0-9]*): Took ([0-9]*\.[0-9]*) seconds"
TOTAL_R='^.*total: Took ([0-9]*\.[0-9]*)'
SHUTDOWN_R='^.*shutdown: Took ([0-9]*\.[0-9]*) seconds'

INPUT_TIME=0.0
INIT_TIME=0.0
TOTAL_TIME=0.0
SHUTDOWN_TIME=0.0
STEP_SOLUTION_TIME=0.0

while read -r line;
do
  if [[ "$line" =~ $INPUT_R ]]; then
    INPUT_TIME="${BASH_REMATCH[1]}"
  elif [[ "$line" =~ $SHUTDOWN_R ]]; then
    SHUTDOWN_TIME="${BASH_REMATCH[1]}"
  elif [[ "$line" =~ $TOTAL_R ]]; then
    TOTAL_TIME="${BASH_REMATCH[1]}"
  elif [[ "$line" =~ $SUPERSTEP_R ]]; then
    thisStep="${BASH_REMATCH[1]}"
    thisTime="${BASH_REMATCH[2]}"
    if ! [[ $thisStep < "${INIT_SUPERSTEP}" ]]; then
      STEP_SOLUTION_TIME=$(echo "${STEP_SOLUTION_TIME} + ${thisTime}" | bc)
      if [[ $thisStep = "${INIT_SUPERSTEP}" ]]; then
        INIT_TIME=$thisTime
      fi
    fi
  fi
done < "${1:-/dev/stdin}"

echo "${INPUT_TIME},${INIT_TIME},${STEP_SOLUTION_TIME},${SHUTDOWN_TIME},${TOTAL_TIME}"