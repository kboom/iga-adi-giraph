# IGA-ADI Giraph Solver
[![Build Status](https://travis-ci.com/kboom/iga-adi-giraph.svg?token=wBhPe1ndPxyFXb6jUk8s&branch=master)](https://travis-ci.com/kboom/iga-adi-giraph)

## Running the solver

This repository contains a number of complete simulations with the input and output values stored under `examples` directory.

### Prerequisites

You have to have JDK 11 installed.

### Snapshot of Giraph

Download the latest Giraph and run the following command from the repository root directory:

` mvn -pl giraph-core -Phadoop_yarn -Dhadoop.version=2.9.2 -fae -DskipTests -Dcheckstyle.skip  clean install`

### Running from maven

You can run any of the available examples using the maven command
```
mvn exec:exec@[YOUR SIMULATION]
```
where `[YOUR SIMULATION]` has to be replaced with one of

| Simulation | Value |
|------------|-------|
| Identity | identity |


## Configuration parameters

| Name | Value | Meaning | Influence |
|------|-------|---------|-----------|
|  *giraph.useNettyDirectMemory*   |  true     |     netty direct buffers   |  speeds up         |
|  *giraph.useUnsafeSerialization*   |  true     |     unsafe serialisation uses unsafe memory allocation    |  speeds up         |

[Reference](https://giraph.apache.org/options.html)

## Links

* [Hadoop mini clusters](https://github.com/sakserv/hadoop-mini-clusters)
* [Giraph on Docker](https://github.com/uwsampa/giraph-docker)
* [Hadoop Matrix Transposition](https://github.com/o19s/Hadoopadoop/blob/master/matrixtranspose/MatrixTranspose.java)
* [ojAlgo examples](https://www.ojalgo.org/code-examples/)