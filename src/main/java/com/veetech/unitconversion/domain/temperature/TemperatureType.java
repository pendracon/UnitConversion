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
	CELSIUS( "celsius" ),
	FAHRENHEIT( "fahrenheit" ),
	KELVIN( "kelvin" ),
	RANKINE( "rankine" );
	
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
	 * Returns a string representation of the type suitable for creating
	 * converter instances with ObjectFactory;
	 */
	@Override
	public String toString() { return description; }

	private TemperatureType( String description ) {
		this.description = description;
	}
	
	private final String description;
}
