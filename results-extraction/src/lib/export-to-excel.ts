import { Suite } from "simulation";
import { writeFile, utils } from 'xlsx';

function suiteToJson(suite: Suite): any[] {
    return suite.simulations.flatMap(
        simulation => simulation.workers.flatMap(
            worker => simulation.superstepsOf(worker).flatMap(
                superstep => ({
                    problemSize: simulation.problem.problemSize(),
                    superstepsInTimeStep: simulation.problem.superstepsInTimeStep(),
                    transposeMapSuperstep: simulation.problem.transposeMapSuperstep(),
                    transposeReduceSuperstep: simulation.problem.transposeReduceSuperstep(),
                    firstRootSuperstep: simulation.problem.firstRootSuperstep(),
                    secondRootSuperstep: simulation.problem.secondRootSuperstep(),
                    ...worker,
                    ...simulation.cluster,
                    ...superstep
                })
            )
        ))
}

export function exportToExcel(
    suite: Suite,
    outputFile: string
) {
    const ws = utils.json_to_sheet(suiteToJson(suite))
    const wb = utils.book_new();
    utils.book_append_sheet(wb, ws, "data");
    writeFile(wb, outputFile, { type: 'file' });
}