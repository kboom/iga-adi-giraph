#!/usr/bin/env bash
set +xe

TIMESTAMP=${TIMESTAMP:-$(date +%s)}
HDFS_RESULTS_DIR=hdfs://$(hostname)/user/$(whoami)/${TIMESTAMP}

# This makes sure our fat JAR including Giraph classes is available for master and for looking for JAR file to send to HDFS for workers
# shellcheck disable=SC2125
HADOOP_CLASSPATH=$(pwd)/*
export HADOOP_CLASSPATH

set +x;
export HDFS_RESULTS_DIR=${HDFS_RESULTS_DIR}
yarn jar solver-1.0-SNAPSHOT.jar edu.agh.iga.adi.giraph.IgaSolverTool \
	-o "${HDFS_RESULTS_DIR}" \
	"$@"