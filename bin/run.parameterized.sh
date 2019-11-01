#!/usr/bin/env bash

# Get the directories
SCRIPTPATH="$( cd "$(dirname "$0")" || exit ; pwd -P )"
RUN_SCRIPT="${SCRIPTPATH}/run.cloud.sh"

IGA_STEPS=${IGA_STEPS:-2}
IGA_PROBLEM_SIZE=${IGA_PROBLEM_SIZE:-384}
IGA_WORKERS=${IGA_WORKERS:-1}
IGA_WORKER_CORES=${IGA_WORKER_CORES:-1}
IGA_WORKER_MEMORY=${IGA_WORKER_MEMORY:-2048}
IGA_PARTITION_HEIGHT=${IGA_PARTITION_HEIGHT:-3}

exec "${RUN_SCRIPT}" \
  -s "${IGA_STEPS}" \
  -e "${IGA_PROBLEM_SIZE}" \
	-w "${IGA_WORKERS}" \
	-c "${IGA_WORKER_CORES}" \
	-m "${IGA_WORKER_MEMORY}" \
	-h "${IGA_PARTITION_HEIGHT}" \
	-t surface \
	-p HEAT \
	--init-problem RADIAL \
	--config giraph.zkList="iga-adi-m:2181" \
  --config giraph.logLevel=debug \
  --config giraph.yarn.task.overhead.percent=0.3 \
  --config iga.storeSolution=false \
  --config giraph.minPartitionsPerComputeThread=3
