#!/usr/bin/env bash

SCRIPTPATH="$( cd "$(dirname "$0")" || exit ; pwd -P )"

if [[ -z "${1}" ]]; then
  echo "Required directory name"
  exit 1
else
  SUITE_DIR="${1}"
fi

declare -A size2InitStep
size2InitStep=(
  ["24576"]=59
  ["12288"]=55
  ["6144"]=51
  ["3072"]=47
  ["1536"]=43
  ["768"]=39
  ["384"]=35
  ["192"]=31
)

for dir in "${SUITE_DIR}"/*
do
    problemSize=$(basename "${dir}")
    initSuperstep="${size2InitStep[${problemSize}]}"
    export INIT_SUPERSTEP="${initSuperstep}" && "${SCRIPTPATH}/extract-results.sh" "${dir}"
done

