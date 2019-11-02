#!/usr/bin/env bash
PROBLEM=${1:-12288}
COLUMN=${2:-2}
echo "=== ${PROBLEM} ==="
awk -F "," -v col="${COLUMN}" -v problem="${PROBLEM}" '{ if($1 == problem) print $2, $col}' single.csv