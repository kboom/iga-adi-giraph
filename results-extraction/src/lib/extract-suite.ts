import { Simulation, Problem, Worker, Suite } from "simulation";
import glob from "glob";
import { createProblem } from "./problem";
import { extractCluster } from "./extract-cluster"
import { extractWorkers } from "./extract-workers"
import { extractAllSupersteps } from "./extract-all-supersteps";
import path from 'path'
import { partitionBoundarySupersteps } from "./extract-partition-boundary-supersteps";

function partitionCount(cluster: import("simulation").Cluster, workers: Worker[]): number {
    return cluster.cores * workers.length;
}

function extractProblemSimulations(dir: string, problem: Problem): Array<Simulation> {
    return glob.sync(`${dir}/*`)
        .map(simulationDir => {
            const workers = extractWorkers(simulationDir)
            const cluster = extractCluster(simulationDir)
            return {
                id: path.basename(simulationDir),
                problem: problem,
                parentDir: simulationDir,
                cluster: cluster,
                workers: workers,
                partitionBoundarySupersteps: partitionBoundarySupersteps(problem, partitionCount(cluster, workers)),
                superstepsOf: (worker: Worker) => {
                    return extractAllSupersteps(problem, worker)
                }
            }
        })
}

export function extractSuite(rootDir: string): Suite {
    const problemDirectories = glob.sync(`${rootDir}/*`)
    return {
        simulations: problemDirectories
            .flatMap(dir => extractProblemSimulations(
                dir,
                createProblem(+path.relative(rootDir, dir))
            ))
    }
}