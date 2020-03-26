// tslint:disable:no-expression-statement no-object-mutation
import test, { before } from 'ava';
import { fileSync, FileResult } from 'tmp';
import { writeFileSync } from 'fs';
import { extractAllSupersteps } from './extract-all-supersteps';
import { Worker } from 'simulation';
import { createProblem } from './problem';

var file: FileResult

before(() => {
    file = fileSync()
    writeFileSync(file.name, problem192WorkerLogs)
})

test('can extract all superstep from a single worker', t => {
    const simulation = createProblem(192)
    const worker: Worker = {
        id: "1",
        logsPath: file.name
    }
    t.snapshot(extractAllSupersteps(simulation, worker));
});

const problem192WorkerLogs = `
End of LogType:prelaunch.err
******************************************************************************

Container: container_1584864522397_0046_01_000003 on iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_37469
LogAggregationType: AGGREGATED
====================================================================================================================
LogType:prelaunch.out
LogLastModifiedTime:Sun Mar 22 12:34:06 +0000 2020
LogLength:70
LogContents:
Setting up env variables
Setting up job resources
Launching container

End of LogType:prelaunch.out
******************************************************************************

Container: container_1584864522397_0046_01_000003 on iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_37469
LogAggregationType: AGGREGATED
====================================================================================================================
LogType:task-3-stderr.log
LogLastModifiedTime:Sun Mar 22 12:34:06 +0000 2020
LogLength:239629
LogContents:
SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [jar:file:/hadoop/yarn/nm-local-dir/usercache/kbhit/appcache/application_1584864522397_0046/filecache/11/solver-1.0-SNAPSHOT.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [jar:file:/usr/lib/hadoop/lib/slf4j-log4j12-1.7.25.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
SLF4J: Actual binding is of type [org.slf4j.impl.Log4jLoggerFactory]

--- METRICS: superstep -1 ---
  superstep time: 8520 ms
  compute all partitions: 0 ms
  time spent in gc: 131 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 376 us

3/22/20 12:33:52 PM ============================================================
giraph.superstep.-1:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 0

  local-requests:
    count = 98

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = 100.0

  received-bytes:
               sum = 20,900.00
               min = 16.00
               max = 1155.00
              mean = 40.19
            stddev = 73.23
            median = 20.50
              75% <= 87.00
              95% <= 87.00
              98% <= 87.00
              99% <= 87.00
            99.9% <= 1155.00
             count = 520

  remote-requests:
    count = 0

  requests-received:
             count = 520
         mean rate = 60.94 requests/s
     1-minute rate = 0.40 requests/s
     5-minute rate = 0.40 requests/s
    15-minute rate = 0.40 requests/s

  requests-sent:
             count = 520
         mean rate = 60.95 requests/s
     1-minute rate = 0.40 requests/s
     5-minute rate = 0.40 requests/s
    15-minute rate = 0.40 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 13,582.00
               min = 16.00
               max = 2837.00
              mean = 26.12
            stddev = 123.69
            median = 20.50
              75% <= 25.00
              95% <= 25.00
              98% <= 25.00
              99% <= 25.00
            99.9% <= 2837.00
             count = 520

  superstep-gc-time-ms:
    count = 131

  superstep-time-ms:
    value = 8520

  time-to-first-message-ms:
    value = 0

  total-requests:
    value = 98

  wait-requests-us:
    value = 376

  worker-context-post-superstep:
    value = 0

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 0 ---
  superstep time: 62 ms
  compute all partitions: 41 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 11 us

3/22/20 12:33:52 PM ============================================================
giraph.superstep.0:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 41

  compute-per-partition-ms:
               sum = 867.00
               min = 0.00
               max = 34.00
              mean = 13.55
            stddev = 9.55
            median = 14.00
              75% <= 21.75
              95% <= 29.50
              98% <= 33.40
              99% <= 34.00
            99.9% <= 34.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 192

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 867.00
               min = 0.00
               max = 34.00
              mean = 13.55
            stddev = 9.58
            median = 14.50
              75% <= 21.75
              95% <= 29.50
              98% <= 33.40
              99% <= 34.00
            99.9% <= 34.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 50.81 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 51.45 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 62

  time-to-first-message-ms:
    value = 0

  total-requests:
    value = 0

  wait-per-thread-ms:
               sum = 2.00
               min = 0.00
               max = 1.00
              mean = 0.03
            stddev = 0.18
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 1.00
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  wait-requests-us:
    value = 11

  worker-context-post-superstep:
    value = 7

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 1 ---
  superstep time: 100 ms
  compute all partitions: 83 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 9 us

3/22/20 12:33:52 PM ============================================================
giraph.superstep.1:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 83

  compute-per-partition-ms:
               sum = 3,262.00
               min = 21.00
               max = 82.00
              mean = 50.97
            stddev = 15.54
            median = 51.00
              75% <= 59.75
              95% <= 77.75
              98% <= 80.80
              99% <= 82.00
            99.9% <= 82.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

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
               sum = 3,262.00
               min = 21.00
               max = 82.00
              mean = 50.97
            stddev = 15.54
            median = 51.00
              75% <= 59.75
              95% <= 77.75
              98% <= 80.80
              99% <= 82.00
            99.9% <= 82.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 35.21 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 35.20 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 100

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
             count = 64

  wait-requests-us:
    value = 9

  worker-context-post-superstep:
    value = 2

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 2 ---
  superstep time: 61 ms
  compute all partitions: 40 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 13 us

3/22/20 12:33:52 PM ============================================================
giraph.superstep.2:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 40

  compute-per-partition-ms:
               sum = 117.00
               min = 0.00
               max = 34.00
              mean = 1.83
            stddev = 4.33
            median = 1.00
              75% <= 2.00
              95% <= 5.75
              98% <= 25.60
              99% <= 34.00
            99.9% <= 34.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 32

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 115.00
               min = 0.00
               max = 34.00
              mean = 1.80
            stddev = 4.86
            median = 0.00
              75% <= 0.00
              95% <= 8.75
              98% <= 26.50
              99% <= 34.00
            99.9% <= 34.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 54.10 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 54.38 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 61

  time-to-first-message-ms:
    value = 0

  total-requests:
    value = 0

  wait-per-thread-ms:
               sum = 1.00
               min = 0.00
               max = 1.00
              mean = 0.02
            stddev = 0.12
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 0.70
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  wait-requests-us:
    value = 13

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 3 ---
  superstep time: 59 ms
  compute all partitions: 39 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 8 us

3/22/20 12:33:52 PM ============================================================
giraph.superstep.3:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 39

  compute-per-partition-ms:
               sum = 35.00
               min = 0.00
               max = 16.00
              mean = 0.55
            stddev = 2.14
            median = 0.00
              75% <= 0.00
              95% <= 3.00
              98% <= 12.70
              99% <= 16.00
            99.9% <= 16.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 16

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 35.00
               min = 0.00
               max = 16.00
              mean = 0.55
            stddev = 2.22
            median = 0.00
              75% <= 0.00
              95% <= 4.00
              98% <= 12.70
              99% <= 16.00
            99.9% <= 16.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 56.92 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 56.93 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 59

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
             count = 64

  wait-requests-us:
    value = 8

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 4 ---
  superstep time: 57 ms
  compute all partitions: 36 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 8 us

3/22/20 12:33:52 PM ============================================================
giraph.superstep.4:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 36

  compute-per-partition-ms:
               sum = 5.00
               min = 0.00
               max = 3.00
              mean = 0.08
            stddev = 0.41
            median = 0.00
              75% <= 0.00
              95% <= 0.75
              98% <= 2.40
              99% <= 3.00
            99.9% <= 3.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 8

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 5.00
               min = 0.00
               max = 3.00
              mean = 0.08
            stddev = 0.45
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 2.70
              99% <= 3.00
            99.9% <= 3.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 58.97 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 58.94 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 57

  time-to-first-message-ms:
    value = 0

  total-requests:
    value = 0

  wait-per-thread-ms:
               sum = 1.00
               min = 0.00
               max = 1.00
              mean = 0.02
            stddev = 0.13
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 0.70
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  wait-requests-us:
    value = 8

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 5 ---
  superstep time: 52 ms
  compute all partitions: 32 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 17 us

3/22/20 12:33:52 PM ============================================================
giraph.superstep.5:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 32

  compute-per-partition-ms:
               sum = 5.00
               min = 0.00
               max = 3.00
              mean = 0.08
            stddev = 0.41
            median = 0.00
              75% <= 0.00
              95% <= 0.75
              98% <= 2.40
              99% <= 3.00
            99.9% <= 3.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 4

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 5.00
               min = 0.00
               max = 3.00
              mean = 0.08
            stddev = 0.45
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 2.70
              99% <= 3.00
            99.9% <= 3.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 61.57 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 61.47 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 52

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
             count = 64

  wait-requests-us:
    value = 17

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 6 ---
  superstep time: 49 ms
  compute all partitions: 32 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 10 us

3/22/20 12:33:52 PM ============================================================
giraph.superstep.6:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 32

  compute-per-partition-ms:
               sum = 5.00
               min = 0.00
               max = 2.00
              mean = 0.08
            stddev = 0.32
            median = 0.00
              75% <= 0.00
              95% <= 1.00
              98% <= 1.70
              99% <= 2.00
            99.9% <= 2.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 2

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 5.00
               min = 0.00
               max = 2.00
              mean = 0.08
            stddev = 0.32
            median = 0.00
              75% <= 0.00
              95% <= 1.00
              98% <= 1.70
              99% <= 2.00
            99.9% <= 2.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 64.92 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 64.93 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 49

  time-to-first-message-ms:
    value = 0

  total-requests:
    value = 0

  wait-per-thread-ms:
               sum = 1.00
               min = 0.00
               max = 1.00
              mean = 0.02
            stddev = 0.12
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 0.70
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  wait-requests-us:
    value = 10

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 7 ---
  superstep time: 49 ms
  compute all partitions: 32 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 11 us

3/22/20 12:33:52 PM ============================================================
giraph.superstep.7:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 32

  compute-per-partition-ms:
               sum = 4.00
               min = 0.00
               max = 3.00
              mean = 0.06
            stddev = 0.39
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 2.40
              99% <= 3.00
            99.9% <= 3.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 2

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 4.00
               min = 0.00
               max = 3.00
              mean = 0.06
            stddev = 0.39
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 2.40
              99% <= 3.00
            99.9% <= 3.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 65.19 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 64.96 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 49

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
             count = 64

  wait-requests-us:
    value = 11

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 8 ---
  superstep time: 46 ms
  compute all partitions: 31 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 8 us

3/22/20 12:33:52 PM ============================================================
giraph.superstep.8:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 31

  compute-per-partition-ms:
               sum = 92.00
               min = 0.00
               max = 13.00
              mean = 1.44
            stddev = 3.69
            median = 0.00
              75% <= 0.00
              95% <= 11.75
              98% <= 12.70
              99% <= 13.00
            99.9% <= 13.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 4

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 92.00
               min = 0.00
               max = 13.00
              mean = 1.44
            stddev = 3.70
            median = 0.00
              75% <= 0.00
              95% <= 11.75
              98% <= 12.70
              99% <= 13.00
            99.9% <= 13.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 69.65 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 69.56 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 46

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
             count = 64

  wait-requests-us:
    value = 8

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 9 ---
  superstep time: 47 ms
  compute all partitions: 28 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 8 us

3/22/20 12:33:52 PM ============================================================
giraph.superstep.9:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 28

  compute-per-partition-ms:
               sum = 52.00
               min = 0.00
               max = 9.00
              mean = 0.81
            stddev = 2.34
            median = 0.00
              75% <= 0.00
              95% <= 8.00
              98% <= 9.00
              99% <= 9.00
            99.9% <= 9.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 8

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 52.00
               min = 0.00
               max = 9.00
              mean = 0.81
            stddev = 2.36
            median = 0.00
              75% <= 0.00
              95% <= 8.00
              98% <= 9.00
              99% <= 9.00
            99.9% <= 9.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 70.63 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 70.59 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 47

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
             count = 64

  wait-requests-us:
    value = 8

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 10 ---
  superstep time: 49 ms
  compute all partitions: 29 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 9 us

3/22/20 12:33:52 PM ============================================================
giraph.superstep.10:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 29

  compute-per-partition-ms:
               sum = 149.00
               min = 0.00
               max = 11.00
              mean = 2.33
            stddev = 3.65
            median = 0.00
              75% <= 5.00
              95% <= 10.00
              98% <= 11.00
              99% <= 11.00
            99.9% <= 11.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 16

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 149.00
               min = 0.00
               max = 11.00
              mean = 2.33
            stddev = 3.65
            median = 0.00
              75% <= 5.00
              95% <= 10.00
              98% <= 11.00
              99% <= 11.00
            99.9% <= 11.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 68.33 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 68.33 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 49

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
             count = 64

  wait-requests-us:
    value = 9

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 11 ---
  superstep time: 46 ms
  compute all partitions: 28 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 8 us

3/22/20 12:33:52 PM ============================================================
giraph.superstep.11:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 28

  compute-per-partition-ms:
               sum = 64.00
               min = 0.00
               max = 10.00
              mean = 1.00
            stddev = 2.56
            median = 0.00
              75% <= 0.00
              95% <= 8.00
              98% <= 9.70
              99% <= 10.00
            99.9% <= 10.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 32

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 63.00
               min = 0.00
               max = 10.00
              mean = 0.98
            stddev = 2.57
            median = 0.00
              75% <= 0.00
              95% <= 8.00
              98% <= 9.70
              99% <= 10.00
            99.9% <= 10.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 71.22 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 71.17 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 46

  time-to-first-message-ms:
    value = 0

  total-requests:
    value = 0

  wait-per-thread-ms:
               sum = 1.00
               min = 0.00
               max = 1.00
              mean = 0.02
            stddev = 0.13
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 0.70
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  wait-requests-us:
    value = 8

  worker-context-post-superstep:
    value = 10

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 12 ---
  superstep time: 48 ms
  compute all partitions: 29 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 11 us

3/22/20 12:33:52 PM ============================================================
giraph.superstep.12:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 29

  compute-per-partition-ms:
               sum = 21.00
               min = 0.00
               max = 14.00
              mean = 0.33
            stddev = 1.77
            median = 0.00
              75% <= 0.00
              95% <= 1.00
              98% <= 10.40
              99% <= 14.00
            99.9% <= 14.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

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
               sum = 21.00
               min = 0.00
               max = 14.00
              mean = 0.33
            stddev = 1.77
            median = 0.00
              75% <= 0.00
              95% <= 1.00
              98% <= 10.40
              99% <= 14.00
            99.9% <= 14.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 67.71 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 67.69 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 48

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
             count = 64

  wait-requests-us:
    value = 11

  worker-context-post-superstep:
    value = 2

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 13 ---
  superstep time: 70 ms
  compute all partitions: 47 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 9 us

3/22/20 12:33:53 PM ============================================================
giraph.superstep.13:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 47

  compute-per-partition-ms:
               sum = 1,628.00
               min = 11.00
               max = 40.00
              mean = 25.44
            stddev = 7.41
            median = 26.50
              75% <= 30.75
              95% <= 38.00
              98% <= 39.70
              99% <= 40.00
            99.9% <= 40.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 0

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 1,628.00
               min = 0.00
               max = 40.00
              mean = 25.44
            stddev = 8.36
            median = 27.50
              75% <= 31.00
              95% <= 38.00
              98% <= 39.70
              99% <= 40.00
            99.9% <= 40.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 49.43 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 49.40 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 70

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
             count = 64

  wait-requests-us:
    value = 9

  worker-context-post-superstep:
    value = 2

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 14 ---
  superstep time: 67 ms
  compute all partitions: 45 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 10 us

3/22/20 12:33:53 PM ============================================================
giraph.superstep.14:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 45

  compute-per-partition-ms:
               sum = 1,091.00
               min = 5.00
               max = 24.00
              mean = 17.05
            stddev = 6.24
            median = 19.50
              75% <= 22.00
              95% <= 23.75
              98% <= 24.00
              99% <= 24.00
            99.9% <= 24.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 12288

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 1,091.00
               min = 0.00
               max = 24.00
              mean = 17.05
            stddev = 7.75
            median = 20.50
              75% <= 22.75
              95% <= 24.00
              98% <= 24.00
              99% <= 24.00
            99.9% <= 24.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 51.25 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 51.22 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 67

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
             count = 64

  wait-requests-us:
    value = 10

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 15 ---
  superstep time: 91 ms
  compute all partitions: 72 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 9 us

3/22/20 12:33:53 PM ============================================================
giraph.superstep.15:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 72

  compute-per-partition-ms:
               sum = 3,221.00
               min = 34.00
               max = 60.00
              mean = 50.33
            stddev = 6.99
            median = 52.50
              75% <= 56.00
              95% <= 58.00
              98% <= 59.40
              99% <= 60.00
            99.9% <= 60.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 0

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 3,216.00
               min = 34.00
               max = 60.00
              mean = 50.25
            stddev = 6.94
            median = 52.50
              75% <= 56.00
              95% <= 58.00
              98% <= 59.40
              99% <= 60.00
            99.9% <= 60.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 39.12 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 39.10 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 91

  time-to-first-message-ms:
    value = 0

  total-requests:
    value = 0

  wait-per-thread-ms:
               sum = 5.00
               min = 0.00
               max = 1.00
              mean = 0.08
            stddev = 0.27
            median = 0.00
              75% <= 0.00
              95% <= 1.00
              98% <= 1.00
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  wait-requests-us:
    value = 9

  worker-context-post-superstep:
    value = 2

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 16 ---
  superstep time: 49 ms
  compute all partitions: 32 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 8 us

3/22/20 12:33:53 PM ============================================================
giraph.superstep.16:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 32

  compute-per-partition-ms:
               sum = 8.00
               min = 0.00
               max = 1.00
              mean = 0.13
            stddev = 0.33
            median = 0.00
              75% <= 0.00
              95% <= 1.00
              98% <= 1.00
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 192

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 8.00
               min = 0.00
               max = 4.00
              mean = 0.13
            stddev = 0.70
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 4.00
              99% <= 4.00
            99.9% <= 4.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 66.33 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 66.28 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 49

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
             count = 64

  wait-requests-us:
    value = 8

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 17 ---
  superstep time: 55 ms
  compute all partitions: 35 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 8 us

3/22/20 12:33:53 PM ============================================================
giraph.superstep.17:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 35

  compute-per-partition-ms:
               sum = 33.00
               min = 0.00
               max = 3.00
              mean = 0.52
            stddev = 0.96
            median = 0.00
              75% <= 1.00
              95% <= 3.00
              98% <= 3.00
              99% <= 3.00
            99.9% <= 3.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

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
               sum = 33.00
               min = 0.00
               max = 5.00
              mean = 0.52
            stddev = 1.23
            median = 0.00
              75% <= 0.00
              95% <= 3.00
              98% <= 5.00
              99% <= 5.00
            99.9% <= 5.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 61.28 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 61.26 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 55

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
             count = 64

  wait-requests-us:
    value = 8

  worker-context-post-superstep:
    value = 2

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 18 ---
  superstep time: 54 ms
  compute all partitions: 34 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 13 us

3/22/20 12:33:53 PM ============================================================
giraph.superstep.18:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 34

  compute-per-partition-ms:
               sum = 19.00
               min = 0.00
               max = 18.00
              mean = 0.30
            stddev = 2.25
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 12.90
              99% <= 18.00
            99.9% <= 18.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 32

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 19.00
               min = 0.00
               max = 18.00
              mean = 0.30
            stddev = 2.25
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 12.90
              99% <= 18.00
            99.9% <= 18.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 63.27 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 63.21 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 54

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
             count = 64

  wait-requests-us:
    value = 13

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 19 ---
  superstep time: 57 ms
  compute all partitions: 38 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 9 us

3/22/20 12:33:53 PM ============================================================
giraph.superstep.19:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 38

  compute-per-partition-ms:
               sum = 3.00
               min = 0.00
               max = 2.00
              mean = 0.05
            stddev = 0.28
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 1.70
              99% <= 2.00
            99.9% <= 2.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 16

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 3.00
               min = 0.00
               max = 2.00
              mean = 0.05
            stddev = 0.28
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 1.70
              99% <= 2.00
            99.9% <= 2.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 60.06 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 59.97 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 57

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
             count = 64

  wait-requests-us:
    value = 9

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 20 ---
  superstep time: 51 ms
  compute all partitions: 31 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 9 us

3/22/20 12:33:53 PM ============================================================
giraph.superstep.20:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 31

  compute-per-partition-ms:
               sum = 6.00
               min = 0.00
               max = 2.00
              mean = 0.09
            stddev = 0.39
            median = 0.00
              75% <= 0.00
              95% <= 1.00
              98% <= 2.00
              99% <= 2.00
            99.9% <= 2.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 8

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 6.00
               min = 0.00
               max = 2.00
              mean = 0.09
            stddev = 0.39
            median = 0.00
              75% <= 0.00
              95% <= 1.00
              98% <= 2.00
              99% <= 2.00
            99.9% <= 2.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 66.39 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 66.33 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 51

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
             count = 64

  wait-requests-us:
    value = 9

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 21 ---
  superstep time: 48 ms
  compute all partitions: 28 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 9 us

3/22/20 12:33:53 PM ============================================================
giraph.superstep.21:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 28

  compute-per-partition-ms:
               sum = 13.00
               min = 0.00
               max = 11.00
              mean = 0.20
            stddev = 1.39
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 8.30
              99% <= 11.00
            99.9% <= 11.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 4

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 13.00
               min = 0.00
               max = 11.00
              mean = 0.20
            stddev = 1.39
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 8.30
              99% <= 11.00
            99.9% <= 11.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 68.20 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 68.12 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 48

  time-to-first-message-ms:
    value = 0

  total-requests:
    value = 0

  wait-per-thread-ms:
               sum = 1.00
               min = 0.00
               max = 1.00
              mean = 0.02
            stddev = 0.13
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 0.70
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  wait-requests-us:
    value = 9

  worker-context-post-superstep:
    value = 2

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 22 ---
  superstep time: 62 ms
  compute all partitions: 41 ms
  time spent in gc: 9 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 10 us

3/22/20 12:33:53 PM ============================================================
giraph.superstep.22:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 41

  compute-per-partition-ms:
               sum = 3.00
               min = 0.00
               max = 1.00
              mean = 0.05
            stddev = 0.21
            median = 0.00
              75% <= 0.00
              95% <= 0.75
              98% <= 1.00
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 2

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 3.00
               min = 0.00
               max = 1.00
              mean = 0.05
            stddev = 0.21
            median = 0.00
              75% <= 0.00
              95% <= 0.75
              98% <= 1.00
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 55.12 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 55.10 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 9

  superstep-time-ms:
    value = 62

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
             count = 64

  wait-requests-us:
    value = 10

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 23 ---
  superstep time: 45 ms
  compute all partitions: 27 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 10 us

3/22/20 12:33:53 PM ============================================================
giraph.superstep.23:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 27

  compute-per-partition-ms:
               sum = 6.00
               min = 0.00
               max = 2.00
              mean = 0.09
            stddev = 0.34
            median = 0.00
              75% <= 0.00
              95% <= 1.00
              98% <= 1.70
              99% <= 2.00
            99.9% <= 2.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 2

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 6.00
               min = 0.00
               max = 2.00
              mean = 0.09
            stddev = 0.39
            median = 0.00
              75% <= 0.00
              95% <= 1.00
              98% <= 2.00
              99% <= 2.00
            99.9% <= 2.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 70.80 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 70.89 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 45

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
             count = 64

  wait-requests-us:
    value = 10

  worker-context-post-superstep:
    value = 2

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 24 ---
  superstep time: 39 ms
  compute all partitions: 20 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 6 us

3/22/20 12:33:53 PM ============================================================
giraph.superstep.24:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 20

  compute-per-partition-ms:
               sum = 1.00
               min = 0.00
               max = 1.00
              mean = 0.02
            stddev = 0.13
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 0.70
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 4

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 1.00
               min = 0.00
               max = 1.00
              mean = 0.02
            stddev = 0.12
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 0.70
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 78.97 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 78.86 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 39

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
             count = 64

  wait-requests-us:
    value = 6

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 25 ---
  superstep time: 46 ms
  compute all partitions: 28 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 10 us

3/22/20 12:33:53 PM ============================================================
giraph.superstep.25:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 28

  compute-per-partition-ms:
               sum = 14.00
               min = 0.00
               max = 10.00
              mean = 0.22
            stddev = 1.28
            median = 0.00
              75% <= 0.00
              95% <= 1.00
              98% <= 7.60
              99% <= 10.00
            99.9% <= 10.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 8

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 14.00
               min = 0.00
               max = 10.00
              mean = 0.22
            stddev = 1.28
            median = 0.00
              75% <= 0.00
              95% <= 1.00
              98% <= 7.60
              99% <= 10.00
            99.9% <= 10.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 73.06 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 72.99 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 46

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
             count = 64

  wait-requests-us:
    value = 10

  worker-context-post-superstep:
    value = 2

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 26 ---
  superstep time: 45 ms
  compute all partitions: 27 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 10 us

3/22/20 12:33:53 PM ============================================================
giraph.superstep.26:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 27

  compute-per-partition-ms:
               sum = 15.00
               min = 0.00
               max = 11.00
              mean = 0.23
            stddev = 1.39
            median = 0.00
              75% <= 0.00
              95% <= 1.00
              98% <= 8.00
              99% <= 11.00
            99.9% <= 11.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 16

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 15.00
               min = 0.00
               max = 11.00
              mean = 0.23
            stddev = 1.39
            median = 0.00
              75% <= 0.00
              95% <= 1.00
              98% <= 8.00
              99% <= 11.00
            99.9% <= 11.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 73.07 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 73.00 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 45

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
             count = 64

  wait-requests-us:
    value = 10

  worker-context-post-superstep:
    value = 2

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 27 ---
  superstep time: 45 ms
  compute all partitions: 26 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 10 us

3/22/20 12:33:53 PM ============================================================
giraph.superstep.27:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 26

  compute-per-partition-ms:
               sum = 3.00
               min = 0.00
               max = 2.00
              mean = 0.05
            stddev = 0.28
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 1.70
              99% <= 2.00
            99.9% <= 2.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 32

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 3.00
               min = 0.00
               max = 2.00
              mean = 0.05
            stddev = 0.28
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 1.70
              99% <= 2.00
            99.9% <= 2.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 71.67 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 71.62 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 45

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
             count = 64

  wait-requests-us:
    value = 10

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 28 ---
  superstep time: 45 ms
  compute all partitions: 25 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 11 us

3/22/20 12:33:54 PM ============================================================
giraph.superstep.28:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 25

  compute-per-partition-ms:
               sum = 8.00
               min = 0.00
               max = 5.00
              mean = 0.13
            stddev = 0.65
            median = 0.00
              75% <= 0.00
              95% <= 1.00
              98% <= 3.80
              99% <= 5.00
            99.9% <= 5.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

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
               sum = 7.00
               min = 0.00
               max = 5.00
              mean = 0.11
            stddev = 0.65
            median = 0.00
              75% <= 0.00
              95% <= 0.75
              98% <= 3.80
              99% <= 5.00
            99.9% <= 5.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 72.73 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 72.66 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 45

  time-to-first-message-ms:
    value = 0

  total-requests:
    value = 0

  wait-per-thread-ms:
               sum = 1.00
               min = 0.00
               max = 1.00
              mean = 0.02
            stddev = 0.13
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 0.70
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  wait-requests-us:
    value = 11

  worker-context-post-superstep:
    value = 2

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 29 ---
  superstep time: 42 ms
  compute all partitions: 24 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 17 us

3/22/20 12:33:54 PM ============================================================
giraph.superstep.29:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 24

  compute-per-partition-ms:
               sum = 114.00
               min = 0.00
               max = 9.00
              mean = 1.78
            stddev = 3.05
            median = 0.00
              75% <= 1.00
              95% <= 8.00
              98% <= 9.00
              99% <= 9.00
            99.9% <= 9.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 0

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 114.00
               min = 0.00
               max = 9.00
              mean = 1.78
            stddev = 3.16
            median = 0.00
              75% <= 3.75
              95% <= 8.00
              98% <= 9.00
              99% <= 9.00
            99.9% <= 9.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 74.13 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 74.07 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 42

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
             count = 64

  wait-requests-us:
    value = 17

  worker-context-post-superstep:
    value = 2

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 30 ---
  superstep time: 51 ms
  compute all partitions: 34 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 10 us

3/22/20 12:33:54 PM ============================================================
giraph.superstep.30:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 34

  compute-per-partition-ms:
               sum = 423.00
               min = 0.00
               max = 16.00
              mean = 6.61
            stddev = 5.25
            median = 7.00
              75% <= 10.00
              95% <= 15.00
              98% <= 16.00
              99% <= 16.00
            99.9% <= 16.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 318

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 422.00
               min = 0.00
               max = 16.00
              mean = 6.59
            stddev = 5.25
            median = 7.00
              75% <= 10.00
              95% <= 15.00
              98% <= 15.70
              99% <= 16.00
            99.9% <= 16.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 66.11 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 66.06 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 51

  time-to-first-message-ms:
    value = 0

  total-requests:
    value = 0

  wait-per-thread-ms:
               sum = 1.00
               min = 0.00
               max = 1.00
              mean = 0.02
            stddev = 0.13
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 0.70
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  wait-requests-us:
    value = 10

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 31 ---
  superstep time: 400 ms
  compute all partitions: 382 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 9 us

3/22/20 12:33:54 PM ============================================================
giraph.superstep.31:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 382

  compute-per-partition-ms:
               sum = 20,364.00
               min = 238.00
               max = 372.00
              mean = 318.19
            stddev = 35.43
            median = 315.50
              75% <= 349.75
              95% <= 366.00
              98% <= 371.40
              99% <= 372.00
            99.9% <= 372.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 0

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 20,364.00
               min = 238.00
               max = 372.00
              mean = 318.19
            stddev = 35.43
            median = 315.50
              75% <= 349.75
              95% <= 366.00
              98% <= 371.40
              99% <= 372.00
            99.9% <= 372.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 9.73 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 9.72 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 400

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
             count = 64

  wait-requests-us:
    value = 9

  worker-context-post-superstep:
    value = 2

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 32 ---
  superstep time: 47 ms
  compute all partitions: 30 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 9 us

3/22/20 12:33:54 PM ============================================================
giraph.superstep.32:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 30

  compute-per-partition-ms:
               sum = 5.00
               min = 0.00
               max = 1.00
              mean = 0.08
            stddev = 0.27
            median = 0.00
              75% <= 0.00
              95% <= 1.00
              98% <= 1.00
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 192

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 5.00
               min = 0.00
               max = 2.00
              mean = 0.08
            stddev = 0.32
            median = 0.00
              75% <= 0.00
              95% <= 1.00
              98% <= 1.70
              99% <= 2.00
            99.9% <= 2.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 68.80 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 68.64 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 47

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
             count = 64

  wait-requests-us:
    value = 9

  worker-context-post-superstep:
    value = 2

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 33 ---
  superstep time: 39 ms
  compute all partitions: 21 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 11 us

3/22/20 12:33:54 PM ============================================================
giraph.superstep.33:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 21

  compute-per-partition-ms:
               sum = 11.00
               min = 0.00
               max = 1.00
              mean = 0.17
            stddev = 0.38
            median = 0.00
              75% <= 0.00
              95% <= 1.00
              98% <= 1.00
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

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
               sum = 11.00
               min = 0.00
               max = 2.00
              mean = 0.17
            stddev = 0.49
            median = 0.00
              75% <= 0.00
              95% <= 1.75
              98% <= 2.00
              99% <= 2.00
            99.9% <= 2.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 81.53 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 81.29 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 39

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
             count = 64

  wait-requests-us:
    value = 11

  worker-context-post-superstep:
    value = 2

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 34 ---
  superstep time: 35 ms
  compute all partitions: 20 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 10 us

3/22/20 12:33:54 PM ============================================================
giraph.superstep.34:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 20

  compute-per-partition-ms:
               sum = 6.00
               min = 0.00
               max = 5.00
              mean = 0.09
            stddev = 0.64
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 3.80
              99% <= 5.00
            99.9% <= 5.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 32

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 6.00
               min = 0.00
               max = 5.00
              mean = 0.09
            stddev = 0.64
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 3.80
              99% <= 5.00
            99.9% <= 5.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 88.98 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 88.84 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 35

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
             count = 64

  wait-requests-us:
    value = 10

  worker-context-post-superstep:
    value = 2

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 35 ---
  superstep time: 33 ms
  compute all partitions: 18 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 8 us

3/22/20 12:33:54 PM ============================================================
giraph.superstep.35:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 18

  compute-per-partition-ms:
               sum = 4.00
               min = 0.00
               max = 3.00
              mean = 0.06
            stddev = 0.39
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 2.40
              99% <= 3.00
            99.9% <= 3.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 16

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 4.00
               min = 0.00
               max = 3.00
              mean = 0.06
            stddev = 0.39
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 2.40
              99% <= 3.00
            99.9% <= 3.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 93.97 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 93.92 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 33

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
             count = 64

  wait-requests-us:
    value = 8

  worker-context-post-superstep:
    value = 2

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 36 ---
  superstep time: 35 ms
  compute all partitions: 16 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 9 us

3/22/20 12:33:54 PM ============================================================
giraph.superstep.36:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 16

  compute-per-partition-ms:
               sum = 3.00
               min = 0.00
               max = 2.00
              mean = 0.05
            stddev = 0.28
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 1.70
              99% <= 2.00
            99.9% <= 2.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 8

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 3.00
               min = 0.00
               max = 2.00
              mean = 0.05
            stddev = 0.28
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 1.70
              99% <= 2.00
            99.9% <= 2.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 88.65 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 88.48 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 35

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
             count = 64

  wait-requests-us:
    value = 9

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 37 ---
  superstep time: 40 ms
  compute all partitions: 23 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 9 us

3/22/20 12:33:54 PM ============================================================
giraph.superstep.37:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 23

  compute-per-partition-ms:
               sum = 2.00
               min = 0.00
               max = 1.00
              mean = 0.03
            stddev = 0.18
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 1.00
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 4

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 2.00
               min = 0.00
               max = 1.00
              mean = 0.03
            stddev = 0.18
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 1.00
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 81.35 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 81.30 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 40

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
             count = 64

  wait-requests-us:
    value = 9

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 38 ---
  superstep time: 38 ms
  compute all partitions: 20 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 7 us

3/22/20 12:33:54 PM ============================================================
giraph.superstep.38:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 20

  compute-per-partition-ms:
               sum = 3.00
               min = 0.00
               max = 2.00
              mean = 0.05
            stddev = 0.28
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 1.70
              99% <= 2.00
            99.9% <= 2.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 2

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 3.00
               min = 0.00
               max = 2.00
              mean = 0.05
            stddev = 0.28
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 1.70
              99% <= 2.00
            99.9% <= 2.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 83.07 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 82.94 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 38

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
             count = 64

  wait-requests-us:
    value = 7

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 39 ---
  superstep time: 41 ms
  compute all partitions: 22 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 10 us

3/22/20 12:33:54 PM ============================================================
giraph.superstep.39:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 22

  compute-per-partition-ms:
               sum = 2.00
               min = 0.00
               max = 1.00
              mean = 0.03
            stddev = 0.18
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 1.00
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 2

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 1.00
               min = 0.00
               max = 1.00
              mean = 0.02
            stddev = 0.12
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 0.70
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 76.30 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 76.14 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 41

  time-to-first-message-ms:
    value = 0

  total-requests:
    value = 0

  wait-per-thread-ms:
               sum = 1.00
               min = 0.00
               max = 1.00
              mean = 0.02
            stddev = 0.12
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 0.70
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  wait-requests-us:
    value = 10

  worker-context-post-superstep:
    value = 2

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 40 ---
  superstep time: 34 ms
  compute all partitions: 19 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 12 us

3/22/20 12:33:54 PM ============================================================
giraph.superstep.40:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 19

  compute-per-partition-ms:
               sum = 1.00
               min = 0.00
               max = 1.00
              mean = 0.02
            stddev = 0.12
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 0.70
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 4

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 1.00
               min = 0.00
               max = 1.00
              mean = 0.02
            stddev = 0.12
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 0.70
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 86.26 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 86.10 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 34

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
             count = 64

  wait-requests-us:
    value = 12

  worker-context-post-superstep:
    value = 2

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 41 ---
  superstep time: 36 ms
  compute all partitions: 19 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 9 us

3/22/20 12:33:55 PM ============================================================
giraph.superstep.41:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 19

  compute-per-partition-ms:
               sum = 3.00
               min = 0.00
               max = 1.00
              mean = 0.05
            stddev = 0.21
            median = 0.00
              75% <= 0.00
              95% <= 0.75
              98% <= 1.00
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 8

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 3.00
               min = 0.00
               max = 1.00
              mean = 0.05
            stddev = 0.21
            median = 0.00
              75% <= 0.00
              95% <= 0.75
              98% <= 1.00
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 86.03 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 85.89 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 36

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
             count = 64

  wait-requests-us:
    value = 9

  worker-context-post-superstep:
    value = 2

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 42 ---
  superstep time: 33 ms
  compute all partitions: 16 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 8 us

3/22/20 12:33:55 PM ============================================================
giraph.superstep.42:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 16

  compute-per-partition-ms:
               sum = 2.00
               min = 0.00
               max = 2.00
              mean = 0.03
            stddev = 0.25
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 1.40
              99% <= 2.00
            99.9% <= 2.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 16

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 2.00
               min = 0.00
               max = 2.00
              mean = 0.03
            stddev = 0.25
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 1.40
              99% <= 2.00
            99.9% <= 2.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 94.84 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 94.75 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 33

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
             count = 64

  wait-requests-us:
    value = 8

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 43 ---
  superstep time: 36 ms
  compute all partitions: 18 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 8 us

3/22/20 12:33:55 PM ============================================================
giraph.superstep.43:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 18

  compute-per-partition-ms:
               sum = 2.00
               min = 0.00
               max = 2.00
              mean = 0.03
            stddev = 0.25
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 1.40
              99% <= 2.00
            99.9% <= 2.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 32

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 2.00
               min = 0.00
               max = 2.00
              mean = 0.03
            stddev = 0.25
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 1.40
              99% <= 2.00
            99.9% <= 2.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 89.06 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 88.96 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 36

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
             count = 64

  wait-requests-us:
    value = 8

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 44 ---
  superstep time: 34 ms
  compute all partitions: 16 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 8 us

3/22/20 12:33:55 PM ============================================================
giraph.superstep.44:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 16

  compute-per-partition-ms:
               sum = 4.00
               min = 0.00
               max = 4.00
              mean = 0.06
            stddev = 0.50
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 2.80
              99% <= 4.00
            99.9% <= 4.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

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
               sum = 4.00
               min = 0.00
               max = 4.00
              mean = 0.06
            stddev = 0.50
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 2.80
              99% <= 4.00
            99.9% <= 4.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 94.57 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 94.45 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 34

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
             count = 64

  wait-requests-us:
    value = 8

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 45 ---
  superstep time: 41 ms
  compute all partitions: 23 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 8 us

3/22/20 12:33:55 PM ============================================================
giraph.superstep.45:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 23

  compute-per-partition-ms:
               sum = 7.00
               min = 0.00
               max = 1.00
              mean = 0.11
            stddev = 0.31
            median = 0.00
              75% <= 0.00
              95% <= 1.00
              98% <= 1.00
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 0

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 7.00
               min = 0.00
               max = 2.00
              mean = 0.11
            stddev = 0.36
            median = 0.00
              75% <= 0.00
              95% <= 1.00
              98% <= 1.70
              99% <= 2.00
            99.9% <= 2.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 80.14 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 80.06 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 41

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
             count = 64

  wait-requests-us:
    value = 8

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 46 ---
  superstep time: 34 ms
  compute all partitions: 15 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 8 us

3/22/20 12:33:55 PM ============================================================
giraph.superstep.46:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 15

  compute-per-partition-ms:
               sum = 11.00
               min = 0.00
               max = 1.00
              mean = 0.17
            stddev = 0.38
            median = 0.00
              75% <= 0.00
              95% <= 1.00
              98% <= 1.00
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 12288

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 11.00
               min = 0.00
               max = 2.00
              mean = 0.17
            stddev = 0.46
            median = 0.00
              75% <= 0.00
              95% <= 1.00
              98% <= 2.00
              99% <= 2.00
            99.9% <= 2.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 90.99 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 90.88 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 34

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
             count = 64

  wait-requests-us:
    value = 8

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 47 ---
  superstep time: 38 ms
  compute all partitions: 23 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 7 us

3/22/20 12:33:55 PM ============================================================
giraph.superstep.47:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 23

  compute-per-partition-ms:
               sum = 675.00
               min = 1.00
               max = 16.00
              mean = 10.55
            stddev = 3.83
            median = 12.00
              75% <= 14.00
              95% <= 14.75
              98% <= 16.00
              99% <= 16.00
            99.9% <= 16.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 0

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 675.00
               min = 0.00
               max = 21.00
              mean = 10.55
            stddev = 7.08
            median = 12.00
              75% <= 14.00
              95% <= 20.75
              98% <= 21.00
              99% <= 21.00
            99.9% <= 21.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 80.78 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 80.85 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 38

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
             count = 64

  wait-requests-us:
    value = 7

  worker-context-post-superstep:
    value = 2

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 48 ---
  superstep time: 35 ms
  compute all partitions: 17 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 8 us

3/22/20 12:33:55 PM ============================================================
giraph.superstep.48:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 17

  compute-per-partition-ms:
               sum = 1.00
               min = 0.00
               max = 1.00
              mean = 0.02
            stddev = 0.12
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 0.70
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 192

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 1.00
               min = 0.00
               max = 1.00
              mean = 0.02
            stddev = 0.12
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 0.70
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 90.44 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 90.34 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 35

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
             count = 64

  wait-requests-us:
    value = 8

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 49 ---
  superstep time: 38 ms
  compute all partitions: 20 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 15 us

3/22/20 12:33:55 PM ============================================================
giraph.superstep.49:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 20

  compute-per-partition-ms:
               sum = 14.00
               min = 0.00
               max = 1.00
              mean = 0.22
            stddev = 0.42
            median = 0.00
              75% <= 0.00
              95% <= 1.00
              98% <= 1.00
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

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
               sum = 14.00
               min = 0.00
               max = 2.00
              mean = 0.22
            stddev = 0.58
            median = 0.00
              75% <= 0.00
              95% <= 2.00
              98% <= 2.00
              99% <= 2.00
            99.9% <= 2.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 85.92 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 85.82 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 38

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
             count = 64

  wait-requests-us:
    value = 15

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 50 ---
  superstep time: 34 ms
  compute all partitions: 16 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 29 us

3/22/20 12:33:55 PM ============================================================
giraph.superstep.50:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 16

  compute-per-partition-ms:
               sum = 4.00
               min = 0.00
               max = 4.00
              mean = 0.06
            stddev = 0.50
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 2.80
              99% <= 4.00
            99.9% <= 4.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 32

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 4.00
               min = 0.00
               max = 4.00
              mean = 0.06
            stddev = 0.50
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 2.80
              99% <= 4.00
            99.9% <= 4.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 92.52 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 92.40 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 34

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
             count = 64

  wait-requests-us:
    value = 29

  worker-context-post-superstep:
    value = 2

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 51 ---
  superstep time: 33 ms
  compute all partitions: 16 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 8 us

3/22/20 12:33:55 PM ============================================================
giraph.superstep.51:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 16

  compute-per-partition-ms:
               sum = 17.00
               min = 0.00
               max = 5.00
              mean = 0.27
            stddev = 0.96
            median = 0.00
              75% <= 0.00
              95% <= 2.50
              98% <= 5.00
              99% <= 5.00
            99.9% <= 5.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 16

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 17.00
               min = 0.00
               max = 8.00
              mean = 0.27
            stddev = 1.19
            median = 0.00
              75% <= 0.00
              95% <= 1.00
              98% <= 7.10
              99% <= 8.00
            99.9% <= 8.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 95.72 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 95.60 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 33

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
             count = 64

  wait-requests-us:
    value = 8

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 52 ---
  superstep time: 34 ms
  compute all partitions: 18 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 7 us

3/22/20 12:33:55 PM ============================================================
giraph.superstep.52:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 18

  compute-per-partition-ms:
               sum = 1.00
               min = 0.00
               max = 1.00
              mean = 0.02
            stddev = 0.13
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 0.70
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 8

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 1.00
               min = 0.00
               max = 1.00
              mean = 0.02
            stddev = 0.12
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 0.70
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 93.68 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 93.55 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 34

  time-to-first-message-ms:
    value = 0

  total-requests:
    value = 0

  wait-per-thread-ms:
               sum = 1.00
               min = 0.00
               max = 1.00
              mean = 0.02
            stddev = 0.13
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 0.70
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  wait-requests-us:
    value = 7

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 53 ---
  superstep time: 32 ms
  compute all partitions: 15 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 7 us

3/22/20 12:33:55 PM ============================================================
giraph.superstep.53:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 15

  compute-per-partition-ms:
               sum = 1.00
               min = 0.00
               max = 1.00
              mean = 0.02
            stddev = 0.13
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 0.70
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 4

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 1.00
               min = 0.00
               max = 1.00
              mean = 0.02
            stddev = 0.12
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 0.70
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 98.44 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 98.33 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 32

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
             count = 64

  wait-requests-us:
    value = 7

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 54 ---
  superstep time: 32 ms
  compute all partitions: 16 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 7 us

3/22/20 12:33:55 PM ============================================================
giraph.superstep.54:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 16

  compute-per-partition-ms:
               sum = 3.00
               min = 0.00
               max = 1.00
              mean = 0.05
            stddev = 0.21
            median = 0.00
              75% <= 0.00
              95% <= 0.75
              98% <= 1.00
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 2

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 3.00
               min = 0.00
               max = 1.00
              mean = 0.05
            stddev = 0.21
            median = 0.00
              75% <= 0.00
              95% <= 0.75
              98% <= 1.00
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 92.79 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 92.69 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 32

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
             count = 64

  wait-requests-us:
    value = 7

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 55 ---
  superstep time: 33 ms
  compute all partitions: 17 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 8 us

3/22/20 12:33:55 PM ============================================================
giraph.superstep.55:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 17

  compute-per-partition-ms:
               sum = 2.00
               min = 0.00
               max = 1.00
              mean = 0.03
            stddev = 0.18
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 1.00
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 2

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 2.00
               min = 0.00
               max = 1.00
              mean = 0.03
            stddev = 0.18
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 1.00
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 91.76 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 91.64 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 33

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
             count = 64

  wait-requests-us:
    value = 8

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 56 ---
  superstep time: 33 ms
  compute all partitions: 16 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 7 us

3/22/20 12:33:55 PM ============================================================
giraph.superstep.56:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 16

  compute-per-partition-ms:
               sum = 2.00
               min = 0.00
               max = 1.00
              mean = 0.03
            stddev = 0.18
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 1.00
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 4

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 2.00
               min = 0.00
               max = 1.00
              mean = 0.03
            stddev = 0.18
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 1.00
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 92.81 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 92.67 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 33

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
             count = 64

  wait-requests-us:
    value = 7

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 57 ---
  superstep time: 37 ms
  compute all partitions: 16 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 7 us

3/22/20 12:33:55 PM ============================================================
giraph.superstep.57:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 16

  compute-per-partition-ms:
               sum = 1.00
               min = 0.00
               max = 1.00
              mean = 0.02
            stddev = 0.13
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 0.70
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 8

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 1.00
               min = 0.00
               max = 1.00
              mean = 0.02
            stddev = 0.13
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 0.70
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 88.22 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 88.13 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 37

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
             count = 64

  wait-requests-us:
    value = 7

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 58 ---
  superstep time: 34 ms
  compute all partitions: 15 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 7 us

3/22/20 12:33:55 PM ============================================================
giraph.superstep.58:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 15

  compute-per-partition-ms:
               sum = 1.00
               min = 0.00
               max = 1.00
              mean = 0.02
            stddev = 0.13
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 0.70
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 16

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 1.00
               min = 0.00
               max = 1.00
              mean = 0.02
            stddev = 0.13
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 0.70
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 94.24 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 94.14 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 34

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
             count = 64

  wait-requests-us:
    value = 7

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 59 ---
  superstep time: 35 ms
  compute all partitions: 18 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 7 us

3/22/20 12:33:55 PM ============================================================
giraph.superstep.59:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 18

  compute-per-partition-ms:
               sum = 2.00
               min = 0.00
               max = 2.00
              mean = 0.03
            stddev = 0.25
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 1.40
              99% <= 2.00
            99.9% <= 2.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 32

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 2.00
               min = 0.00
               max = 2.00
              mean = 0.03
            stddev = 0.25
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 1.40
              99% <= 2.00
            99.9% <= 2.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 92.02 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 91.93 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 35

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
             count = 64

  wait-requests-us:
    value = 7

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 60 ---
  superstep time: 34 ms
  compute all partitions: 16 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 8 us

3/22/20 12:33:55 PM ============================================================
giraph.superstep.60:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 16

  compute-per-partition-ms:
               sum = 4.00
               min = 0.00
               max = 4.00
              mean = 0.06
            stddev = 0.50
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 2.80
              99% <= 4.00
            99.9% <= 4.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

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
               sum = 4.00
               min = 0.00
               max = 4.00
              mean = 0.06
            stddev = 0.50
            median = 0.00
              75% <= 0.00
              95% <= 0.00
              98% <= 2.80
              99% <= 4.00
            99.9% <= 4.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 93.59 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 93.45 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 34

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
             count = 64

  wait-requests-us:
    value = 8

  worker-context-post-superstep:
    value = 1

  worker-context-pre-superstep:
    value = 0




--- METRICS: superstep 61 ---
  superstep time: 32 ms
  compute all partitions: 15 ms
  time spent in gc: 0 ms
  bytes transferred in out-of-core: 0
  network communication time: 0 ms
  time to first message: 0 us
  wait on requests time: 7 us

3/22/20 12:33:55 PM ============================================================
giraph.superstep.61:
  communication-time-ms:
    value = 0

  compute-all-ms:
    value = 15

  compute-per-partition-ms:
               sum = 16.00
               min = 0.00
               max = 1.00
              mean = 0.25
            stddev = 0.44
            median = 0.00
              75% <= 0.75
              95% <= 1.00
              98% <= 1.00
              99% <= 1.00
            99.9% <= 1.00
             count = 64

  gc-per-thread-ms:
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
             count = 64

  local-requests:
    count = 0

  message-bytes-sent:
    count = 0

  messages-sent:
    count = 0

  ooc-bytes-load:
    count = 0

  ooc-bytes-store:
    count = 0

  percent-local-requests:
    value = NaN

  processing-per-thread-ms:
               sum = 16.00
               min = 0.00
               max = 3.00
              mean = 0.25
            stddev = 0.64
            median = 0.00
              75% <= 0.00
              95% <= 2.00
              98% <= 2.70
              99% <= 3.00
            99.9% <= 3.00
             count = 64

  received-bytes:
               sum = 2,262.00
               min = 16.00
               max = 1155.00
              mean = 565.50
            stddev = 635.35
            median = 545.50
              75% <= 1135.00
              95% <= 1155.00
              98% <= 1155.00
              99% <= 1155.00
            99.9% <= 1155.00
             count = 4

  remote-requests:
    count = 0

  requests-received:
             count = 4
         mean rate = 93.83 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  requests-sent:
             count = 4
         mean rate = 93.74 requests/s
     1-minute rate = 0.00 requests/s
     5-minute rate = 0.00 requests/s
    15-minute rate = 0.00 requests/s

  send-aggregators-to-master-requests:
    count = 1

  send-aggregators-to-owner-requests:
    count = 0

  send-aggregators-to-worker-requests:
    count = 0

  send-partition-current-messages-requests:
    count = 0

  send-partition-mutations-requests:
    count = 0

  send-vertex-requests:
    count = 0

  send-worker-aggregators-requests:
    count = 0

  send-worker-messages-requests:
    count = 0

  sent-bytes:
               sum = 3,004.00
               min = 16.00
               max = 2837.00
              mean = 751.00
            stddev = 1391.80
            median = 75.50
              75% <= 2161.50
              95% <= 2837.00
              98% <= 2837.00
              99% <= 2837.00
            99.9% <= 2837.00
             count = 4

  superstep-gc-time-ms:
    count = 0

  superstep-time-ms:
    value = 32

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
             count = 64

  wait-requests-us:
    value = 7

  worker-context-post-superstep:
    value = 2

  worker-context-pre-superstep:
    value = 0




3/22/20 12:33:58 PM ============================================================
giraph.job:
  memory-free-pct:
    value = 93.73628989046509

  worker-context-post-app:
    value = 10

  worker-context-pre-app:
    value = 0




3/22/20 12:33:58 PM ============================================================
giraph.job:
  edges-filtered:
    count = 0

  edges-filtered-pct:
    value = 0.0

  edges-loaded:
             count = 444
         mean rate = 29.36 edges/s
     1-minute rate = 6.53 edges/s
     5-minute rate = 1.44 edges/s
    15-minute rate = 0.49 edges/s

  vertices-filtered:
    count = 0

  vertices-filtered-pct:
    value = 0.0

  vertices-loaded:
             count = 319
         mean rate = 21.09 vertices/s
     1-minute rate = 4.69 vertices/s
     5-minute rate = 1.04 vertices/s
    15-minute rate = 0.35 vertices/s




End of LogType:task-3-stderr.log
**********************************************************************************

Container: container_1584864522397_0046_01_000003 on iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_37469
LogAggregationType: AGGREGATED
====================================================================================================================
LogType:task-3-stdout.log
LogLastModifiedTime:Sun Mar 22 12:34:06 +0000 2020
LogLength:268962
LogContents:
[Global flags]
     bool AbortVMOnSafepointTimeout                 = false                               {diagnostic}
     intx ActiveProcessorCount                      = -1                                  {product}
    uintx AdaptiveSizeDecrementScaleFactor          = 4                                   {product}
    uintx AdaptiveSizeMajorGCDecayTimeScale         = 10                                  {product}
    uintx AdaptiveSizePausePolicy                   = 0                                   {product}
    uintx AdaptiveSizePolicyCollectionCostMargin    = 50                                  {product}
    uintx AdaptiveSizePolicyInitializingSteps       = 20                                  {product}
    uintx AdaptiveSizePolicyOutputInterval          = 0                                   {product}
    uintx AdaptiveSizePolicyWeight                  = 10                                  {product}
    uintx AdaptiveSizeThroughPutPolicy              = 0                                   {product}
    uintx AdaptiveTimeWeight                        = 25                                  {product}
     bool AdjustConcurrency                         = false                               {product}
     bool AggressiveHeap                            = false                               {product}
     bool AggressiveOpts                            = false                               {product}
     intx AliasLevel                                = 3                                   {C2 product}
     bool AlignVector                               = false                               {C2 product}
     intx AllocateInstancePrefetchLines             = 1                                   {product}
     intx AllocatePrefetchDistance                  = 192                                 {product}
     intx AllocatePrefetchInstr                     = 3                                   {product}
     intx AllocatePrefetchLines                     = 4                                   {product}
     intx AllocatePrefetchStepSize                  = 64                                  {product}
     intx AllocatePrefetchStyle                     = 1                                   {product}
     bool AllowJNIEnvProxy                          = false                               {product}
     bool AllowNonVirtualCalls                      = false                               {product}
     bool AllowParallelDefineClass                  = false                               {product}
     bool AllowUserSignalHandlers                   = false                               {product}
     bool AlwaysActAsServerClassMachine             = false                               {product}
     bool AlwaysCompileLoopMethods                  = false                               {product}
     bool AlwaysLockClassLoader                     = false                               {product}
     bool AlwaysPreTouch                            = false                               {product}
     bool AlwaysRestoreFPU                          = false                               {product}
     bool AlwaysTenure                              = false                               {product}
     bool AssertOnSuspendWaitFailure                = false                               {product}
     bool AssumeMP                                  = false                               {product}
     intx AutoBoxCacheMax                           = 128                                 {C2 product}
    uintx AutoGCSelectPauseMillis                   = 5000                                {product}
     intx BCEATraceLevel                            = 0                                   {product}
     intx BackEdgeThreshold                         = 100000                              {pd product}
     bool BackgroundCompilation                     = true                                {pd product}
    uintx BaseFootPrintEstimate                     = 268435456                           {product}
     intx BiasedLockingBulkRebiasThreshold          = 20                                  {product}
     intx BiasedLockingBulkRevokeThreshold          = 40                                  {product}
     intx BiasedLockingDecayTime                    = 25000                               {product}
     intx BiasedLockingStartupDelay                 = 4000                                {product}
     bool BindCMSThreadToCPU                        = false                               {diagnostic}
     bool BindGCTaskThreadsToCPUs                   = false                               {product}
     bool BlockLayoutByFrequency                    = true                                {C2 product}
     intx BlockLayoutMinDiamondPercentage           = 20                                  {C2 product}
     bool BlockLayoutRotateLoops                    = true                                {C2 product}
     bool BlockOffsetArrayUseUnallocatedBlock       = false                               {diagnostic}
     bool BranchOnRegister                          = false                               {C2 product}
     bool BytecodeVerificationLocal                 = false                               {product}
     bool BytecodeVerificationRemote                = true                                {product}
     bool C1OptimizeVirtualCallProfiling            = true                                {C1 product}
     bool C1PatchInvokeDynamic                      = true                                {C1 diagnostic}
     bool C1ProfileBranches                         = true                                {C1 product}
     bool C1ProfileCalls                            = true                                {C1 product}
     bool C1ProfileCheckcasts                       = true                                {C1 product}
     bool C1ProfileInlinedCalls                     = true                                {C1 product}
     bool C1ProfileVirtualCalls                     = true                                {C1 product}
     bool C1UpdateMethodData                        = true                                {C1 product}
     intx CICompilerCount                          := 18                                  {product}
     bool CICompilerCountPerCPU                     = true                                {product}
     bool CITime                                    = false                               {product}
     bool CMSAbortSemantics                         = false                               {product}
    uintx CMSAbortablePrecleanMinWorkPerIteration   = 100                                 {product}
     intx CMSAbortablePrecleanWaitMillis            = 100                                 {manageable}
    uintx CMSBitMapYieldQuantum                     = 10485760                            {product}
    uintx CMSBootstrapOccupancy                     = 50                                  {product}
     bool CMSClassUnloadingEnabled                  = true                                {product}
    uintx CMSClassUnloadingMaxInterval              = 0                                   {product}
     bool CMSCleanOnEnter                           = true                                {product}
     bool CMSCompactWhenClearAllSoftRefs            = true                                {product}
    uintx CMSConcMarkMultiple                       = 32                                  {product}
     bool CMSConcurrentMTEnabled                    = true                                {product}
    uintx CMSCoordinatorYieldSleepCount             = 10                                  {product}
     bool CMSDumpAtPromotionFailure                 = false                               {product}
     bool CMSEdenChunksRecordAlways                 = true                                {product}
    uintx CMSExpAvgFactor                           = 50                                  {product}
     bool CMSExtrapolateSweep                       = false                               {product}
    uintx CMSFullGCsBeforeCompaction                = 0                                   {product}
    uintx CMSIncrementalDutyCycle                   = 10                                  {product}
    uintx CMSIncrementalDutyCycleMin                = 0                                   {product}
     bool CMSIncrementalMode                        = false                               {product}
    uintx CMSIncrementalOffset                      = 0                                   {product}
     bool CMSIncrementalPacing                      = true                                {product}
    uintx CMSIncrementalSafetyFactor                = 10                                  {product}
    uintx CMSIndexedFreeListReplenish               = 4                                   {product}
     intx CMSInitiatingOccupancyFraction            = -1                                  {product}
    uintx CMSIsTooFullPercentage                    = 98                                  {product}
   double CMSLargeCoalSurplusPercent                = 0.950000                            {product}
   double CMSLargeSplitSurplusPercent               = 1.000000                            {product}
     bool CMSLoopWarn                               = false                               {product}
    uintx CMSMaxAbortablePrecleanLoops              = 0                                   {product}
     intx CMSMaxAbortablePrecleanTime               = 5000                                {product}
    uintx CMSOldPLABMax                             = 1024                                {product}
    uintx CMSOldPLABMin                             = 16                                  {product}
    uintx CMSOldPLABNumRefills                      = 4                                   {product}
    uintx CMSOldPLABReactivityFactor                = 2                                   {product}
     bool CMSOldPLABResizeQuicker                   = false                               {product}
    uintx CMSOldPLABToleranceFactor                 = 4                                   {product}
     bool CMSPLABRecordAlways                       = true                                {product}
    uintx CMSParPromoteBlocksToClaim                = 16                                  {product}
     bool CMSParallelInitialMarkEnabled             = true                                {product}
     bool CMSParallelRemarkEnabled                  = true                                {product}
     bool CMSParallelSurvivorRemarkEnabled          = true                                {product}
    uintx CMSPrecleanDenominator                    = 3                                   {product}
    uintx CMSPrecleanIter                           = 3                                   {product}
    uintx CMSPrecleanNumerator                      = 2                                   {product}
     bool CMSPrecleanRefLists1                      = true                                {product}
     bool CMSPrecleanRefLists2                      = false                               {product}
     bool CMSPrecleanSurvivors1                     = false                               {product}
     bool CMSPrecleanSurvivors2                     = true                                {product}
    uintx CMSPrecleanThreshold                      = 1000                                {product}
     bool CMSPrecleaningEnabled                     = true                                {product}
     bool CMSPrintChunksInDump                      = false                               {product}
     bool CMSPrintEdenSurvivorChunks                = false                               {product}
     bool CMSPrintObjectsInDump                     = false                               {product}
    uintx CMSRemarkVerifyVariant                    = 1                                   {product}
     bool CMSReplenishIntermediate                  = true                                {product}
    uintx CMSRescanMultiple                         = 32                                  {product}
    uintx CMSSamplingGrain                          = 16384                               {product}
     bool CMSScavengeBeforeRemark                   = false                               {product}
    uintx CMSScheduleRemarkEdenPenetration          = 50                                  {product}
    uintx CMSScheduleRemarkEdenSizeThreshold        = 2097152                             {product}
    uintx CMSScheduleRemarkSamplingRatio            = 5                                   {product}
   double CMSSmallCoalSurplusPercent                = 1.050000                            {product}
   double CMSSmallSplitSurplusPercent               = 1.100000                            {product}
     bool CMSSplitIndexedFreeListBlocks             = true                                {product}
     intx CMSTriggerInterval                        = -1                                  {manageable}
    uintx CMSTriggerRatio                           = 80                                  {product}
     intx CMSWaitDuration                           = 2000                                {manageable}
    uintx CMSWorkQueueDrainThreshold                = 10                                  {product}
     bool CMSYield                                  = true                                {product}
    uintx CMSYieldSleepCount                        = 0                                   {product}
    uintx CMSYoungGenPerWorker                      = 67108864                            {pd product}
    uintx CMS_FLSPadding                            = 1                                   {product}
    uintx CMS_FLSWeight                             = 75                                  {product}
    uintx CMS_SweepPadding                          = 1                                   {product}
    uintx CMS_SweepTimerThresholdMillis             = 10                                  {product}
    uintx CMS_SweepWeight                           = 75                                  {product}
    uintx CPUForCMSThread                           = 0                                   {diagnostic}
     bool CheckEndorsedAndExtDirs                   = false                               {product}
     bool CheckJNICalls                             = false                               {product}
     bool ClassUnloading                            = true                                {product}
     bool ClassUnloadingWithConcurrentMark          = true                                {product}
     intx ClearFPUAtPark                            = 0                                   {product}
     bool ClipInlining                              = true                                {product}
    uintx CodeCacheExpansionSize                    = 65536                               {pd product}
    uintx CodeCacheMinimumFreeSpace                 = 512000                              {product}
     bool CollectGen0First                          = false                               {product}
     bool CompactFields                             = true                                {product}
     intx CompilationPolicyChoice                   = 3                                   {product}
ccstrlist CompileCommand                            =                                     {product}
    ccstr CompileCommandFile                        =                                     {product}
ccstrlist CompileOnly                               =                                     {product}
     intx CompileThreshold                          = 10000                               {pd product}
     bool CompilerThreadHintNoPreempt               = true                                {product}
     intx CompilerThreadPriority                    = -1                                  {product}
     intx CompilerThreadStackSize                   = 0                                   {pd product}
    uintx CompressedClassSpaceSize                  = 1073741824                          {product}
    uintx ConcGCThreads                             = 0                                   {product}
     intx ConditionalMoveLimit                      = 3                                   {C2 pd product}
     intx ContendedPaddingWidth                     = 128                                 {product}
     bool ConvertSleepToYield                       = true                                {pd product}
     bool ConvertYieldToSleep                       = false                               {product}
     bool CrashOnOutOfMemoryError                   = false                               {product}
     bool CreateMinidumpOnCrash                     = false                               {product}
     bool CriticalJNINatives                        = true                                {product}
     bool DTraceAllocProbes                         = false                               {product}
     bool DTraceMethodProbes                        = false                               {product}
     bool DTraceMonitorProbes                       = false                               {product}
     bool DebugInlinedCalls                         = true                                {C2 diagnostic}
     bool DebugNonSafepoints                        = false                               {diagnostic}
     bool Debugging                                 = false                               {product}
    uintx DefaultMaxRAMFraction                     = 4                                   {product}
     intx DefaultThreadPriority                     = -1                                  {product}
     bool DeferInitialCardMark                      = false                               {diagnostic}
     intx DeferPollingPageLoopCount                 = -1                                  {product}
     intx DeferThrSuspendLoopCount                  = 4000                                {product}
     bool DeoptimizeRandom                          = false                               {product}
     bool DisableAttachMechanism                    = false                               {product}
     bool DisableExplicitGC                         = false                               {product}
ccstrlist DisableIntrinsic                          =                                     {C2 diagnostic}
     bool DisplayVMOutput                           = true                                {diagnostic}
     bool DisplayVMOutputToStderr                   = false                               {product}
     bool DisplayVMOutputToStdout                   = false                               {product}
     bool DoEscapeAnalysis                          = true                                {C2 product}
     intx DominatorSearchLimit                      = 1000                                {C2 diagnostic}
     bool DontCompileHugeMethods                    = true                                {product}
     bool DontYieldALot                             = false                               {pd product}
    ccstr DumpLoadedClassList                       =                                     {product}
     bool DumpReplayDataOnError                     = true                                {product}
     bool DumpSharedSpaces                          = false                               {product}
     bool EagerXrunInit                             = false                               {product}
     intx EliminateAllocationArraySizeLimit         = 64                                  {C2 product}
     bool EliminateAllocations                      = true                                {C2 product}
     bool EliminateAutoBox                          = true                                {C2 product}
     bool EliminateLocks                            = true                                {C2 product}
     bool EliminateNestedLocks                      = true                                {C2 product}
     intx EmitSync                                  = 0                                   {product}
     bool EnableContended                           = true                                {product}
     bool EnableInvokeDynamic                       = true                                {diagnostic}
     bool EnableTracing                             = false                               {product}
    uintx ErgoHeapSizeLimit                         = 0                                   {product}
    ccstr ErrorFile                                 =                                     {product}
    ccstr ErrorReportServer                         =                                     {product}
   double EscapeAnalysisTimeout                     = 20.000000                           {C2 product}
     bool EstimateArgEscape                         = true                                {product}
     bool ExitOnOutOfMemoryError                    = false                               {product}
     bool ExplicitGCInvokesConcurrent               = false                               {product}
     bool ExplicitGCInvokesConcurrentAndUnloadsClasses  = false                               {product}
     bool ExtendedDTraceProbes                      = false                               {product}
    ccstr ExtraSharedClassListFile                  =                                     {product}
     bool FLSAlwaysCoalesceLarge                    = false                               {product}
    uintx FLSCoalescePolicy                         = 2                                   {product}
   double FLSLargestBlockCoalesceProximity          = 0.990000                            {product}
     bool FLSVerifyAllHeapReferences                = false                               {diagnostic}
     bool FLSVerifyIndexTable                       = false                               {diagnostic}
     bool FLSVerifyLists                            = false                               {diagnostic}
     bool FailOverToOldVerifier                     = true                                {product}
     bool FastTLABRefill                            = true                                {product}
     intx FenceInstruction                          = 0                                   {ARCH product}
     intx FieldsAllocationStyle                     = 1                                   {product}
     bool FilterSpuriousWakeups                     = true                                {product}
     bool FoldStableValues                          = true                                {diagnostic}
     bool ForceDynamicNumberOfGCThreads             = false                               {diagnostic}
     bool ForceNUMA                                 = false                               {product}
     bool ForceTimeHighResolution                   = false                               {product}
     bool ForceUnreachable                          = false                               {diagnostic}
     intx FreqInlineSize                            = 325                                 {pd product}
   double G1ConcMarkStepDurationMillis              = 10.000000                           {product}
    uintx G1ConcRSHotCardLimit                      = 4                                   {product}
    uintx G1ConcRSLogCacheSize                      = 10                                  {product}
     intx G1ConcRefinementGreenZone                 = 0                                   {product}
     intx G1ConcRefinementRedZone                   = 0                                   {product}
     intx G1ConcRefinementServiceIntervalMillis     = 300                                 {product}
    uintx G1ConcRefinementThreads                   = 0                                   {product}
     intx G1ConcRefinementThresholdStep             = 0                                   {product}
     intx G1ConcRefinementYellowZone                = 0                                   {product}
    uintx G1ConfidencePercent                       = 50                                  {product}
    uintx G1HeapRegionSize                          = 0                                   {product}
    uintx G1HeapWastePercent                        = 5                                   {product}
    uintx G1MixedGCCountTarget                      = 8                                   {product}
     bool G1PrintHeapRegions                        = false                               {diagnostic}
     bool G1PrintRegionLivenessInfo                 = false                               {diagnostic}
     intx G1RSetRegionEntries                       = 0                                   {product}
    uintx G1RSetScanBlockSize                       = 64                                  {product}
     intx G1RSetSparseRegionEntries                 = 0                                   {product}
     intx G1RSetUpdatingPauseTimePercent            = 10                                  {product}
     intx G1RefProcDrainInterval                    = 10                                  {product}
    uintx G1ReservePercent                          = 10                                  {product}
    uintx G1SATBBufferEnqueueingThresholdPercent    = 60                                  {product}
     intx G1SATBBufferSize                          = 1024                                {product}
     bool G1SummarizeConcMark                       = false                               {diagnostic}
     bool G1SummarizeRSetStats                      = false                               {diagnostic}
     intx G1SummarizeRSetStatsPeriod                = 0                                   {diagnostic}
     bool G1TraceConcRefinement                     = false                               {diagnostic}
     intx G1UpdateBufferSize                        = 256                                 {product}
     bool G1UseAdaptiveConcRefinement               = true                                {product}
     bool G1VerifyHeapRegionCodeRoots               = false                               {diagnostic}
     bool G1VerifyRSetsDuringFullGC                 = false                               {diagnostic}
    uintx GCDrainStackTargetSize                    = 64                                  {product}
    uintx GCHeapFreeLimit                           = 2                                   {product}
    uintx GCLockerEdenExpansionPercent              = 5                                   {product}
     bool GCLockerInvokesConcurrent                 = false                               {product}
    uintx GCLockerRetryAllocationCount              = 2                                   {diagnostic}
    uintx GCLogFileSize                             = 8192                                {product}
     bool GCParallelVerificationEnabled             = true                                {diagnostic}
    uintx GCPauseIntervalMillis                     = 0                                   {product}
    uintx GCTaskTimeStampEntries                    = 200                                 {product}
    uintx GCTimeLimit                               = 98                                  {product}
    uintx GCTimeRatio                               = 99                                  {product}
     intx GuaranteedSafepointInterval               = 1000                                {diagnostic}
    uintx HeapBaseMinAddress                        = 2147483648                          {pd product}
     bool HeapDumpAfterFullGC                       = false                               {manageable}
     bool HeapDumpBeforeFullGC                      = false                               {manageable}
     bool HeapDumpOnOutOfMemoryError                = false                               {manageable}
    ccstr HeapDumpPath                              =                                     {manageable}
    uintx HeapFirstMaximumCompactionCount           = 3                                   {product}
    uintx HeapMaximumCompactionInterval             = 20                                  {product}
    uintx HeapSizePerGCThread                       = 87241520                            {product}
     bool IgnoreEmptyClassPaths                     = false                               {product}
     bool IgnoreUnrecognizedVMOptions               = false                               {product}
     bool IgnoreUnverifiableClassesDuringDump       = false                               {diagnostic}
    uintx IncreaseFirstTierCompileThresholdAt       = 50                                  {product}
     bool IncrementalInline                         = true                                {C2 product}
    uintx InitialBootClassLoaderMetaspaceSize       = 4194304                             {product}
    uintx InitialCodeCacheSize                      = 2555904                             {pd product}
    uintx InitialHeapSize                          := 98784247808                         {product}
    uintx InitialRAMFraction                        = 64                                  {product}
   double InitialRAMPercentage                      = 1.562500                            {product}
    uintx InitialSurvivorRatio                      = 8                                   {product}
    uintx InitialTenuringThreshold                  = 7                                   {product}
    uintx InitiatingHeapOccupancyPercent            = 45                                  {product}
     bool Inline                                    = true                                {product}
    ccstr InlineDataFile                            =                                     {product}
     intx InlineSmallCode                           = 2000                                {pd product}
     bool InlineSynchronizedMethods                 = true                                {C1 product}
     bool InsertMemBarAfterArraycopy                = true                                {C2 product}
     intx InteriorEntryAlignment                    = 16                                  {C2 pd product}
     intx InterpreterProfilePercentage              = 33                                  {product}
     bool JNIDetachReleasesMonitors                 = true                                {product}
     bool JavaMonitorsInStackTrace                  = true                                {product}
     intx JavaPriority10_To_OSPriority              = -1                                  {product}
     intx JavaPriority1_To_OSPriority               = -1                                  {product}
     intx JavaPriority2_To_OSPriority               = -1                                  {product}
     intx JavaPriority3_To_OSPriority               = -1                                  {product}
     intx JavaPriority4_To_OSPriority               = -1                                  {product}
     intx JavaPriority5_To_OSPriority               = -1                                  {product}
     intx JavaPriority6_To_OSPriority               = -1                                  {product}
     intx JavaPriority7_To_OSPriority               = -1                                  {product}
     intx JavaPriority8_To_OSPriority               = -1                                  {product}
     intx JavaPriority9_To_OSPriority               = -1                                  {product}
     bool LIRFillDelaySlots                         = false                               {C1 pd product}
    uintx LargePageHeapSizeThreshold                = 134217728                           {product}
    uintx LargePageSizeInBytes                      = 0                                   {product}
     bool LazyBootClassLoader                       = true                                {product}
     intx LiveNodeCountInliningCutoff               = 40000                               {C2 product}
     bool LoadExecStackDllInVMThread                = true                                {product}
     bool LogCompilation                            = false                               {diagnostic}
     bool LogEvents                                 = true                                {diagnostic}
    uintx LogEventsBufferEntries                    = 10                                  {diagnostic}
    ccstr LogFile                                   =                                     {diagnostic}
     bool LogVMOutput                               = false                               {diagnostic}
     bool LoopLimitCheck                            = true                                {C2 diagnostic}
     intx LoopMaxUnroll                             = 16                                  {C2 product}
     intx LoopOptsCount                             = 43                                  {C2 product}
     intx LoopUnrollLimit                           = 60                                  {C2 pd product}
     intx LoopUnrollMin                             = 4                                   {C2 product}
     bool LoopUnswitching                           = true                                {C2 product}
    uintx MallocMaxTestWords                        = 0                                   {diagnostic}
     intx MallocVerifyInterval                      = 0                                   {diagnostic}
     intx MallocVerifyStart                         = 0                                   {diagnostic}
     bool ManagementServer                          = false                               {product}
    uintx MarkStackSize                             = 4194304                             {product}
    uintx MarkStackSizeMax                          = 536870912                           {product}
    uintx MarkSweepAlwaysCompactCount               = 4                                   {product}
    uintx MarkSweepDeadRatio                        = 1                                   {product}
     intx MaxBCEAEstimateLevel                      = 5                                   {product}
     intx MaxBCEAEstimateSize                       = 150                                 {product}
    uintx MaxDirectMemorySize                       = 0                                   {product}
     bool MaxFDLimit                                = true                                {product}
    uintx MaxGCMinorPauseMillis                     = 18446744073709551615                    {product}
    uintx MaxGCPauseMillis                          = 18446744073709551615                    {product}
    uintx MaxHeapFreeRatio                          = 100                                 {manageable}
    uintx MaxHeapSize                              := 98784247808                         {product}
     intx MaxInlineLevel                            = 9                                   {product}
     intx MaxInlineSize                             = 35                                  {product}
     intx MaxJNILocalCapacity                       = 65536                               {product}
     intx MaxJavaStackTraceDepth                    = 1024                                {product}
     intx MaxJumpTableSize                          = 65000                               {C2 product}
     intx MaxJumpTableSparseness                    = 5                                   {C2 product}
     intx MaxLabelRootDepth                         = 1100                                {C2 product}
     intx MaxLoopPad                                = 11                                  {C2 product}
    uintx MaxMetaspaceExpansion                     = 5451776                             {product}
    uintx MaxMetaspaceFreeRatio                     = 70                                  {product}
    uintx MaxMetaspaceSize                          = 18446744073709547520                    {product}
    uintx MaxNewSize                               := 9663676416                          {product}
     intx MaxNodeLimit                              = 75000                               {C2 product}
 uint64_t MaxRAM                                    = 137438953472                        {pd product}
    uintx MaxRAMFraction                            = 4                                   {product}
   double MaxRAMPercentage                          = 25.000000                           {product}
     intx MaxRecursiveInlineLevel                   = 1                                   {product}
    uintx MaxTenuringThreshold                      = 15                                  {product}
     intx MaxTrivialSize                            = 6                                   {product}
     intx MaxVectorSize                             = 32                                  {C2 product}
    uintx MetaspaceSize                             = 21807104                            {pd product}
     bool MethodFlushing                            = true                                {product}
    uintx MinHeapDeltaBytes                        := 524288                              {product}
    uintx MinHeapFreeRatio                          = 0                                   {manageable}
     intx MinInliningThreshold                      = 250                                 {product}
     intx MinJumpTableSize                          = 10                                  {C2 pd product}
    uintx MinMetaspaceExpansion                     = 339968                              {product}
    uintx MinMetaspaceFreeRatio                     = 40                                  {product}
    uintx MinRAMFraction                            = 2                                   {product}
   double MinRAMPercentage                          = 50.000000                           {product}
    uintx MinSurvivorRatio                          = 3                                   {product}
    uintx MinTLABSize                               = 2048                                {product}
     intx MonitorBound                              = 0                                   {product}
     bool MonitorInUseLists                         = false                               {product}
     intx MultiArrayExpandLimit                     = 6                                   {C2 product}
     bool MustCallLoadClassInternal                 = false                               {product}
    uintx NUMAChunkResizeWeight                     = 20                                  {product}
    uintx NUMAInterleaveGranularity                 = 2097152                             {product}
    uintx NUMAPageScanRate                          = 256                                 {product}
    uintx NUMASpaceResizeRate                       = 1073741824                          {product}
     bool NUMAStats                                 = false                               {product}
    ccstr NativeMemoryTracking                      = off                                 {product}
     bool NeedsDeoptSuspend                         = false                               {pd product}
     bool NeverActAsServerClassMachine              = false                               {pd product}
     bool NeverTenure                               = false                               {product}
    uintx NewRatio                                  = 2                                   {product}
    uintx NewSize                                  := 9663676416                          {product}
    uintx NewSizeThreadIncrease                     = 5320                                {pd product}
     intx NmethodSweepActivity                      = 10                                  {product}
     intx NmethodSweepCheckInterval                 = 5                                   {product}
     intx NmethodSweepFraction                      = 16                                  {product}
     intx NodeLimitFudgeFactor                      = 2000                                {C2 product}
    uintx NumberOfGCLogFiles                        = 0                                   {product}
     intx NumberOfLoopInstrToAlign                  = 4                                   {C2 product}
     intx ObjectAlignmentInBytes                    = 8                                   {lp64_product}
    uintx OldPLABSize                               = 1024                                {product}
    uintx OldPLABWeight                             = 50                                  {product}
    uintx OldSize                                  := 89120571392                         {product}
     bool OmitStackTraceInFastThrow                 = true                                {product}
ccstrlist OnError                                   =                                     {product}
ccstrlist OnOutOfMemoryError                       := free -m                             {product}
     intx OnStackReplacePercentage                  = 140                                 {pd product}
     bool OptimizeExpensiveOps                      = true                                {C2 diagnostic}
     bool OptimizeFill                              = true                                {C2 product}
     bool OptimizePtrCompare                        = true                                {C2 product}
     bool OptimizeStringConcat                      = true                                {C2 product}
     bool OptoBundling                              = false                               {C2 pd product}
     intx OptoLoopAlignment                         = 16                                  {pd product}
     bool OptoScheduling                            = false                               {C2 pd product}
    uintx PLABWeight                                = 75                                  {product}
     bool PSChunkLargeArrays                        = true                                {product}
     intx ParGCArrayScanChunk                       = 50                                  {product}
     intx ParGCCardsPerStrideChunk                  = 256                                 {diagnostic}
    uintx ParGCDesiredObjsFromOverflowList          = 20                                  {product}
    uintx ParGCStridesPerThread                     = 2                                   {diagnostic}
     bool ParGCTrimOverflow                         = true                                {product}
     bool ParGCUseLocalOverflow                     = false                               {product}
    uintx ParallelGCBufferWastePct                  = 10                                  {product}
     bool ParallelGCRetainPLAB                      = false                               {diagnostic}
    uintx ParallelGCThreads                         = 63                                  {product}
     bool ParallelGCVerbose                         = false                               {product}
    uintx ParallelOldDeadWoodLimiterMean            = 50                                  {product}
    uintx ParallelOldDeadWoodLimiterStdDev          = 80                                  {product}
     bool ParallelRefProcBalancingEnabled           = true                                {product}
     bool ParallelRefProcEnabled                    = false                               {product}
     bool PartialPeelAtUnsignedTests                = true                                {C2 product}
     bool PartialPeelLoop                           = true                                {C2 product}
     intx PartialPeelNewPhiDelta                    = 0                                   {C2 product}
     bool PauseAtExit                               = false                               {diagnostic}
     bool PauseAtStartup                            = false                               {diagnostic}
    ccstr PauseAtStartupFile                        =                                     {diagnostic}
    uintx PausePadding                              = 1                                   {product}
     intx PerBytecodeRecompilationCutoff            = 200                                 {product}
     intx PerBytecodeTrapLimit                      = 4                                   {product}
     intx PerMethodRecompilationCutoff              = 400                                 {product}
     intx PerMethodTrapLimit                        = 100                                 {product}
     bool PerfAllowAtExitRegistration               = false                               {product}
     bool PerfBypassFileSystemCheck                 = false                               {product}
     intx PerfDataMemorySize                        = 32768                               {product}
     intx PerfDataSamplingInterval                  = 50                                  {product}
    ccstr PerfDataSaveFile                          =                                     {product}
     bool PerfDataSaveToFile                        = false                               {product}
     bool PerfDisableSharedMem                      = false                               {product}
     intx PerfMaxStringConstLength                  = 1024                                {product}
     intx PreInflateSpin                            = 10                                  {pd product}
     bool PreferContainerQuotaForCPUCount           = true                                {product}
     bool PreferInterpreterNativeStubs              = false                               {pd product}
     intx PrefetchCopyIntervalInBytes               = 576                                 {product}
     intx PrefetchFieldsAhead                       = 1                                   {product}
     intx PrefetchScanIntervalInBytes               = 576                                 {product}
     bool PreserveAllAnnotations                    = false                               {product}
     bool PreserveFramePointer                      = false                               {pd product}
    uintx PretenureSizeThreshold                    = 0                                   {product}
     bool PrintActiveCpus                           = false                               {diagnostic}
     bool PrintAdapterHandlers                      = false                               {diagnostic}
     bool PrintAdaptiveSizePolicy                   = false                               {product}
     bool PrintAssembly                             = false                               {diagnostic}
    ccstr PrintAssemblyOptions                      =                                     {diagnostic}
     bool PrintBiasedLockingStatistics              = false                               {diagnostic}
     bool PrintCMSInitiationStatistics              = false                               {product}
     intx PrintCMSStatistics                        = 0                                   {product}
     bool PrintClassHistogram                       = false                               {manageable}
     bool PrintClassHistogramAfterFullGC            = false                               {manageable}
     bool PrintClassHistogramBeforeFullGC           = false                               {manageable}
     bool PrintCodeCache                            = false                               {product}
     bool PrintCodeCacheOnCompilation               = false                               {product}
     bool PrintCommandLineFlags                     = false                               {product}
     bool PrintCompilation                          = false                               {product}
     bool PrintCompilation2                         = false                               {diagnostic}
     bool PrintCompressedOopsMode                   = false                               {diagnostic}
     bool PrintConcurrentLocks                      = false                               {manageable}
     bool PrintContainerInfo                        = false                               {diagnostic}
     bool PrintDTraceDOF                            = false                               {diagnostic}
     intx PrintFLSCensus                            = 0                                   {product}
     intx PrintFLSStatistics                        = 0                                   {product}
     bool PrintFlagsFinal                          := true                                {product}
     bool PrintFlagsInitial                         = false                               {product}
     bool PrintGC                                   = false                               {manageable}
     bool PrintGCApplicationConcurrentTime          = false                               {product}
     bool PrintGCApplicationStoppedTime             = false                               {product}
     bool PrintGCCause                              = true                                {product}
     bool PrintGCDateStamps                         = false                               {manageable}
     bool PrintGCDetails                            = false                               {manageable}
     bool PrintGCID                                 = false                               {manageable}
     bool PrintGCTaskTimeStamps                     = false                               {product}
     bool PrintGCTimeStamps                         = false                               {manageable}
     bool PrintHeapAtGC                             = false                               {product rw}
     bool PrintHeapAtGCExtended                     = false                               {product rw}
     bool PrintHeapAtSIGBREAK                       = true                                {product}
     bool PrintInlining                             = false                               {diagnostic}
     bool PrintInterpreter                          = false                               {diagnostic}
     bool PrintIntrinsics                           = false                               {C2 diagnostic}
     bool PrintJNIGCStalls                          = false                               {product}
     bool PrintJNIResolving                         = false                               {product}
     bool PrintMethodFlushingStatistics             = false                               {diagnostic}
     bool PrintMethodHandleStubs                    = false                               {diagnostic}
     bool PrintNMTStatistics                        = false                               {diagnostic}
     bool PrintNMethods                             = false                               {diagnostic}
     bool PrintNativeNMethods                       = false                               {diagnostic}
     bool PrintOldPLAB                              = false                               {product}
     bool PrintOopAddress                           = false                               {product}
     bool PrintPLAB                                 = false                               {product}
     bool PrintParallelOldGCPhaseTimes              = false                               {product}
     bool PrintPreciseBiasedLockingStatistics       = false                               {C2 diagnostic}
     bool PrintPreciseRTMLockingStatistics          = false                               {C2 diagnostic}
     bool PrintPromotionFailure                     = false                               {product}
     bool PrintReferenceGC                          = false                               {product}
     bool PrintSafepointStatistics                  = false                               {product}
     intx PrintSafepointStatisticsCount             = 300                                 {product}
     intx PrintSafepointStatisticsTimeout           = -1                                  {product}
     bool PrintSharedArchiveAndExit                 = false                               {product}
     bool PrintSharedDictionary                     = false                               {product}
     bool PrintSharedSpaces                         = false                               {product}
     bool PrintSignatureHandlers                    = false                               {diagnostic}
     bool PrintStringDeduplicationStatistics        = false                               {product}
     bool PrintStringTableStatistics                = false                               {product}
     bool PrintStubCode                             = false                               {diagnostic}
     bool PrintTLAB                                 = false                               {product}
     bool PrintTenuringDistribution                 = false                               {product}
     bool PrintTieredEvents                         = false                               {product}
     bool PrintVMOptions                            = false                               {product}
     bool PrintVMQWaitTime                          = false                               {product}
     bool PrintWarnings                             = true                                {product}
    uintx ProcessDistributionStride                 = 4                                   {product}
     bool ProfileDynamicTypes                       = true                                {C2 diagnostic}
     bool ProfileInterpreter                        = true                                {pd product}
     bool ProfileIntervals                          = false                               {product}
     intx ProfileIntervalsTicks                     = 100                                 {product}
     intx ProfileMaturityPercentage                 = 20                                  {product}
     bool ProfileVM                                 = false                               {product}
     bool ProfilerPrintByteCodeStatistics           = false                               {product}
     bool ProfilerRecordPC                          = false                               {product}
    uintx PromotedPadding                           = 3                                   {product}
    uintx QueuedAllocationWarningCount              = 0                                   {product}
    uintx RTMRetryCount                             = 5                                   {ARCH product}
     bool RangeCheckElimination                     = true                                {product}
     bool RangeLimitCheck                           = true                                {C2 diagnostic}
     intx ReadPrefetchInstr                         = 0                                   {ARCH product}
     bool ReassociateInvariants                     = true                                {C2 product}
     bool ReduceBulkZeroing                         = true                                {C2 product}
     bool ReduceFieldZeroing                        = true                                {C2 product}
     bool ReduceInitialCardMarks                    = true                                {C2 product}
     bool ReduceSignalUsage                         = false                               {product}
     intx RefDiscoveryPolicy                        = 0                                   {product}
     bool ReflectionWrapResolutionErrors            = true                                {product}
     bool RegisterFinalizersAtInit                  = true                                {product}
     bool RelaxAccessControlCheck                   = false                               {product}
    ccstr ReplayDataFile                            =                                     {product}
     bool RequireSharedSpaces                       = false                               {product}
    uintx ReservedCodeCacheSize                     = 251658240                           {pd product}
     bool ResizeOldPLAB                             = true                                {product}
     bool ResizePLAB                                = true                                {product}
     bool ResizeTLAB                                = true                                {pd product}
     bool RestoreMXCSROnJNICalls                    = false                               {product}
     bool RestrictContended                         = true                                {product}
     bool RewriteBytecodes                          = true                                {pd product}
     bool RewriteFrequentPairs                      = true                                {pd product}
     intx SafepointPollOffset                       = 256                                 {C1 pd product}
     intx SafepointSpinBeforeYield                  = 2000                                {product}
     bool SafepointTimeout                          = false                               {product}
     intx SafepointTimeoutDelay                     = 10000                               {product}
     bool ScavengeBeforeFullGC                      = true                                {product}
     intx ScavengeRootsInCode                       = 2                                   {diagnostic}
     intx SelfDestructTimer                         = 0                                   {product}
     bool SerializeVMOutput                         = true                                {diagnostic}
    ccstr SharedArchiveFile                         =                                     {diagnostic}
    uintx SharedBaseAddress                         = 34359738368                         {product}
    ccstr SharedClassListFile                       =                                     {product}
    uintx SharedMiscCodeSize                        = 122880                              {product}
    uintx SharedMiscDataSize                        = 4194304                             {product}
    uintx SharedReadOnlySize                        = 16777216                            {product}
    uintx SharedReadWriteSize                       = 16777216                            {product}
     bool ShowHiddenFrames                          = false                               {diagnostic}
     bool ShowMessageBoxOnError                     = false                               {product}
     intx SoftRefLRUPolicyMSPerMB                   = 1000                                {product}
     bool SpecialEncodeISOArray                     = true                                {C2 product}
     bool SplitIfBlocks                             = true                                {C2 product}
     intx StackRedPages                             = 1                                   {pd product}
     intx StackShadowPages                          = 20                                  {pd product}
     bool StackTraceInThrowable                     = true                                {product}
     intx StackYellowPages                          = 2                                   {pd product}
     bool StartAttachListener                       = false                               {product}
     intx StarvationMonitorInterval                 = 200                                 {product}
     bool StressLdcRewrite                          = false                               {product}
    uintx StringDeduplicationAgeThreshold           = 3                                   {product}
     bool StringDeduplicationRehashALot             = false                               {diagnostic}
     bool StringDeduplicationResizeALot             = false                               {diagnostic}
    uintx StringTableSize                           = 60013                               {product}
     bool SuppressFatalErrorMessage                 = false                               {product}
    uintx SurvivorPadding                           = 3                                   {product}
    uintx SurvivorRatio                             = 8                                   {product}
     intx SuspendRetryCount                         = 50                                  {product}
     intx SuspendRetryDelay                         = 5                                   {product}
     intx SyncFlags                                 = 0                                   {product}
    ccstr SyncKnobs                                 =                                     {product}
     intx SyncVerbose                               = 0                                   {product}
    uintx TLABAllocationWeight                      = 35                                  {product}
    uintx TLABRefillWasteFraction                   = 64                                  {product}
    uintx TLABSize                                  = 0                                   {product}
     bool TLABStats                                 = true                                {product}
    uintx TLABWasteIncrement                        = 4                                   {product}
    uintx TLABWasteTargetPercent                    = 1                                   {product}
    uintx TargetPLABWastePct                        = 10                                  {product}
    uintx TargetSurvivorRatio                       = 50                                  {product}
    uintx TenuredGenerationSizeIncrement            = 20                                  {product}
    uintx TenuredGenerationSizeSupplement           = 80                                  {product}
    uintx TenuredGenerationSizeSupplementDecay      = 2                                   {product}
     intx ThreadPriorityPolicy                      = 0                                   {product}
     bool ThreadPriorityVerbose                     = false                               {product}
    uintx ThreadSafetyMargin                        = 52428800                            {product}
     intx ThreadStackSize                           = 1024                                {pd product}
    uintx ThresholdTolerance                        = 10                                  {product}
     intx Tier0BackedgeNotifyFreqLog                = 10                                  {product}
     intx Tier0InvokeNotifyFreqLog                  = 7                                   {product}
     intx Tier0ProfilingStartPercentage             = 200                                 {product}
     intx Tier23InlineeNotifyFreqLog                = 20                                  {product}
     intx Tier2BackEdgeThreshold                    = 0                                   {product}
     intx Tier2BackedgeNotifyFreqLog                = 14                                  {product}
     intx Tier2CompileThreshold                     = 0                                   {product}
     intx Tier2InvokeNotifyFreqLog                  = 11                                  {product}
     intx Tier3BackEdgeThreshold                    = 60000                               {product}
     intx Tier3BackedgeNotifyFreqLog                = 13                                  {product}
     intx Tier3CompileThreshold                     = 2000                                {product}
     intx Tier3DelayOff                             = 2                                   {product}
     intx Tier3DelayOn                              = 5                                   {product}
     intx Tier3InvocationThreshold                  = 200                                 {product}
     intx Tier3InvokeNotifyFreqLog                  = 10                                  {product}
     intx Tier3LoadFeedback                         = 5                                   {product}
     intx Tier3MinInvocationThreshold               = 100                                 {product}
     intx Tier4BackEdgeThreshold                    = 40000                               {product}
     intx Tier4CompileThreshold                     = 15000                               {product}
     intx Tier4InvocationThreshold                  = 5000                                {product}
     intx Tier4LoadFeedback                         = 3                                   {product}
     intx Tier4MinInvocationThreshold               = 600                                 {product}
     bool TieredCompilation                         = true                                {pd product}
     intx TieredCompileTaskTimeout                  = 50                                  {product}
     intx TieredRateUpdateMaxTime                   = 25                                  {product}
     intx TieredRateUpdateMinTime                   = 1                                   {product}
     intx TieredStopAtLevel                         = 4                                   {product}
     bool TimeLinearScan                            = false                               {C1 product}
     bool TraceBiasedLocking                        = false                               {product}
     bool TraceClassLoading                         = false                               {product rw}
     bool TraceClassLoadingPreorder                 = false                               {product}
     bool TraceClassPaths                           = false                               {product}
     bool TraceClassResolution                      = false                               {product}
     bool TraceClassUnloading                       = false                               {product rw}
     bool TraceDynamicGCThreads                     = false                               {product}
     bool TraceGCTaskThread                         = false                               {diagnostic}
     bool TraceGen0Time                             = false                               {product}
     bool TraceGen1Time                             = false                               {product}
    ccstr TraceJVMTI                                =                                     {product}
     bool TraceJVMTIObjectTagging                   = false                               {diagnostic}
     bool TraceLoaderConstraints                    = false                               {product rw}
     bool TraceMetadataHumongousAllocation          = false                               {product}
     bool TraceMonitorInflation                     = false                               {product}
     bool TraceNMethodInstalls                      = false                               {diagnostic}
     bool TraceParallelOldGCTasks                   = false                               {product}
     intx TraceRedefineClasses                      = 0                                   {product}
     bool TraceSafepointCleanupTime                 = false                               {product}
     bool TraceSuspendWaitFailures                  = false                               {product}
     bool TraceTypeProfile                          = false                               {C2 diagnostic}
     intx TrackedInitializationLimit                = 50                                  {C2 product}
     bool TransmitErrorReport                       = false                               {product}
     bool TrapBasedNullChecks                       = false                               {pd product}
     bool TrapBasedRangeChecks                      = false                               {C2 pd product}
     intx TypeProfileArgsLimit                      = 2                                   {product}
    uintx TypeProfileLevel                          = 111                                 {pd product}
     intx TypeProfileMajorReceiverPercent           = 90                                  {C2 product}
     intx TypeProfileParmsLimit                     = 2                                   {product}
     intx TypeProfileWidth                          = 2                                   {product}
     intx UnguardOnExecutionViolation               = 0                                   {product}
     bool UnlinkSymbolsALot                         = false                               {product}
     bool UnlockDiagnosticVMOptions                := true                                {diagnostic}
     bool UnrollLimitCheck                          = true                                {C2 diagnostic}
     bool UnsyncloadClass                           = false                               {diagnostic}
     bool Use486InstrsOnly                          = false                               {ARCH product}
     bool UseAES                                    = true                                {product}
     bool UseAESIntrinsics                          = true                                {product}
     intx UseAVX                                    = 2                                   {ARCH product}
     bool UseAdaptiveGCBoundary                     = false                               {product}
     bool UseAdaptiveGenerationSizePolicyAtMajorCollection  = true                                {product}
     bool UseAdaptiveGenerationSizePolicyAtMinorCollection  = true                                {product}
     bool UseAdaptiveNUMAChunkSizing                = true                                {product}
     bool UseAdaptiveSizeDecayMajorGCCost           = true                                {product}
     bool UseAdaptiveSizePolicy                     = true                                {product}
     bool UseAdaptiveSizePolicyFootprintGoal        = true                                {product}
     bool UseAdaptiveSizePolicyWithSystemGC         = false                               {product}
     bool UseAddressNop                             = true                                {ARCH product}
     bool UseAltSigs                                = false                               {product}
     bool UseAutoGCSelectPolicy                     = false                               {product}
     bool UseBMI1Instructions                       = true                                {ARCH product}
     bool UseBMI2Instructions                       = true                                {ARCH product}
     bool UseBiasedLocking                          = true                                {product}
     bool UseBimorphicInlining                      = true                                {C2 product}
     bool UseBoundThreads                           = true                                {product}
     bool UseCLMUL                                  = true                                {ARCH product}
     bool UseCMSBestFit                             = true                                {product}
     bool UseCMSCollectionPassing                   = true                                {product}
     bool UseCMSCompactAtFullCollection             = true                                {product}
     bool UseCMSInitiatingOccupancyOnly             = false                               {product}
     bool UseCRC32Intrinsics                        = true                                {product}
     bool UseCodeCacheFlushing                      = true                                {product}
     bool UseCompiler                               = true                                {product}
     bool UseCompilerSafepoints                     = true                                {product}
     bool UseCompressedClassPointers                = false                               {lp64_product}
     bool UseCompressedOops                         = false                               {lp64_product}
     bool UseConcMarkSweepGC                        = false                               {product}
     bool UseCondCardMark                           = false                               {C2 product}
     bool UseContainerSupport                       = true                                {product}
     bool UseCountLeadingZerosInstruction           = true                                {ARCH product}
     bool UseCountTrailingZerosInstruction          = true                                {ARCH product}
     bool UseCountedLoopSafepoints                  = false                               {C2 product}
     bool UseCounterDecay                           = true                                {product}
     bool UseDivMod                                 = true                                {C2 product}
     bool UseDynamicNumberOfGCThreads               = false                               {product}
     bool UseFPUForSpilling                         = true                                {C2 product}
     bool UseFastAccessorMethods                    = false                               {product}
     bool UseFastEmptyMethods                       = false                               {product}
     bool UseFastJNIAccessors                       = true                                {product}
     bool UseFastStosb                              = true                                {ARCH product}
     bool UseG1GC                                   = false                               {product}
     bool UseGCLogFileRotation                      = false                               {product}
     bool UseGCOverheadLimit                        = true                                {product}
     bool UseGCTaskAffinity                         = false                               {product}
     bool UseGHASHIntrinsics                        = true                                {product}
     bool UseHeavyMonitors                          = false                               {product}
     bool UseHugeTLBFS                              = false                               {product}
     bool UseImplicitStableValues                   = true                                {C2 diagnostic}
     bool UseIncDec                                 = true                                {ARCH diagnostic}
     bool UseInlineCaches                           = true                                {product}
     bool UseInlineDepthForSpeculativeTypes         = true                                {C2 diagnostic}
     bool UseInterpreter                            = true                                {product}
     bool UseJumpTables                             = true                                {C2 product}
     bool UseLWPSynchronization                     = true                                {product}
     bool UseLargePages                             = false                               {pd product}
     bool UseLargePagesInMetaspace                  = false                               {product}
     bool UseLargePagesIndividualAllocation         = false                               {pd product}
     bool UseLinuxPosixThreadCPUClocks              = true                                {product}
     bool UseLockedTracing                          = false                               {product}
     bool UseLoopCounter                            = true                                {product}
     bool UseLoopInvariantCodeMotion                = true                                {C1 product}
     bool UseLoopPredicate                          = true                                {C2 product}
     bool UseMathExactIntrinsics                    = true                                {C2 product}
     bool UseMaximumCompactionOnSystemGC            = true                                {product}
     bool UseMembar                                 = false                               {pd product}
     bool UseMontgomeryMultiplyIntrinsic            = true                                {C2 product}
     bool UseMontgomerySquareIntrinsic              = true                                {C2 product}
     bool UseMulAddIntrinsic                        = true                                {C2 product}
     bool UseMultiplyToLenIntrinsic                 = true                                {C2 product}
     bool UseNUMA                                  := false                               {product}
     bool UseNUMAInterleaving                       = false                               {product}
     bool UseNewCode                                = false                               {diagnostic}
     bool UseNewCode2                               = false                               {diagnostic}
     bool UseNewCode3                               = false                               {diagnostic}
     bool UseNewLongLShift                          = false                               {ARCH product}
     bool UseOSErrorReporting                       = false                               {pd product}
     bool UseOldInlining                            = true                                {C2 product}
     bool UseOnStackReplacement                     = true                                {pd product}
     bool UseOnlyInlinedBimorphic                   = true                                {C2 product}
     bool UseOprofile                               = false                               {product}
     bool UseOptoBiasInlining                       = true                                {C2 product}
     bool UsePSAdaptiveSurvivorSizePolicy           = true                                {product}
     bool UseParNewGC                               = false                               {product}
     bool UseParallelGC                            := true                                {product}
     bool UseParallelOldGC                         := true                                {product}
     bool UsePerfData                               = true                                {product}
     bool UsePopCountInstruction                    = true                                {product}
     bool UseRDPCForConstantTableBase               = false                               {C2 product}
     bool UseRTMDeopt                               = false                               {ARCH product}
     bool UseRTMLocking                             = false                               {ARCH product}
     bool UseSHA                                    = false                               {product}
     bool UseSHA1Intrinsics                         = false                               {product}
     bool UseSHA256Intrinsics                       = false                               {product}
     bool UseSHA512Intrinsics                       = false                               {product}
     bool UseSHM                                    = false                               {product}
     intx UseSSE                                    = 4                                   {product}
     bool UseSSE42Intrinsics                        = true                                {product}
     bool UseSerialGC                               = false                               {product}
     bool UseSharedSpaces                           = false                               {product}
     bool UseSignalChaining                         = true                                {product}
     bool UseSquareToLenIntrinsic                   = true                                {C2 product}
     bool UseStoreImmI16                            = false                               {ARCH product}
     bool UseStringDeduplication                    = false                               {product}
     bool UseSuperWord                              = true                                {C2 product}
     bool UseTLAB                                   = true                                {pd product}
     bool UseThreadPriorities                       = true                                {pd product}
     bool UseTransparentHugePages                   = false                               {product}
     bool UseTypeProfile                            = true                                {product}
     bool UseTypeSpeculation                        = true                                {C2 product}
     bool UseUnalignedLoadStores                    = true                                {ARCH product}
     bool UseVMInterruptibleIO                      = false                               {product}
     bool UseXMMForArrayCopy                        = true                                {product}
     bool UseXmmI2D                                 = false                               {ARCH product}
     bool UseXmmI2F                                 = false                               {ARCH product}
     bool UseXmmLoadAndClearUpper                   = true                                {ARCH product}
     bool UseXmmRegToRegMoveAll                     = true                                {ARCH product}
     bool VMThreadHintNoPreempt                     = false                               {product}
     intx VMThreadPriority                          = -1                                  {product}
     intx VMThreadStackSize                         = 1024                                {pd product}
     intx ValueMapInitialSize                       = 11                                  {C1 product}
     intx ValueMapMaxLoopSize                       = 8                                   {C1 product}
     intx ValueSearchLimit                          = 1000                                {C2 product}
     bool VerboseVerification                       = false                               {diagnostic}
     bool VerifyAdapterCalls                        = false                               {diagnostic}
     bool VerifyAfterGC                             = false                               {diagnostic}
     bool VerifyBeforeExit                          = false                               {diagnostic}
     bool VerifyBeforeGC                            = false                               {diagnostic}
     bool VerifyBeforeIteration                     = false                               {diagnostic}
     bool VerifyDuringGC                            = false                               {diagnostic}
     bool VerifyDuringStartup                       = false                               {diagnostic}
     intx VerifyGCLevel                             = 0                                   {diagnostic}
    uintx VerifyGCStartAt                           = 0                                   {diagnostic}
     bool VerifyMergedCPBytecodes                   = true                                {product}
     bool VerifyMethodHandles                       = false                               {diagnostic}
     bool VerifyObjectStartArray                    = true                                {diagnostic}
     bool VerifyRememberedSets                      = false                               {diagnostic}
     bool VerifySharedSpaces                        = false                               {product}
     bool VerifySilently                            = false                               {diagnostic}
     bool VerifyStringTableAtExit                   = false                               {diagnostic}
ccstrlist VerifySubSet                              =                                     {diagnostic}
     bool WhiteBoxAPI                               = false                               {diagnostic}
     intx WorkAroundNPTLTimedWaitHang               = 1                                   {product}
    uintx YoungGenerationSizeIncrement              = 20                                  {product}
    uintx YoungGenerationSizeSupplement             = 80                                  {product}
    uintx YoungGenerationSizeSupplementDecay        = 8                                   {product}
    uintx YoungPLABSize                             = 4096                                {product}
     bool ZeroTLAB                                  = false                               {product}
     intx hashCode                                  = 5                                   {product}
INFO  [GiraphYarnTask] Yarn client user: kbhit
INFO  [GiraphYarnTask] Setting up
INFO  [GiraphYarnTask$1] [STATUS: task-1] setup: Beginning worker setup.
INFO  [GraphTaskManager] setup: Log level remains at error
INFO    2020-03-22 12:33:43,159 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] setup: Initializing Zookeeper services.
INFO    2020-03-22 12:33:43,185 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] setup: Connected to Zookeeper service iga-adi-m:2181
INFO    2020-03-22 12:33:43,185 [main] org.apache.giraph.graph.GraphTaskManager  - setup: Starting up BspServiceWorker...
INFO    2020-03-22 12:33:43,528 [main] org.apache.giraph.graph.GraphTaskManager  - setup: Registering health of this worker...
INFO    2020-03-22 12:33:43,528 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] WORKER_ONLY starting...
INFO    2020-03-22 12:33:43,528 [main] org.apache.giraph.yarn.GiraphYarnTask  - Executing
INFO    2020-03-22 12:33:43,546 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=-1 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/-1/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:46,817 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:46,817 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=-1
INFO    2020-03-22 12:33:49,912 [main] org.apache.giraph.worker.BspServiceWorker  - loadInputSplits: Using 64 thread(s), originally 64 threads(s)
INFO    2020-03-22 12:33:51,708 [main] org.apache.giraph.worker.BspServiceWorker  - loadInputSplits: Using 64 thread(s), originally 64 threads(s)
INFO    2020-03-22 12:33:51,904 [Service Thread] org.apache.giraph.graph.GraphTaskManager  - installGCMonitoring: name = PS Scavenge, action = end of minor GC, cause = Metadata GC Threshold, duration = 19ms
INFO    2020-03-22 12:33:51,906 [Service Thread] org.apache.giraph.graph.GraphTaskManager  - installGCMonitoring: name = PS MarkSweep, action = end of major GC, cause = Metadata GC Threshold, duration = 112ms
INFO    2020-03-22 12:33:51,969 [main] org.apache.giraph.worker.BspServiceWorker  - setup: Finally loaded a total of (v=319, e=444)
INFO    2020-03-22 12:33:52,009 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep -1 Memory (free/total/max) = 90391.03M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:52,016 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep -1, messages = 0 , message bytes = 0 , Memory (free/total/max) = 90391.03M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:52,024 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=-1
INFO    2020-03-22 12:33:52,024 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=-1
INFO    2020-03-22 12:33:52,054 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep -1 with global stats (vtx=319,finVtx=0,edges=444,msgCount=0,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.initialisation.InitialComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@71a3a190,outgoing=org.apache.giraph.conf.DefaultMessageClasses@588ffeb)
INFO    2020-03-22 12:33:52,055 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=-1
INFO    2020-03-22 12:33:52,072 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=0 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/0/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:52,079 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:52,079 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=0
INFO    2020-03-22 12:33:52,081 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:52,081 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:52,085 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 0
INFO    2020-03-22 12:33:52,127 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 0 Memory (free/total/max) = 90217.54M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:52,130 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 0, messages = 192 , message bytes = 0 , Memory (free/total/max) = 90217.54M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:52,132 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=0
INFO    2020-03-22 12:33:52,132 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=0
INFO    2020-03-22 12:33:52,140 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 0 with global stats (vtx=319,finVtx=319,edges=444,msgCount=192,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@13cd7ea5,outgoing=org.apache.giraph.conf.DefaultMessageClasses@102d92c4)
INFO    2020-03-22 12:33:52,141 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=0
INFO    2020-03-22 12:33:52,152 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=1 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/1/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:52,160 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:52,160 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=1
INFO    2020-03-22 12:33:52,162 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:52,162 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:52,163 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 1
INFO    2020-03-22 12:33:52,247 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 1 Memory (free/total/max) = 89905.80M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:52,248 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 1, messages = 64 , message bytes = 0 , Memory (free/total/max) = 89905.80M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:52,252 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=1
INFO    2020-03-22 12:33:52,252 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=1
INFO    2020-03-22 12:33:52,258 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 1 with global stats (vtx=319,finVtx=319,edges=444,msgCount=64,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@160396db,outgoing=org.apache.giraph.conf.DefaultMessageClasses@7a799159)
INFO    2020-03-22 12:33:52,258 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=1
INFO    2020-03-22 12:33:52,266 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:52,276 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=2 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/2/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:52,280 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:52,280 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=2
INFO    2020-03-22 12:33:52,281 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:52,282 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:52,282 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 2
INFO    2020-03-22 12:33:52,323 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 2 Memory (free/total/max) = 89701.95M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:52,325 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 2, messages = 32 , message bytes = 0 , Memory (free/total/max) = 89701.95M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:52,327 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=2
INFO    2020-03-22 12:33:52,327 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=2
INFO    2020-03-22 12:33:52,334 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 2 with global stats (vtx=319,finVtx=319,edges=444,msgCount=32,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@425d5d46,outgoing=org.apache.giraph.conf.DefaultMessageClasses@198ef2ce)
INFO    2020-03-22 12:33:52,334 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=2
INFO    2020-03-22 12:33:52,340 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:52,349 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=3 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/3/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:52,353 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:52,353 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=3
INFO    2020-03-22 12:33:52,355 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:52,355 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:52,356 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 3
INFO    2020-03-22 12:33:52,396 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 3 Memory (free/total/max) = 89528.47M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:52,397 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 3, messages = 16 , message bytes = 0 , Memory (free/total/max) = 89528.47M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:52,399 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=3
INFO    2020-03-22 12:33:52,399 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=3
INFO    2020-03-22 12:33:52,406 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 3 with global stats (vtx=319,finVtx=319,edges=444,msgCount=16,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@4d8126f,outgoing=org.apache.giraph.conf.DefaultMessageClasses@6d3c232f)
INFO    2020-03-22 12:33:52,406 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=3
INFO    2020-03-22 12:33:52,412 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:52,421 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=4 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/4/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:52,426 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:52,426 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=4
INFO    2020-03-22 12:33:52,428 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:52,428 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:52,429 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 4
INFO    2020-03-22 12:33:52,466 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 4 Memory (free/total/max) = 89327.34M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:52,467 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 4, messages = 8 , message bytes = 0 , Memory (free/total/max) = 89327.34M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:52,469 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=4
INFO    2020-03-22 12:33:52,469 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=4
INFO    2020-03-22 12:33:52,475 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 4 with global stats (vtx=319,finVtx=319,edges=444,msgCount=8,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@3a022576,outgoing=org.apache.giraph.conf.DefaultMessageClasses@2dbd803f)
INFO    2020-03-22 12:33:52,475 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=4
INFO    2020-03-22 12:33:52,483 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:52,489 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=5 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/5/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:52,496 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:52,496 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=5
INFO    2020-03-22 12:33:52,498 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:52,498 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:52,499 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 5
INFO    2020-03-22 12:33:52,531 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 5 Memory (free/total/max) = 89140.04M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:52,532 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 5, messages = 4 , message bytes = 0 , Memory (free/total/max) = 89140.04M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:52,534 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=5
INFO    2020-03-22 12:33:52,534 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=5
INFO    2020-03-22 12:33:52,540 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 5 with global stats (vtx=319,finVtx=319,edges=444,msgCount=4,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@1d12b024,outgoing=org.apache.giraph.conf.DefaultMessageClasses@72fe8a4f)
INFO    2020-03-22 12:33:52,540 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=5
INFO    2020-03-22 12:33:52,548 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:52,557 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=6 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/6/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:52,560 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:52,560 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=6
INFO    2020-03-22 12:33:52,561 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:52,562 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:52,562 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 6
INFO    2020-03-22 12:33:52,595 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 6 Memory (free/total/max) = 88966.57M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:52,596 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 6, messages = 2 , message bytes = 0 , Memory (free/total/max) = 88966.57M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:52,598 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=6
INFO    2020-03-22 12:33:52,598 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=6
INFO    2020-03-22 12:33:52,605 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 6 with global stats (vtx=319,finVtx=319,edges=444,msgCount=2,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@71870da7,outgoing=org.apache.giraph.conf.DefaultMessageClasses@6dd91637)
INFO    2020-03-22 12:33:52,606 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=6
INFO    2020-03-22 12:33:52,611 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:52,618 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=7 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/7/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:52,622 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:52,622 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=7
INFO    2020-03-22 12:33:52,624 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:52,624 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:52,624 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 7
INFO    2020-03-22 12:33:52,657 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 7 Memory (free/total/max) = 88793.09M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:52,658 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 7, messages = 2 , message bytes = 0 , Memory (free/total/max) = 88793.09M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:52,662 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=7
INFO    2020-03-22 12:33:52,662 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=7
INFO    2020-03-22 12:33:52,666 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 7 with global stats (vtx=319,finVtx=319,edges=444,msgCount=2,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@63ec445c,outgoing=org.apache.giraph.conf.DefaultMessageClasses@3104351d)
INFO    2020-03-22 12:33:52,667 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=7
INFO    2020-03-22 12:33:52,672 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:52,681 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=8 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/8/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:52,684 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:52,684 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=8
INFO    2020-03-22 12:33:52,686 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:52,686 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:52,687 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 8
INFO    2020-03-22 12:33:52,719 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 8 Memory (free/total/max) = 88619.61M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:52,720 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 8, messages = 4 , message bytes = 0 , Memory (free/total/max) = 88619.61M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:52,722 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=8
INFO    2020-03-22 12:33:52,722 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=8
INFO    2020-03-22 12:33:52,728 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 8 with global stats (vtx=319,finVtx=319,edges=444,msgCount=4,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@48c3205a,outgoing=org.apache.giraph.conf.DefaultMessageClasses@121c54fa)
INFO    2020-03-22 12:33:52,728 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=8
INFO    2020-03-22 12:33:52,733 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:52,744 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=9 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/9/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:52,748 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:52,748 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=9
INFO    2020-03-22 12:33:52,749 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:52,749 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:52,750 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 9
INFO    2020-03-22 12:33:52,778 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 9 Memory (free/total/max) = 88446.13M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:52,780 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 9, messages = 8 , message bytes = 0 , Memory (free/total/max) = 88446.13M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:52,782 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=9
INFO    2020-03-22 12:33:52,782 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=9
INFO    2020-03-22 12:33:52,787 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 9 with global stats (vtx=319,finVtx=319,edges=444,msgCount=8,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@4a3be6a5,outgoing=org.apache.giraph.conf.DefaultMessageClasses@7cf162bc)
INFO    2020-03-22 12:33:52,787 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=9
INFO    2020-03-22 12:33:52,793 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:52,801 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=10 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/10/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:52,806 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:52,806 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=10
INFO    2020-03-22 12:33:52,807 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:52,807 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:52,808 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 10
INFO    2020-03-22 12:33:52,838 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 10 Memory (free/total/max) = 88272.66M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:52,839 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 10, messages = 16 , message bytes = 0 , Memory (free/total/max) = 88272.66M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:52,842 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=10
INFO    2020-03-22 12:33:52,842 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=10
INFO    2020-03-22 12:33:52,847 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 10 with global stats (vtx=319,finVtx=319,edges=444,msgCount=16,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@15bc339,outgoing=org.apache.giraph.conf.DefaultMessageClasses@7e75bf2d)
INFO    2020-03-22 12:33:52,847 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=10
INFO    2020-03-22 12:33:52,853 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:52,860 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=11 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/11/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:52,865 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:52,865 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=11
INFO    2020-03-22 12:33:52,866 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:52,866 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:52,867 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 11
INFO    2020-03-22 12:33:52,896 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 11 Memory (free/total/max) = 88099.18M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:52,897 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 11, messages = 32 , message bytes = 0 , Memory (free/total/max) = 88099.18M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:52,899 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=11
INFO    2020-03-22 12:33:52,899 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=11
INFO    2020-03-22 12:33:52,904 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 11 with global stats (vtx=319,finVtx=319,edges=444,msgCount=32,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@29c2c826,outgoing=org.apache.giraph.conf.DefaultMessageClasses@3350ebdd)
INFO    2020-03-22 12:33:52,904 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=11
INFO    2020-03-22 12:33:52,909 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:52,918 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=12 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/12/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:52,923 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:52,923 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=12
INFO    2020-03-22 12:33:52,924 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:52,924 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:52,925 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 12
INFO    2020-03-22 12:33:52,955 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 12 Memory (free/total/max) = 87925.70M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:52,956 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 12, messages = 64 , message bytes = 0 , Memory (free/total/max) = 87925.70M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:52,958 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=12
INFO    2020-03-22 12:33:52,958 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=12
INFO    2020-03-22 12:33:52,965 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 12 with global stats (vtx=319,finVtx=319,edges=444,msgCount=64,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@59532566,outgoing=org.apache.giraph.conf.DefaultMessageClasses@dca2615)
INFO    2020-03-22 12:33:52,965 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=12
INFO    2020-03-22 12:33:52,973 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:52,983 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=13 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/13/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:52,988 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:52,988 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=13
INFO    2020-03-22 12:33:52,989 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:52,989 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:52,990 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 13
INFO    2020-03-22 12:33:53,037 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 13 Memory (free/total/max) = 87752.22M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:53,039 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 13, messages = 0 , message bytes = 0 , Memory (free/total/max) = 87752.22M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:53,041 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=13
INFO    2020-03-22 12:33:53,041 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=13
INFO    2020-03-22 12:33:53,047 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 13 with global stats (vtx=319,finVtx=255,edges=444,msgCount=0,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.transposition.TranspositionComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@35636217,outgoing=org.apache.giraph.conf.DefaultMessageClasses@1549bba7)
INFO    2020-03-22 12:33:53,047 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=13
INFO    2020-03-22 12:33:53,053 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:53,063 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=14 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/14/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:53,068 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:53,068 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=14
INFO    2020-03-22 12:33:53,069 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:53,070 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:53,070 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 14
INFO    2020-03-22 12:33:53,115 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 14 Memory (free/total/max) = 87578.75M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:53,117 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 14, messages = 12288 , message bytes = 0 , Memory (free/total/max) = 87578.75M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:53,119 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=14
INFO    2020-03-22 12:33:53,119 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=14
INFO    2020-03-22 12:33:53,125 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 14 with global stats (vtx=319,finVtx=319,edges=444,msgCount=12288,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.transposition.TranspositionComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@345cf395,outgoing=org.apache.giraph.conf.DefaultMessageClasses@bc4d5e1)
INFO    2020-03-22 12:33:53,125 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=14
INFO    2020-03-22 12:33:53,133 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:53,139 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=15 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/15/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:53,144 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:53,144 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=15
INFO    2020-03-22 12:33:53,146 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:53,146 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:53,147 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 15
INFO    2020-03-22 12:33:53,219 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 15 Memory (free/total/max) = 87363.80M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:53,220 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 15, messages = 0 , message bytes = 0 , Memory (free/total/max) = 87363.80M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:53,222 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=15
INFO    2020-03-22 12:33:53,222 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=15
INFO    2020-03-22 12:33:53,228 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 15 with global stats (vtx=319,finVtx=127,edges=444,msgCount=0,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.initialisation.InitialComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@3c904f1e,outgoing=org.apache.giraph.conf.DefaultMessageClasses@4eb30d44)
INFO    2020-03-22 12:33:53,229 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=15
INFO    2020-03-22 12:33:53,235 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:53,243 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=16 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/16/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:53,246 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:53,246 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=16
INFO    2020-03-22 12:33:53,247 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:53,247 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:53,248 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 16
INFO    2020-03-22 12:33:53,280 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 16 Memory (free/total/max) = 87190.32M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:53,281 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 16, messages = 192 , message bytes = 0 , Memory (free/total/max) = 87190.32M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:53,284 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=16
INFO    2020-03-22 12:33:53,284 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=16
INFO    2020-03-22 12:33:53,290 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 16 with global stats (vtx=319,finVtx=319,edges=444,msgCount=192,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@692fd26,outgoing=org.apache.giraph.conf.DefaultMessageClasses@36f1046f)
INFO    2020-03-22 12:33:53,291 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=16
INFO    2020-03-22 12:33:53,297 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:53,301 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=17 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/17/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:53,309 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:53,309 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=17
INFO    2020-03-22 12:33:53,312 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:53,312 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:53,312 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 17
INFO    2020-03-22 12:33:53,348 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 17 Memory (free/total/max) = 87011.42M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:53,349 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 17, messages = 64 , message bytes = 0 , Memory (free/total/max) = 87011.42M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:53,351 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=17
INFO    2020-03-22 12:33:53,351 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=17
INFO    2020-03-22 12:33:53,357 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 17 with global stats (vtx=319,finVtx=319,edges=444,msgCount=64,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@c6634d,outgoing=org.apache.giraph.conf.DefaultMessageClasses@65f58c6e)
INFO    2020-03-22 12:33:53,357 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=17
INFO    2020-03-22 12:33:53,363 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:53,367 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=18 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/18/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:53,376 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:53,376 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=18
INFO    2020-03-22 12:33:53,378 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:53,378 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:53,378 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 18
INFO    2020-03-22 12:33:53,413 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 18 Memory (free/total/max) = 86835.23M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:53,414 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 18, messages = 32 , message bytes = 0 , Memory (free/total/max) = 86835.23M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:53,416 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=18
INFO    2020-03-22 12:33:53,416 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=18
INFO    2020-03-22 12:33:53,421 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 18 with global stats (vtx=319,finVtx=319,edges=444,msgCount=32,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@15f2eda3,outgoing=org.apache.giraph.conf.DefaultMessageClasses@34cf294c)
INFO    2020-03-22 12:33:53,421 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=18
INFO    2020-03-22 12:33:53,427 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:53,434 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=19 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/19/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:53,439 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:53,439 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=19
INFO    2020-03-22 12:33:53,440 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:53,441 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:53,441 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 19
INFO    2020-03-22 12:33:53,480 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 19 Memory (free/total/max) = 86592.63M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:53,481 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 19, messages = 16 , message bytes = 0 , Memory (free/total/max) = 86592.63M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:53,483 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=19
INFO    2020-03-22 12:33:53,483 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=19
INFO    2020-03-22 12:33:53,488 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 19 with global stats (vtx=319,finVtx=319,edges=444,msgCount=16,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@37ce3644,outgoing=org.apache.giraph.conf.DefaultMessageClasses@4b869331)
INFO    2020-03-22 12:33:53,488 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=19
INFO    2020-03-22 12:33:53,495 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:53,501 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=20 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/20/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:53,508 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:53,508 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=20
INFO    2020-03-22 12:33:53,510 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:53,510 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:53,510 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 20
INFO    2020-03-22 12:33:53,542 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 20 Memory (free/total/max) = 86377.68M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:53,543 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 20, messages = 8 , message bytes = 0 , Memory (free/total/max) = 86377.68M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:53,545 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=20
INFO    2020-03-22 12:33:53,545 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=20
INFO    2020-03-22 12:33:53,550 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 20 with global stats (vtx=319,finVtx=319,edges=444,msgCount=8,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@7318daf8,outgoing=org.apache.giraph.conf.DefaultMessageClasses@70f31322)
INFO    2020-03-22 12:33:53,550 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=20
INFO    2020-03-22 12:33:53,556 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:53,563 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=21 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/21/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:53,569 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:53,570 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=21
INFO    2020-03-22 12:33:53,571 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:53,571 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:53,571 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 21
INFO    2020-03-22 12:33:53,600 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 21 Memory (free/total/max) = 86204.21M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:53,602 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 21, messages = 4 , message bytes = 0 , Memory (free/total/max) = 86204.21M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:53,603 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=21
INFO    2020-03-22 12:33:53,604 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=21
INFO    2020-03-22 12:33:53,608 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 21 with global stats (vtx=319,finVtx=319,edges=444,msgCount=4,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@3b90a30a,outgoing=org.apache.giraph.conf.DefaultMessageClasses@69fa8e76)
INFO    2020-03-22 12:33:53,608 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=21
INFO    2020-03-22 12:33:53,615 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:53,624 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=22 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/22/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:53,628 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:53,628 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=22
INFO    2020-03-22 12:33:53,629 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:53,630 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:53,630 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 22
INFO    2020-03-22 12:33:53,656 [Service Thread] org.apache.giraph.graph.GraphTaskManager  - installGCMonitoring: name = PS Scavenge, action = end of minor GC, cause = Allocation Failure, duration = 9ms
INFO    2020-03-22 12:33:53,672 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 22 Memory (free/total/max) = 92595.37M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:53,675 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 22, messages = 2 , message bytes = 0 , Memory (free/total/max) = 92570.62M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:53,678 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=22
INFO    2020-03-22 12:33:53,678 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=22
INFO    2020-03-22 12:33:53,683 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 22 with global stats (vtx=319,finVtx=319,edges=444,msgCount=2,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@1fba386c,outgoing=org.apache.giraph.conf.DefaultMessageClasses@7e736350)
INFO    2020-03-22 12:33:53,683 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=22
INFO    2020-03-22 12:33:53,691 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:53,698 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=23 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/23/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:53,702 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:53,702 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=23
INFO    2020-03-22 12:33:53,703 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:53,703 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:53,704 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 23
INFO    2020-03-22 12:33:53,732 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 23 Memory (free/total/max) = 92358.40M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:53,733 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 23, messages = 2 , message bytes = 0 , Memory (free/total/max) = 92340.42M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:53,735 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=23
INFO    2020-03-22 12:33:53,735 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=23
INFO    2020-03-22 12:33:53,741 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 23 with global stats (vtx=319,finVtx=319,edges=444,msgCount=2,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@58ec7116,outgoing=org.apache.giraph.conf.DefaultMessageClasses@63bde6c2)
INFO    2020-03-22 12:33:53,741 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=23
INFO    2020-03-22 12:33:53,747 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:53,754 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=24 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/24/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:53,760 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:53,760 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=24
INFO    2020-03-22 12:33:53,761 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:53,761 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:53,762 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 24
INFO    2020-03-22 12:33:53,782 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 24 Memory (free/total/max) = 92187.60M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:53,784 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 24, messages = 4 , message bytes = 0 , Memory (free/total/max) = 92178.62M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:53,788 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=24
INFO    2020-03-22 12:33:53,788 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=24
INFO    2020-03-22 12:33:53,792 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 24 with global stats (vtx=319,finVtx=319,edges=444,msgCount=4,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@4e73b552,outgoing=org.apache.giraph.conf.DefaultMessageClasses@221dad51)
INFO    2020-03-22 12:33:53,792 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=24
INFO    2020-03-22 12:33:53,799 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:53,807 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=25 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/25/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:53,810 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:53,810 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=25
INFO    2020-03-22 12:33:53,812 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:53,812 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:53,813 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 25
INFO    2020-03-22 12:33:53,841 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 25 Memory (free/total/max) = 92007.82M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:53,843 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 25, messages = 8 , message bytes = 0 , Memory (free/total/max) = 91998.84M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:53,845 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=25
INFO    2020-03-22 12:33:53,845 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=25
INFO    2020-03-22 12:33:53,849 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 25 with global stats (vtx=319,finVtx=319,edges=444,msgCount=8,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@42d73c61,outgoing=org.apache.giraph.conf.DefaultMessageClasses@5a8cbffe)
INFO    2020-03-22 12:33:53,849 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=25
INFO    2020-03-22 12:33:53,855 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:53,863 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=26 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/26/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:53,868 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:53,868 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=26
INFO    2020-03-22 12:33:53,869 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:53,869 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:53,870 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 26
INFO    2020-03-22 12:33:53,898 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 26 Memory (free/total/max) = 91828.04M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:53,899 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 26, messages = 16 , message bytes = 0 , Memory (free/total/max) = 91828.04M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:53,901 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=26
INFO    2020-03-22 12:33:53,901 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=26
INFO    2020-03-22 12:33:53,906 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 26 with global stats (vtx=319,finVtx=319,edges=444,msgCount=16,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@4eb45fec,outgoing=org.apache.giraph.conf.DefaultMessageClasses@211febf3)
INFO    2020-03-22 12:33:53,906 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=26
INFO    2020-03-22 12:33:53,912 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:53,919 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=27 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/27/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:53,925 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:53,925 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=27
INFO    2020-03-22 12:33:53,926 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:53,926 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:53,926 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 27
INFO    2020-03-22 12:33:53,953 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 27 Memory (free/total/max) = 91684.21M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:53,955 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 27, messages = 32 , message bytes = 0 , Memory (free/total/max) = 91684.21M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:53,957 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=27
INFO    2020-03-22 12:33:53,957 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=27
INFO    2020-03-22 12:33:53,962 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 27 with global stats (vtx=319,finVtx=319,edges=444,msgCount=32,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@14d8444b,outgoing=org.apache.giraph.conf.DefaultMessageClasses@71466383)
INFO    2020-03-22 12:33:53,962 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=27
INFO    2020-03-22 12:33:53,968 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:53,975 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=28 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/28/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:53,982 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:53,982 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=28
INFO    2020-03-22 12:33:53,983 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:53,983 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:53,983 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 28
INFO    2020-03-22 12:33:54,009 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 28 Memory (free/total/max) = 91556.65M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:54,011 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 28, messages = 64 , message bytes = 0 , Memory (free/total/max) = 91556.65M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:54,013 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=28
INFO    2020-03-22 12:33:54,013 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=28
INFO    2020-03-22 12:33:54,018 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 28 with global stats (vtx=319,finVtx=319,edges=444,msgCount=64,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@149c3204,outgoing=org.apache.giraph.conf.DefaultMessageClasses@64f16277)
INFO    2020-03-22 12:33:54,018 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=28
INFO    2020-03-22 12:33:54,024 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:54,032 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=29 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/29/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:54,036 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:54,036 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=29
INFO    2020-03-22 12:33:54,037 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:54,037 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:54,038 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 29
INFO    2020-03-22 12:33:54,062 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 29 Memory (free/total/max) = 91366.20M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:54,064 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 29, messages = 0 , message bytes = 0 , Memory (free/total/max) = 91348.23M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:54,066 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=29
INFO    2020-03-22 12:33:54,066 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=29
INFO    2020-03-22 12:33:54,072 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 29 with global stats (vtx=319,finVtx=255,edges=444,msgCount=0,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.initialisation.InitialisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@36fc05ff,outgoing=org.apache.giraph.conf.DefaultMessageClasses@57c47a9e)
INFO    2020-03-22 12:33:54,072 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=29
INFO    2020-03-22 12:33:54,080 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:54,087 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=30 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/30/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:54,090 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:54,090 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=30
INFO    2020-03-22 12:33:54,091 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:54,091 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:54,092 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 30
INFO    2020-03-22 12:33:54,126 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 30 Memory (free/total/max) = 91195.41M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:54,128 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 30, messages = 318 , message bytes = 0 , Memory (free/total/max) = 91195.41M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:54,130 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=30
INFO    2020-03-22 12:33:54,130 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=30
INFO    2020-03-22 12:33:54,135 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 30 with global stats (vtx=319,finVtx=319,edges=444,msgCount=318,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.initialisation.InitialisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@28501a4b,outgoing=org.apache.giraph.conf.DefaultMessageClasses@5b051a5c)
INFO    2020-03-22 12:33:54,135 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=30
INFO    2020-03-22 12:33:54,141 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:54,148 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=31 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/31/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:54,152 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:54,152 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=31
INFO    2020-03-22 12:33:54,154 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:54,154 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:54,154 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 31
INFO    2020-03-22 12:33:54,537 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 31 Memory (free/total/max) = 90782.00M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:54,538 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 31, messages = 0 , message bytes = 0 , Memory (free/total/max) = 90782.00M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:54,540 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=31
INFO    2020-03-22 12:33:54,540 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=31
INFO    2020-03-22 12:33:54,547 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 31 with global stats (vtx=319,finVtx=127,edges=444,msgCount=0,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.initialisation.InitialComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@4d1f1ff5,outgoing=org.apache.giraph.conf.DefaultMessageClasses@222afc67)
INFO    2020-03-22 12:33:54,547 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=31
INFO    2020-03-22 12:33:54,553 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:54,560 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=32 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/32/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:54,564 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:54,564 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=32
INFO    2020-03-22 12:33:54,566 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:54,566 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:54,566 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 32
INFO    2020-03-22 12:33:54,597 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 32 Memory (free/total/max) = 90629.18M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:54,598 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 32, messages = 192 , message bytes = 0 , Memory (free/total/max) = 90629.18M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:54,601 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=32
INFO    2020-03-22 12:33:54,601 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=32
INFO    2020-03-22 12:33:54,605 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 32 with global stats (vtx=319,finVtx=319,edges=444,msgCount=192,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@4e1ce44,outgoing=org.apache.giraph.conf.DefaultMessageClasses@69228e85)
INFO    2020-03-22 12:33:54,605 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=32
INFO    2020-03-22 12:33:54,611 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:54,620 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=33 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/33/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:54,623 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:54,623 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=33
INFO    2020-03-22 12:33:54,626 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:54,626 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:54,626 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 33
INFO    2020-03-22 12:33:54,648 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 33 Memory (free/total/max) = 90485.35M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:54,649 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 33, messages = 64 , message bytes = 0 , Memory (free/total/max) = 90485.35M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:54,651 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=33
INFO    2020-03-22 12:33:54,651 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=33
INFO    2020-03-22 12:33:54,655 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 33 with global stats (vtx=319,finVtx=319,edges=444,msgCount=64,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@1daf3b44,outgoing=org.apache.giraph.conf.DefaultMessageClasses@7fd8c559)
INFO    2020-03-22 12:33:54,655 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=33
INFO    2020-03-22 12:33:54,661 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:54,671 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=34 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/34/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:54,673 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:54,673 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=34
INFO    2020-03-22 12:33:54,675 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:54,675 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:54,675 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 34
INFO    2020-03-22 12:33:54,696 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 34 Memory (free/total/max) = 90374.08M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:54,697 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 34, messages = 32 , message bytes = 0 , Memory (free/total/max) = 90374.08M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:54,698 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=34
INFO    2020-03-22 12:33:54,698 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=34
INFO    2020-03-22 12:33:54,703 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 34 with global stats (vtx=319,finVtx=319,edges=444,msgCount=32,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@27df0f3d,outgoing=org.apache.giraph.conf.DefaultMessageClasses@c35af2a)
INFO    2020-03-22 12:33:54,703 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=34
INFO    2020-03-22 12:33:54,708 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:54,718 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=35 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/35/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:54,720 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:54,720 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=35
INFO    2020-03-22 12:33:54,722 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:54,722 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:54,722 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 35
INFO    2020-03-22 12:33:54,741 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 35 Memory (free/total/max) = 90219.08M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:54,742 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 35, messages = 16 , message bytes = 0 , Memory (free/total/max) = 90219.08M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:54,743 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=35
INFO    2020-03-22 12:33:54,743 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=35
INFO    2020-03-22 12:33:54,748 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 35 with global stats (vtx=319,finVtx=319,edges=444,msgCount=16,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@126f1ba8,outgoing=org.apache.giraph.conf.DefaultMessageClasses@3a08078c)
INFO    2020-03-22 12:33:54,748 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=35
INFO    2020-03-22 12:33:54,755 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:54,762 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=36 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/36/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:54,768 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:54,768 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=36
INFO    2020-03-22 12:33:54,769 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:54,769 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:54,770 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 36
INFO    2020-03-22 12:33:54,786 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 36 Memory (free/total/max) = 90111.18M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:54,787 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 36, messages = 8 , message bytes = 0 , Memory (free/total/max) = 90111.18M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:54,789 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=36
INFO    2020-03-22 12:33:54,789 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=36
INFO    2020-03-22 12:33:54,793 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 36 with global stats (vtx=319,finVtx=319,edges=444,msgCount=8,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@3f4f5330,outgoing=org.apache.giraph.conf.DefaultMessageClasses@14b7786)
INFO    2020-03-22 12:33:54,794 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=36
INFO    2020-03-22 12:33:54,800 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:54,808 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=37 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/37/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:54,812 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:54,812 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=37
INFO    2020-03-22 12:33:54,813 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:54,813 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:54,813 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 37
INFO    2020-03-22 12:33:54,837 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 37 Memory (free/total/max) = 90003.29M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:54,838 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 37, messages = 4 , message bytes = 0 , Memory (free/total/max) = 90003.29M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:54,840 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=37
INFO    2020-03-22 12:33:54,840 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=37
INFO    2020-03-22 12:33:54,845 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 37 with global stats (vtx=319,finVtx=319,edges=444,msgCount=4,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@2016f509,outgoing=org.apache.giraph.conf.DefaultMessageClasses@6f1a80fb)
INFO    2020-03-22 12:33:54,845 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=37
INFO    2020-03-22 12:33:54,852 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:54,859 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=38 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/38/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:54,863 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:54,863 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=38
INFO    2020-03-22 12:33:54,864 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:54,864 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:54,865 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 38
INFO    2020-03-22 12:33:54,885 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 38 Memory (free/total/max) = 89895.40M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:54,886 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 38, messages = 2 , message bytes = 0 , Memory (free/total/max) = 89895.40M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:54,888 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=38
INFO    2020-03-22 12:33:54,888 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=38
INFO    2020-03-22 12:33:54,894 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 38 with global stats (vtx=319,finVtx=319,edges=444,msgCount=2,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@29a1505c,outgoing=org.apache.giraph.conf.DefaultMessageClasses@24db6ce)
INFO    2020-03-22 12:33:54,894 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=38
INFO    2020-03-22 12:33:54,900 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:54,907 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=39 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/39/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:54,912 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:54,912 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=39
INFO    2020-03-22 12:33:54,914 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:54,914 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:54,914 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 39
INFO    2020-03-22 12:33:54,937 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 39 Memory (free/total/max) = 89787.50M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:54,938 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 39, messages = 2 , message bytes = 0 , Memory (free/total/max) = 89787.50M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:54,940 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=39
INFO    2020-03-22 12:33:54,940 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=39
INFO    2020-03-22 12:33:54,946 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 39 with global stats (vtx=319,finVtx=319,edges=444,msgCount=2,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@49c675f0,outgoing=org.apache.giraph.conf.DefaultMessageClasses@14823f76)
INFO    2020-03-22 12:33:54,946 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=39
INFO    2020-03-22 12:33:54,952 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:54,959 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=40 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/40/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:54,963 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:54,963 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=40
INFO    2020-03-22 12:33:54,964 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:54,964 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:54,965 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 40
INFO    2020-03-22 12:33:54,985 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 40 Memory (free/total/max) = 89679.61M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:54,986 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 40, messages = 4 , message bytes = 0 , Memory (free/total/max) = 89679.61M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:54,989 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=40
INFO    2020-03-22 12:33:54,989 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=40
INFO    2020-03-22 12:33:54,994 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 40 with global stats (vtx=319,finVtx=319,edges=444,msgCount=4,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@46185a1b,outgoing=org.apache.giraph.conf.DefaultMessageClasses@51288417)
INFO    2020-03-22 12:33:54,994 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=40
INFO    2020-03-22 12:33:54,999 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:55,009 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=41 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/41/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:55,013 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:55,013 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=41
INFO    2020-03-22 12:33:55,015 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:55,015 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:55,015 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 41
INFO    2020-03-22 12:33:55,035 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 41 Memory (free/total/max) = 89571.71M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,036 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 41, messages = 8 , message bytes = 0 , Memory (free/total/max) = 89571.71M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,037 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=41
INFO    2020-03-22 12:33:55,037 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=41
INFO    2020-03-22 12:33:55,042 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 41 with global stats (vtx=319,finVtx=319,edges=444,msgCount=8,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@1e8ab90f,outgoing=org.apache.giraph.conf.DefaultMessageClasses@78d6447a)
INFO    2020-03-22 12:33:55,042 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=41
INFO    2020-03-22 12:33:55,047 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:55,058 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=42 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/42/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:55,060 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:55,060 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=42
INFO    2020-03-22 12:33:55,061 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:55,061 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:55,062 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 42
INFO    2020-03-22 12:33:55,079 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 42 Memory (free/total/max) = 89463.82M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,080 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 42, messages = 16 , message bytes = 0 , Memory (free/total/max) = 89463.82M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,081 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=42
INFO    2020-03-22 12:33:55,081 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=42
INFO    2020-03-22 12:33:55,086 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 42 with global stats (vtx=319,finVtx=319,edges=444,msgCount=16,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@66273da0,outgoing=org.apache.giraph.conf.DefaultMessageClasses@2127e66e)
INFO    2020-03-22 12:33:55,086 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=42
INFO    2020-03-22 12:33:55,092 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:55,101 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=43 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/43/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:55,105 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:55,105 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=43
INFO    2020-03-22 12:33:55,106 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:55,106 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:55,107 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 43
INFO    2020-03-22 12:33:55,125 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 43 Memory (free/total/max) = 89355.92M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,126 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 43, messages = 32 , message bytes = 0 , Memory (free/total/max) = 89355.92M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,128 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=43
INFO    2020-03-22 12:33:55,128 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=43
INFO    2020-03-22 12:33:55,132 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 43 with global stats (vtx=319,finVtx=319,edges=444,msgCount=32,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@e84fb85,outgoing=org.apache.giraph.conf.DefaultMessageClasses@68a4dcc6)
INFO    2020-03-22 12:33:55,132 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=43
INFO    2020-03-22 12:33:55,138 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:55,146 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=44 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/44/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:55,150 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:55,150 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=44
INFO    2020-03-22 12:33:55,151 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:55,151 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:55,152 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 44
INFO    2020-03-22 12:33:55,169 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 44 Memory (free/total/max) = 89246.34M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,170 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 44, messages = 64 , message bytes = 0 , Memory (free/total/max) = 89246.34M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,171 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=44
INFO    2020-03-22 12:33:55,171 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=44
INFO    2020-03-22 12:33:55,175 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 44 with global stats (vtx=319,finVtx=319,edges=444,msgCount=64,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@5e5af8e1,outgoing=org.apache.giraph.conf.DefaultMessageClasses@30b131b2)
INFO    2020-03-22 12:33:55,175 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=44
INFO    2020-03-22 12:33:55,183 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:55,188 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=45 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/45/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:55,193 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:55,193 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=45
INFO    2020-03-22 12:33:55,195 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:55,195 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:55,195 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 45
INFO    2020-03-22 12:33:55,219 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 45 Memory (free/total/max) = 89138.45M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,220 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 45, messages = 0 , message bytes = 0 , Memory (free/total/max) = 89138.45M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,222 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=45
INFO    2020-03-22 12:33:55,222 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=45
INFO    2020-03-22 12:33:55,226 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 45 with global stats (vtx=319,finVtx=255,edges=444,msgCount=0,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.transposition.TranspositionComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@6b350309,outgoing=org.apache.giraph.conf.DefaultMessageClasses@7ecec90d)
INFO    2020-03-22 12:33:55,226 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=45
INFO    2020-03-22 12:33:55,234 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:55,240 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=46 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/46/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:55,245 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:55,245 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=46
INFO    2020-03-22 12:33:55,246 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:55,247 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:55,247 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 46
INFO    2020-03-22 12:33:55,263 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 46 Memory (free/total/max) = 89030.55M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,264 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 46, messages = 12288 , message bytes = 0 , Memory (free/total/max) = 89030.55M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,266 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=46
INFO    2020-03-22 12:33:55,266 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=46
INFO    2020-03-22 12:33:55,271 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 46 with global stats (vtx=319,finVtx=319,edges=444,msgCount=12288,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.transposition.TranspositionComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@38eb0f4d,outgoing=org.apache.giraph.conf.DefaultMessageClasses@437486cd)
INFO    2020-03-22 12:33:55,271 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=46
INFO    2020-03-22 12:33:55,277 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:55,284 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=47 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/47/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:55,287 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:55,287 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=47
INFO    2020-03-22 12:33:55,288 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:55,288 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:55,289 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 47
INFO    2020-03-22 12:33:55,312 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 47 Memory (free/total/max) = 88877.24M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,314 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 47, messages = 0 , message bytes = 0 , Memory (free/total/max) = 88877.24M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,315 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=47
INFO    2020-03-22 12:33:55,315 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=47
INFO    2020-03-22 12:33:55,322 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 47 with global stats (vtx=319,finVtx=127,edges=444,msgCount=0,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.initialisation.InitialComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@58ba5b30,outgoing=org.apache.giraph.conf.DefaultMessageClasses@4dba773d)
INFO    2020-03-22 12:33:55,322 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=47
INFO    2020-03-22 12:33:55,328 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:55,334 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=48 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/48/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:55,339 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:55,339 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=48
INFO    2020-03-22 12:33:55,342 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:55,342 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:55,342 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 48
INFO    2020-03-22 12:33:55,360 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 48 Memory (free/total/max) = 88769.34M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,361 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 48, messages = 192 , message bytes = 0 , Memory (free/total/max) = 88769.34M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,363 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=48
INFO    2020-03-22 12:33:55,363 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=48
INFO    2020-03-22 12:33:55,367 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 48 with global stats (vtx=319,finVtx=319,edges=444,msgCount=192,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@3aa41da1,outgoing=org.apache.giraph.conf.DefaultMessageClasses@74fab04a)
INFO    2020-03-22 12:33:55,367 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=48
INFO    2020-03-22 12:33:55,373 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:55,380 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=49 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/49/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:55,385 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:55,385 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=49
INFO    2020-03-22 12:33:55,386 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:55,387 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:55,387 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 49
INFO    2020-03-22 12:33:55,408 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 49 Memory (free/total/max) = 88661.45M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,409 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 49, messages = 64 , message bytes = 0 , Memory (free/total/max) = 88661.45M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,410 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=49
INFO    2020-03-22 12:33:55,410 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=49
INFO    2020-03-22 12:33:55,414 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 49 with global stats (vtx=319,finVtx=319,edges=444,msgCount=64,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@885e7ff,outgoing=org.apache.giraph.conf.DefaultMessageClasses@8bd86c8)
INFO    2020-03-22 12:33:55,414 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=49
INFO    2020-03-22 12:33:55,420 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:55,427 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=50 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/50/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:55,432 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:55,432 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=50
INFO    2020-03-22 12:33:55,433 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:55,433 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:55,434 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 50
INFO    2020-03-22 12:33:55,451 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 50 Memory (free/total/max) = 88550.18M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,453 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 50, messages = 32 , message bytes = 0 , Memory (free/total/max) = 88550.18M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,454 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=50
INFO    2020-03-22 12:33:55,454 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=50
INFO    2020-03-22 12:33:55,459 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 50 with global stats (vtx=319,finVtx=319,edges=444,msgCount=32,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@6e7c351d,outgoing=org.apache.giraph.conf.DefaultMessageClasses@7b4a0aef)
INFO    2020-03-22 12:33:55,459 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=50
INFO    2020-03-22 12:33:55,465 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:55,471 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=51 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/51/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:55,476 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:55,476 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=51
INFO    2020-03-22 12:33:55,477 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:55,478 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:55,478 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 51
INFO    2020-03-22 12:33:55,495 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 51 Memory (free/total/max) = 88440.60M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,496 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 51, messages = 16 , message bytes = 0 , Memory (free/total/max) = 88440.60M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,497 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=51
INFO    2020-03-22 12:33:55,497 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=51
INFO    2020-03-22 12:33:55,501 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 51 with global stats (vtx=319,finVtx=319,edges=444,msgCount=16,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@6f70a21b,outgoing=org.apache.giraph.conf.DefaultMessageClasses@6ae62c7e)
INFO    2020-03-22 12:33:55,501 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=51
INFO    2020-03-22 12:33:55,507 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:55,514 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=52 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/52/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:55,518 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:55,518 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=52
INFO    2020-03-22 12:33:55,519 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:55,520 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:55,520 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 52
INFO    2020-03-22 12:33:55,538 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 52 Memory (free/total/max) = 88332.71M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,539 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 52, messages = 8 , message bytes = 0 , Memory (free/total/max) = 88332.71M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,541 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=52
INFO    2020-03-22 12:33:55,541 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=52
INFO    2020-03-22 12:33:55,545 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 52 with global stats (vtx=319,finVtx=319,edges=444,msgCount=8,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@f88bfbe,outgoing=org.apache.giraph.conf.DefaultMessageClasses@59bbe88a)
INFO    2020-03-22 12:33:55,545 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=52
INFO    2020-03-22 12:33:55,552 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:55,558 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=53 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/53/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:55,562 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:55,562 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=53
INFO    2020-03-22 12:33:55,563 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:55,563 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:55,564 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 53
INFO    2020-03-22 12:33:55,580 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 53 Memory (free/total/max) = 88224.81M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,581 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 53, messages = 4 , message bytes = 0 , Memory (free/total/max) = 88224.81M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,582 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=53
INFO    2020-03-22 12:33:55,582 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=53
INFO    2020-03-22 12:33:55,586 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 53 with global stats (vtx=319,finVtx=319,edges=444,msgCount=4,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@68ac9ec5,outgoing=org.apache.giraph.conf.DefaultMessageClasses@a50d709)
INFO    2020-03-22 12:33:55,586 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=53
INFO    2020-03-22 12:33:55,594 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:55,599 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=54 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/54/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:55,604 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:55,604 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=54
INFO    2020-03-22 12:33:55,605 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:55,605 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:55,605 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 54
INFO    2020-03-22 12:33:55,622 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 54 Memory (free/total/max) = 88116.92M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,623 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 54, messages = 2 , message bytes = 0 , Memory (free/total/max) = 88116.92M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,624 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=54
INFO    2020-03-22 12:33:55,625 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=54
INFO    2020-03-22 12:33:55,630 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 54 with global stats (vtx=319,finVtx=319,edges=444,msgCount=2,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@a4b5ce3,outgoing=org.apache.giraph.conf.DefaultMessageClasses@f5b6e78)
INFO    2020-03-22 12:33:55,630 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=54
INFO    2020-03-22 12:33:55,636 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:55,644 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=55 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/55/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:55,647 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:55,647 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=55
INFO    2020-03-22 12:33:55,648 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:55,648 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:55,649 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 55
INFO    2020-03-22 12:33:55,666 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 55 Memory (free/total/max) = 88009.03M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,667 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 55, messages = 2 , message bytes = 0 , Memory (free/total/max) = 88009.03M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,670 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=55
INFO    2020-03-22 12:33:55,670 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=55
INFO    2020-03-22 12:33:55,675 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 55 with global stats (vtx=319,finVtx=319,edges=444,msgCount=2,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@79c5460e,outgoing=org.apache.giraph.conf.DefaultMessageClasses@7d904ff1)
INFO    2020-03-22 12:33:55,675 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=55
INFO    2020-03-22 12:33:55,681 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:55,688 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=56 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/56/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:55,692 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:55,692 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=56
INFO    2020-03-22 12:33:55,694 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:55,694 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:55,695 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 56
INFO    2020-03-22 12:33:55,711 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 56 Memory (free/total/max) = 87901.13M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,712 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 56, messages = 4 , message bytes = 0 , Memory (free/total/max) = 87901.13M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,714 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=56
INFO    2020-03-22 12:33:55,714 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=56
INFO    2020-03-22 12:33:55,718 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 56 with global stats (vtx=319,finVtx=319,edges=444,msgCount=4,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@34b9eb03,outgoing=org.apache.giraph.conf.DefaultMessageClasses@43fda8d9)
INFO    2020-03-22 12:33:55,718 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=56
INFO    2020-03-22 12:33:55,728 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:55,731 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=57 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/57/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:55,741 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:55,741 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=57
INFO    2020-03-22 12:33:55,742 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:55,743 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:55,743 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 57
INFO    2020-03-22 12:33:55,759 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 57 Memory (free/total/max) = 87793.24M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,760 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 57, messages = 8 , message bytes = 0 , Memory (free/total/max) = 87793.24M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,762 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=57
INFO    2020-03-22 12:33:55,762 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=57
INFO    2020-03-22 12:33:55,765 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 57 with global stats (vtx=319,finVtx=319,edges=444,msgCount=8,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@b34832b,outgoing=org.apache.giraph.conf.DefaultMessageClasses@48f4713c)
INFO    2020-03-22 12:33:55,765 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=57
INFO    2020-03-22 12:33:55,773 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:55,779 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=58 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/58/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:55,784 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:55,784 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=58
INFO    2020-03-22 12:33:55,785 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:55,786 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:55,786 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 58
INFO    2020-03-22 12:33:55,802 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 58 Memory (free/total/max) = 87685.34M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,803 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 58, messages = 16 , message bytes = 0 , Memory (free/total/max) = 87685.34M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,804 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=58
INFO    2020-03-22 12:33:55,804 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=58
INFO    2020-03-22 12:33:55,808 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 58 with global stats (vtx=319,finVtx=319,edges=444,msgCount=16,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@a22c4d8,outgoing=org.apache.giraph.conf.DefaultMessageClasses@45cd7bc5)
INFO    2020-03-22 12:33:55,808 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=58
INFO    2020-03-22 12:33:55,814 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:55,823 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=59 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/59/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:55,826 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:55,826 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=59
INFO    2020-03-22 12:33:55,827 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:55,828 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:55,828 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 59
INFO    2020-03-22 12:33:55,846 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 59 Memory (free/total/max) = 87532.03M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,847 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 59, messages = 32 , message bytes = 0 , Memory (free/total/max) = 87532.03M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,849 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=59
INFO    2020-03-22 12:33:55,849 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=59
INFO    2020-03-22 12:33:55,853 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 59 with global stats (vtx=319,finVtx=319,edges=444,msgCount=32,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@1a8df0b3,outgoing=org.apache.giraph.conf.DefaultMessageClasses@7c112f5f)
INFO    2020-03-22 12:33:55,853 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=59
INFO    2020-03-22 12:33:55,858 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:55,868 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=60 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/60/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:55,871 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:55,871 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=60
INFO    2020-03-22 12:33:55,872 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:55,872 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:55,873 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 60
INFO    2020-03-22 12:33:55,890 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 60 Memory (free/total/max) = 87422.45M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,891 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 60, messages = 64 , message bytes = 0 , Memory (free/total/max) = 87422.45M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,892 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=60
INFO    2020-03-22 12:33:55,892 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=60
INFO    2020-03-22 12:33:55,896 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 60 with global stats (vtx=319,finVtx=319,edges=444,msgCount=64,msgBytesCount=0,haltComputation=false, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@5a0bef24,outgoing=org.apache.giraph.conf.DefaultMessageClasses@6468a7b6)
INFO    2020-03-22 12:33:55,896 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=60
INFO    2020-03-22 12:33:55,903 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:55,910 [main] org.apache.giraph.worker.BspServiceWorker  - registerHealth: Created my health node for attempt=0, superstep=61 with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_applicationAttemptsDir/0/_superstepDir/61/_workerHealthyDir/iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal_1 and workerInfo= Worker(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal hostOrIp=10.128.15.204, MRtaskID=1, port=30001)
INFO    2020-03-22 12:33:55,914 [main] org.apache.giraph.worker.BspServiceWorker  - startSuperstep: Master(hostname=iga-adi-m.us-central1-a.c.charismatic-cab-252315.internal, MRtaskID=0, port=30000)
INFO    2020-03-22 12:33:55,914 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] startSuperstep: WORKER_ONLY - Attempt=0, Superstep=61
INFO    2020-03-22 12:33:55,915 [main] org.apache.giraph.worker.BspServiceWorker  - sendWorkerPartitions: Done sending all my partitions.
INFO    2020-03-22 12:33:55,915 [main] org.apache.giraph.worker.BspServiceWorker  - exchangeVertexPartitions: Done with exchange.
INFO    2020-03-22 12:33:55,916 [main] org.apache.giraph.graph.GraphTaskManager  - execute: 64 partitions to process with 64 compute thread(s), originally 64 thread(s) on superstep 61
INFO    2020-03-22 12:33:55,931 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Waiting on all requests, superstep 61 Memory (free/total/max) = 87314.55M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,932 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Superstep 61, messages = 0 , message bytes = 0 , Memory (free/total/max) = 87314.55M / 93056.00M / 93056.00M
INFO    2020-03-22 12:33:55,934 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=61
INFO    2020-03-22 12:33:55,934 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: (waiting for rest of workers) WORKER_ONLY - Attempt=0, Superstep=61
INFO    2020-03-22 12:33:55,940 [main] org.apache.giraph.worker.BspServiceWorker  - finishSuperstep: Completed superstep 61 with global stats (vtx=319,finVtx=255,edges=444,msgCount=0,msgBytesCount=0,haltComputation=true, checkpointStatus=NONE) and classes (computation=edu.agh.iga.adi.giraph.direction.computation.factorization.FactorisationComputation,incoming=org.apache.giraph.conf.DefaultMessageClasses@63d5874f,outgoing=org.apache.giraph.conf.DefaultMessageClasses@60c73e58)
INFO    2020-03-22 12:33:55,940 [main] org.apache.giraph.yarn.GiraphYarnTask  - [STATUS: task-1] finishSuperstep: (all workers done) WORKER_ONLY - Attempt=0, Superstep=61
INFO    2020-03-22 12:33:55,943 [main] org.apache.giraph.graph.GraphTaskManager  - execute: BSP application done (global vertices marked done)
INFO    2020-03-22 12:33:55,945 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:55,953 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent: Job state changed, checking to see if it needs to restart
INFO    2020-03-22 12:33:55,954 [main] org.apache.giraph.yarn.GiraphYarnTask  - Cleaning up
INFO    2020-03-22 12:33:55,954 [main] org.apache.giraph.graph.GraphTaskManager  - cleanup: Starting for WORKER_ONLY
INFO    2020-03-22 12:33:55,972 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent : partitionExchangeChildrenChanged (at least one worker is done sending partitions)
INFO    2020-03-22 12:33:55,995 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - processEvent: Job state changed, checking to see if it needs to restart
ERROR   2020-03-22 12:33:55,998 [main-EventThread] org.apache.giraph.worker.BspServiceWorker  - BspServiceWorker#getJobState() came back NULL.
INFO    2020-03-22 12:33:58,270 [main] org.apache.giraph.worker.BspServiceWorker  - saveVertices: The option for doing output during computation is selected, so there will be no saving of the output in the end of application
WARN    2020-03-22 12:33:58,270 [main] org.apache.giraph.worker.BspServiceWorker  - saveEdges:   giraph.edgeOutputFormatClass => null [EdgeOutputFormat]  (class)
Make sure that the EdgeOutputFormat is not required.
INFO    2020-03-22 12:33:58,274 [main] org.apache.giraph.worker.BspServiceWorker  - cleanup: Notifying master its okay to cleanup with /_hadoopBsp/giraph_yarn_application_1584864522397_0046/_cleanedUpDir/1_worker
INFO    2020-03-22 12:34:02,497 [main] org.apache.giraph.graph.GraphTaskManager  - cleanup: Stopping metrics
INFO    2020-03-22 12:34:02,498 [main] org.apache.giraph.graph.GraphTaskManager  - cleanup: Metrics stopped
INFO    2020-03-22 12:34:02,498 [main] org.apache.giraph.yarn.GiraphYarnTask  - Finalizing yarn job
INFO    2020-03-22 12:34:02,498 [main] org.apache.giraph.yarn.GiraphYarnTask  - Yarn job finalized

End of LogType:task-3-stdout.log
**********************************************************************************
`;
