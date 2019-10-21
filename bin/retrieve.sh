#!/usr/bin/env bash
MASTER_ID=${1}

BIN_DIR=$(dirname "$0")
IGA_HOME=$(cd "${BIN_DIR}/.." || exit ; pwd)

# Upload the JAR and these scripts
gcloud compute scp --zone "europe-west4-a" --tunnel-through-iap  --recurse "${MASTER_ID}":~/logs/* "${IGA_HOME}/logs"