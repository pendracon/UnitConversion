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

import java.math.BigDecimal;


/**
 * An interface for classes performing temperature and/or volumetric unit
 * conversions
 * 
 * @author a.cook@veetechis.com
 */
public interface Conversion
{
	/**
	 * Returns the conversion unit type of this instance.
	 * 
	 * @return The unit conversion type.
	 */
	ConversionType getConversionType();

	/**
	 * Converts the given source units value to the specified target type.
	 * 
	 * Throws an exception if temperature conversion is not supported, if the
	 * given units are non-numeric, or if either parameter value is null.
	 * 
	 * @param units The source unit type value to convert.
	 * @param type The target unit type to return.
	 * @return The converted value.
	 */
	BigDecimal convertUnits( String units, ConversionType type )
			throws NullPointerException, NumberFormatException, UnsupportedOperationException;
}
