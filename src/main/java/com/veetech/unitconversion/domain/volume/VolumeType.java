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

import com.veetech.unitconversion.domain.ConversionType;


/**
 * Base class for convertible volumetric unit types.
 * 
 * @author a.cook@veetechis.com
 */
public abstract class VolumeType
		extends ConversionType
{
	/**
	 * Returns false.
	 */
	@Override
	public boolean isTemperatureType()
	{
		return false;
	}
	
	/**
	 * Returns true.
	 */
	@Override
	public boolean isVolumeType()
	{
		return true;
	}
	
	/**
	 * Returns true if this is a cubic feet measurement type.
	 * 
	 * @return False.
	 */
	public boolean isCubicFeetType()
	{
		return false;
	}
	
	/**
	 * Returns true if this is a cubic inches measurement type.
	 * 
	 * @return False.
	 */
	public boolean isCubicInchesType()
	{
		return false;
	}
	
	/**
	 * Returns true if this is a cups measurement type.
	 * 
	 * @return False.
	 */
	public boolean isCupsType()
	{
		return false;
	}
	
	/**
	 * Returns true if this is a gallons measurement type.
	 * 
	 * @return False.
	 */
	public boolean isGallonsType()
	{
		return false;
	}
	
	/**
	 * Returns true if this is a liters measurement type.
	 * 
	 * @return False.
	 */
	public boolean isLitersType()
	{
		return false;
	}
	
	/**
	 * Returns true if this is a tablespoons measurement type.
	 * 
	 * @return False.
	 */
	public boolean isTablespoonsType()
	{
		return false;
	}
	
	/**
	 * Returns the proper name of the unit type suitable for printing.
	 * 
	 * @return The unit type's printable name.
	 */
	@Override
	public abstract String getPrintableName();
	
	/**
	 * Returns the type's printable symbol.
	 * 
	 * @return The unit type symbol.
	 */
	@Override
	public abstract String getSymbol();
}
