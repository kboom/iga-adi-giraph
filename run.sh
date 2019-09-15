#!/usr/bin/env bash
docker run -ti -p 8020:8020 -p 8032:8032 -p 8088:8088 -p 9000:9000 -p 10020:10020 -p 19888:19888 -p 50010:50010 -p 50020:50020 -p 50070:50070 -p 50075:50075 -p 50090:50090 -v $(pwd):/opt/iga harisekhon/hadoop:2.9

less /hadoop/logs/userlogs/application


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
	-Dgiraph.yarn.task.heap.mb=256 \
	-Dmapred.map.max.attempts=1 \
	-Dgiraph.logLevel=trace \
	-cp $(hadoop classpath):solver-1.0-SNAPSHOT.jar \
	edu.agh.iga.adi.giraph.IgaSolverTool

# Don't move the jar anywhere, just do
export HADOOP_CLASSPATH=$(pwd)/*
yarn jar solver-1.0-SNAPSHOT.jar edu.agh.iga.adi.giraph.IgaSolverTool \
 -w 1 \
 -t surface \
 -o hdfs://iga-adi-m/user/kbhit/1 \
 -c giraph.yarn.task.heap.mb=2048 \
 -c giraph.metrics.enable=true \
 -c giraph.logLevel=debug




 -c mapreduce.map.java.opts.max.heap=256 \
 -c mapreduce.reduce.java.opts.max.heap=256


 -c env.java.opts='-XX:ReservedCodeCacheSize=50M -XX:MaxMetaspaceSize=256m -XX:CompressedClassSpaceSize=256m -Xmx256m -Xms256m' \
 -c mapred.map.child.java.opts="-XX:ReservedCodeCacheSize=50M -XX:MaxMetaspaceSize=256m -XX:CompressedClassSpaceSize=256m -Xmx256m -Xms256m" \
 -c mapreduce.map.child.java.opts="-XX:ReservedCodeCacheSize=50M -XX:MaxMetaspaceSize=256m -XX:CompressedClassSpaceSize=256m -Xmx256m -Xms256m" \
 -c mapreduce.reduce.child.java.opts="-XX:ReservedCodeCacheSize=50M -XX:MaxMetaspaceSize=256m -XX:CompressedClassSpaceSize=256m -Xmx256m -Xms256m"

# CORRECT COMMAND IF JAR COPIED TO LOCAL LIBS
cp /opt/iga/dist/solver-1.0-SNAPSHOT.jar $HADOOP_HOME/share/hadoop/yarn/lib/
yarn jar /opt/iga/dist/solver-1.0-SNAPSHOT.jar edu.agh.iga.adi.giraph.IgaSolverTool \
	-D "mapred.child.java.opts=256" \
	-D "yarn.nodemanager.remote-app-log-dir=$(pwd)" \
	-D "yarn.log.dir=$(pwd)" \
	-D "giraph.metrics.directory=$(pwd)" \
	-D "giraph.minWorkers"=1 \
	-D "giraph.maxWorkers"=1 \
	-D giraph.heap.minFreeMb=16 \
	-D "giraph.logLevel=debug" \
	-D giraph.yarn.task.heap.mb=256 \
	-D mapreduce.map.memory.mb=256 \
	-D mapreduce.reduce.memory.mb=256 \
	-D env.java.opts='-XX:ReservedCodeCacheSize=50M -XX:MaxMetaspaceSize=256m -XX:CompressedClassSpaceSize=256m -Xmx256m -Xms256m' \
	-D mapred.map.child.java.opts='-XX:ReservedCodeCacheSize=50M -XX:MaxMetaspaceSize=256m -XX:CompressedClassSpaceSize=256m -Xmx256m -Xms256m' \
	-D mapred.reduce.child.java.opts='-XX:ReservedCodeCacheSize=50M -XX:MaxMetaspaceSize=256m -XX:CompressedClassSpaceSize=256m -Xmx256m -Xms256m' \
	-D mapreduce.map.java.opts='-XX:ReservedCodeCacheSize=50M -XX:MaxMetaspaceSize=256m -XX:CompressedClassSpaceSize=256m -Xmx256m -Xms256m' \
	-D mapreduce.reduce.java.opts='-XX:ReservedCodeCacheSize=50M -XX:MaxMetaspaceSize=256m -XX:CompressedClassSpaceSize=256m -Xmx256m -Xms256m'


yarn.nodemanager.resource.memory-mb=256 \
yarn.scheduler.maximum-allocation-mb=256 \
yarn.nodemanager.vmem-pmem-ratio=1 \
yarn.app.mapreduce.am.command-opts='-XX:ReservedCodeCacheSize=50M -XX:MaxMetaspaceSize=256m -XX:CompressedClassSpaceSize=256m -Xmx256m -Xms128m' \
yarn.app.mapreduce.am.resource.mb=256 \
yarn.nodemanager.vmem-check-enabled=false \


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

# Gcloud

gcloud compute scp dist/solver-1.0-SNAPSHOT.jar 1397227797894730441:~/
