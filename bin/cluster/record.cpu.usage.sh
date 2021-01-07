#!/usr/bin/env bash
set +xe
# https://man7.org/linux/man-pages/man1/ps.1.html
RUN="$1"
SCRIPT_PATH="$( cd "$(dirname "$0")" || exit ; pwd -P )"
LOGS_DIR="$SCRIPT_PATH/logs"
mkdir -p "$LOGS_DIR"
LOG_PS_FILE="$LOGS_DIR/usage-$RUN.txt"
LOG_PSH_FILE="$LOGS_DIR/hierarchy-$RUN.txt"

COLUMNS_OF_INTEREST="pgrp,ppid,pid,thcount,tid,numa,psr,sgi_p,state,stat,cp,%mem,pri,rtprio,policy,sched,cputime,comm"
HEADER_COMMAND="ps -o $COLUMNS_OF_INTEREST | head -n 1 | echo \" SYSTIME      \$(cat -)\""
PS_COMMAND="ps --no-headers Hr -u yarn -o $COLUMNS_OF_INTEREST --sort=cp | sed \"s#^#\$(date +%s%3N) #\""
PSH_COMMAND="ps -AH -o $COLUMNS_OF_INTEREST --sort=cp | sed \"s#^#\$(date +%s%3N) #\""

bash -c "$HEADER_COMMAND" >"$LOG_PS_FILE"
watch -t -n 0.1 "($PS_COMMAND) | tee -a $LOG_PS_FILE" > /dev/null 2>&1 &
watch -t -n 10 "($PSH_COMMAND) | tee -a $LOG_PSH_FILE" > /dev/null 2>&1 &

for job in $(jobs -p); do wait "${job}"; done
