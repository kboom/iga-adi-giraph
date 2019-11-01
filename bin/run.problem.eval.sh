#!/usr/bin/env bash
# This script runs the same problem on a variable numbers of nodes.

# Get the directories
SCRIPTPATH="$( cd "$(dirname "$0")" || exit ; pwd -P )"
RUN_SCRIPT="${SCRIPTPATH}/run.parameterized.sh"

# If no arguments passed run this on a single node
[ "$#" == "0" ] && set -- 1

for ((i=0;i<=$#;i++)); do
  IGA_WORKERS="${!i}" eval "${RUN_SCRIPT}"
done
