#!/usr/bin/env bash

SCRIPTPATH="$( cd "$(dirname "$0")" || exit ; pwd -P )"

if [[ -z "${1}" ]]; then
  echo "Required directory name"
  exit 1
else
  SUITE_DIR="${1}"
fi

declare -A size2height
size2height=(
  ["49152"]=14
  ["24576"]=13
  ["12288"]=12
  ["6144"]=11
  ["3072"]=10
  ["1536"]=9
  ["768"]=8
  ["384"]=7
  ["192"]=6
)

printf "problemSize,workers,threads,input_ms,shutdown_ms,step_solution_ms,total_ms,init_ms,factorization_ms,backwards_substitution_ms,transpose_map_ms,transpose_reduce_ms\n"

for dir in "${SUITE_DIR}"/*
do
    problemSize=$(basename "${dir}")
    dagHeight="${size2height[${problemSize}]}"
    export DAG_HEIGHT="${dagHeight}" && "${SCRIPTPATH}/extract-results.sh" "${dir}"
done

