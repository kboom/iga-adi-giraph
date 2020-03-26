import { Worker } from "simulation"
import glob from "glob"
import path from "path";

export function extractWorkers(dir: string): Array<Worker> {
    return glob.sync(`${dir}/**/container*`)
        .filter(containerPath => !containerPath.endsWith("000001") && !containerPath.endsWith("000002"))
        .map(containerPath => ({
            container: path.basename(containerPath),
            node: path.relative(dir, path.join(containerPath, "..")),
            logsPath: containerPath
        }))
}
