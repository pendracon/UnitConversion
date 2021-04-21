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
package com.veetech.unitconversion.domain.temperature;

import com.veetech.unitconversion.ConversionUtil;
import com.veetech.unitconversion.domain.ConversionImpl;
import java.math.BigDecimal;


/**
 * A unit converter for converting temperatures from degrees Celsius to the
 * equivalent value in Fahrenheit, Kelvin, and Rankine. Conversions are returned
 * in values rounded to tenths;
 * 
 * @author a.cook@veetechis.com
 */
public class CelsiusConversion
		extends ConversionImpl
{
	/**
	 * Creates a new instance for converting temperatures measured in Celsius.
	 * 
	 * @param type The temperature type.
	 */
	public CelsiusConversion( CelsiusType type )
	{
		super( type );
	}

	/**
	 * Returns the temperature measured in degrees Celsius rounded to the
	 * specified precision.
	 * 
	 * @param units The temperature to convert.
	 * @return The temperature in Celsius.
	 */
	@Override
	protected BigDecimal toCelsius( BigDecimal units )
	{
		return ConversionUtil.toSingleScale( units );
	}
	
	/**
	 * Returns the temperature measured in degrees Fahrenheit rounded to the
	 * specified precision.
	 * 
	 * @param units The temperature to convert.
	 * @return The temperature in Fahrenheit.
	 */
	@Override
	protected BigDecimal toFahrenheit( BigDecimal units )
	{
		return ConversionUtil.toSingleScale( ConversionUtil.fromCelsiusToFahrenheit(units) );
	}

	/**
	 * Returns the temperature measured in degrees Kelvin rounded to the
	 * specified precision.
	 * 
	 * @param units The temperature to convert.
	 * @return The temperature in Kelvin.
	 */
	@Override
	protected BigDecimal toKelvin( BigDecimal units )
	{
		return ConversionUtil.toSingleScale( units.add(BigDecimal.valueOf(273.15)) );
	}

	/**
	 * Returns the temperature measured in degrees Rankine rounded to the
	 * specified precision.
	 * 
	 * @param units The temperature to convert.
	 * @return The temperature in Rankine.
	 */
	@Override
	protected BigDecimal toRankine( BigDecimal units )
	{
		return ConversionUtil.toSingleScale(
				ConversionUtil.fromCelsiusToFahrenheit(units).add(BigDecimal.valueOf(459.67)) );
	}
}
