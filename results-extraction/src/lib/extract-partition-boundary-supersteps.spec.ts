// tslint:disable:no-expression-statement
import test from 'ava';
import { createProblem } from './problem';
import { partitionBoundarySupersteps } from './extract-partition-boundary-supersteps';

test('superstep for problem 768 and partition count 32', t => {
    t.deepEqual(partitionBoundarySupersteps(createProblem(768), 32), [44, 53, 64, 73]);
});

test('superstep for problem 1536 and partition count 64', t => {
    t.deepEqual(partitionBoundarySupersteps(createProblem(1536), 32), [49, 58, 71, 80]);
});

test('superstep for problem 3072 and partition count 32', t => {
    t.deepEqual(partitionBoundarySupersteps(createProblem(3072), 32), [54, 63, 78, 87]);
});

test('superstep for problem 3072 and partition count 64', t => {
    t.deepEqual(partitionBoundarySupersteps(createProblem(3072), 64), [53, 64, 77, 88]);
});

test('superstep for problem 6144 and partition count 64', t => {
    t.deepEqual(partitionBoundarySupersteps(createProblem(6144), 64), [58, 69, 84, 95]);
});

test('superstep for problem 12288 and partition count 64', t => {
    t.deepEqual(partitionBoundarySupersteps(createProblem(12288), 64), [63, 74, 91, 102]);
});

test('superstep for problem 24576 and partition count 64', t => {
    t.deepEqual(partitionBoundarySupersteps(createProblem(24576), 64), [68, 79, 98, 109]);
});

