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
import com.veetech.factory.ObjectFactory;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Common helper methods.
 * 
 * @author a.cook@veetechis.com
 */
public class ConversionUtil
{
	/**
	 * Returns the given units formatted to single scale precision.
	 * @param units The value to format.
	 * @return The units in tenths.
	 */
	public static BigDecimal toSingleScale( BigDecimal units )
	{
		RoundingMode rounding = (units.compareTo(BigDecimal.ZERO) < 0 ? RoundingMode.HALF_DOWN : RoundingMode.HALF_UP);
		return units.setScale( 1, rounding );
	}

	/*
	 * Converts the given value from Celsius to Fahrenheit with no rounding.
	 */
	public static BigDecimal fromCelsiusToFahrenheit( BigDecimal units )
	{
		return units.multiply( BigDecimal.valueOf(9/5f) ).add( BigDecimal.valueOf(32) );
	}
	
	/*
	 * Converts the given value from Fahrenheit to Celsius with no rounding.
	 */
	public static BigDecimal fromFahrenheitToCelsius( BigDecimal units )
	{
		return units.subtract( BigDecimal.valueOf(32) ).multiply( BigDecimal.valueOf(5/9f) );
	}

	/**
	 * Returns a unit converter for the specified conversion type. Returns null
	 * if a matching converter type can't be found.
	 * 
	 * @param type The conversion type to return.
	 * @return A matching unit converter.
	 */
	public static Conversion getConverter( ConversionType type )
	{
		Conversion converter = null;
		
		try {
			return (Conversion) ObjectFactory.getInstance().createObject( type.getFactoryKey(), new Object[] {type} );
		}
		catch (ClassNotFoundException exc) {
			if (log.isDebugEnabled()) {
				log.debug( String.format("Converter for units type %s not found.", type), exc );
			}
		}
		catch (InstantiationException exc) { 
			if (log.isErrorEnabled()) {
				log.error( String.format("Failed to create converter for units type %s.", type), exc );
			}
		}
		
		return converter;
	}
	
	/**
	 * Returns the matching ConversionType for the given type value.
	 * 
	 * Throws an exception if a matching type isn't found.
	 * 
	 * @param type The conversion type to return.
	 * @return The matching conversion type.
	 * @throws java.lang.IllegalArgumentException If no matching conversion is
	 * found.
	 */
	public static ConversionType getConversionType( String type )
			throws IllegalArgumentException
	{
		ConversionType ctype = null;
		
		String key = type.replaceAll( "_", "" ).replaceAll( "-", "" ).toLowerCase() + "Type";

		try {
			ctype = (ConversionType) ObjectFactory.getInstance().createObject( key );
		}
		catch (ClassNotFoundException exc) {
			if (log.isDebugEnabled()) {
				log.debug( String.format("Conversion type instance for key '%s' not found.", key), exc );
			}
			
			throw new IllegalArgumentException( String.format("No matching conversion type for '%s'.", type) );
		}
		catch (InstantiationException exc) {
			if (log.isErrorEnabled()) {
				log.error( String.format("Failed to create conversion type instance for key '%s'.", key), exc );
			}
			
			throw new IllegalArgumentException( String.format("Conversion type error for '%s'.", type) );
		}
		
		return ctype;
	}
	
	/**
	 * Returns the configured message text for the given key value. Returns
	 * error message 'Message text not found.' if a corresponding message can't
	 * be found in configuration.
	 * 
	 * @param key The message text key.
	 * @return The configured message text or error message.
	 */
	public static String getMessageText( String key )
	{
		String message = messages.getProperty( key );
		if (message == null) message = Constants.DEFAULT_MESSAGE_TEXT;
		
		return message;
	}
	
	/**
	 * Performs a simple text-only token replacement.  Returns the given
	 * template parameter with token values defined in the tokens parameter.
	 * 
	 * @param template The template with tokens to replace.
	 * @param tokens The token replacement values.
	 * @return The token-substituted template.
	 */
	public static String textFormat( String template, Map<String,String> tokens )
	{
		String value;
		String text = template;
		
		for (String token : tokens.keySet()) {
			value = tokens.get( token );
			if (token != null && value != null) {
				text = text.replaceAll( token, value );
			}
		}
		
		return text;
	}

	
	private final static Log log = LogFactory.getLog( ConversionUtil.class );
	
	private final static Properties messages = new Properties();
	static {
		try {
			messages.load( ConversionUtil.class.getClassLoader().getResourceAsStream(Constants.MESSAGES) );
		}
		catch (IOException exc) {
			if (log.isWarnEnabled() ) {
				log.warn( String.format("Could not load resource %s.", Constants.MESSAGES) );
			}
		}
	}
}
