#!/usr/bin/env bash
set -ex

# See what is the effect of JIT on the run times (should start to get better after some warmup iterations)


# run times vs runs
IGA_PROBLEM_SIZE=3072 \
IGA_WORKERS=2 \
IGA_WORKER_MEMORY=4 \
IGA_WORKER_CORES=4 \
RUNS=1 \
SUITE_NAME=manyruns \
./run.suite.sh IGA_STEPS 2 3 4 5 6 7 8 9 10

# container options
IGA_PROBLEM_SIZE=1536 \
IGA_WORKERS=2 \
IGA_WORKER_MEMORY=4 \
IGA_WORKER_CORES=4 \
RUNS=1 \
SUITE_NAME=test-1 \
./run.suite.sh IGA_CONTAINER_JVM_OPTIONS \
  "-XX:+PrintFlagsFinal -XX:+UnlockDiagnosticVMOptions -XX:+UseParallelGC -XX:+UseParallelOldGC -XX:+AlwaysPreTouch"


# tested
  "-XX:+PrintFlagsFinal -XX:+UnlockDiagnosticVMOptions" \
  "-XX:+PrintFlagsFinal -XX:+UnlockDiagnosticVMOptions -XX:+UseParallelGC -XX:+UseParallelOldGC" \