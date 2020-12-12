# Machines
# 64 * n2-standard-16 (16 CPU / 64 GiB)
# Price for CPU: 64 * $0.854638 * H = $55 / H
# Price for Dataproc: $10 / H
# Price total: $65 / H
# N = 49152^2

export SUITE_NAME="weak-scaling"
export IGA_WORKER_CORES=16
export IGA_WORKER_MEMORY=40
export RUNS=3