import { Superstep } from 'simulation';

const superstepSummaryMatcher = (superstep: number) => new RegExp(`\
--- METRICS: superstep ${superstep} ---
  superstep time: (?<superstepTime>\\d+) ms
  compute all partitions: (?<computeAllPartitions>\\d+) ms
  time spent in gc: (?<timeSpentInGc>\\d+) ms
  bytes transferred in out-of-core: \\d+
  network communication time: (?<networkCommunicationTime>\\d+) ms
  time to first message: (?<timeToFirstMessage>\\d+) us
  wait on requests time: (?<waitOnRequestsTime>\\d+) us

.*
giraph\\.superstep\\.\\d+:
  communication-time-ms:
    value = (?<communicationTime>\\d+)

  compute-all-ms:
    value = (?<computeAll>\\d+)

  compute-per-partition-ms:
               sum = (?<computePerPartitionSum>[\\d-,.]+)
               min = (?<computePerPartitionMin>[\\d-,.]+)
               max = (?<computePerPartitionMax>[\\d-,.]+)
              mean = (?<computePerPartitionMean>[\\d-,.]+)
            stddev = (?<computePerPartitionStddev>[\\d-,.]+)
            median = (?<computePerPartitionMedian>[\\d-,.]+)
              75% <= (?<computePerPartitionP75>[\\d-,.]+)
              95% <= (?<computePerPartitionP95>[\\d-,.]+)
              98% <= (?<computePerPartitionP98>[\\d-,.]+)
              99% <= (?<computePerPartitionP99>[\\d-,.]+)
            99\\.9% <= (?<computePerPartitionP999>[\\d-,.]+)
             count = (?<partitionsCount>\\d+)

  gc-per-thread-ms:
               sum = (?<gcPerThreadSum>[\\d-,.]+)
               min = (?<gcPerThreadMin>[\\d-,.]+)
               max = (?<gcPerThreadMax>[\\d-,.]+)
              mean = (?<gcPerThreadMean>[\\d-,.]+)
            stddev = (?<gcPerThreadStdev>[\\d-,.]+)
            median = (?<gcPerThreadMedian>[\\d-,.]+)
              75% <= (?<gcPerThreadP75>[\\d-,.]+)
              95% <= (?<gcPerThreadP95>[\\d-,.]+)
              98% <= (?<gcPerThreadP98>[\\d-,.]+)
              99% <= (?<gcPerThreadP99>[\\d-,.]+)
            99\\.9% <= (?<gcPerThreadP999>[\\d-,.]+)
             count = \\d+

  local-requests:
    count = (?<localRequestsCount>\\d+)

  message-bytes-sent:
    count = (?<messageBytesSent>\\d+)

  messages-sent:
    count = (?<messagesSent>\\d+)

  ooc-bytes-load:
    count = \\d+

  ooc-bytes-store:
    count = \\d+

  percent-local-requests:
    value = (?<percentLocalRequests>[\\d\\.NaN)]+)

  processing-per-thread-ms:
               sum = (?<processingPerThreadSum>[\\d-,.]+)
               min = (?<processingPerThreadMin>[\\d-,.]+)
               max = (?<processingPerThreadMax>[\\d-,.]+)
              mean = (?<processingPerThreadMean>[\\d-,.]+)
            stddev = (?<processingPerThreadStddev>[\\d-,.]+)
            median = (?<processingPerThreadMedian>[\\d-,.]+)
              75% <= (?<processingPerThreadP75>[\\d-,.]+)
              95% <= (?<processingPerThreadP95>[\\d-,.]+)
              98% <= (?<processingPerThreadP98>[\\d-,.]+)
              99% <= (?<processingPerThreadP99>[\\d-,.]+)
            99\\.9% <= (?<processingPerThreadP999>[\\d-,.]+)
             count = \\d+

  received-bytes:
               sum = (?<receivedBytesSum>[\\d-,.]+)
               min = (?<receivedBytesMin>[\\d-,.]+)
               max = (?<receivedBytesMax>[\\d-,.]+)
              mean = (?<receivedBytesMean>[\\d-,.]+)
            stddev = (?<receivedBytesStddev>[\\d-,.]+)
            median = (?<receivedBytesMedian>[\\d-,.]+)
              75% <= (?<receivedBytesP75>[\\d-,.]+)
              95% <= (?<receivedBytesP95>[\\d-,.]+)
              98% <= (?<receivedBytesP98>[\\d-,.]+)
              99% <= (?<receivedBytesP99>[\\d-,.]+)
            99\\.9% <= (?<receivedBytesP999>[\\d-,.]+)
             count = \\d+

  remote-requests:
    count = (?<remoteRequests>\\d+)

  requests-received:
             count = (?<requestsReceived>\\d+)
         mean rate = (?<requestsReceivedMeanRate>[\\d-,.]+) requests\\/s
     1-minute rate = (?<requestsReceived1mRate>[\\d-,.]+) requests\\/s
     5-minute rate = (?<requestsReceived5mRate>[\\d-,.]+) requests\\/s
    15-minute rate = (?<requestsReceived15mRate>[\\d-,.]+) requests\\/s

  requests-sent:
             count = (?<requestsSent>\\d+)
         mean rate = (?<requestsSentMeanRate>[\\d-,.]+) requests\\/s
     1-minute rate = (?<requestsSent1mRate>[\\d-,.]+) requests\\/s
     5-minute rate = (?<requestsSent5mRate>[\\d-,.]+) requests\\/s
    15-minute rate = (?<requestsSent15mRate>[\\d-,.]+) requests\\/s

  send-aggregators-to-master-requests:
    count = \\d+

  send-aggregators-to-owner-requests:
    count = \\d+

  send-aggregators-to-worker-requests:
    count = \\d+

  send-partition-current-messages-requests:
    count = \\d+

  send-partition-mutations-requests:
    count = \\d+

  send-vertex-requests:
    count = \\d+

  send-worker-aggregators-requests:
    count = \\d+

  send-worker-messages-requests:
    count = \\d+

  sent-bytes:
               sum = (?<sentBytesSum>[\\d-,.]+)
               min = (?<sentBytesMin>[\\d-,.]+)
               max = (?<sentBytesMax>[\\d-,.]+)
              mean = (?<sentBytesMean>[\\d-,.]+)
            stddev = (?<sentBytesStddev>[\\d-,.]+)
            median = (?<sentBytesMedian>[\\d-,.]+)
              75% <= (?<sentBytesP75>[\\d-,.]+)
              95% <= (?<sentBytesP95>[\\d-,.]+)
              98% <= (?<sentBytesP98>[\\d-,.]+)
              99% <= (?<sentBytesP99>[\\d-,.]+)
            99\\.9% <= (?<sentBytesP999>[\\d-,.]+)
             count = \\d+

  superstep-gc-time-ms:
    count = (?<superstepGcTime>\\d+)

  superstep-time-ms:
    value = \\d+

  time-to-first-message-ms:
    value = \\d+

  total-requests:
    value = (?<totalRequests>\\d+)

  wait-per-thread-ms:
               sum = (?<waitPerThreadSum>[\\d-,.]+)
               min = (?<waitPerThreadMin>[\\d-,.]+)
               max = (?<waitPerThreadMax>[\\d-,.]+)
              mean = (?<waitPerThreadMean>[\\d-,.]+)
            stddev = (?<waitPerThreadStddev>[\\d-,.]+)
            median = (?<waitPerThreadMedian>[\\d-,.]+)
              75% <= (?<waitPerThreadP75>[\\d-,.]+)
              95% <= (?<waitPerThreadP95>[\\d-,.]+)
              98% <= (?<waitPerThreadP98>[\\d-,.]+)
              99% <= (?<waitPerThreadP99>[\\d-,.]+)
            99\\.9% <= (?<waitPerThreadP999>[\\d-,.]+)
             count = \\d+

  wait-requests-us:
    value = \\d+

  worker-context-post-superstep:
    value = \\d+

  worker-context-pre-superstep:
    value = \\d+
`, 'gm')

