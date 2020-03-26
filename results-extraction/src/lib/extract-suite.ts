import { Simulation, Problem, Worker } from "simulation";
import glob from "glob";
import { createProblem } from "./problem";
import { extractCluster } from "./extract-cluster"
import { extractWorkers } from "./extract-workers"
import { extractAllSupersteps } from "./extract-all-supersteps";

function extractProblemSimulations(dir: string, problem: Problem): Array<Simulation> {
    return glob.sync(`${dir}/*`)
        .map(simulationDir => ({
            problem: problem,
            cluster: extractCluster(simulationDir),
            workers: extractWorkers(simulationDir),
            superstepsOf: (worker: Worker) => {
                return extractAllSupersteps(problem, worker)
            }
        }))
}

export function extractSuite(rootDir: string): Array<Simulation> {
    const problemDirectories = glob.sync(`${rootDir}/*`)
    console.log(rootDir)
    return problemDirectories
        .flatMap(dir => extractProblemSimulations(
            dir,
            createProblem(+dir.replace(rootDir, ""))
        ))
}