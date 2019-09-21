#!/usr/bin/env bash
STEPS=${IGA_STEPS:-3}
PROBLEM_SIZE=${IGA_PROBLEM_SIZE:-384}
WORKERS=${IGA_WORKERS:-1}
PARTITION_HEIGHT=${IGA_PARTITION_HEIGHT:-3}
WORKER_HEAP=${IGA_WORKER_HEAP:-2048}

exec ./run.cloud.sh \
  -s "$STEPS" \
  -e "$PROBLEM_SIZE" \
	-w "$WORKERS" \
	-h "$PARTITION_HEIGHT" \
	-t surface \
	-p HEAT \
	--init-problem RADIAL \
	-c giraph.logLevel=error \
	-c giraph.yarn.task.heap.mb="$WORKER_HEAP"


