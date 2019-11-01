#!/usr/bin/env bash
MASTER_ID=${1}

BIN_DIR=$(dirname "$0")
IGA_HOME=$(cd "${BIN_DIR}/../.." || exit ; pwd)
IGA_DIST=$(cd "${IGA_HOME}/dist" || exit ; pwd)

# Compile
cd "${IGA_HOME}" && mvn clean package -DskipTests

# Upload the JAR and these scripts
gcloud compute scp --zone "europe-west4-a" --tunnel-through-iap --recurse "${IGA_DIST}"/*.jar "${BIN_DIR}/cluster"/*
"${MASTER_ID}":~/