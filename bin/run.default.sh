#!/usr/bin/env bash
exec ./run.cloud.sh \
	-w 4 \
	-e 6144 \
	-h 4 \
	-t surface \
	-c giraph.yarn.task.heap.mb=2048 \
	-c giraph.metrics.enable=true \
	-c giraph.logLevel=debug