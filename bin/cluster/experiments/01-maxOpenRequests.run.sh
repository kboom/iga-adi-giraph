#!/usr/bin/env bash
source env.sh

IGA_STEPS=5 ./run-parameterized.sh \
  --config giraph.waitForPerWorkerRequests=false \
  --config giraph.maxNumberOfUnsentRequests=10000 \
  --config giraph.maxOpenRequestsPerWorker=100