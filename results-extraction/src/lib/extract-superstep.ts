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
               sum = (?<computePerPartitionSum>[\\d,.]+)
               min = (?<computePerPartitionMin>[\\d,.]+)
               max = (?<computePerPartitionMax>[\\d,.]+)
              mean = (?<computePerPartitionMean>[\\d,.]+)
            stddev = (?<computePerPartitionStddev>[\\d,.]+)
            median = (?<computePerPartitionMedian>[\\d,.]+)
              75% <= (?<computePerPartitionP75>[\\d,.]+)
              95% <= (?<computePerPartitionP95>[\\d,.]+)
              98% <= (?<computePerPartitionP98>[\\d,.]+)
              99% <= (?<computePerPartitionP99>[\\d,.]+)
            99\\.9% <= (?<computePerPartitionP999>[\\d,.]+)
             count = (?<partitionsCount>\\d+)

  gc-per-thread-ms:
               sum = (?<gcPerThreadSum>[\\d,.]+)
               min = (?<gcPerThreadMin>[\\d,.]+)
               max = (?<gcPerThreadMax>[\\d,.]+)
              mean = (?<gcPerThreadMean>[\\d,.]+)
            stddev = (?<gcPerThreadStdev>[\\d,.]+)
            median = (?<gcPerThreadMedian>[\\d,.]+)
              75% <= (?<gcPerThreadP75>[\\d,.]+)
              95% <= (?<gcPerThreadP95>[\\d,.]+)
              98% <= (?<gcPerThreadP98>[\\d,.]+)
              99% <= (?<gcPerThreadP99>[\\d,.]+)
            99\\.9% <= (?<gcPerThreadP999>[\\d,.]+)
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
    value = (?<percentLocalRequests>[\\dNaN)]+)

  processing-per-thread-ms:
               sum = (?<processingPerThreadSum>[\\d,.]+)
               min = (?<processingPerThreadMin>[\\d,.]+)
               max = (?<processingPerThreadMax>[\\d,.]+)
              mean = (?<processingPerThreadMean>[\\d,.]+)
            stddev = (?<processingPerThreadStddev>[\\d,.]+)
            median = (?<processingPerThreadMedian>[\\d,.]+)
              75% <= (?<processingPerThreadP75>[\\d,.]+)
              95% <= (?<processingPerThreadP95>[\\d,.]+)
              98% <= (?<processingPerThreadP98>[\\d,.]+)
              99% <= (?<processingPerThreadP99>[\\d,.]+)
            99\\.9% <= (?<processingPerThreadP999>[\\d,.]+)
             count = \\d+

  received-bytes:
               sum = (?<receivedBytesSum>[\\d,.]+)
               min = (?<receivedBytesMin>[\\d,.]+)
               max = (?<receivedBytesMax>[\\d,.]+)
              mean = (?<receivedBytesMean>[\\d,.]+)
            stddev = (?<receivedBytesStddev>[\\d,.]+)
            median = (?<receivedBytesMedian>[\\d,.]+)
              75% <= (?<receivedBytesP75>[\\d,.]+)
              95% <= (?<receivedBytesP95>[\\d,.]+)
              98% <= (?<receivedBytesP98>[\\d,.]+)
              99% <= (?<receivedBytesP99>[\\d,.]+)
            99\\.9% <= (?<receivedBytesP999>[\\d,.]+)
             count = \\d+

  remote-requests:
    count = (?<remoteRequests>\\d+)

  requests-received:
             count = (?<requestsReceived>\\d+)
         mean rate = (?<requestsReceivedMeanRate>[\\d,.]+) requests\\/s
     1-minute rate = (?<requestsReceived1mRate>[\\d,.]+) requests\\/s
     5-minute rate = (?<requestsReceived5mRate>[\\d,.]+) requests\\/s
    15-minute rate = (?<requestsReceived15mRate>[\\d,.]+) requests\\/s

  requests-sent:
             count = (?<requestsSent>\\d+)
         mean rate = (?<requestsSentMeanRate>[\\d,.]+) requests\\/s
     1-minute rate = (?<requestsSent1mRate>[\\d,.]+) requests\\/s
     5-minute rate = (?<requestsSent5mRate>[\\d,.]+) requests\\/s
    15-minute rate = (?<requestsSent15mRate>[\\d,.]+) requests\\/s

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
               sum = (?<sentBytesSum>[\\d,.]+)
               min = (?<sentBytesMin>[\\d,.]+)
               max = (?<sentBytesMax>[\\d,.]+)
              mean = (?<sentBytesMean>[\\d,.]+)
            stddev = (?<sentBytesStddev>[\\d,.]+)
            median = (?<sentBytesMedian>[\\d,.]+)
              75% <= (?<sentBytesP75>[\\d,.]+)
              95% <= (?<sentBytesP95>[\\d,.]+)
              98% <= (?<sentBytesP98>[\\d,.]+)
              99% <= (?<sentBytesP99>[\\d,.]+)
            99\\.9% <= (?<sentBytesP999>[\\d,.]+)
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
               sum = (?<waitPerThreadSum>[\\d,.]+)
               min = (?<waitPerThreadMin>[\\d,.]+)
               max = (?<waitPerThreadMax>[\\d,.]+)
              mean = (?<waitPerThreadMean>[\\d,.]+)
            stddev = (?<waitPerThreadStddev>[\\d,.]+)
            median = (?<waitPerThreadMedian>[\\d,.]+)
              75% <= (?<waitPerThreadP75>[\\d,.]+)
              95% <= (?<waitPerThreadP95>[\\d,.]+)
              98% <= (?<waitPerThreadP98>[\\d,.]+)
              99% <= (?<waitPerThreadP99>[\\d,.]+)
            99\\.9% <= (?<waitPerThreadP999>[\\d,.]+)
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
    id: superstep,
    superstepTime: +groups.superstepTime,
    computeAllPartitions: +groups.computeAllPartitions,
    timeSpentInGc: +groups.timeSpentInGc,
    networkCommunicationTime: +groups.networkCommunicationTime,
    timeToFirstMessage: +groups.timeToFirstMessage,
    waitOnRequestsTime: +groups.waitOnRequestsTime,
    communicationTime: +groups.communicationTime,
    computeAll: +groups.computeAll,
    computePerPartitionSum: +groups.computePerPartitionSum,
    computePerPartitionMin: +groups.computePerPartitionMin,
    computePerPartitionMax: +groups.computePerPartitionMax,
    computePerPartitionMean: +groups.computePerPartitionMean,
    computePerPartitionStddev: +groups.computePerPartitionStddev,
    computePerPartitionMedian: +groups.computePerPartitionMedian,
    computePerPartitionP75: +groups.computePerPartitionP75,
    computePerPartitionP95: +groups.computePerPartitionP95,
    computePerPartitionP98: +groups.computePerPartitionP98,
    computePerPartitionP99: +groups.computePerPartitionP99,
    computePerPartitionP999: +groups.computePerPartitionP999,
    partitionsCount: +groups.partitionsCount,
    gcPerThreadSum: +groups.gcPerThreadSum,
    gcPerThreadMin: +groups.gcPerThreadMin,
    gcPerThreadMax: +groups.gcPerThreadMax,
    gcPerThreadMean:+groups.gcPerThreadMean,
    gcPerThreadStdev: +groups.gcPerThreadStdev,
    gcPerThreadMedian: +groups.gcPerThreadMedian,
    gcPerThreadP75: +groups.gcPerThreadP75,
    gcPerThreadP95: +groups.gcPerThreadP95,
    gcPerThreadP98: +groups.gcPerThreadP98,
    gcPerThreadP99: +groups.gcPerThreadP99,
    gcPerThreadP999: +groups.gcPerThreadP999,
    localRequestsCount: +groups.localRequestsCount,
    messageBytesSent: +groups.messageBytesSent,
    messagesSent: +groups.messagesSent,
    percentLocalRequests: +groups.percentLocalRequests,
    processingPerThreadSum: +groups.processingPerThreadSum,
    processingPerThreadMin: +groups.processingPerThreadMin,
    processingPerThreadMax: +groups.processingPerThreadMax,
    processingPerThreadMean: +groups.processingPerThreadMean,
    processingPerThreadStddev: +groups.processingPerThreadStddev,
    processingPerThreadMedian: +groups.processingPerThreadMedian,
    processingPerThreadP75: +groups.processingPerThreadP75,
    processingPerThreadP95: +groups.processingPerThreadP95,
    processingPerThreadP98: +groups.processingPerThreadP98,
    processingPerThreadP99: +groups.processingPerThreadP99,
    processingPerThreadP999: +groups.processingPerThreadP999,
    receivedBytesSum: +groups.receivedBytesSum,
    receivedBytesMin: +groups.receivedBytesMin,
    receivedBytesMax: +groups.receivedBytesMax,
    receivedBytesMean: +groups.receivedBytesMean,
    receivedBytesStddev: +groups.receivedBytesStddev,
    receivedBytesMedian: +groups.receivedBytesMedian,
    receivedBytesP75: +groups.receivedBytesP75,
    receivedBytesP95: +groups.receivedBytesP95,
    receivedBytesP98: +groups.receivedBytesP98,
    receivedBytesP99: +groups.receivedBytesP99,
    receivedBytesP999: +groups.receivedBytesP999,
    remoteRequests: +groups.remoteRequests,
    requestsReceived: +groups.requestsReceived,
    requestsReceivedMeanRate: +groups.requestsReceivedMeanRate,
    requestsReceived1mRate: +groups.requestsReceived1mRate,
    requestsReceived5mRate: +groups.requestsReceived5mRate,
    requestsReceived15mRate: +groups.requestsReceived15mRate,
    requestsSent: +groups.requestsSent,
    requestsSentMeanRate: +groups.requestsSentMeanRate,
    requestsSent1mRate: +groups.requestsSent1mRate,
    requestsSent5mRate: +groups.requestsSent5mRate,
    requestsSent15mRate: +groups.requestsSent15mRate,
    sentBytesSum: +groups.sentBytesSum,
    sentBytesMin: +groups.sentBytesMin,
    sentBytesMax: +groups.sentBytesMax,
    sentBytesMean: +groups.sentBytesMean,
    sentBytesStddev: +groups.sentBytesStddev,
    sentBytesMedian: +groups.sentBytesMedian,
    sentBytesP75: +groups.sentBytesP75,
    sentBytesP95: +groups.sentBytesP95,
    sentBytesP98: +groups.sentBytesP98,
    sentBytesP99: +groups.sentBytesP99,
    sentBytesP999: +groups.sentBytesP999,
    superstepGcTime: +groups.superstepGcTime,
    totalRequests: +groups.totalRequests,
    waitPerThreadSum: +groups.waitPerThreadSum,
    waitPerThreadMin: +groups.waitPerThreadMin,
    waitPerThreadMax: +groups.waitPerThreadMax,
    waitPerThreadMean: +groups.waitPerThreadMean,
    waitPerThreadStddev: +groups.waitPerThreadStddev,
    waitPerThreadMedian: +groups.waitPerThreadMedian,
    waitPerThreadP75: +groups.waitPerThreadP75,
    waitPerThreadP95: +groups.waitPerThreadP95,
    waitPerThreadP98: +groups.waitPerThreadP98,
    waitPerThreadP99: +groups.waitPerThreadP99,
    waitPerThreadP999: +groups.waitPerThreadP999
  };
}
