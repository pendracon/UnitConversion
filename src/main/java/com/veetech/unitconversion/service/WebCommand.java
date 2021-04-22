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
package com.veetech.unitconversion.service;

import com.veetech.unitconversion.CommandLine;
import com.veetech.unitconversion.ConversionUtil;
import com.veetech.unitconversion.domain.Constants;
import java.util.HashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Command processor for Unit Conversion web service.
 * 
 * @author a.cook@veetechis.com
 */
public class WebCommand
		extends CommandLine
{
	/**
	 * Creates a new WebCommand instance.
	 * 
	 * @param fromType The type to convert.
	 * @param toType The type to output.
	 * @param units The value to convert.
	 * @param validation The value to validate.
	 */
	public WebCommand( String fromType, String toType, String units, String validation )
	{
		super( fromType, toType, units, validation );		
	}

	
	/**
	 * Returns the conversion result in JSON formation.
	 * 
	 * @return The conversion result.
	 */
	public String getResponseBody()
	{
		return responseBody;
	}
	
	/**
	 * Cleans up the instance.
	 */
	@Override
	public void cleanup()
	{
		super.cleanup();
		
		queryFromType = null;
		queryToType = null;
		queryUnits = null;
		queryValidation = null;
		responseBody = null;
	}

	
	/**
	 * Returns the unit type to convert.
	 * 
	 * @return The source unit type.
	 */
	protected String getResponseFromType()
	{
		String type = queryFromType;
		if (getFromType() != null) {
			type = getFromType().getPrintableName();
		}

		return (type != null ? type : Constants.NO_VALUE);
	}
	
	/**
	 * Returns the unit type to output.
	 * 
	 * @return The target unit type.
	 */
	protected String getResponseToType()
	{
		String type = queryToType;
		if (getToType() != null) {
			type = getToType().getPrintableName();
		}

		return (type != null ? type : Constants.NO_VALUE);
	}
	
	/**
	 * Returns the unit value to convert.
	 * 
	 * @return The value to convert.
	 */
	protected String getResponseUnitValue()
	{
		String value = queryUnits;
		if (getUnitValue() != null) {
			value = ConversionUtil.toSingleScale(getUnitValue()).toPlainString();
		}

		return (value != null ? value : Constants.NO_VALUE);
	}
	
	/**
	 * Returns the converted unit value.
	 * 
	 * @return The converted unit value, or null.
	 */
	protected String getResponseOutputValue()
	{
		String value = Constants.NO_VALUE;
		if (getOutputValue() != null) {
			value = getOutputValue().toPlainString();
		}

		return (value != null ? value : Constants.NO_VALUE);
	}
	
	/**
	 * Returns the unit value to validate.
	 * 
	 * @return The value to verify.
	 */
	protected String getResponseValidationValue()
	{
		String value = queryValidation;
		if (getValidationValue() != null) {
			value = ConversionUtil.toSingleScale(getValidationValue()).toPlainString();
		}

		return (value != null ? value : Constants.NO_VALUE);
	}
	
	/**
	 * Performs the unit conversion and creates the result in JSON format.
	 */
	@Override
	public void execute()
	{
		super.execute();
	}
	
	/**
	 * Creates a result message in JSON format for returning to the web client.
	 * 
	 * @param result The result to create.
	 */
	@Override
	protected void printResult( ResultType result )
	{
		printResult( result, null );
	}

	/**
	 * Creates a result message in JSON format for returning to the web client.
	 * 
	 * @param result The result to create.
	 * @param parms The substitution parameters.
	 */
	@Override
	protected void printResult( ResultType result, Object[] parms )
	{
		// We're creating just a simple result message with the following
		// scheme, so we'll just build it manually for now, e.g.:
		// {
		//   "appVersion": "Unit Conversion 1.0",
		//   "inputType": "Celsius",
		//   "inputValue": "100.0",
		//   "outputType": "Fahrenheit",
		//   "outputValue": "212.0",
		//   "validationValue": "212.0",
		//   "validation": "Correct"
		// }
		//
		String validationResult;
		switch(result) {
			case CONVERTED:
				validationResult = Constants.NO_VALUE;
				break;
			case VALIDATED:
				validationResult = "Correct";
				break;
			default:
				validationResult = "Incorrect";
		}
		HashMap<String,String> templateValues = new HashMap();
		templateValues.put( Constants.INPUT_TYPE_TAG, getResponseFromType() );
		templateValues.put( Constants.INPUT_VALUE_TAG, getResponseUnitValue() );
		templateValues.put( Constants.OUTPUT_TYPE_TAG, getResponseToType() );
		templateValues.put( Constants.OUTPUT_VALUE_TAG, getResponseOutputValue() );
		templateValues.put( Constants.VALIDATION_VALUE_TAG, getResponseValidationValue() );
		templateValues.put( Constants.VALIDATION_RESULT_TAG, validationResult );
		
		if( log.isDebugEnabled() ) {
			log.debug( String.format("Generating response '%s' from template with values: %s", result.toString(), templateValues) );
		}
		
		responseBody = ConversionUtil.textFormat(
				ConversionUtil.getMessageText("responseBodyTemplate"), templateValues );
	}

	/**
	 * Verifies the required input parameters are entered. Creates an error
	 * message and returns false if any parameter is null.
	 * 
	 * @param fromType The unit type to convert.
	 * @param toType The unit to type to output.
	 * @param units The unit value to convert.
	 * @param validation The unit value to validate.
	 * @return False if any parameter is null, otherwise true.
	 */
	@Override
	protected boolean validateParameters( String fromType, String toType, String units, String validation )
	{
		queryFromType = fromType;
		queryToType = toType;
		queryUnits = units;
		queryValidation = validation;

		return super.validateParameters( fromType, toType, units, validation );
	}

	
	private String queryFromType;
	private String queryToType;
	private String queryUnits;
	private String queryValidation;
	private String responseBody;
	
	private final static Log log = LogFactory.getLog( WebCommand.class );
}
