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
package com.veetech.unitconversion.domain;


/**
 * Base class for temperature and volumetric conversion types.
 * 
 * @author a.cook@veetechis.com
 */
public abstract class ConversionType
{
	/**
	 * Returns true if this instance and the given object are equivalent
	 * conversion types.
	 * 
	 * @param obj The object to check for equivalence.
	 * @return Whether the object is the same conversion type as this instance.
	 */
	@Override
	public boolean equals( Object obj )
	{
		return ((obj instanceof ConversionType) && (hashCode() == obj.hashCode()));
	}

	/**
	 * Returns a hash code for this instance.
	 * @return The hash code.
	 */
	@Override
	public int hashCode()
	{
		return String.format( "%s-%s-%s",
				new Object[] {getFactoryKey(), getPrintableName(), getSymbol()} ).
				hashCode();
	}
	
	/**
	 * Returns true if this instance is a type for converting temperatures.
	 * 
	 * @return True if this is a temperature conversion type.
	 */
	public abstract boolean isTemperatureType();
	
	/**
	 * Returns true if this instance is a type for converting volumes.
	 * 
	 * @return True if this is a volumetric conversion type.
	 */
	public abstract boolean isVolumeType();
	
	/**
	 * Returns the proper name of the unit type suitable for printing.
	 * 
	 * @return The unit type's printable name.
	 */
	public abstract String getPrintableName();
	
	/**
	 * Returns the type's printable symbol.
	 * 
	 * @return The unit type symbol.
	 */
	public abstract String getSymbol();
	
	/**
	 * Returns a string representation of the type suitable for creating
	 * converter instances with a factory.
	 * 
	 * @return The conversion type factory key.
	 */
	public abstract String getFactoryKey();
}
