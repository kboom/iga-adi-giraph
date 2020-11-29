#!/usr/bin/env bash

# https://cloud.google.com/sdk/gcloud/reference/dataproc/clusters/create

gcloud auth activate-service-account grzegorz-gurgul-584@hyperflow-268022.iam.gserviceaccount.com \
  --key-file="/Users/ggurgul/Keys/hyperflow-key"

gcloud compute networks subnets create iga-adi \
    --network=default \
    --range=192.168.0.0/24 \
    --region=europe-west4 \
    --enable-private-ip-google-access