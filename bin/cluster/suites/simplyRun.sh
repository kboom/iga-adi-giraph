#!/usr/bin/env bash
set -ex

# See what is the effect of JIT on the run times (should start to get better after some warmup iterations)
IGA_PROBLEM_SIZE=1536 IGA_WORKERS=2 IGA_WORKER_MEMORY=2 IGA_WORKER_CORES=4 RUNS=1 SUITE_NAME=test-1 ./run.suite.sh IGA_STEPS 2 3 4 5 6 7 8 9 10