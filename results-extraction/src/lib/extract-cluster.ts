import { Cluster } from "simulation"
import { readFileSync } from "fs"

const workerMemoryPattern = /IGA_WORKER_MEMORY=(\d+)$/m
const workerCoresPattern = /IGA_WORKER_CORES=(\d+)$/m

export function extractCluster(dir: string): Cluster {
    const parameters = readFileSync(`${dir}/parameters.sh`, 'utf8')
    return {
        cores: +parameters.match(workerCoresPattern)[1],
        memory: +parameters.match(workerMemoryPattern)[1]
    }
}
