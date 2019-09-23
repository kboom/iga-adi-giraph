#!/usr/bin/env bash
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
  -c giraph.resendTimedOutRequests=false
