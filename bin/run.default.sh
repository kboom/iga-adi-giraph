#!/usr/bin/env bash
exec ./run.cloud.sh \
  -s 2 \
  -e 1536 \
	-w 2 \
	-h 4 \
	-t surface \
	-p HEAT \
	--init-problem RADIAL \
	-c giraph.yarn.task.heap.mb=3000 \
  -c giraph.numComputeThreads=2 \
  -c giraph.numInputThreads=2 \
  -c giraph.numOutputThreads=2 \
  -c giraph.minPartitionsPerComputeThread=1 \
  -c giraph.msgRequestWarningThreshold=1 \
  -c giraph.logLevel=error
