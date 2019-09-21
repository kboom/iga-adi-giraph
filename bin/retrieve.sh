#!/usr/bin/env bash
MASTER_ID=${1}

BIN_DIR=$(dirname "$0")
IGA_HOME=$(cd "${BIN_DIR}/.." || exit ; pwd)

# Upload the JAR and these scripts
gcloud compute scp --recurse "${MASTER_ID}":~/logs/* "${IGA_HOME}/logs"