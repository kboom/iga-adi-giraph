import { Superstep } from 'superstep';

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
            99.9% <= (?<computePerPartitionP999>[\\d,.]+)
             count = (?<partitionsCount>\\d+)
`, 'gm')

export function extractSuperstepSummary(
  input: string,
  superstep: number
): Superstep {
  const groups = superstepSummaryMatcher(superstep).exec(input).groups;
  console.log(groups)
  return {
    id: superstep,
    superstepTime: +groups.superstepTime,
    computeAllPartitions: +groups.computeAllPartitions
  };
}
