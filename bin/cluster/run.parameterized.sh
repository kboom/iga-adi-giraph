#!/usr/bin/env bash

# Get the directories
SCRIPTPATH="$( cd "$(dirname "$0")" || exit ; pwd -P )"
RUN_SCRIPT="${SCRIPTPATH}/run.cloud.sh"

set -x
IGA_STEPS=${IGA_STEPS:-2}
IGA_PROBLEM_SIZE=${IGA_PROBLEM_SIZE:-384}
IGA_WORKERS=${IGA_WORKERS:-1}
IGA_WORKER_CORES=${IGA_WORKER_CORES:-1}
IGA_WORKER_MEMORY=${IGA_WORKER_MEMORY:-1}
IGA_MEMORY_OVERHEAD_PERCENT=${IGA_MEMORY_OVERHEAD_PERCENT:-0.2}
IGA_USE_DIRECT_MEMORY=${IGA_USE_DIRECT_MEMORY:-true}
IGA_MIN_PARTITIONS_PER_COMPUTE_THREAD=${IGA_MIN_PARTITIONS_PER_COMPUTE_THREAD:-1}
IGA_LOG_LEVEL=${IGA_LOG_LEVEL:-error}

exec "${RUN_SCRIPT}" \
  -s "${IGA_STEPS}" \
  -e "${IGA_PROBLEM_SIZE}" \
	-w "${IGA_WORKERS}" \
	-c "${IGA_WORKER_CORES}" \
	-m "${IGA_WORKER_MEMORY}" \
	-t surface \
	-p HEAT \
	--init-problem RADIAL \
  --config giraph.zkList="iga-adi-m:2181" \
  --config giraph.logLevel="${IGA_LOG_LEVEL}" \
  --config giraph.yarn.task.overhead.percent="${IGA_MEMORY_OVERHEAD_PERCENT}" \
  --config giraph.useNettyDirectMemory="${IGA_USE_DIRECT_MEMORY}" \
  --config giraph.minPartitionsPerComputeThread="${IGA_MIN_PARTITIONS_PER_COMPUTE_THREAD}" \
  --config iga.storeSolution=false
  "${@}"
