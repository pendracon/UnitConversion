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
		boolean hasParam = false;
		
		String[] arg;
		for (String parm : args) {
			if (parm.startsWith("--")) {
				arg = parm.split( "\\=" );
				if (arg[0].substring(2).equalsIgnoreCase(Constants.HELP_ARG)) {
					showUsage = true;
					break;
				}
				if (arg[0].substring(2).equalsIgnoreCase(Constants.FROM_TYPE_ARG)) {
					fromType = (arg.length == 2 ? arg[1] : null);
					hasParam = true;
				}
				if (arg[0].substring(2).equalsIgnoreCase(Constants.TO_TYPE_ARG)) {
					toType = (arg.length == 2 ? arg[1] : null);
					hasParam = true;
				}
				if (arg[0].substring(2).equalsIgnoreCase(Constants.UNIT_VALUE_ARG)) {
					units = (arg.length == 2 ? arg[1] : null);
					hasParam = true;
				}
				if (arg[0].substring(2).equalsIgnoreCase(Constants.VALIDATE_VALUE_ARG)) {
					validate = (arg.length == 2 ? arg[1] : null);
					hasParam = true;
				}
			}
		}
		
		if (showUsage) {
			usage();
		}
		else if (!hasParam) {
			System.out.println( String.format("%s\n  %s", Constants.VERSION, Constants.NO_ARG) );
		}
		else {
			try {
				CommandLine main = new CommandLine( fromType, toType, units, validate );
				main.execute();
				main.cleanup();  // good citizen :)
			}
			catch (Exception exc) {
				if (log.isErrorEnabled()) {
					log.error( "Conversion execution error.", exc );
				}
			}
		}
	}

	/**
	 * Display usage text.
	 */
	public static void usage()
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(
				CommandLine.class.getClassLoader().getResourceAsStream(Constants.USAGE_FILE))))
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
		if (validateParameters(fromType, toType, units, validation)) {
			try {
				this.fromType = ConversionUtil.getConversionType( fromType );
				this.toType = ConversionUtil.getConversionType( toType );
				this.units = new BigDecimal( units );
				try {
					if (validation != null && !validation.equals(Constants.NO_VALUE)) {
						this.validation = new BigDecimal( validation );
					}
				}
				catch (NumberFormatException exc ) {
					// bad validation input
				}
				initialized = true;
			}
			catch (NumberFormatException exc) {
				if (this.units == null) {
					printResult( ResultType.BAD_UNITS, new Object[] {units} );
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
	 * @return The source unit type, or null.
	 */
	protected ConversionType getFromType()
	{
		return fromType;
	}
	
	/**
	 * Returns the unit type to output.
	 * 
	 * @return The target unit type, or null.
	 */
	protected ConversionType getToType()
	{
		return toType;
	}
	
	/**
	 * Returns the unit value to convert.
	 * 
	 * @return The value to convert, or null.
	 */
	protected BigDecimal getUnitValue()
	{
		return units;
	}
	
	/**
	 * Returns the converted unit value.
	 * 
	 * @return The converted unit value, or null.
	 */
	protected BigDecimal getOutputValue()
	{
		return output;
	}
	
	/**
	 * Returns the unit value to validate.
	 * 
	 * @return The value to verify, or null.
	 */
	protected BigDecimal getValidationValue()
	{
		return validation;
	}

	/**
	 * Returns the unit value to validate as a string.
	 * 
	 * @return The validation units.
	 */
	protected String getValidationUnits()
	{
		return validationUnits;
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
			output = converter.convertUnits( getUnitValue().toPlainString(), outType );
			
			if (output != null) {
				if (log.isDebugEnabled()) {
					log.debug( String.format("Value %s converted to %s.", getUnitValue().toPlainString(), output.toPlainString()) );
				}
				
				ResultType result = ResultType.CONVERTED;
				ArrayList<String> resultData = new ArrayList();
				resultData.add( ConversionUtil.toSingleScale(getUnitValue()).toPlainString() );
				resultData.add( unitType.getSymbol() );
				resultData.add( output.toPlainString() );
				resultData.add( outType.getSymbol() );
				
				if (doValidation()) {
					BigDecimal validatedValue = ConversionUtil.toSingleScale( getValidationValue() );
					resultData.add( validatedValue.toPlainString() );
					if (validatedValue.compareTo(output) == 0) {
						result = ResultType.VALIDATED;
					}
					else {
						result = ResultType.INCORRECT;
					}
				}
				else if (getValidationUnits() != null && !getValidationUnits().equals(Constants.NO_VALUE)) {
					resultData.add( getValidationUnits() );
					result = ResultType.INCORRECT;
				}
				printResult( result, resultData.toArray() );
			}
		}
		catch (UnsupportedOperationException exc) {
			printResult( ResultType.MISMATCH, new Object[] {unitType.getPrintableName(), outType.getPrintableName()} );
		}
	}
	
	/**
	 * Prints a result message to the console and returns the message.
	 * 
	 * @param result The result to print.
	 * @return The result message.
	 */
	protected String printResult( ResultType result )
	{
		return printResult( result, null );
	}

	/**
	 * Generates a result message from a template using parms as substitution
	 * values and prints it to the console. Returns the generated message.
	 * 
	 * @param result The result to print.
	 * @param parms The substitution parameters.
	 * @return The result message.
	 */
	protected String printResult( ResultType result, Object[] parms )
	{
		return printResult( result, parms, true );
	}
	
	/**
	 * Generates a result message from a template using parms as substitution
	 * values and returns the generated message. If printMessage is true then
	 * the message is printed to the console's standard output.
	 * 
	 * @param result The result to print.
	 * @param parms The substitution parameters.
	 * @param printMessage Flag whether to output the message to stdout.
	 * @return The result message.
	 */
	protected String printResult( ResultType result, Object[] parms, boolean printMessage )
	{
		StringBuilder message = new StringBuilder( String.format("%s\n", Constants.VERSION) ).
				append( "  " ).
				append( String.format(ConversionUtil.getMessageText(result.toString()), parms) );
		
		if (printMessage) System.out.println( message );
		
		if (log.isInfoEnabled()) {
			log.info( message );
		}
		
		return message.toString();
	}

	/**
	 * Verifies the required input parameters are entered. Prints an error
	 * message the console and returns false if any parameter other than
	 * validation is null.
	 * 
	 * @param fromType The unit type to convert.
	 * @param toType The unit to type to output.
	 * @param units The unit value to convert.
	 * @param validation The unit value to validate.
	 * @return False if any parameter is null, otherwise true.
	 */
	protected boolean validateParameters( String fromType, String toType, String units, String validation )
	{
		boolean valid = true;
		
		if (fromType == null || fromType.equals(Constants.NO_VALUE)) {
			printResult( ResultType.NO_FROM );
			valid = false;
		}
		else if (toType == null || toType.equals(Constants.NO_VALUE)) {
			printResult( ResultType.NO_TO );
			valid = false;
		}
		else if (units == null || units.equals(Constants.NO_VALUE)) {
			printResult( ResultType.NO_UNITS );
			valid = false;
		}
		
		validationUnits = validation;
		
		return valid;
	}
	
	/**
	 * Cleans up the instance.
	 */
	protected void cleanup()
	{
		fromType = null;
		toType = null;
		units = null;
		output = null;
		validation = null;
		validationUnits = null;
	}
	
	
	public static enum ResultType {
		CONVERTED( "convertedResult" ),
		VALIDATED( "validationGood" ),
		INCORRECT( "validationBad" ),
		NO_FROM( "noFromType" ),
		NO_TO( "noToType" ),
		NO_UNITS( "noUnits" ),
		BAD_TYPE( "badType" ),
		BAD_UNITS( "badUnits" ),
		BAD_VALUE( "badValue" ),
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
	private BigDecimal output;
	private BigDecimal validation;
	private String validationUnits;
	
	private final static Log log = LogFactory.getLog( CommandLine.class );
}
