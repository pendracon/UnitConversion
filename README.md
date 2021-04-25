## "Professor Frink's" Unit Conversion

_`America is inching towards the metric system.`_

_Unit Conversion_ is a temperature and dry volume unit conversion solution for
the Flexion Coding challenge.

Converting units between the following types is supported:

- **Temperature:**
  - _Celsius_, _Fahrenheit_, _Kelvin_, _Rankine_
- **Volumetric:**
  - _cubic inches_, _cubic feet_, _cups_, _gallons_, _liters_, _tablespoons_


### Installation

Unit Conversion can be run as both a command-line utility and as a web
application. A downloadable ZIP package with launch scripts for Linux and
Windows is available on this site.

1. Command-line

Running the command-line interface requires a Java7, or later, JVM installed on
the host computer. If one is not already installed then a compatible JVM can be
downloaded from [AdoptOpenJDK](https://adoptopenjdk.net/) free of charge.

   - Download the latest `Unit Conversion CLI` package from the **Releases**
page.
   - Unzip the package in your home directory (~) if on Linux or to the root of
drive C: if on Windows. Unzipping the archive creates directory/folder
`UnitConversionCLI` with all needed resources to run the utility.
   - Open a terminal window (Linux) or command-prompt (Windows) and enter the
following for usage information:
     - _Linux:_ `sh ~/UnitConversionCLI/convert.sh --help`
     - _Windows:_ `C:\UnitConversionCLI\convert.bat --help`

2. Web

A ready to run Docker image of the web interface is available from Docker Hub
repository `pendracon/unit-conversion:latest`. Information and instructions for
installing Docker and using Docker on all supported platforms is available on
the [Docker](https://docker.com/get-started) website.

   - Open a terminal window (Linux) or command-prompt (Windows) and enter the
following to download and run the Docker image on your system:

```
docker pull pendracon/unit-conversion:latest
docker run -d -p 8081:8080 pendracon/unit-conversion:latest
```
	 
Replace port **8081** in the second line with another port if 8081 is already
being used on your system.

   - Open your web browser and enter `localhost:8081/UnitConversion`
   in the location bar to access the web page.


##### TODO
1. **Convert All Function**
   - Add an option to the application to return conversion results for all
supported compatible types. This would improve the application's usability as
a general purpose conversion tool, e.g.:

```
\> convert --convertFrom=celsius --convertTo=all --units=100
Unit Conversion 1.0
  Result: 100.0/C = 212.0/F, 373.2/K, 671.7/R
```

2. **Form Input Control**
   - Modify the web page's input form to ensure only valid unit types can be input.
This can be accomplished by changing the unit type input fields to select lists
which offer only unit types supported by the application and which are
compatible with each other. This would improve usability by eliminating time
wasted on unnecessary error result messages.

3. **Form Input Validation**
   - Modify the web page's input form to ensure only numeric values can be input for
conversion and validation amounts. As with controlling unit type entry, this
would improve usability by eliminating time wasted on unnecessary error result
messages.

4. **Localization**
   - Move all error messages from code to the `messages` file for localization
support. This would make the application more accessible to non-English
speaking users.

5. **Configuration Chaining**
   - Add `nextFile` chaining and loading to the `classes` and `messages`
configuration properties files. This would improve extensibility of the tool by
allowing addition of new unit types and conversion implementations without need
to modify existing sources.
