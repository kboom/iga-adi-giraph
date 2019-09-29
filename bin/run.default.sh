#!/usr/bin/env bash
exec ./run.cloud.sh \
  -s 2 \
  -e 768 \
	-w 2 \
	-h 3 \
	-c 2 \
	-m 3 \
	-t surface \
	-p HEAT \
	--init-problem RADIAL \
	--config giraph.zkList="iga-adi-m:2181" \
  --config giraph.msgRequestWarningThreshold=1 \
  --config giraph.logLevel=debug




  --config yarn.app.mapreduce.am.resource.mb=2300 \


// giraph.numComputeThreads


  -c yarn.app.mapreduce.am.resource.mb=1500
