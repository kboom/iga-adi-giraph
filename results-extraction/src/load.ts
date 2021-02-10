import { getopt } from 'stdio';
import { extractSuite } from './lib/extract-suite';
import { exportToExcel } from './lib/export-to-excel';

const options = getopt({
    'output': { key: 'o', required: true, args: 1, description: 'The output excel file name' },
    'input': { key: 'i', required: true, args: 1, description: 'The input directory to scan through' }
});

console.log(`Going to read from ${JSON.stringify(options)}`)

exportToExcel(extractSuite(options.input as string), options.output as string)
