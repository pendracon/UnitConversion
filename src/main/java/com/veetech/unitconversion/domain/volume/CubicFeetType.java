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
package com.veetech.unitconversion.domain.volume;


/**
 * Volumetric conversion type for cubic foot measurements.
 * 
 * @author a.cook@veetechis.com
 */
public class CubicFeetType
		extends VolumeType
{
	/**
	 * Returns true.
	 */
	@Override
	public boolean isCubicFeetType()
	{
		return true;
	}
	
	/**
	 * Returns a string representation of the type suitable for printing.
	 */
	@Override
	public String getPrintableName()
	{
		return "cubic feet";
	}

	/**
	 * Returns the type's symbol value.
	 */
	@Override
	public String getSymbol()
	{
		return "cf";
	}

	/**
	 * Returns a string representation of the type.
	 */
	@Override
	public String toString()
	{ 
		return getPrintableName();
	}
	
	/**
	 * Returns "cubicFeet".
	 */
	@Override
	public String getFactoryKey()
	{
		return "cubicFeet";
	}
}
