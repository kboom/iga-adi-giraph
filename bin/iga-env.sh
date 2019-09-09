# This is the runner for the solver.
# Use run it like this ./iga-env.sh

#!/usr/bin/env bash

THIS_DIR=`dirname "$THIS"`
IGA_HOME=`cd "$THIS_DIR/.." ; pwd`

# Hadoop properties
HADOOP_PROPERTIES=
while [[ $1 ]] && [[ ${1:0:2} == "-D" ]] ; do
    HADOOP_PROPERTIES="$1 $HADOOP_PROPERTIES"
    shift
done

# Application fat-jar from the distribution
IGA_JAR=`cd "$IGA_HOME/dist/" ; pwd`
shift

if [[ ! -e "$IGA_JAR" ]]; then
  echo "No iga jar found at $IGA_JAR. You might need to issue 'mvn clean package' first"
else
  CLASSPATH=${IGA_JAR}
fi

CLASSPATH=${CLASSPATH}:${IGA_HOME}/conf
CLASS=org.apache.giraph.GiraphRunner

# so that filenames w/ spaces are handled correctly in loops below
IFS=

# add release dependencies to CLASSPATH
if [[ -d "$IGA_HOME/lib" ]]; then
	for f in ${$IGA_HOME}/lib/*.jar; do
	  CLASSPATH=${CLASSPATH}:${f}
	done

	for f in ${IGA_HOME}/giraph-core*.jar ; do
	  if [[ -e "$f" ]]; then
	    JAR=${f}
           CLASSPATH=${CLASSPATH}:$f
	    break
	  fi
	done
else
	echo "No lib directory, assuming dev environment"
	if [[ ! -d "$IGA_HOME/solver/target" ]]; then
		echo "No target directory. Build before proceeding."
		exit 1
	fi

	CLASSPATH2=`mvn dependency:build-classpath | grep -v "[INFO]"`
	CLASSPATH=${CLASSPATH}:${CLASSPATH2}

	for f in ${IGA_HOME}/solver/target/*.jar; do
	  if [[ -e "$f" ]]; then
	    JAR=${f}
	    break
	  fi
	done
fi
# restore ordinary behaviour
unset IFS

if [[ "$JAR" = "" ]] ; then
  echo "Can't find Giraph jar."
  exit 1
fi

if [[ "$HADOOP_CONF_DIR" = "" ]] ; then
  HADOOP_CONF_DIR=${HADOOP_HOME}/conf
  echo "No HADOOP_CONF_DIR set, using $HADOOP_HOME/conf "
else
  echo "HADOOP_CONF_DIR=$HADOOP_CONF_DIR"
fi

# Giraph's jars to add to distributed cache via -libjar, which are csv rather than :sv
GIRAPH_JARS=`echo ${JAR}:${CLASSPATH}|sed s/:/,/g`
export HADOOP_CLASSPATH=${HADOOP_CLASSPATH}:${CLASSPATH}