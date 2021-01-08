#!/usr/bin/env bash
MASTER_ID=${1}

THIS_DIR=$(dirname "$0")
CLUSTER_DIR=$(cd "${THIS_DIR}/../cluster" || exit ; pwd)

# Upload the JAR and these scripts
gcloud compute scp \
  --zone "europe-west4-a" \
  --project "hyperflow-268022" \
  --recurse "${MASTER_ID}":~/.config/* \
  "${CLUSTER_DIR}/.config"