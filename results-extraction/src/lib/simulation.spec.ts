// tslint:disable:no-expression-statement
import test from 'ava';
import { createSimulation } from './simulation';

test('double', t => {
    t.is(createSimulation(12288).initSuperstep(), 55);
});
