import { Simulation } from "simulation";

class DefaultSimulation implements Simulation {
    dagHeight: number;

    constructor(dagHeight: number) {
        this.dagHeight = dagHeight
    }

    superstepsInTimeStep(): number {
        return 4 * this.dagHeight + 6
    }

    firstSuperstepOfSecondTimeStep(): number {
        return this.superstepsInTimeStep() + 1
    }

    initSuperstep(): number {
        return this.firstSuperstepOfSecondTimeStep();
    }

    firstRootSuperstep(): number {
        return this.firstSuperstepOfSecondTimeStep() + this.dagHeight + 1
    }

    transposeMapSuperstep(): number {
        return this.firstSuperstepOfSecondTimeStep() + 2 * this.dagHeight + 3
    }

    transposeReduceSuperstep(): number {
        return this.transposeMapSuperstep() + 1
    }

    secondRootSuperstep(): number {
        return this.firstSuperstepOfSecondTimeStep() + 3 * this.dagHeight + 5
    }
}

export function createSimulation(problemSize: number): Simulation {
    const dagHeight = Math.log2(problemSize / 3);
    return new DefaultSimulation(dagHeight)
}
