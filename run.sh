#!/usr/bin/env bash





yarn jar /home/kbhit/solver-1.0-SNAPSHOT.jar \
	edu.agh.iga.adi.giraph.IgaSolverTool \
	-Dgiraph.yarn.libjars=/home/kbhit/solver-1.0-SNAPSHOT.jar \
    -Diga.problem.size=12 \
    -Diga.initialisation.type=surface \
	-Dmapreduce.jobtracker.address=localhost \
	-Dmapreduce.output.fileoutputformat.outputdir=/opt/iga/output \
	-Dgiraph.pure.yarn.job=true \
	-Dgiraph.yarn.task.heap.mb=256 \
	-Dmapred.map.max.attempts=3 \
	-Dgiraph.logLevel=debug


java \
    -Diga.problem.size=12 \
    -Diga.initialisation.type=surface \
	-Dgiraph.pure.yarn.job=true \
	-Dmapreduce.output.fileoutputformat.outputdir=/opt/iga/output \
	-Dgiraph.yarn.task.heap.mb=128 \
	-Dmapred.map.max.attempts=1 \
	-Dgiraph.logLevel=trace \
	-cp $(hadoop classpath):solver-1.0-SNAPSHOT.jar \
	edu.agh.iga.adi.giraph.IgaSolverTool


# CORRECT COMMAND IF JAR COPIED TO LOCAL LIBS
cp /opt/iga/dist/solver-1.0-SNAPSHOT.jar $HADOOP_HOME/share/hadoop/yarn/lib/
yarn jar /opt/iga/dist/solver-1.0-SNAPSHOT.jar edu.agh.iga.adi.giraph.IgaSolverTool

# THIS IS HOW GIRAPH BINARIES RUN
[root@18199ed9fa07 bin]# ./giraph -Dgiraph.logLevel=verbose -Dgiraph.dupa.something=yxx solver-1.0-SNAPSHOT.jar
No HADOOP_CONF_DIR set, using /hadoop/conf
/hadoop/bin/hadoop --config /hadoop/conf jar /opt/iga/giraph-core.jar org.apache.giraph.GiraphRunner -Dgiraph.dupa.something=yxx -Dgiraph.logLevel=verbose -libjars /opt/iga/giraph-core.jar,solver-1.0-SNAPSHOT.jar,/opt/iga/conf,/opt/iga/lib/giraph-core.jar,/opt/iga/giraph-core.jar



giraph.isStaticGraph
giraph.metrics.enable
giraph.pure.yarn.job
giraph.useNettyDirectMemory
giraph.useNettyPooledAllocator
giraph.useOutOfCoreGraph
giraph.vertex.resolver.create.on.msgs=false
giraph.vertexOutputFormatThreadSafe=true
giraph.messageEncodeAndStoreType
giraph.maxRequestMilliseconds=999999999
giraph.useMessageSizeEncoding