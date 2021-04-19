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
 *
 * @author a.cook@veetechis.com
 */
public enum TemperatureType implements ConversionType {
	CELSIUS( "Celsius", "C" ),
	FAHRENHEIT( "Fahrenheit", "F" ),
	KELVIN( "Kelvin", "K" ),
	RANKINE( "Rankine", "R" );
	
	/**
	 * Returns true;
	 */
	@Override
	public boolean isTemperatureType() { return true; }
	
	/**
	 * Returns false;
	 */
	@Override
	public boolean isVolumeType() { return false; }
	
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
	public String toString() { return printName.toLowerCase(); }

	private TemperatureType( String printName, String symbol ) {
		this.printName = printName;
		this.symbol = symbol;
	}
	
	private final String printName;
	private final String symbol;
}
