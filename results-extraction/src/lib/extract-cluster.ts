import { Cluster } from "simulation"
import glob from "glob"
import { readFileSync } from "fs"

const workerMemoryPattern = /IGA_WORKER_MEMORY=(\\d+)/gm
const workerCoresPattern = /IGA_WORKER_CORES=(\\d+)/gm

export function extractCluster(dir: string): Cluster {
    const simulationDir = glob.sync(`${dir}/*`).shift()
    const parameters = readFileSync(`${simulationDir}/parameters.sh`, 'utf8')
    return {
        cores: +workerMemoryPattern.exec(parameters)[0],
        memory: +workerCoresPattern.exec(parameters)[0]
    }
}
