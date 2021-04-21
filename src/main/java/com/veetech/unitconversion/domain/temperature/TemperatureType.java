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

import com.veetech.unitconversion.domain.ConversionType;


/**
 * Base class for convertible temperature unit types.
 * 
 * @author a.cook@veetechis.com
 */
public abstract class TemperatureType
		extends ConversionType
{
	/**
	 * Returns true.
	 */
	@Override
	public boolean isTemperatureType()
	{
		return true;
	}
	
	/**
	 * Returns false.
	 */
	@Override
	public boolean isVolumeType()
	{
		return false;
	}
	
	/**
	 * Returns true if this is a Celsius type.
	 * 
	 * @return False.
	 */
	public boolean isCelsiusType()
	{
		return false;
	}
	
	/**
	 * Returns true if this is a Fahrenheit type.
	 * 
	 * @return False.
	 */
	public boolean isFahrenheitType()
	{
		return false;
	}
	
	/**
	 * Returns true if this is a Kelvin type.
	 * 
	 * @return False.
	 */
	public boolean isKelvinType()
	{
		return false;
	}
	
	/**
	 * Returns true if this is a Rankine type.
	 * 
	 * @return False.
	 */
	public boolean isRankineType()
	{
		return false;
	}
}
