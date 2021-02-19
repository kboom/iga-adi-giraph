#!/usr/bin/env bash
set -ex

# run times vs runs
IGA_PROBLEM_SIZE=3072 \
IGA_WORKERS=4 \
IGA_WORKER_MEMORY=8 \
IGA_WORKER_CORES=8 \
IGA_STEPS=2 \
RUNS=1 \
SUITE_NAME=testowyrun \
IGA_CONTAINER_JVM_OPTIONS="-XX:+PrintFlagsFinal -XX:+UnlockDiagnosticVMOptions -XX:+UseParallelGC -XX:+UseParallelOldGC" \
./run.suite.sh IGA_WORKERS 4 2 1
