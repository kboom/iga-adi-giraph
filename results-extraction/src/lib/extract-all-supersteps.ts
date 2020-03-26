import { readFileSync } from 'fs';
import { Problem, Worker, Superstep } from 'simulation';
import { range } from 'lodash'
import { extractSuperstepSummary } from './extract-superstep';

export function extractAllSupersteps(simulation: Problem, worker: Worker): Array<Superstep> {
    const logs = readFileSync(worker.logsPath, 'utf8')
    const firstSuperstep = simulation.initSuperstep()
    const lastSuperstep = simulation.initSuperstep() + simulation.superstepsInTimeStep()
    return range(firstSuperstep, lastSuperstep + 1)
        .map(superstep => {
            try {
                return extractSuperstepSummary(logs, superstep)
            } catch(e) {
                console.error(`Superstep ${superstep} of ${worker.logsPath} was malformed:\n${logs}`)
                throw e
            }
        })
}