#!/usr/bin/env bash
export LOGGING_JVM_OPTS="
-XX:+AggressiveOpts
-XX:+UnlockDiagnosticVMOptions
-XX:+UnlockExperimentalVMOptions
-XX:+PrintFlagsFinal
-XX:+PrintFlagsWithComments
"

export WORKER_JVM_OPTS="
-Xmx2000M
-Xms2000M
-XX:+UseParNewGC
-XX:NewSize=1000M
-XX:MaxNewSize=1000M
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
  -e 12288 \
	-w 6 \
	-h 4 \
	-t surface \
	-p HEAT \
	--init-problem RADIAL \
	-c giraph.zkList="iga-adi-m:2181" \
	-c giraph.yarn.task.heap.mb=2500 \
  -c giraph.msgRequestWarningThreshold=1 \
  -c giraph.metrics.enable=true \
  -c giraph.logLevel=debug \
  -c giraph.nettyRequestEncoderBufferSize=327680 \
  -c giraph.clientReceiveBufferSize=327680 \
  -c giraph.clientSendBufferSize=5242880 \
  -c yarn.app.mapreduce.am.command-opts="$LOGGING_JVM_OPTS $WORKER_JVM_OPTS"


// giraph.numComputeThreads


  -c yarn.app.mapreduce.am.resource.mb=1500
