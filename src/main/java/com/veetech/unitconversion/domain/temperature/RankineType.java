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


/**
 * Temperature conversion type for Rankine scale.
 * 
 * @author a.cook@veetechis.com
 */
public class RankineType
		extends TemperatureType
{
	/**
	 * Returns true.
	 */
	@Override
	public boolean isRankineType()
	{
		return true;
	}
	
	/**
	 * Returns a string representation of the type suitable for printing.
	 */
	@Override
	public String getPrintableName()
	{
		return "Rankine";
	}

	/**
	 * Returns the type's symbol value.
	 */
	@Override
	public String getSymbol()
	{
		return "R";
	}

	/**
	 * Returns a string representation of the type suitable for creating
	 * converter instances with ObjectFactory.
	 */
	@Override
	public String toString()
	{ 
		return getPrintableName();
	}
	
	/**
	 * Returns "rankine".
	 */
	@Override
	public String getFactoryKey()
	{
		return "rankine";
	}
}
