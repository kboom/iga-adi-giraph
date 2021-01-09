#!/usr/bin/env bash
set +xe
trap "exit" INT TERM
trap 'jobs -p | xargs kill' EXIT

# https://man7.org/linux/man-pages/man1/ps.1.html
RUN="$1"
SCRIPT_PATH="$( cd "$(dirname "$0")" || exit ; pwd -P )"
LOGS_DIR=${OUTPUT_DIR:-"$SCRIPT_PATH/logs"}
mkdir -p "$LOGS_DIR"
LOG_MEMINFO_FILE="$LOGS_DIR/$RUN-meminfo.csv"
LOG_NUMASTAT_FILE="$LOGS_DIR/$RUN-numastat.csv"
CONVERT_SPACES_TO_COMMAS="sed 's/\( \{1,\}\)/,/g'"
PREPEND_WITH_TIME="sed \"s#^#\$(date +%s%3N) #\""
PREPEND_WITH_SUITE="awk -v suite=\"$RUN\" '{ print suite,\$0}'"
CONVERT_TO_SUITE_CSV="$PREPEND_WITH_SUITE | $CONVERT_SPACES_TO_COMMAS"

watch -t -n 1 "(cat /sys/devices/system/node/node*/numastat | $PREPEND_WITH_TIME | $CONVERT_TO_SUITE_CSV) | tee -a $LOG_NUMASTAT_FILE" > /dev/null 2>&1 &
watch -t -n 1 "(cat /sys/devices/system/node/node*/meminfo | sed -r 's/Node //' | $PREPEND_WITH_TIME | $CONVERT_TO_SUITE_CSV) | tee -a $LOG_MEMINFO_FILE" > /dev/null 2>&1 &

for job in $(jobs -p); do wait "${job}"; done
