export * from './lib/extract-suite';
import { getopt } from 'stdio';
import { extractSuite } from './lib/extract-suite';
import { exportToExcel } from './lib/export-to-excel';

const options = getopt({
    'output': { key: 'o', required: true, description: 'The output excel file name' },
    'input': { key: 'i', description: 'The input directory to scan through' }
});

exportToExcel(extractSuite(options.input as string), options.output as string)
