#!/bin/bash
#
# Script for running Unit Conversion from Linux command line.
#
export APPHOME=${APPHOME:-~/UnitConversionCLI}

LIBDIR=${LIBDIR:-${APPHOME}/lib}
CONFIG=${CONFIG:-${APPHOME}/config}
RUNTIME=$(ls ${LIBDIR}/UnitConversion-*-jar-with-dependencies.jar)
JAVA=${JAVA:-java}

LOGGER=-Dorg.apache.commons.logging.Log=org.apache.commons.logging.impl.Jdk14Logger
LOGCFG=-Djava.util.logging.config.file=${CONFIG}/logging.properties

echo "Starting application main with:" > run.out
echo -e "$JAVA $LOGGER $LOGCFG -jar $RUNTIME" >> run.out

$JAVA $LOGGER $LOGCFG -jar $RUNTIME "$@"

echo -e "\nShutdown complete." >> run.out
