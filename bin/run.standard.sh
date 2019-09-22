#!/usr/bin/env bash
exec ./run.cloud.sh \
  -s 2 \
  -e 1536 \
	-w 4 \
	-h 4 \
	-t surface \
	-p HEAT \
	--init-problem RADIAL \
	-c giraph.yarn.task.heap.mb=2048 \
  -c giraph.msgRequestWarningThreshold=1 \
  -c giraph.logLevel=error
