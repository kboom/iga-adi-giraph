#!/usr/bin/env bash
exec ./run.cloud.sh \
	-w 2 \
	-e 12 \
	-t surface \
	-c giraph.yarn.task.heap.mb=2048 \
	-c giraph.metrics.enable=true \
	-c giraph.logLevel=debug