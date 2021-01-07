#!/usr/bin/env bash
set +xe

mkdir -p logs
lscpu > logs/cpu_info.txt
less /proc/cpuinfo > logs/cpu_details.txt