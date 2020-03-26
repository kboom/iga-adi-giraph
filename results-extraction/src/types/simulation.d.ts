declare module 'simulation' {
    export interface Worker {
        id: number
        logsPath: string
    }
    export interface Simulation {
        superstepsInTimeStep(): number;
        initSuperstep(): number;
        firstRootSuperstep(): number;
        transposeMapSuperstep(): number;
        transposeReduceSuperstep(): number;
        secondRootSuperstep(): number;
    }
}