#!/usr/bin/env bash

if [[ -z "${1}" ]]; then
  echo "Required <SCALABILITY_XLSX> <SUPERSTEPS_XLSX>"
  exit 1
else
  resultsPath="${1}"
fi

if [[ -z "${2}" ]]; then
  echo "Required <SCALABILITY_XLSX> <SUPERSTEPS_XLSX>"
  exit 1
else
  clusterDetailsPath="${1}"
fi

dir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

for problemSize in 3072 1536 768;
do
Rscript "$dir/scalability.r" "${resultsPath}" multinode strong_speedup_init threads $problemSize
Rscript "$dir/scalability.r" "${resultsPath}" multinode strong_speedup_step threads $problemSize
Rscript "$dir/scalability.r" "${resultsPath}" multinode strong_speedup_factorization threads $problemSize
Rscript "$dir/scalability.r" "${resultsPath}" multinode strong_speedup_backwards_substitution threads $problemSize
Rscript "$dir/scalability.r" "${resultsPath}" multinode strong_speedup_transpose_map threads $problemSize
Rscript "$dir/scalability.r" "${resultsPath}" multinode strong_speedup_transpose_reduce threads $problemSize
Rscript "$dir/overhead.r" "${resultsPath}" multinode strong_speedup_init threads $problemSize
Rscript "$dir/overhead.r" "${resultsPath}" multinode strong_speedup_step threads $problemSize
Rscript "$dir/overhead.r" "${resultsPath}" multinode strong_speedup_factorization threads $problemSize
Rscript "$dir/overhead.r" "${resultsPath}" multinode strong_speedup_backwards_substitution threads $problemSize
Rscript "$dir/overhead.r" "${resultsPath}" multinode strong_speedup_transpose_map threads $problemSize
Rscript "$dir/overhead.r" "${resultsPath}" multinode strong_speedup_transpose_reduce threads $problemSize
Rscript "$dir/times.r" "${resultsPath}" multinode $problemSize
done;""
Rscript "$dir/gustafsson.r" "${resultsPath}" multinode weak_step_speedup threads
Rscript "$dir/gustafsson.r" "${resultsPath}" multinode weak_init_speedup threads
Rscript "$dir/gustafsson.r" "${resultsPath}" multinode weak_factorization_speedup threads
Rscript "$dir/gustafsson.r" "${resultsPath}" multinode weak_backwards_substitution_speedup threads
Rscript "$dir/gustafsson.r" "${resultsPath}" multinode weak_transposition_map_speedup threads
Rscript "$dir/gustafsson.r" "${resultsPath}" multinode weak_transposition_reduce_speedup threads

# Individual simulations
while read simulation
do
Rscript "$dir/heatmap.r" "${clusterDetailsPath}" "${simulation}" czasu_wątku nr_węzła 'ms'
Rscript "$dir/heatmap.r" "${clusterDetailsPath}" "${simulation}" obciążenia_wątków nr_węzła 'ms'
Rscript "$dir/density.r" "${clusterDetailsPath}" "${simulation}" sent_bytes_max worker_id byte
Rscript "$dir/density.r" "${clusterDetailsPath}" "${simulation}" processing_per_thread_stddev worker_id '-'
Rscript "$dir/density.r" "${clusterDetailsPath}" "${simulation}" received_bytes_max worker_id byte
Rscript "$dir/density.r" "${clusterDetailsPath}" "${simulation}" requests_received worker_id '-'
Rscript "$dir/density.r" "${clusterDetailsPath}" "${simulation}" requests_sent worker_id '-'
Rscript "$dir/density.r" "${clusterDetailsPath}" "${simulation}" network_communication_time worker_id ms
Rscript "$dir/density.r" "${clusterDetailsPath}" "${simulation}" time_spent_in_gc worker_id ms

Rscript "$dir/heatmap.r" "${clusterDetailsPath}" "${simulation}" compute_all worker_id ms
Rscript "$dir/heatmap.r" "${clusterDetailsPath}" "${simulation}" messages_sent worker_id '-'
Rscript "$dir/heatmap.r" "${clusterDetailsPath}" "${simulation}" sent_bytes_max worker_id byte
Rscript "$dir/heatmap.r" "${clusterDetailsPath}" "${simulation}" processing_per_thread_stddev worker_id '-'
Rscript "$dir/heatmap.r" "${clusterDetailsPath}" "${simulation}" received_bytes_max worker_id byte
Rscript "$dir/heatmap.r" "${clusterDetailsPath}" "${simulation}" requests_received worker_id '-'
Rscript "$dir/heatmap.r" "${clusterDetailsPath}" "${simulation}" requests_sent worker_id '-'

Rscript "$dir/radar.r" "${clusterDetailsPath}" "${simulation}" compute_all worker_id
Rscript "$dir/radar.r" "${clusterDetailsPath}" "${simulation}" messages_sent worker_id
Rscript "$dir/radar.r" "${clusterDetailsPath}" "${simulation}" sent_bytes_max worker_id
Rscript "$dir/radar.r" "${clusterDetailsPath}" "${simulation}" processing_per_thread_stddev worker_id
Rscript "$dir/radar.r" "${clusterDetailsPath}" "${simulation}" received_bytes_max worker_id
Rscript "$dir/radar.r" "${clusterDetailsPath}" "${simulation}" requests_received worker_id
Rscript "$dir/radar.r" "${clusterDetailsPath}" "${simulation}" requests_sent worker_id
done <<< '\
suite-strong-scalability-6144-application_1584994634058_0006
'
