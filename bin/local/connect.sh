#!/usr/bin/env bash

INSTANCE="iga-adi-m"

if [ -z "$1" ]
then
    echo "Connecting to master"
else
  INSTANCE="$1"
fi

gcloud beta compute ssh --zone "europe-west4-a" "${INSTANCE}" --tunnel-through-iap --project "hyperflow-268022"
