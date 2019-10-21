#!/usr/bin/env bash
exec ./run.cloud.sh \
  -s 2 \
  -e 12288 \
	-w 4 \
	-h 4 \
	-c 8 \
	-m 22 \
	-t surface \
	-p HEAT \
	--init-problem RADIAL \
	--config giraph.zkList="iga-adi-m:2181" \
  --config giraph.logLevel=verbose \
  --config iga.storeSolution=false \
  --config giraph.minPartitionsPerComputeThread=8
