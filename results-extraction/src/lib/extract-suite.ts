import { Simulation, Problem, Worker, Suite } from "simulation";
import glob from "glob";
import { createProblem } from "./problem";
import { extractCluster } from "./extract-cluster"
import { extractWorkers } from "./extract-workers"
import { extractAllSupersteps } from "./extract-all-supersteps";
import path from 'path'

function extractProblemSimulations(dir: string, problem: Problem): Array<Simulation> {
    return glob.sync(`${dir}/*`)
        .map(simulationDir => ({
            id: path.basename(simulationDir),
            problem: problem,
            parentDir: simulationDir,
            cluster: extractCluster(simulationDir),
            workers: extractWorkers(simulationDir),
            superstepsOf: (worker: Worker) => {
                return extractAllSupersteps(problem, worker)
            }
        }))
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