#!/usr/bin/env bash
THIS_DIR=`dirname "$THIS"`
IGA_HOME=`cd "$THIS_DIR/.." ; pwd`

TIMESTAMP=$(date +%s)

# This makes sure our fat JAR including Giraph classes is available for master and for looking for JAR file to send to HDFS for workers
export HADOOP_CLASSPATH=$(pwd)/*

set +x;
yarn jar solver-1.0-SNAPSHOT.jar edu.agh.iga.adi.giraph.IgaSolverTool \
	-o hdfs://iga-adi-m/user/kbhit/${TIMESTAMP} \
	"$@"
