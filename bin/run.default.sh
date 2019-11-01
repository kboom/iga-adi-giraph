#!/usr/bin/env bash
exec ./run.cloud.sh \
  -s 2 \
  -e 12288 \
	-w 4 \
	-h 4 \
	-c 4 \
	-m 8 \
	-t surface \
	-p HEAT \
	--init-problem RADIAL \
	--config giraph.zkList="iga-adi-m:2181" \
  --config giraph.logLevel=debug \
  --config giraph.yarn.task.overhead.percent=0.3 \
  --config iga.storeSolution=false \
  --config giraph.minPartitionsPerComputeThread=3
