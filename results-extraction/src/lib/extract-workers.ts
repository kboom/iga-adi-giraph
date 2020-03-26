import { Worker } from "simulation"
import glob from "glob"

const directoryNameRegexp = /(.*)\/(.*)$/

export function extractWorkers(dir: string): Array<Worker> {
    return glob.sync(`${dir}/**/container*`)
        .filter(containerPath => !containerPath.endsWith("000001") && containerPath.endsWith("000002"))
        .map(containerPath => ({
            id: directoryNameRegexp.exec(containerPath)[0],
            logsPath: containerPath
        }))
}
