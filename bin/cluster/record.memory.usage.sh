#!/usr/bin/env bash
set +xe
# https://man7.org/linux/man-pages/man1/ps.1.html
RUN="$1"
SCRIPT_PATH="$( cd "$(dirname "$0")" || exit ; pwd -P )"
LOGS_DIR=${OUTPUT_DIR:-"$SCRIPT_PATH/logs"}
mkdir -p "$LOGS_DIR"
LOG_MEMINFO_FILE="$LOGS_DIR/$RUN-meminfo.txt"
LOG_NUMASTAT_FILE="$LOGS_DIR/$RUN-numastat.txt"

watch -t -n 1 "(date +time:%s%3N ; cat /sys/devices/system/node/node*/numastat) | tee -a $LOG_NUMASTAT_FILE" > /dev/null 2>&1 &
watch -t -n 1 "(date +time:%s%3N ; cat /sys/devices/system/node/node*/meminfo) | tee -a $LOG_MEMINFO_FILE" > /dev/null 2>&1 &

trap 'jobs -p | xargs kill' EXIT
for job in $(jobs -p); do wait "${job}"; done
