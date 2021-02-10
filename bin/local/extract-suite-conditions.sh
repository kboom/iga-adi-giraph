#!/usr/bin/env bash

SCRIPTPATH="$(
  cd "$(dirname "$0")" || exit
  pwd -P
)"

if [[ -z "${1}" ]]; then
  echo "Required directory name"
  exit 1
else
  SUITE_DIR="${1}"
fi

echo "EXP,SYSTIME,PGRP,PPID,PID,THCNT,TID,NUMA,PSR,P,S,STAT,CP,%MEM,PRI,RTPRIO,POL,SCH,TIME,COMMAND"
for dir in "${SUITE_DIR}"/*; do
  ps_filename=$(realpath "$dir"/*-ps.txt)
  suite_exp_name=$(basename "$dir")
  "${SCRIPTPATH}/extract-ps-stats.sh" "$ps_filename" "$suite_exp_name"
done
