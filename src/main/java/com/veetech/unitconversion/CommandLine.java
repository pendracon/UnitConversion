/*
 * Copyright (c) 2021 a.cook@veetechis.com.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    a.cook - initial API and implementation and/or initial documentation
 */
package com.veetech.unitconversion;

import com.veetech.unitconversion.domain.Constants;
import com.veetech.unitconversion.domain.Conversion;
import com.veetech.unitconversion.domain.ConversionType;
import com.veetech.unitconversion.domain.temperature.TemperatureType;
import com.veetech.unitconversion.domain.volume.VolumeType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * This class provides a command-line interface to Unit Conversion.
 * 
 * @author a.cook@veetechis.com
 */
public class CommandLine
{
	public static void main( String args[] )
	{
		String fromType = null;
		String toType = null;
		String units = null;
		String validate = null;
		boolean showUsage = false;
		
		String[] arg;
		for (String parm : args) {
			if (parm.startsWith("--")) {
				arg = parm.split( "\\=" );
				if (arg[0].equalsIgnoreCase("--help")) {
					showUsage = true;
					break;
				}
				if (arg[0].equalsIgnoreCase("--convertFrom")) {
					fromType = (arg.length == 2 ? arg[1] : null);
				}
				if (arg[0].equalsIgnoreCase("--convertTo")) {
					toType = (arg.length == 2 ? arg[1] : null);
				}
				if (arg[0].equalsIgnoreCase("--units")) {
					units = (arg.length == 2 ? arg[1] : null);
				}
				if (arg[0].equalsIgnoreCase("--validate")) {
					validate = (arg.length == 2 ? arg[1] : null);
				}
			}
		}

		if (showUsage) {
			usage();
			System.exit( 0 );
		}

		try {
			CommandLine main = new CommandLine( fromType, toType, units, validate );
			main.execute();
		}
		catch (Exception exc) {
			if (log.isErrorEnabled()) {
				// TODO
				log.error( "Error in main...", exc );
			}
		}
	}

