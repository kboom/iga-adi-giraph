#!/usr/bin/env bash
# This script runs the same problem on a variable numbers of nodes.
set -e

# Get the directories
SCRIPTPATH="$( cd "$(dirname "$0")" || exit ; pwd -P )"
RUN_SCRIPT="${SCRIPTPATH}/run.parameterized.sh"

# Naming
TIMESTAMP=$(date +%s)

# If no arguments passed run this on a single node
[ "$#" == "0" ] && set -- 1

for ((i=1;i<=$#;i++)); do
  IGA_WORKERS="${!i}"
  "${RUN_SCRIPT}" |& tee  "run-problem-eval-${TIMESTAMP}-${IGA_WORKERS}.txt"
done
