# iga-adi-results-extractor

Extracts results from the raw IGA-ADI output log files.

## Prerequisites

Make sure you have the right version of node installed and active.
This version was build and tested with Node
```
v12.10.0
```

## How to run

The raw results need to be put in the correct directory structure like this,
where results for the same problem sizes are grouped under the same directory.

```text
> tree -L 2 -d <YOUR_DIR>
└── n1-standard-16
    ├── 12288
    ├── 1536
    ├── 24576
    ├── 3072
    ├── 6144
    └── 768
```

```bash
node build/main/index.js -i <FULL_PATH_TO_INPUT_DIR> -o <OUTPUT_EXCEL_FILE>
```

## The output

The output is an excel file which can be used to draw a number of diagrams with the RScripts.
See [the scripts](https://github.com/kboom/phd/tree/master/results).
