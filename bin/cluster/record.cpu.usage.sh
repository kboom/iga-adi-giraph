#!/usr/bin/env bash
set +xe
# This will sample the usage without computing running averages like top does.
# Therefore we might not have 100% accurate results particularly for short-duration changes.
# We lower the watch interval to every 100 ms (the lowest value possible).
trap "exit" INT TERM
trap 'jobs -p | xargs kill' EXIT

# https://man7.org/linux/man-pages/man1/ps.1.html
RUN="$1"
SCRIPT_PATH="$( cd "$(dirname "$0")" || exit ; pwd -P )"
LOGS_DIR=${OUTPUT_DIR:-"$SCRIPT_PATH/logs"}
mkdir -p "$LOGS_DIR"
LOG_PS_FILE="$LOGS_DIR/$RUN-ps.csv"
LOG_TOP_PROCESS_FILE="$LOGS_DIR/$RUN-top-proc.csv"
LOG_TOP_CPUS_FILE="$LOGS_DIR/$RUN-top-cpus.csv"
LOG_PSH_FILE="$LOGS_DIR/$RUN-psh.txt"
SCHEDSTAT_CPU_FILE="$LOGS_DIR/$RUN-schedstat-cpu.csv"
SCHEDSTAT_PROC_FILE="$LOGS_DIR/$RUN-schedstat-proc.csv"

DELTA=0.1

#top -1 -bSHEk -u yarn  | grep -Fv -e '%Cpu' -e 'KiB Mem' -e 'KiB Swap' -e 'Threads:' -e 'top -' -e ' PPID ' | grep -v -e '^$' | while IFS= read -r line; do printf '%s %s\n' "$(date +%s%3N)" "$line"; done
CONVERT_SPACES_TO_COMMAS="sed 's/\( \{1,\}\)/,/g'"
PREPEND_WITH_TIME="sed \"s#^#\$(date +%s%3N) #\""
PREPEND_WITH_SUITE="awk -v suite=\"$RUN\" '{ print suite,\$0}'"
CONVERT_TO_SUITE_CSV="$PREPEND_WITH_SUITE | $CONVERT_SPACES_TO_COMMAS"

# Top
TOP_BASE_CMD="top -1 -bSHEk -u yarn"
TOP_CPUS_HEADER="echo 'exp,time,cpu,us,sy,ni,id,wa,hi,si,st'"
TOP_PROCESS_HEADER="$TOP_BASE_CMD | grep -m 1  \"PPID\" | $CONVERT_SPACES_TO_COMMAS | cut -c 2-"
bash -c "$TOP_PROCESS_HEADER" > "$LOG_TOP_PROCESS_FILE"
bash -c "$TOP_CPUS_HEADER" > "$LOG_TOP_CPUS_FILE"

# Use the same input for all outputs to save some CPU time
TOP_PROCESSES_FILTER="grep -Fv -e '%Cpu' -e 'KiB Mem' -e 'KiB Swap' -e 'Threads:' -e 'top -' -e ' PPID ' | grep -v -e '^$' | while IFS= read -r line; do printf '%s %s\n' \"\$(date +%s%3N)\" \"\$line\"; done" # all configuration is expected to be in toprc file
TOP_PROCESSES_TO_SUITE_CSV="awk -v suite=\"$RUN\" '{ cmd=substr(\$0,147); gsub(/ +/, \"-\", cmd); print suite,substr(\$0,0,146), cmd}' | $CONVERT_SPACES_TO_COMMAS"
TOP_CPUS_FILTER="grep %Cpu | sed 's/[%A-Za-z ]\+//g' | sed 's/:/,/g' | while IFS= read -r line; do printf '%s %s\n' \"\$(date +%s%3N)\" \"\$line\"; done"
bash -c "$TOP_BASE_CMD" | tee >(bash -c "$TOP_PROCESSES_FILTER | $TOP_PROCESSES_TO_SUITE_CSV" >> "$LOG_TOP_PROCESS_FILE") >(bash -c "$TOP_CPUS_FILTER | $CONVERT_TO_SUITE_CSV" >> "$LOG_TOP_CPUS_FILE") > /dev/null &

# Schedstat reveals what happens with our CPUs (cumulative sum)
# http://eaglet.pdxhosts.com/rick/linux/schedstat/v4/format-4.html
SCHEDSTAT_CPU_CMD="cat /proc/schedstat | grep cpu | sed 's/\( \{1,\}\)/,/g'"
SCHEDSTAT_PROC_CMD="tail -n +1 /proc/*/schedstat | awk -v tag=\"^[\d ]+$\" '/^==>.*<==$/ { block = \$0; output = 0; next } { gsub(/==> \/proc\//, \"\", block); gsub(/\/schedstat <==/, \" \", block); block = block \$0 } { if(NR%2==0) print block }'"
watch -t -n $DELTA "($SCHEDSTAT_CPU_CMD | $PREPEND_WITH_TIME | $CONVERT_TO_SUITE_CSV) | tee -a $SCHEDSTAT_CPU_FILE" > /dev/null 2>&1 &
watch -t -n $DELTA "($SCHEDSTAT_PROC_CMD | $PREPEND_WITH_TIME | $CONVERT_TO_SUITE_CSV) | tee -a $SCHEDSTAT_PROC_FILE" > /dev/null 2>&1 &

# schedstat-proc does something weird - race conditions

# PS
PS_COLUMNS_OF_INTEREST="pgrp,ppid,pid,thcount,tid,numa,psr,sgi_p,state,stat,cp,%mem,pri,rtprio,policy,sched,cputime,comm" # add utime and stime to see what is the proportion or running time in user and kernel mode
PS_HEADER_COMMAND="ps -o $PS_COLUMNS_OF_INTEREST | head -n 1 | echo \"SUITE SYSTIME      \$(cat -)\" | $CONVERT_SPACES_TO_COMMAS"
PS_COMMAND="ps --no-headers Hr -u yarn -o $PS_COLUMNS_OF_INTEREST --sort=cp | $PREPEND_WITH_TIME"
PSH_COMMAND="ps -AH -o $PS_COLUMNS_OF_INTEREST --sort=cp | sed \"s#^#\$(date +%s%3N) #\""
PS_TO_SUITE_CSV="awk -v suite=\"$RUN\" '{ cmd=substr(\$0,100); gsub(/ +/, \"-\", cmd); print suite,substr(\$0,0,99), cmd}' | sed '1d;\$d' | sed 's/\( \{1,\}\)/,/g'"
bash -c "$PS_HEADER_COMMAND" > "$LOG_PS_FILE"
watch -t -n $DELTA "($PS_COMMAND | $PS_TO_SUITE_CSV) | tee -a $LOG_PS_FILE" > /dev/null 2>&1 &
watch -t -n "$(echo "$DELTA*10" | bc)" "($PSH_COMMAND) | tee -a $LOG_PSH_FILE" > /dev/null 2>&1 &

for job in $(jobs -p); do wait "${job}"; done
