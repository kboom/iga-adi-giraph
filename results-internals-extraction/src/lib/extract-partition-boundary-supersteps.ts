import { Problem } from "simulation";

export function partitionBoundarySupersteps(problem: Problem, partitionCount: number): Array<number> {
    const leavesPerPartition = problem.problemSize() / partitionCount
    const bottomTreeHeight = Math.floor(leavesPerPartition > 1 ? Math.log2((leavesPerPartition + 1) / 3) + 1 : 1)
    const tipTreeHeight = problem.treeHeight() - bottomTreeHeight;
    return [
        problem.firstRootSuperstep() - tipTreeHeight - 1,
        problem.firstRootSuperstep() + tipTreeHeight + 2,
        problem.secondRootSuperstep() - tipTreeHeight - 1,
        problem.secondRootSuperstep() + tipTreeHeight + 2
    ]
}