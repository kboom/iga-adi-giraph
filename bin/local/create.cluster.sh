#!/usr/bin/env bash

# https://cloud.google.com/sdk/gcloud/reference/dataproc/clusters/create

gcloud auth activate-service-account grzegorz-gurgul-584@hyperflow-268022.iam.gserviceaccount.com \
  --key-file="/Users/ggurgul/Keys/hyperflow-key"

gcloud \
    dataproc clusters create iga-adi \
    --region=europe-west4 \
    --service-account=grzegorz-gurgul-584@hyperflow-268022.iam.gserviceaccount.com \
    --master-boot-disk-size=50GB \
    --worker-boot-disk-size=25GB \
    --master-machine-type=n2-standard-2 \
    --worker-machine-type=n2-standard-4 \
    --num-workers=2 \
    --max-idle=1h \
    --max-age=1d \
    --no-address \
    --optional-components=ZOOKEEPER \
    --properties=yarn:yarn.log-aggregation-enable=true