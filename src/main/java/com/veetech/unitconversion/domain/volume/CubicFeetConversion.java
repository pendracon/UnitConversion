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

import com.veetech.unitconversion.domain.ConversionImpl;
import java.math.BigDecimal;


/**
 * A unit converter for converting dry volumes from cubic feet to the equivalent
 * value in cubic inches, cups, gallons, liters, and tablespoons. Conversions
 * are calculated to 4-digit precision and returned in values rounded to tenths.
 * 
 * Only U.S. measurements are converted by this class.
 * 
 * @author a.cook@veetechis.com
 */
public class CubicFeetConversion
		extends ConversionImpl
{
	/**
	 * Creates a new instance for converting volumes measured in cubic feet.
	 */
	public CubicFeetConversion()
	{
		super( VolumeType.CUBIC_FEET );
	}

	
	/**
	 * Returns the volume measured in cubic feet.
	 * 
	 * @param units The volume to convert.
	 * @return The volume in cubic feet.
	 */
	@Override
	protected BigDecimal toCubicFeet( BigDecimal units )
	{
		return toSingleScale( units );
	}

	/**
	 * Returns the volume measured in cubic inches.
	 * 
	 * @param units The volume to convert.
	 * @return The volume in cubic inches.
	 */
	@Override
	protected BigDecimal toCubicInches( BigDecimal units )
	{
		return toSingleScale( units.multiply(BigDecimal.valueOf(1728)) );
	}

	/**
	 * Returns the volume measured in cups.
	 * 
	 * @param units The volume to convert.
	 * @return The volume in cups.
	 */
	@Override
	protected BigDecimal toCups( BigDecimal units )
	{
		return toSingleScale( units.multiply(BigDecimal.valueOf(119.6883)) );
	}

	/**
	 * Returns the volume measured in gallons.
	 * 
	 * @param units The volume to convert.
	 * @return The volume in gallons.
	 */
	@Override
	protected BigDecimal toGallons( BigDecimal units )
	{
		return toSingleScale( units.multiply(BigDecimal.valueOf(6.4285)) );
	}

	/**
	 * Returns the volume measured in liters.
	 * 
	 * @param units The volume to convert.
	 * @return The volume in liters.
	 */
	@Override
	protected BigDecimal toLiters( BigDecimal units )
	{
		return toSingleScale( units.multiply(BigDecimal.valueOf(28.3169)) );
	}

	/**
	 * Returns the volume measured in tablespoons.
	 * 
	 * @param units The volume to convert.
	 * @return The volume in tablespoons.
	 */
	@Override
	protected BigDecimal toTablespoons( BigDecimal units )
	{
		return toSingleScale( units.multiply(BigDecimal.valueOf(1915.013)) );
	}
}
