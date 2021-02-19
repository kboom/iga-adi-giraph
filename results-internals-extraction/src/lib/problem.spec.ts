// tslint:disable:no-expression-statement
import test from 'ava';
import { createProblem } from './problem';

test('size of 192 is 192', t => {
    t.is(createProblem(192).problemSize(), 192);
});

test('size of 384 is 384', t => {
    t.is(createProblem(384).problemSize(), 384);
});

test('size of 12288 is 12288', t => {
    t.is(createProblem(12288).problemSize(), 12288);
});

test('init step of 12288 is 55', t => {
    t.is(createProblem(12288).initSuperstep(), 55);
});

test('first root step of 12288 is 68', t => {
    t.is(createProblem(12288).firstRootSuperstep(), 68);
});

test('transpose map step of 12288 is 82', t => {
    t.is(createProblem(12288).transposeMapSuperstep(), 82);
});

test('transpose reduce step of 12288 is 83', t => {
    t.is(createProblem(12288).transposeReduceSuperstep(), 83);
});

test('second root step of 12288 is 96', t => {
    t.is(createProblem(12288).secondRootSuperstep(), 96);
});

test('init step of 6144 is 51', t => {
    t.is(createProblem(6144).initSuperstep(), 51);
});

test('first root step of 6144 is 63', t => {
    t.is(createProblem(6144).firstRootSuperstep(), 63);
});

test('transpose map step of 6144 is 76', t => {
    t.is(createProblem(6144).transposeMapSuperstep(), 76);
});

test('transpose reduce step of 6144 is 77', t => {
    t.is(createProblem(6144).transposeReduceSuperstep(), 77);
});

test('second root step of 6144 is 89', t => {
    t.is(createProblem(6144).secondRootSuperstep(), 89);
});

