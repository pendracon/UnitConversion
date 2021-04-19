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
 *
 * @author a.cook@veetechis.com
 */
public enum VolumeType implements ConversionType {
	CUBIC_FEET( "cubic feet", "CF", "cubicFeet" ),
	CUBIC_INCHES( "cubic inches", "CI", "cubicInches" ),
	CUPS( "cups", "cups", "cups" ),
	GALLONS( "gallons", "gal", "gallons" ),
	LITERS( "liters", "l", "liters" ),
	TABLESPOONS( "tablespoons", "tbl", "tablespoons" );
	
	@Override
	/**
	 * Returns false.
	 */
	public boolean isTemperatureType() { return false; }
	
	@Override
	/**
	 * Returns true.
	 */
	public boolean isVolumeType() { return true; }
	
	/**
	 * Returns a string representation of the type suitable for printing.
	 */
	@Override
	public String getPrintableName() { return printName; }

	/**
	 * Returns the type's symbol value.
	 */
	@Override
	public String getSymbol() { return symbol; }

	/**
	 * Returns a string representation of the type suitable for creating
	 * converter instances with ObjectFactory.
	 */
	@Override
	public String toString() { return objectKey; }

	private VolumeType( String printName, String symbol, String objectKey ) {
		this.printName = printName;
		this.symbol = symbol;
		this.objectKey = objectKey;
	}
	
	private final String printName;
	private final String symbol;
	private final String objectKey;
}
