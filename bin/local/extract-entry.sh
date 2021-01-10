#!/usr/bin/env bash

STEPS_COUNT=${STEPS_COUNT:-2}
DAG_HEIGHT=${DAG_HEIGHT:-11}
SUPERSTEPS_IN_TIME_STEP=$(echo "4 * ${DAG_HEIGHT} + 6" | bc)
FIRST_SUPERSTEP_OF_SECOND_TIME_STEP=$(echo "(${STEPS_COUNT} - 1) * ${SUPERSTEPS_IN_TIME_STEP} + 1" | bc)

INIT_SUPERSTEP=$FIRST_SUPERSTEP_OF_SECOND_TIME_STEP
FIRST_ROOT_SUPERSTEP=$(echo "${FIRST_SUPERSTEP_OF_SECOND_TIME_STEP} + ${DAG_HEIGHT} + 1" | bc)
TRANSPOSE_MAP_SUPERSTEP=$(echo "${FIRST_SUPERSTEP_OF_SECOND_TIME_STEP} + 2*${DAG_HEIGHT} + 3" | bc)
TRANSPOSE_REDUCE_SUPERSTEP=$(echo "${TRANSPOSE_MAP_SUPERSTEP} + 1" | bc)
SECOND_ROOT_SUPERSTEP=$(echo "${FIRST_SUPERSTEP_OF_SECOND_TIME_STEP} + 3*${DAG_HEIGHT} + 5" | bc)

if [[ -n "${DEBUG}" ]]; then
printf "SUPERSTEPS_IN_TIME_STEP=${SUPERSTEPS_IN_TIME_STEP}\n"
printf "INIT_SUPERSTEP=${INIT_SUPERSTEP}\n"
printf "FIRST_ROOT_SUPERSTEP=${FIRST_ROOT_SUPERSTEP}\n"
printf "TRANSPOSE_MAP_SUPERSTEP=${TRANSPOSE_MAP_SUPERSTEP}\n"
printf "TRANSPOSE_REDUCE_SUPERSTEP=${TRANSPOSE_REDUCE_SUPERSTEP}\n"
printf "SECOND_ROOT_SUPERSTEP=${SECOND_ROOT_SUPERSTEP}\n"
fi

INPUT_R='^.*input superstep: Took ([0-9]*\.[0-9]*) seconds'
SUPERSTEP_R="^.*superstep ([0-9]*): Took ([0-9]*\.[0-9]*) seconds"
TOTAL_R='^.*total: Took ([0-9]*\.[0-9]*)'
SHUTDOWN_R='^.*shutdown: Took ([0-9]*\.[0-9]*) seconds'

INPUT_TIME=0.0
INIT_TIME=0.0
FACTORIZATION_TIME=0.0
BACKWARDS_SUBSTITUTION_TIME=0.0
TRANSPOSE_MAP_TIME=0.0
TRANSPOSE_REDUCE_TIME=0.0
STEP_SOLUTION_TIME=0.0
SHUTDOWN_TIME=0.0
TOTAL_TIME=0.0

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

    if [[ $thisStep -ge "${INIT_SUPERSTEP}" ]]; then
      STEP_SOLUTION_TIME=$(echo "${STEP_SOLUTION_TIME} + ${thisTime}" | bc)
    fi

    if [[ $thisStep = "${INIT_SUPERSTEP}" ]]; then
      INIT_TIME=$thisTime
      continue
    elif [[ $thisStep = "${TRANSPOSE_MAP_SUPERSTEP}" ]]; then
      TRANSPOSE_MAP_TIME=$thisTime
      continue
    elif [[ $thisStep = "${TRANSPOSE_REDUCE_SUPERSTEP}" ]]; then
      TRANSPOSE_REDUCE_TIME=$thisTime
      continue
    fi

    if [[ ($thisStep -gt "${INIT_SUPERSTEP}" && $thisStep -le "${FIRST_ROOT_SUPERSTEP}") || ($thisStep -gt "${TRANSPOSE_REDUCE_SUPERSTEP}" && $thisStep -le "${SECOND_ROOT_SUPERSTEP}") ]]; then
      FACTORIZATION_TIME=$(echo "${FACTORIZATION_TIME} + ${thisTime}" | bc)
    fi

    if [[ ($thisStep -gt "${FIRST_ROOT_SUPERSTEP}" && $thisStep -lt "${TRANSPOSE_MAP_SUPERSTEP}") || $thisStep -gt "${SECOND_ROOT_SUPERSTEP}" ]]; then
      BACKWARDS_SUBSTITUTION_TIME=$(echo "${BACKWARDS_SUBSTITUTION_TIME} + ${thisTime}" | bc)
    fi
  fi
done < "${1:-/dev/stdin}"

echo "${INPUT_TIME},${SHUTDOWN_TIME},${STEP_SOLUTION_TIME},${TOTAL_TIME},${INIT_TIME},${FACTORIZATION_TIME},${BACKWARDS_SUBSTITUTION_TIME},${TRANSPOSE_MAP_TIME},${TRANSPOSE_REDUCE_TIME}"