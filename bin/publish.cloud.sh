#!/usr/bin/env bash
MASTER_ID=${1}

BIN_DIR=$(dirname "$0")
IGA_HOME=`cd "${BIN_DIR}/.." ; pwd`
IGA_DIST=`cd "${IGA_HOME}/dist" ; pwd`

# Compile
cd ${IGA_HOME} && mvn clean package -DskipTests

# Upload the JAR and these scripts
gcloud compute scp ${IGA_DIST}/*.jar ${BIN_DIR}/* ${MASTER_ID}:~/