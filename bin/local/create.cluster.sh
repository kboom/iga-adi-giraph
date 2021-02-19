#!/usr/bin/env bash

# https://cloud.google.com/sdk/gcloud/reference/dataproc/clusters/create

gcloud auth activate-service-account grzegorz-gurgul-584@hyperflow-268022.iam.gserviceaccount.com \
  --key-file="/Users/ggurgul/Keys/hyperflow-key"

gcloud \
    dataproc clusters create iga-adi \
    --region=europe-west4 \
    --service-account=grzegorz-gurgul-584@hyperflow-268022.iam.gserviceaccount.com \
    --master-boot-disk-size=100GB \
    --worker-boot-disk-size=50GB \
    --master-machine-type=n1-standard-4 \
    --worker-machine-type=n1-standard-8 \
    --master-min-cpu-platform="Intel Skylake" \
    --worker-min-cpu-platform="Intel Skylake" \
    --image-version="1.3-debian10" \
    --num-workers=4 \
    --max-age=1d \
    --no-address \
    --optional-components=ZOOKEEPER \
    --properties=yarn:yarn.log-aggregation-enable=true