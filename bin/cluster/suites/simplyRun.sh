#!/usr/bin/env bash
set -ex

# See what is the effect of JIT on the run times (should start to get better after some warmup iterations)


# run times vs runs
IGA_PROBLEM_SIZE=3072 \
IGA_WORKERS=2 \
IGA_WORKER_MEMORY=4 \
IGA_WORKER_CORES=8 \
RUNS=1 \
SUITE_NAME=test \
./run.suite.sh IGA_PROBLEM_SIZE 768 1536 3072

# container options
IGA_PROBLEM_SIZE=1536 \
IGA_WORKERS=1 \
IGA_WORKER_MEMORY=12 \
IGA_WORKER_CORES=64 \
IGA_STEPS=1 \
RUNS=1 \
IGA_CONTAINER_JVM_OPTIONS="-XX:+PrintFlagsFinal -XX:+UnlockDiagnosticVMOptions -XX:+UseParallelGC -XX:+UseParallelOldGC" \
SUITE_NAME="12288-minmem-4w-16c" \
./run.suite.sh IGA_WORKER_MEMORY 100

# tested
  "-XX:+PrintFlagsFinal -XX:+UnlockDiagnosticVMOptions" \
  "-XX:+PrintFlagsFinal -XX:+UnlockDiagnosticVMOptions -XX:+UseParallelGC -XX:+UseParallelOldGC" \