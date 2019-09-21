#!/usr/bin/env bash
exec ./run.cloud.sh \
  -s 2 \
  -e 1536 \
	-w 4 \
	-h 4 \
	-t surface \
	-p HEAT \
	--init-problem RADIAL \
	-c giraph.useInputSplitLocality=false \
	-c giraph.yarn.task.heap.mb=2048 \
  -c giraph.logLevel=error \
	-c giraph.metrics.enable=true \
	-c giraph.useNettyDirectMemory=true \
	-c giraph.useNettyPooledAllocator=true