export function extractSuperstepSummary(
  input: string,
  superstep: number
): Superstep {
  const groups = superstepSummaryMatcher(superstep).exec(input).groups;
  return {
    superstepId: superstep,
    superstepTime: +groups.superstepTime || 0,
    computeAllPartitions: +groups.computeAllPartitions || 0,
    timeSpentInGc: +groups.timeSpentInGc || 0,
    networkCommunicationTime: +groups.networkCommunicationTime || 0,
    timeToFirstMessage: +groups.timeToFirstMessage || 0,
    waitOnRequestsTime: +groups.waitOnRequestsTime || 0,
    communicationTime: +groups.communicationTime || 0,
    computeAll: +groups.computeAll || 0,
    computePerPartitionSum: +groups.computePerPartitionSum || 0,
    computePerPartitionMin: +groups.computePerPartitionMin || 0,
    computePerPartitionMax: +groups.computePerPartitionMax || 0,
    computePerPartitionMean: +groups.computePerPartitionMean || 0,
    computePerPartitionStddev: +groups.computePerPartitionStddev || 0,
    computePerPartitionMedian: +groups.computePerPartitionMedian || 0,
    computePerPartitionP75: +groups.computePerPartitionP75 || 0,
    computePerPartitionP95: +groups.computePerPartitionP95 || 0,
    computePerPartitionP98: +groups.computePerPartitionP98 || 0,
    computePerPartitionP99: +groups.computePerPartitionP99 || 0,
    computePerPartitionP999: +groups.computePerPartitionP999 || 0,
    partitionsCount: +groups.partitionsCount || 0,
    gcPerThreadSum: +groups.gcPerThreadSum || 0,
    gcPerThreadMin: +groups.gcPerThreadMin || 0,
    gcPerThreadMax: +groups.gcPerThreadMax || 0,
    gcPerThreadMean:+groups.gcPerThreadMean || 0,
    gcPerThreadStdev: +groups.gcPerThreadStdev || 0,
    gcPerThreadMedian: +groups.gcPerThreadMedian || 0,
    gcPerThreadP75: +groups.gcPerThreadP75 || 0,
    gcPerThreadP95: +groups.gcPerThreadP95 || 0,
    gcPerThreadP98: +groups.gcPerThreadP98 || 0,
    gcPerThreadP99: +groups.gcPerThreadP99 || 0,
    gcPerThreadP999: +groups.gcPerThreadP999 || 0,
    localRequestsCount: +groups.localRequestsCount || 0,
    messageBytesSent: +groups.messageBytesSent || 0,
    messagesSent: +groups.messagesSent || 0,
    percentLocalRequests: +groups.percentLocalRequests || 0,
    processingPerThreadSum: +groups.processingPerThreadSum || 0,
    processingPerThreadMin: +groups.processingPerThreadMin || 0,
    processingPerThreadMax: +groups.processingPerThreadMax || 0,
    processingPerThreadMean: +groups.processingPerThreadMean || 0,
    processingPerThreadStddev: +groups.processingPerThreadStddev || 0,
    processingPerThreadMedian: +groups.processingPerThreadMedian || 0,
    processingPerThreadP75: +groups.processingPerThreadP75 || 0,
    processingPerThreadP95: +groups.processingPerThreadP95 || 0,
    processingPerThreadP98: +groups.processingPerThreadP98 || 0,
    processingPerThreadP99: +groups.processingPerThreadP99 || 0,
    processingPerThreadP999: +groups.processingPerThreadP999 || 0,
    receivedBytesSum: +groups.receivedBytesSum || 0,
    receivedBytesMin: +groups.receivedBytesMin || 0,
    receivedBytesMax: +groups.receivedBytesMax || 0,
    receivedBytesMean: +groups.receivedBytesMean || 0,
    receivedBytesStddev: +groups.receivedBytesStddev || 0,
    receivedBytesMedian: +groups.receivedBytesMedian || 0,
    receivedBytesP75: +groups.receivedBytesP75 || 0,
    receivedBytesP95: +groups.receivedBytesP95 || 0,
    receivedBytesP98: +groups.receivedBytesP98 || 0,
    receivedBytesP99: +groups.receivedBytesP99 || 0,
    receivedBytesP999: +groups.receivedBytesP999 || 0,
    remoteRequests: +groups.remoteRequests || 0,
    requestsReceived: +groups.requestsReceived || 0,
    requestsReceivedMeanRate: +groups.requestsReceivedMeanRate || 0,
    requestsReceived1mRate: +groups.requestsReceived1mRate || 0,
    requestsReceived5mRate: +groups.requestsReceived5mRate || 0,
    requestsReceived15mRate: +groups.requestsReceived15mRate || 0,
    requestsSent: +groups.requestsSent || 0,
    requestsSentMeanRate: +groups.requestsSentMeanRate || 0,
    requestsSent1mRate: +groups.requestsSent1mRate || 0,
    requestsSent5mRate: +groups.requestsSent5mRate || 0,
    requestsSent15mRate: +groups.requestsSent15mRate || 0,
    sentBytesSum: +groups.sentBytesSum || 0,
    sentBytesMin: +groups.sentBytesMin || 0,
    sentBytesMax: +groups.sentBytesMax || 0,
    sentBytesMean: +groups.sentBytesMean || 0,
    sentBytesStddev: +groups.sentBytesStddev || 0,
    sentBytesMedian: +groups.sentBytesMedian || 0,
    sentBytesP75: +groups.sentBytesP75 || 0,
    sentBytesP95: +groups.sentBytesP95 || 0,
    sentBytesP98: +groups.sentBytesP98 || 0,
    sentBytesP99: +groups.sentBytesP99 || 0,
    sentBytesP999: +groups.sentBytesP999 || 0,
    superstepGcTime: +groups.superstepGcTime || 0,
    totalRequests: +groups.totalRequests || 0,
    waitPerThreadSum: +groups.waitPerThreadSum || 0,
    waitPerThreadMin: +groups.waitPerThreadMin || 0,
    waitPerThreadMax: +groups.waitPerThreadMax || 0,
    waitPerThreadMean: +groups.waitPerThreadMean || 0,
    waitPerThreadStddev: +groups.waitPerThreadStddev || 0,
    waitPerThreadMedian: +groups.waitPerThreadMedian || 0,
    waitPerThreadP75: +groups.waitPerThreadP75 || 0,
    waitPerThreadP95: +groups.waitPerThreadP95 || 0,
    waitPerThreadP98: +groups.waitPerThreadP98 || 0,
    waitPerThreadP99: +groups.waitPerThreadP99 || 0,
    waitPerThreadP999: +groups.waitPerThreadP999 || 0
  };
}