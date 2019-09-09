#!/usr/bin/env bash
if [[ $# = 0 ]]; then
  echo "Usage: iga [-D<Hadoop property>] <program parameters>"
  exit 1
fi