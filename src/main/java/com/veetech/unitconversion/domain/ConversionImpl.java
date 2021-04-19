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

import com.veetech.unitconversion.domain.volume.VolumeType;
import com.veetech.unitconversion.domain.temperature.TemperatureType;
import java.math.BigDecimal;


/**
 * An abstract implementation of temperature and/or volumetric unit converter
 * classes. Includes methods for performing Celsius-to-Fahrenheit and
 * Fahrenheit-to-Celsius conversions with no rounding. Also includes a method
 * for returning values rounded to tenths (0.n);
 * 
 * @author a.cook@veetechis.com
 */
public abstract class ConversionImpl
		implements Conversion
{
	/**
	 * Creates a new instance of ConversionImpl for specified source temperature
	 * type.
	 * 
	 * Throws an exception if the parameter value is null or is not a supported
	 * unit conversion type.
	 * 
	 * @param type The source unit type to convert.
	 */
	public ConversionImpl( ConversionType type )
			throws IllegalArgumentException, NullPointerException
	{
		if (type.isTemperatureType()) {
			this.sourceTemperatureType = (TemperatureType) type;
		}
		else
		if (type.isVolumeType()) {
			this.sourceVolumeType = (VolumeType) type;
		}
		else {
			throw new IllegalArgumentException( String.format("Not a supported conversion type: %s.", type) );
		}
	}
	
	
	/**
	 * Converts the given source units value to the specified target
	 * temperature type.
	 * 
	 * Throws an exception if temperature conversion is not supported, if the
	 * given units are non-numeric, or if either parameter value is null.
	 * 
	 * @param units The source temperature value to convert.
	 * @param type The target temperature type to return.
	 * @return The converted temperature value.
	 */
	@Override
	public BigDecimal convertTemperature( String units, TemperatureType type )
			throws NullPointerException, NumberFormatException, UnsupportedOperationException
	{
		if (sourceTemperatureType == null)
			throw new UnsupportedOperationException( "Temperature conversion not supported." );
		if (units == null)
			throw new NullPointerException( "Null source value." );
		if (type == null)
			throw new NullPointerException( "Null target conversion type." );

		BigDecimal sourceUnits = new BigDecimal( units );
		BigDecimal targetUnits;
		switch( type ) {
			case CELSIUS:
				targetUnits = toCelsius( sourceUnits );
				break;
			case FAHRENHEIT:
				targetUnits = toFahrenheit( sourceUnits );
				break;
			case KELVIN:
				targetUnits = toKelvin( sourceUnits );
				break;
			case RANKINE:
				targetUnits = toRankine( sourceUnits );
				break;
			default:
				throw new UnsupportedOperationException(
						String.format("Conversion to temperature type %s not supported.", type) );
		}
		
		return targetUnits;
	}
	
	/**
	 * Converts the given source units value to the specified target
	 * volume type.
	 * 
	 * Throws an exception if volumetric conversion is not supported, if the
	 * given units are non-numeric, or if either parameter value is null.
	 * 
	 * @param units The source volume value to convert.
	 * @param type The target volume type to return.
	 * @return The converted volumetric value.
	 */
	@Override
	public BigDecimal convertVolume( String units, VolumeType type )
			throws NullPointerException, NumberFormatException, UnsupportedOperationException
	{
		if (sourceVolumeType == null)
			throw new UnsupportedOperationException( "Volumetric conversion not supported." );
		if (units == null)
			throw new NullPointerException( "Null source value." );
		if (type == null)
			throw new NullPointerException( "Null target conversion value." );
		
		BigDecimal sourceUnits = new BigDecimal( units );
		BigDecimal targetUnits;
		switch( type ) {
			case CUBIC_FEET:
				targetUnits = toCubicFeet( sourceUnits );
				break;
			case CUBIC_INCHES:
				targetUnits = toCubicInches( sourceUnits );
				break;
			case CUPS:
				targetUnits = toCups( sourceUnits );
				break;
			case GALLONS:
				targetUnits = toGallons( sourceUnits );
				break;
			case LITERS:
				targetUnits = toLiters( sourceUnits );
				break;
			case TABLESPOONS:
				targetUnits = toTablespoons( sourceUnits );
				break;
			default:
				throw new UnsupportedOperationException(
						String.format("Conversion to temperature type %s not supported.", type) );
		}
		
		return targetUnits;
	}

	/**
	 * Returns the conversion unit type of this instance.
	 * 
	 * @return The unit conversion type.
	 */
	@Override
	public ConversionType getConversionType()
	{
		if( sourceTemperatureType != null ) return sourceTemperatureType;
		if( sourceVolumeType != null ) return sourceVolumeType;
		
		return null;
	}

	
	/**
	 * Returns the temperature measured in degrees Celsius.
	 * 
	 * Throws an exception if not implemented by the subclass.
	 * 
	 * @param units The temperature to convert.
	 * @return The temperature in Celsius.
	 */
	protected BigDecimal toCelsius( BigDecimal units )
			throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException( "Not a temperature converter." );
	}

	/**
	 * Returns the temperature measured in degrees Fahrenheit.
	 * 
	 * Throws an exception if not implemented by the subclass.
	 * 
	 * @param units The temperature to convert.
	 * @return The temperature in Fahrenheit.
	 */
	protected BigDecimal toFahrenheit( BigDecimal units )
			throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException( "Not a temperature converter." );
	}
	
	/**
	 * Returns the temperature measured in degrees Kelvin.
	 * 
	 * Throws an exception if not implemented by the subclass.
	 * 
	 * @param units The temperature to convert.
	 * @return The temperature in Kelvin.
	 */
	protected BigDecimal toKelvin( BigDecimal units )
			throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException( "Not a temperature converter." );
	}

	/**
	 * Returns the temperature measured in degrees Rankine.
	 * 
	 * Throws an exception if not implemented by the subclass.
	 * 
	 * @param units The temperature to convert.
	 * @return The temperature in Rankine.
	 */
	protected BigDecimal toRankine( BigDecimal units )
			throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException( "Not a temperature converter." );
	}

	/**
	 * Returns the volume measured in cubic feet.
	 * 
	 * Throws an exception if not implemented by the subclass.
	 * 
	 * @param units The volume to convert.
	 * @return The volume in cubic feet.
	 */
	protected BigDecimal toCubicFeet( BigDecimal units )
			throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException( "Not a volumetric converter." );
	}

	/**
	 * Returns the volume measured in cubic inches.
	 * 
	 * Throws an exception if not implemented by the subclass.
	 * 
	 * @param units The volume to convert.
	 * @return The volume in cubic inches.
	 */
	protected BigDecimal toCubicInches( BigDecimal units )
			throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException( "Not a volumetric converter." );
	}

	/**
	 * Returns the volume measured in cups.
	 * 
	 * Throws an exception if not implemented by the subclass.
	 * 
	 * @param units The volume to convert.
	 * @return The volume in cups.
	 */
	protected BigDecimal toCups( BigDecimal units )
			throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException( "Not a volumetric converter." );
	}

	/**
	 * Returns the volume measured in gallons.
	 * 
	 * Throws an exception if not implemented by the subclass.
	 * 
	 * @param units The volume to convert.
	 * @return The volume in gallons.
	 */
	protected BigDecimal toGallons( BigDecimal units )
			throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException( "Not a volumetric converter." );
	}

	/**
	 * Returns the volume measured in liters.
	 * 
	 * Throws an exception if not implemented by the subclass.
	 * 
	 * @param units The volume to convert.
	 * @return The volume in liters.
	 */
	protected BigDecimal toLiters( BigDecimal units )
			throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException( "Not a volumetric converter." );
	}

	/**
	 * Returns the volume measured in tablespoons.
	 * 
	 * Throws an exception if not implemented by the subclass.
	 * 
	 * @param units The volume to convert.
	 * @return The volume in tablespoons.
	 */
	protected BigDecimal toTablespoons( BigDecimal units )
			throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException( "Not a volumetric converter." );
	}
	
	
	// instance members
	private TemperatureType sourceTemperatureType;
	private VolumeType sourceVolumeType;
}
