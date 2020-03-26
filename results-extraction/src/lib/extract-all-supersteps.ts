import { readFileSync } from 'fs';
import { Superstep } from 'superstep';
import { Simulation, Worker } from 'simulation';
import { range } from 'lodash'
import { extractSuperstepSummary } from './extract-superstep';

export function extractAllSupersteps(simulation: Simulation, worker: Worker): Array<Superstep> {
    const logs = readFileSync(worker.logsPath, 'utf8')
    const firstSuperstep = simulation.initSuperstep()
    const lastSuperstep = simulation.initSuperstep() + simulation.superstepsInTimeStep()
    return range(firstSuperstep, lastSuperstep + 1)
        .map(superstep => {
            console.log(`Processing ${superstep}\n`)
            return extractSuperstepSummary(logs, superstep)
        })
}