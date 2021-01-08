#!/usr/bin/env bash
set +xe
# This will sample the usage without computing running averages like top does.
# Therefore we might not have 100% accurate results particularly for short-duration changes.
# We lower the watch interval to every 100 ms (the lowest value possible).

# https://man7.org/linux/man-pages/man1/ps.1.html
RUN="$1"
SCRIPT_PATH="$( cd "$(dirname "$0")" || exit ; pwd -P )"
LOGS_DIR=${OUTPUT_DIR:-"$SCRIPT_PATH/logs"}
mkdir -p "$LOGS_DIR"
LOG_PS_FILE="$LOGS_DIR/$RUN-ps.txt"
LOG_TOP_FILE="$LOGS_DIR/$RUN-top.txt"
LOG_PSH_FILE="$LOGS_DIR/$RUN-psh.txt"

COLUMNS_OF_INTEREST="pgrp,ppid,pid,thcount,tid,numa,psr,sgi_p,state,stat,cp,%mem,pri,rtprio,policy,sched,cputime,comm"
PS_HEADER_COMMAND="ps -o $COLUMNS_OF_INTEREST | head -n 1 | echo \" SYSTIME      \$(cat -)\""
PS_COMMAND="ps --no-headers Hr -u yarn -o $COLUMNS_OF_INTEREST --sort=cp | sed \"s#^#\$(date +%s%3N) #\""
PSH_COMMAND="ps -AH -o $COLUMNS_OF_INTEREST --sort=cp | sed \"s#^#\$(date +%s%3N) #\""

#top -1 -bSHEk -u yarn  | grep -Fv -e '%Cpu' -e 'KiB Mem' -e 'KiB Swap' -e 'Threads:' -e 'top -' -e ' PPID ' | grep -v -e '^$' | while IFS= read -r line; do printf '%s %s\n' "$(date +%s%3N)" "$line"; done
TOP_COMMAND="top -1 -bSHEk -u yarn  | grep -Fv -e '%Cpu' -e 'KiB Mem' -e 'KiB Swap' -e 'Threads:' -e 'top -' -e ' PPID ' | grep -v -e '^$' | while IFS= read -r line; do printf '%s %s\n' \"\$(date +%s%3N)\" \"\$line\"; done" # all configuration is expected to be in toprc file
CONVERT_TO_CSV="awk -v suite=\"$RUN\" '{ cmd=substr(\$0,147); gsub(/ +/, \"-\", cmd); print suite,substr(\$0,0,146), cmd}' | sed 's/\( \{1,\}\)/,/g'"

bash -c "$PS_HEADER_COMMAND" > "$LOG_PS_FILE"
watch -t -n 0.1 "($PS_COMMAND) | tee -a $LOG_PS_FILE" > /dev/null 2>&1 &
watch -t -n 10 "($PSH_COMMAND) | tee -a $LOG_PSH_FILE" > /dev/null 2>&1 &
bash -c "$TOP_COMMAND | $CONVERT_TO_CSV" > "$LOG_TOP_FILE" &

for job in $(jobs -p); do wait "${job}"; done
