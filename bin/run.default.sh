#!/usr/bin/env bash
export WORKER_JVM_OPTS="
-Xmx4048M
-Xms4048M
-XX:+UseParNewGC
-XX:NewSize=1024M
-XX:MaxNewSize=1024M
-XX:MaxTenuringThreshold=2
-XX:SurvivorRatio=30
-XX:-UseAdaptiveSizePolicy
-XX:+UseConcMarkSweepGC
-XX:+CMSParallelRemarkEnabled
-XX:+ParallelRefProcEnabled
-XX:+CMSClassUnloadingEnabled
-XX:CMSInitiatingOccupancyFraction=80
-XX:+UseCMSInitiatingOccupancyOnly
"

exec ./run.cloud.sh \
  -s 2 \
  -e 3092 \
	-w 14 \
	-h 4 \
	-t surface \
	-p HEAT \
	--init-problem RADIAL \
	-c giraph.yarn.task.heap.mb=2600 \
  -c giraph.msgRequestWarningThreshold=1 \
  -c giraph.metrics.enable=true \
  -c giraph.logLevel=warn \
  -c giraph.nettyRequestEncoderBufferSize=327680 \
  -c giraph.clientReceiveBufferSize=327680 \
  -c giraph.clientSendBufferSize=5242880 \
  -c giraph.resendTimedOutRequests=false \
  -c yarn.app.mapreduce.am.command-opts="$WORKER_JVM_OPTS" \
  -c yarn.app.mapreduce.am.resource.mb=2300
