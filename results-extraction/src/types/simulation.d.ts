declare module 'simulation' {
    export interface Worker {
        workerId: number
        container: string
        node: string
        logsPath: string
    }
    export interface Problem {
        problemSize(): number;
        superstepsInTimeStep(): number;
        initSuperstep(): number;
        firstRootSuperstep(): number;
        transposeMapSuperstep(): number;
        transposeReduceSuperstep(): number;
        secondRootSuperstep(): number;
    }

    export interface Cluster {
        memory: number;
        cores: number;
    }

    export interface Suite {
        simulations: Array<Simulation>
    }

    export interface Simulation {
        id: string
        problem: Problem
        cluster: Cluster
        parentDir: string
        workers: Array<Worker>

        superstepsOf(worker: Worker): Array<Superstep>
    }
    export interface Superstep {
        superstepId: number;
        superstepTime: number;
        computeAllPartitions: number;
        timeSpentInGc: number;
        networkCommunicationTime: number;
        timeToFirstMessage: number;
        waitOnRequestsTime: number;
        communicationTime: number;
        computeAll: number;
        computePerPartitionSum: number;
        computePerPartitionMin: number;
        computePerPartitionMax: number;
        computePerPartitionMean: number;
        computePerPartitionStddev: number;
        computePerPartitionMedian: number;
        computePerPartitionP75: number;
        computePerPartitionP95: number;
        computePerPartitionP98: number;
        computePerPartitionP99: number;
        computePerPartitionP999: number;
        partitionsCount: number;
        gcPerThreadSum: number;
        gcPerThreadMin: number;
        gcPerThreadMax: number;
        gcPerThreadMean: number;
        gcPerThreadStdev: number;
        gcPerThreadMedian: number;
        gcPerThreadP75: number;
        gcPerThreadP95: number;
        gcPerThreadP98: number;
        gcPerThreadP99: number;
        gcPerThreadP999: number;
        localRequestsCount: number;
        messageBytesSent: number;
        messagesSent: number;
        percentLocalRequests: number;
        processingPerThreadSum: number;
        processingPerThreadMin: number;
        processingPerThreadMax: number;
        processingPerThreadMean: number;
        processingPerThreadStddev: number;
        processingPerThreadMedian: number;
        processingPerThreadP75: number;
        processingPerThreadP95: number;
        processingPerThreadP98: number;
        processingPerThreadP99: number;
        processingPerThreadP999: number;
        receivedBytesSum: number;
        receivedBytesMin: number;
        receivedBytesMax: number;
        receivedBytesMean: number;
        receivedBytesStddev: number;
        receivedBytesMedian: number;
        receivedBytesP75: number;
        receivedBytesP95: number;
        receivedBytesP98: number;
        receivedBytesP99: number;
        receivedBytesP999: number;
        remoteRequests: number;
        requestsReceived: number;
        requestsReceivedMeanRate: number;
        requestsReceived1mRate: number;
        requestsReceived5mRate: number;
        requestsReceived15mRate: number;
        requestsSent: number;
        requestsSentMeanRate: number;
        requestsSent1mRate: number;
        requestsSent5mRate: number;
        requestsSent15mRate: number;
        sentBytesSum: number;
        sentBytesMin: number;
        sentBytesMax: number;
        sentBytesMean: number;
        sentBytesStddev: number;
        sentBytesMedian: number;
        sentBytesP75: number;
        sentBytesP95: number;
        sentBytesP98: number;
        sentBytesP99: number;
        sentBytesP999: number;
        superstepGcTime: number;
        totalRequests: number;
        waitPerThreadSum: number;
        waitPerThreadMin: number;
        waitPerThreadMax: number;
        waitPerThreadMean: number;
        waitPerThreadStddev: number;
        waitPerThreadMedian: number;
        waitPerThreadP75: number;
        waitPerThreadP95: number;
        waitPerThreadP98: number;
        waitPerThreadP99: number;
        waitPerThreadP999: number;
    }
}