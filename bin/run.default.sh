#!/usr/bin/env bash
exec ./run.cloud.sh \
  -s 1 \
  -e 768 \
	-w 4 \
	-h 4 \
	-t surface \
	-c giraph.yarn.task.heap.mb=2048 \
	-c giraph.metrics.enable=true \
	-c giraph.logLevel=debug