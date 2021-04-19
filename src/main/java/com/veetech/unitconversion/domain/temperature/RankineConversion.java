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
 * A unit converter for converting temperatures from degrees Rankine to the
 * equivalent value in Celsius, Fahrenheit, and Kelvin. Conversions are returned
 * in values rounded to tenths;
 * 
 * @author a.cook@veetechis.com
 */
public class RankineConversion
		extends ConversionImpl
{
	/**
	 * Creates a new instance for converting temperatures measured in Rankine.
	 */
	public RankineConversion()
	{
		super( TemperatureType.RANKINE );
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
		return ConversionUtil.toSingleScale(
				ConversionUtil.fromFahrenheitToCelsius(units.subtract(BigDecimal.valueOf(459.67))) );
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
		return ConversionUtil.toSingleScale( units.subtract(BigDecimal.valueOf(459.67)) );
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
		return ConversionUtil.toSingleScale( units.multiply(BigDecimal.valueOf(5/9f)) );
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
		return ConversionUtil.toSingleScale( units );
	}
}
