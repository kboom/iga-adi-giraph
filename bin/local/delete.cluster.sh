#!/usr/bin/env bash

# https://cloud.google.com/sdk/gcloud/reference/dataproc/clusters/create

gcloud auth activate-service-account grzegorz-gurgul-584@hyperflow-268022.iam.gserviceaccount.com \
  --key-file="/Users/ggurgul/Keys/hyperflow-key"

gcloud \
    dataproc clusters delete iga-adi \
    --region=europe-west4
