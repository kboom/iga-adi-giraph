#!/usr/bin/env bash
yarn jar /opt/iga/solver-1.0-SNAPSHOT.jar \
    -D iga.problem.size=12 \
    -D iga.initialisation.type=surface \
	-D mapreduce.jobtracker.address=localhost \
	-D giraph.minWorkers=2 \
	-D giraph.maxWorkers=2 \
	-D mapred.output.dir=/opt/iga/output