#!/usr/bin/env bash

if [[ $# -eq 0 ]] ; then
    echo 'Use with <input_excel> <certain_simulation_id_from_this_excel>'
    exit 1
fi

clusterDetailsPath="$1"
simulation="$2"

Rscript results/density.r "${clusterDetailsPath}" "${simulation}" compute_all worker_id ms
Rscript results/density.r "${clusterDetailsPath}" "${simulation}" messages_sent worker_id '-'
Rscript results/density.r "${clusterDetailsPath}" "${simulation}" sent_bytes_max worker_id byte
Rscript results/density.r "${clusterDetailsPath}" "${simulation}" processing_per_thread_stddev worker_id '-'
Rscript results/density.r "${clusterDetailsPath}" "${simulation}" received_bytes_max worker_id byte
Rscript results/density.r "${clusterDetailsPath}" "${simulation}" requests_received worker_id '-'
Rscript results/density.r "${clusterDetailsPath}" "${simulation}" requests_sent worker_id '-'
Rscript results/density.r "${clusterDetailsPath}" "${simulation}" network_communication_time worker_id ms
Rscript results/density.r "${clusterDetailsPath}" "${simulation}" time_spent_in_gc worker_id ms

Rscript results/heatmap.r "${clusterDetailsPath}" "${simulation}" compute_all worker_id ms
Rscript results/heatmap.r "${clusterDetailsPath}" "${simulation}" messages_sent worker_id '-'
Rscript results/heatmap.r "${clusterDetailsPath}" "${simulation}" sent_bytes_max worker_id byte
Rscript results/heatmap.r "${clusterDetailsPath}" "${simulation}" processing_per_thread_stddev worker_id '-'
Rscript results/heatmap.r "${clusterDetailsPath}" "${simulation}" received_bytes_max worker_id byte
Rscript results/heatmap.r "${clusterDetailsPath}" "${simulation}" requests_received worker_id '-'
Rscript results/heatmap.r "${clusterDetailsPath}" "${simulation}" requests_sent worker_id '-'

Rscript results/radar.r "${clusterDetailsPath}" "${simulation}" compute_all worker_id
Rscript results/radar.r "${clusterDetailsPath}" "${simulation}" messages_sent worker_id
Rscript results/radar.r "${clusterDetailsPath}" "${simulation}" sent_bytes_max worker_id
Rscript results/radar.r "${clusterDetailsPath}" "${simulation}" processing_per_thread_stddev worker_id
Rscript results/radar.r "${clusterDetailsPath}" "${simulation}" received_bytes_max worker_id
Rscript results/radar.r "${clusterDetailsPath}" "${simulation}" requests_received worker_id
Rscript results/radar.r "${clusterDetailsPath}" "${simulation}" requests_sent worker_id
