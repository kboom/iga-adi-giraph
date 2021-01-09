#!/usr/bin/env bash

# https://cloud.google.com/sdk/gcloud/reference/dataproc/clusters/create
# https://cloud.google.com/compute/docs/instances/specify-min-cpu-platform#gcloud
# https://cloud.google.com/compute/disks-image-pricing

gcloud auth activate-service-account grzegorz-gurgul-584@hyperflow-268022.iam.gserviceaccount.com \
  --key-file="/Users/ggurgul/Keys/hyperflow-key"

gcloud \
    dataproc clusters create iga-adi \
    --region=europe-west4 \
    --service-account=grzegorz-gurgul-584@hyperflow-268022.iam.gserviceaccount.com \
    --master-boot-disk-size=500GB \
    --single-node \
    --master-machine-type=n1-standard-96 \
    --master-min-cpu-platform="Intel Skylake" \
    --image-version="1.5-debian10" \
    --max-age=1d \
    --no-address \
    --optional-components=ZOOKEEPER \
    --properties=yarn:yarn.log-aggregation-enable=true