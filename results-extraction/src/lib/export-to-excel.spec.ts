// tslint:disable:no-expression-statement no-object-mutation
import test from 'ava';
import { extractSuite } from './extract-suite';
import path from 'path';
import { exportToExcel } from './export-to-excel';

const outputExcelPath = path.join(__dirname, "..", "excel.xlsx")
const suitePath = path.join(__dirname, "..", "..", "..", "logs", "suite1")

test('can export to excel', t => {
    exportToExcel(extractSuite(suitePath), outputExcelPath)
    t.pass()
});
