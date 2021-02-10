#!/usr/bin/env bash

# SYSTIME       PGRP  PPID   PID THCNT   TID NUMA PSR P S STAT  CP %MEM PRI RTPRIO POL SCH     TIME COMMAND
#1610094825475  3584     1  3662   225  4718    0   0 0 R Rl     0  1.5  19      - TS    0 00:00:00 SchedulerEventD

if [[ -z "${1}" ]]; then
  echo "Missing input file"
  exit 1
else
  INPUT="${1:-/dev/stdin}"
fi

if [[ -z "${2}" ]]; then
  echo "Missing prefix for each row"
  exit 1
else
  ROW_PREFIX="${2}"
fi

awk -v suite="$ROW_PREFIX" '{ cmd=substr($0,100); gsub(/ +/, "-", cmd); print suite,substr($0,0,99), cmd}' < "$INPUT" | sed '1d;$d' | sed 's/\( \{1,\}\)/,/g'
