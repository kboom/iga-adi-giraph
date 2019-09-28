#!/usr/bin/env bash
exec ./run.cloud.sh \
  -s 2 \
  -e 6144 \
	-w 3 \
	-h 4 \
	-c 16 \
	-m 12 \
	-t surface \
	-p HEAT \
	--init-problem RADIAL \
	--config giraph.zkList="iga-adi-m:2181" \
  --config giraph.msgRequestWarningThreshold=1 \
  --config giraph.logLevel=debug




  --config yarn.app.mapreduce.am.resource.mb=2300 \


// giraph.numComputeThreads


  -c yarn.app.mapreduce.am.resource.mb=1500
