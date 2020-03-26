// tslint:disable:no-expression-statement no-object-mutation
import test from 'ava';
import { extractSuperstepSummary } from './extract-superstep';

test('can extract superstep', t => {
  t.snapshot(extractSuperstepSummary(dummySuperstep, 5));
});

const dummySuperstep = `
--- METRICS: superstep 5 ---
  superstep time: 177 ms
  compute all partitions: 79 ms
  time spent in gc: 18 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 8 us

3/23/20 8:26:22 PM =============================================================
giraph.superstep.5:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 79

  compute-per-partition-ms:
               sum = 1,092.00
               min = 65.00
               max = 75.00
              mean = 68.25
            stddev = 2.11
            median = 68.00
              75% <= 69.00
              95% <= 75.00
              98% <= 75.00
              99% <= 75.00
            99.9% <= 75.00
             count = 16

  gc-per-thread-ms:
               sum = 288.00
               min = 18.00
               max = 18.00
              mean = 18.00
            stddev = 0.00
            median = 18.00
              75% <= 18.00
              95% <= 18.00
              98% <= 18.00
              99% <= 18.00
            99.9% <= 18.00
             count = 16

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 64

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 804.00
               min = 47.00
               max = 57.00
              mean = 50.25
            stddev = 2.11
            median = 50.00
              75% <= 51.00
              95% <= 57.00
              98% <= 57.00
              99% <= 57.00
            99.9% <= 57.00
             count = 16

  received-bytes:
               sum = 3,145.00
               min = 16.00
               max = 1343.00
              mean = 196.56
            stddev = 351.50
            median = 50.00
              75% <= 284.00
              95% <= 1343.00
              98% <= 1343.00
              99% <= 1343.00
            99.9% <= 1343.00
             count = 16

  remote-requests:
    count = 0

  requests-received:
             count = 16
         mean rate = 82.04 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 16
         mean rate = 82.02 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 3

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 3

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 2,251.00
               min = 16.00
               max = 725.00
              mean = 140.69
            stddev = 206.66
            median = 34.50
              75% <= 302.25
              95% <= 725.00
              98% <= 725.00
              99% <= 725.00
            99.9% <= 725.00
             count = 16

  superstep-gc-time-ms:
    count = 18

  superstep-time-ms:
    value = 177

  time-to-first-message-ms:
    value = 0

  total-requests:
    value = 0

  wait-per-thread-ms:
               sum = 0.00
               min = 0.00
               max = 0.00
              mean = 0.00
            stddev = 0.00
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 0.00
              99% <= 0.00
            99.9% <= 0.00
             count = 16

  wait-requests-us:
    value = 8

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0
`;
