#!/bin/bash
#
# Script for running Unit Conversion from Linux command line.
#
export DIRNAME=${CONTEXT}CLI
export APPHOME=${APPHOME:-~/$DIRNAME}

LIBDIR=${LIBDIR:-${APPHOME}/lib}
CONFIG=${CONFIG:-${APPHOME}/config}
RUNTIME=${LIBDIR}/${NAME}-${VERS}-jar-with-deps.jar
JAVA=${JAVA:-java}

LOGGER=-Dorg.apache.commons.logging.Log=org.apache.commons.logging.impl.Jdk14Logger
LOGCFG=-Djava.util.logging.config.file=${CONFIG}/logging.properties

echo "Starting application main with:" > run.out
echo "$JAVA $LOGGER $LOGCFG -jar $RUNTIME" >> run.out

$JAVA $LOGGER $LOGCFG -jar $RUNTIME "$@"

echo "\nShutdown complete." >> run.out
