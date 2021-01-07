#!/usr/bin/env bash
set +xe

SCRIPT_PATH="$( cd "$(dirname "$0")" || exit ; pwd -P )"
RUN="$1"
LOGS_DIR=${OUTPUT_DIR:-"$SCRIPT_PATH/logs"}
mkdir -p "$LOGS_DIR"

lscpu > "$LOGS_DIR/$RUN-lscpu.txt"
less /proc/cpuinfo > "$LOGS_DIR/$RUN-cpuinfo.txt"
tail -n +1 /sys/devices/system/cpu/cpu*/topology/thread_siblings_list > "$LOGS_DIR/$RUN-topology-thread_siblings_list.txt"
tail -n +1 /sys/devices/system/cpu/cpu*/topology/die_cpus_list > "$LOGS_DIR/$RUN-topology-die_cpus_list.txt"
tail -n +1 /sys/devices/system/cpu/cpu*/topology/core_cpus_list > "$LOGS_DIR/$RUN-topology-core_cpus_list.txt"
tail -n +1 /sys/devices/system/cpu/cpu*/cache/index*/shared_cpu_list > "$LOGS_DIR/$RUN-cache-index-shared_cpu_list.txt"