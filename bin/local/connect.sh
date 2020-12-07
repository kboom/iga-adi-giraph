#!/usr/bin/env bash

gcloud beta compute ssh --zone "europe-west4-a" "iga-adi-m" --tunnel-through-iap --project "hyperflow-268022"
