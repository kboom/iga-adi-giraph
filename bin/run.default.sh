#!/usr/bin/env bash
exec ./run.cloud.sh \
  -s 2 \
  -e 24576 \
	-w 2 \
	-h 4 \
	-c 4 \
	-m 14 \
	-t surface \
	-p HEAT \
	--init-problem RADIAL \
	--config giraph.zkList="iga-adi-m:2181" \
  --config giraph.logLevel=error \
  --config giraph.yarn.task.overhead.percent=0.3 \
  --config iga.storeSolution=false \
  --config giraph.minPartitionsPerComputeThread=3 \
  --config giraph.useNettyDirectMemory=true \
  --config giraph.heap.enableReactiveJmapDumping=true \
  --config giraph.jmap.histo.enable=true \
  --config giraph.jmap.histo.live=true
