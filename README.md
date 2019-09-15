# IGA-ADI Giraph Solver
[![Build Status](https://travis-ci.com/kboom/iga-adi-giraph.svg?token=wBhPe1ndPxyFXb6jUk8s&branch=master)](https://travis-ci.com/kboom/iga-adi-giraph)

## Running the solver

This repository contains a number of complete simulations with the input and output values stored under `examples` directory.

### Prerequisites

You have to have JDK 11 installed.

### Snapshot of Giraph

Download the latest Giraph, switch to release_1.2 branch, and run the following command from the repository root directory:

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

### Running on cloud

There is an [unresolved issue](https://issues.apache.org/jira/browse/GIRAPH-859) in Giraph which translates into inability to automatically
upload JARs to the workers if the user launching the computations is different than yarn (Giraph would upload the JARs to the directory belonging to the user
launching the application but look for them in the yarn user directory so they would be missing).

There are severeal possible workarounds to this:
* just to run the application using yarn user
* upload the jars manually before each computation.
* apply the patch and build against this version of Giraph

We use the 3rd solution here using the fork [https://github.com/kboom/giraph](https://github.com/kboom/giraph).

To build & publish the JAR with the solver just run
```bash
cd bin
./bin/publish.cloud.sh <your master instance number>
```

The to run
```bash
./run.cloud.sh # your IGA parameters
```

Or one of the defaults
```bash
./run.default.sh
```

## Configuration parameters

| Name | Value | Meaning | Influence |
|------|-------|---------|-----------|
|  *giraph.useNettyDirectMemory*   |  true     |     netty direct buffers   |  speeds up         |
|  *giraph.useUnsafeSerialization*   |  true     |     unsafe serialisation uses unsafe memory allocation    |  speeds up         |

[Reference](https://giraph.apache.org/options.html)

## Solved issues

[Missing filesystem](https://exceptionshub.com/hadoop-no-filesystem-for-scheme-file.html)

## Links

* [Hadoop mini clusters](https://github.com/sakserv/hadoop-mini-clusters)
* [YARN property list](https://hadoop.apache.org/docs/current/hadoop-yarn/hadoop-yarn-common/yarn-default.xml)
* [Giraph on Docker](https://github.com/uwsampa/giraph-docker)
* [Hadoop Matrix Transposition](https://github.com/o19s/Hadoopadoop/blob/master/matrixtranspose/MatrixTranspose.java)
* [ojAlgo examples](https://www.ojalgo.org/code-examples/)