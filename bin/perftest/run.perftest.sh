#!/usr/bin/env bash
STEPS=$(IGA_STEPS:-3)
PROBLEM_SIZE=$(IGA_PROBLEM_SIZE:-384)
WORKERS=$(IGA_WORKERS:-1)
PARTITION_HEIGHT=$(IGA_PARTITION_HEIGHT:-3)
WORKER_HEAP=$(IGA_WORKER_HEAP:-2048)

TIMESTAMP=$(date +%s)
PERFTEST_NAME=$(IGA_PERFTEST_NAME:-"iga-perftest-$TIMESTAMP")
METRICS_DIRECTORY="hdfs://user/$(whoami)/iga-metrics/$PERFTEST_NAME"

exec ../run.cloud.sh \
  -s "$STEPS" \
  -e "$PROBLEM_SIZE" \
	-w "$WORKERS" \
	-h "$PARTITION_HEIGHT" \
	-t heat \
	-c giraph.logLevel=error \
	-c giraph.metrics.enable=true \
	-c giraph.metrics.directory="$METRICS_DIRECTORY" \
	-c giraph.yarn.task.heap.mb="$WORKER_HEAP" \
