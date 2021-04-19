@echo off

REM ========================================
REM  Startup batch file for Unit Conversion
REM ========================================

set HOMEDIR=C:\UnitConversionCLI
set LIBSDIR=%HOMEDIR%\lib
set CONFDIR=%HOMEDIR%\config
set RUNTIME=%LIBSDIR%\${NAME}-${VERS}-jar-with-dependencies.jar

set LOGGER=-Dorg.apache.commons.logging.Log=org.apache.commons.logging.impl.Jdk14Logger
set LOGCFG=-Djava.util.logging.config.file=%CONFIG%\logging.properties

c:
cd %HOMEDIR%
echo Starting application main with: > run.out
echo java %LOGGER% %LOGCFG% -jar %RUNTIME% %1 %2 %3 %4 >> run.out

java %LOGGER% %LOGCFG% -jar %RUNTIME% "%1=%2" "%3=%4" "%5=%6" "%7=%8"

echo >> run.out
echo Shutdown complete >> run.out
