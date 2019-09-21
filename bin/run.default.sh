#!/usr/bin/env bash
exec ./run.cloud.sh \
  -s 2 \
  -e 48 \
	-w 4 \
	-h 2 \
	-t surface \
	-p HEAT \
	--init-problem RADIAL \
	-c giraph.yarn.task.heap.mb=2048 \
  -c giraph.logLevel=error \
	-c giraph.metrics.enable=true