	/**
	 * Display usage text.
	 */
	public static void usage()
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(
				CommandLine.class.getClassLoader().getResourceAsStream(Constants.USAGE_FILE)) ))
		{
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println( line );
			}
		}
		catch (IOException exc) {
			if (log.isErrorEnabled()) {
				log.error( String.format("Error trying to read usage text '%s'.", Constants.USAGE_FILE), exc );
			}
			throw new RuntimeException( exc );
		}
	}
	
	
	protected CommandLine( String fromType, String toType, String units, String validation )
	{
		if (validateParameters(fromType, toType, units)) {
			try {
				this.fromType = ConversionUtil.getConversionType( fromType );
				this.toType = ConversionUtil.getConversionType( toType );
				this.units = new BigDecimal( units );
				if (validation != null) {
					this.validation = new BigDecimal( validation );
				}
				initialized = true;
			}
			catch (NumberFormatException exc) {
				if (this.units == null) {
					printResult( ResultType.BAD_UNITS, new Object[] {units} );
				}
				else if (this.validation == null) {
					printResult( ResultType.BAD_UNITS, new Object[] {validation} );
				}
				else if (log.isErrorEnabled()) {
					log.error( "Unexpected error while validating parameters.", exc );
				}
			}
			catch (IllegalArgumentException exc) {
				if (this.fromType == null) {
					printResult( ResultType.BAD_TYPE, new Object[] {fromType} );
				}
				else if (this.toType == null) {
					printResult( ResultType.BAD_TYPE, new Object[] {toType} );
				}
				else if (log.isErrorEnabled()) {
					log.error( "Unexpected error while validating parameters.", exc );
				}
			}
		}
	}


	/**
	 * Returns the unit type to convert.
	 * 
	 * @return The source unit type.
	 */
	protected ConversionType getFromType()
	{
		return fromType;
	}
	
	/**
	 * Returns the unit type to output.
	 * 
	 * @return The target unit type.
	 */
	protected ConversionType getToType()
	{
		return toType;
	}
	
	/**
	 * Returns the unit value to convert.
	 * 
	 * @return The value to convert.
	 */
	protected BigDecimal getUnitValue()
	{
		return units;
	}
	
	/**
	 * Returns the unit value to validate.
	 * 
	 * @return The value to verify.
	 */
	protected BigDecimal getValidationValue()
	{
		return validation;
	}

	/**
	 * Returns true if a value to validate is entered.
	 * 
	 * @return True if perform validation.
	 */
	protected boolean doValidation()
	{
		return (validation != null);
	}
	
	/**
	 * Returns true if application is initialized for execution.
	 * 
	 * @return True if initialized.
	 */
	protected boolean isInitialized()
	{
		return initialized;
	}
	
	/**
	 * Performs the unit conversion and prints the result to the console.
	 */
	protected void execute()
	{
		if (!isInitialized()) return;
		
		ConversionType unitType = getFromType();
		ConversionType outType = getToType();
		Conversion converter = ConversionUtil.getConverter( unitType );
		
		try {
			BigDecimal output = null;
			if (outType.isTemperatureType()) {
				output = converter.convertTemperature( getUnitValue().toPlainString(), (TemperatureType)outType );
			}
			else if (outType.isVolumeType()) {
				output = converter.convertVolume( getUnitValue().toPlainString(), (VolumeType)outType );
			}

			if (output != null) {
				ArrayList<String> results = new ArrayList();
				results.add( ConversionUtil.toSingleScale(getUnitValue()).toPlainString() );
				results.add( unitType.getSymbol() );
				results.add( output.toPlainString() );
				results.add( outType.getSymbol() );
				
				if (doValidation()) {
					BigDecimal validatedValue = ConversionUtil.toSingleScale( getValidationValue() );
					results.add( validatedValue.toPlainString() );
					if (validatedValue.compareTo(output) == 0) {
						printResult( ResultType.VALIDATED, results.toArray() );
					}
					else {
						printResult( ResultType.INCORRECT, results.toArray() );
					}
				}
				else {
					printResult( ResultType.CONVERTED, results.toArray() );
				}
			}
		}
		catch (UnsupportedOperationException exc) {
			printResult( ResultType.MISMATCH, new Object[] {unitType.getPrintableName(), outType.getPrintableName()} );
		}
	}
	
	/**
	 * Prints a result message to the console.
	 * 
	 * @param result The result to print.
	 */
	protected void printResult( ResultType result )
	{
		printResult( result, null );
	}

	/**
	 * Prints a result message to the console using parms as substitution values
	 * for String.format().
	 * 
	 * @param result The result to print.
	 * @param parms The substitution parameters.
	 */
	protected void printResult( ResultType result, Object[] parms )
	{
		StringBuilder message = new StringBuilder( "Unit Conversion 1.0:\n" ).
				append( "  " ).
				append( String.format(ConversionUtil.getMessageText(result.toString()), parms) );
		System.out.println( message );
	}

	/**
	 * Verifies the required input parameters are entered. Prints an error
	 * message the console and returns false if any parameter is null.
	 * 
	 * @param fromType The unit type to convert.
	 * @param toType The unit to type to output.
	 * @param units The unit value to convert.
	 * @return False if any parameter is null, otherwise true.
	 */
	protected boolean validateParameters( String fromType, String toType, String units )
	{
		boolean valid = true;
		
		if (fromType == null) {
			printResult( ResultType.NO_FROM );
			valid = false;
		}
		else if (toType == null) {
			printResult( ResultType.NO_TO );
			valid = false;
		}
		else if (units == null) {
			printResult( ResultType.NO_UNITS );
			valid = false;
		}
		
		return valid;
	}
	
	
	protected enum ResultType {
		CONVERTED( "convertedResult" ),
		VALIDATED( "validationGood" ),
		INCORRECT( "validationBad" ),
		NO_FROM( "noFromType" ),
		NO_TO( "noToType" ),
		NO_UNITS( "noUnits" ),
		BAD_TYPE( "badType" ),
		BAD_UNITS( "badValue" ),
		MISMATCH( "typeMismatch" );
	
		@Override
		public String toString() { return messageKey; }

		private ResultType( String messageKey ) {
			this.messageKey = messageKey;
		}
	
		private final String messageKey;
	}


	private boolean initialized;
	private ConversionType fromType;
	private ConversionType toType;
	private BigDecimal units;
	private BigDecimal validation;
	
	private final static Log log = LogFactory.getLog( CommandLine.class );
}
