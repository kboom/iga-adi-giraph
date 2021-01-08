#!/usr/bin/env bash

# SYSTIME       PGRP  PPID   PID THCNT   TID NUMA PSR P S STAT  CP %MEM PRI RTPRIO POL SCH     TIME COMMAND
#1610094825475  3584     1  3662   225  4718    0   0 0 R Rl     0  1.5  19      - TS    0 00:00:00 SchedulerEventD
#1610094825475  3584     1  3662   225  3789    0   2 2 R Rl     4  1.5  19      - TS    0 00:00:03 C2 CompilerThre
#1610094826515  3586     1  3657    94  4667    0   0 0 R Rl     0  1.5  19      - TS    0 00:00:00 NM ContainerMan
#1610094826515  3586     1  3657    94  3797    0   1 1 R Rl     2  1.5  19      - TS    0 00:00:02 C2 CompilerThre
#1610094827553  3586     1  3657    98  9436    0   1 1 R Rl     0  1.5  19      - TS    0 00:00:00 ContainerLocali
#1610094828589  9480  9480 19484    20  9485    0   6 6 R RNl    0  0.2  18      - TS    0 00:00:00 java
#1610094828589  9480 19480  9484    20  9499    0   1 1 R RNl    0  0.2  18      - TS    0 00:00:00 C2 CompilerThre
#1610094828589 19480  9480  9484    20  9501    0   2 2 R RNl    0  0.2  18      - TS    0 00:00:00 C1 CompilerThre
#1610094829627  9480  9480  9484    22  9498    0   0 0 R RNl  600  0.7  18      - TS    0 00:00:00 C2 CompilerThre

# As only the command is the free text field we can convert its spaces to - and then simply replace the spaces with comma


 awk '{ cmd=substr($0,100); gsub(/ +/, "-", cmd); print substr($0,0,99), cmd}' < "${1:-/dev/stdin}" | sed 's/\( \{1,\}\)/,/g'
