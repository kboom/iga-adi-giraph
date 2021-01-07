#!/usr/bin/env bash
set +xe
# https://man7.org/linux/man-pages/man1/ps.1.html

INTERESTING_PROCESSES="$( pgrep -f java | xargs | sed -e 's/ /,/g')"
INTERESTING_COLUMNS=ppid,pid,thcount,tid,numa,psr,sgi_p,state,stat,cp,%mem,pri,policy,sched,cputime,comm,etime
PS_COMMAND="ps -p $INTERESTING_PROCESSES H -u yarn -o $INTERESTING_COLUMNS  | grep compute | sed \"s#^#$(date +%s%3N) #\""

watch -t -n 0.5 "($PS_COMMAND) | tee -a usage.txt"
