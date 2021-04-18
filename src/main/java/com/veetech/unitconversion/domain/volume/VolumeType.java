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
	CUBIC_FEET( "cubicFeet" ),
	CUBIC_INCHES( "cubicInches" ),
	CUPS( "cups" ),
	GALLONS( "gallons" ),
	LITERS( "liters" ),
	TABLESPOONS( "tablespoons" );
	
	@Override
	public boolean isTemperatureType() { return false; }
	
	@Override
	public boolean isVolumeType() { return true; }
	
	@Override
	public String toString() { return description; }

	private VolumeType( String description ) {
		this.description = description;
	}
	
	private final String description;
}
