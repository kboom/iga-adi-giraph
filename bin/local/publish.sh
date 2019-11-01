#!/usr/bin/env bash
MASTER_ID=${1}

THIS_DIR=$(dirname "$0")
CLUSTER_DIR=$(cd "${THIS_DIR}/../cluster" || exit ; pwd)
IGA_HOME=$(cd "${THIS_DIR}/../.." || exit ; pwd)
IGA_DIST=$(cd "${IGA_HOME}/dist" || exit ; pwd)

# Compile
#cd "${IGA_HOME}" && mvn clean package -DskipTests

# Upload the JAR and these scripts
gcloud compute scp \
  --zone "europe-west4-a" \
  --tunnel-through-iap \
  --recurse "${IGA_DIST}"/*.jar "${CLUSTER_DIR}"/* \
  "${MASTER_ID}":~/