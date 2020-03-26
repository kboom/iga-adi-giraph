// tslint:disable:no-expression-statement no-object-mutation
import test from 'ava';
import { extractSuite } from './extract-suite';
import path from 'path';

const suite1Path = path.join(__dirname, "..", "..", "..", "logs", "suite1")

test('can extract suite 1', t => {
    t.snapshot(extractSuite(suite1Path));
});
