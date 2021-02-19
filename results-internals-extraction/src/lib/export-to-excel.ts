import { Suite } from "simulation";
import { writeFile, utils } from 'xlsx';
import deepMapKeys from 'deep-map-keys';
import decamelize from 'decamelize';

function suiteToJson(suite: Suite): any[] {
    return suite.simulations.flatMap(
        simulation => simulation.workers.flatMap(
            worker => simulation.superstepsOf(worker).flatMap(
                superstep => ({
                    problemSize: simulation.problem.problemSize(),
                    simulationId: simulation.id,
                    parentDir: simulation.parentDir,
                    initSuperstep: simulation.problem.initSuperstep(),
                    superstepsInTimeStep: simulation.problem.superstepsInTimeStep(),
                    transposeMapSuperstep: simulation.problem.transposeMapSuperstep(),
                    transposeReduceSuperstep: simulation.problem.transposeReduceSuperstep(),
                    firstRootSuperstep: simulation.problem.firstRootSuperstep(),
                    secondRootSuperstep: simulation.problem.secondRootSuperstep(),
                    firstGather: simulation.partitionBoundarySupersteps[0],
                    firstScatter: simulation.partitionBoundarySupersteps[1],
                    secondGather: simulation.partitionBoundarySupersteps[2],
                    secondScatter: simulation.partitionBoundarySupersteps[3],
                    ...worker,
                    ...simulation.cluster,
                    ...superstep
                })
            ).map(v => deepMapKeys(v, k => decamelize(k, '_')))
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