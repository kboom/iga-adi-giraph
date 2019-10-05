#!/usr/bin/env bash
exec ./run.cloud.sh \
  -s 2 \
  -e 1536 \
	-w 2 \
	-h 2 \
	-c 2 \
	-m 3 \
	-t surface \
	-p HEAT \
	--init-problem RADIAL \
	--config giraph.zkList="iga-adi-m:2181" \
  --config giraph.msgRequestWarningThreshold=1 \
  --config giraph.logLevel=debug
