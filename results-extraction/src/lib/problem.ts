import { Problem } from "simulation";

class DefaultProblem implements Problem {
    dagHeight: number;

    constructor(dagHeight: number) {
        this.dagHeight = dagHeight
    }

    problemSize(): number {
        return 3 * Math.pow(this.dagHeight - 1, 2)
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

export function createProblem(problemSize: number): Problem {
    const dagHeight = Math.log2(problemSize / 3);
    return new DefaultProblem(dagHeight)
}
