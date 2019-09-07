#!/usr/bin/env bash
HADOOP_VERSION=2.5.1

docker run -it sequenceiq/hadoop-docker:${HADOOP_VERSION} -bash