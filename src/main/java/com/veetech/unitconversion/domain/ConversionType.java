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
 * An interface for temperature and volumetric conversion types.
 * 
 * @author a.cook@veetechis.com
 */
public interface ConversionType
{
	/**
	 * Returns true if this instance is a type for converting temperatures.
	 * 
	 * @return True if this is a temperature conversion type.
	 */
	public boolean isTemperatureType();
	
	/**
	 * Returns true if this instance is a type for converting volumes.
	 * 
	 * @return True if this is a volumetric conversion type.
	 */
	public boolean isVolumeType();
}
