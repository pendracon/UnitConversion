package com.veetech.unitconversion.test;

import com.veetech.unitconversion.domain.Conversion;
import com.veetech.unitconversion.domain.temperature.TemperatureType;
import com.veetech.unitconversion.domain.volume.VolumeType;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;


public class VolsConversionTest
		extends ConversionTestBase
{
	@Test
	public void nullValue()
	{
		Conversion converter = getConverter( VolumeType.CUBIC_FEET );
		Assert.assertNotNull( converter );

		try {
			converter.convertVolume( null, VolumeType.LITERS );
			Assert.fail();
		}
		catch( NullPointerException exc ) {
			Assert.assertTrue( true );
		}

		try {
			converter.convertVolume( "10", null );
			Assert.fail();
		}
		catch( NullPointerException exc ) {
			Assert.assertTrue( true );
		}
	}
	
	@Test
	public void toTemperature()
	{
		Conversion converter = getConverter( VolumeType.CUBIC_FEET );
		Assert.assertNotNull( converter );

		try {
			converter.convertTemperature( "10", TemperatureType.CELSIUS );
			Assert.fail();
		}
		catch( UnsupportedOperationException exc ) {
			Assert.assertTrue( true );
		}
	}
	
	@Test
	public void fromCubicFeet()
	{
		Conversion converter = getConverter( VolumeType.CUBIC_FEET );
		Assert.assertNotNull( converter );
		Assert.assertEquals( VolumeType.CUBIC_FEET, converter.getConversionType() );
		
		BigDecimal cf = converter.convertVolume( "2.25", VolumeType.CUBIC_FEET );
		Assert.assertEquals( "2.3", cf.toPlainString() );
			
		BigDecimal ci = converter.convertVolume( "2.25", VolumeType.CUBIC_INCHES );
		Assert.assertEquals( "3888.0", ci.toPlainString() );
			
		BigDecimal cu = converter.convertVolume( "2.25", VolumeType.CUPS );
		Assert.assertEquals( "269.3", cu.toPlainString() );
			
		BigDecimal ga = converter.convertVolume( "2.25", VolumeType.GALLONS );
		Assert.assertEquals( "14.5", ga.toPlainString() );
			
		BigDecimal li = converter.convertVolume( "2.25", VolumeType.LITERS );
		Assert.assertEquals( "63.7", li.toPlainString() );
			
		BigDecimal ta = converter.convertVolume( "2.25", VolumeType.TABLESPOONS );
		Assert.assertEquals( "4308.8", ta.toPlainString() );
	}
	
	@Test
	public void fromCubicInches()
	{
		Conversion converter = getConverter( VolumeType.CUBIC_INCHES );
		Assert.assertNotNull( converter );
		Assert.assertEquals( VolumeType.CUBIC_INCHES, converter.getConversionType() );
		
		BigDecimal cf = converter.convertVolume( "2250", VolumeType.CUBIC_FEET );
		Assert.assertEquals( "1.3", cf.toPlainString() );
			
		BigDecimal ci = converter.convertVolume( "2250", VolumeType.CUBIC_INCHES );
		Assert.assertEquals( "2250.0", ci.toPlainString() );
			
		BigDecimal cu = converter.convertVolume( "2250", VolumeType.CUPS );
		Assert.assertEquals( "155.8", cu.toPlainString() );
			
		BigDecimal ga = converter.convertVolume( "2250", VolumeType.GALLONS );
		Assert.assertEquals( "8.4", ga.toPlainString() );
			
		BigDecimal li = converter.convertVolume( "2250", VolumeType.LITERS );
		Assert.assertEquals( "36.9", li.toPlainString() );
			
		BigDecimal ta = converter.convertVolume( "2250", VolumeType.TABLESPOONS );
		Assert.assertEquals( "2493.5", ta.toPlainString() );
	}
	
	@Test
	public void fromCups()
	{
		Conversion converter = getConverter( VolumeType.CUPS );
		Assert.assertNotNull( converter );
		Assert.assertEquals( VolumeType.CUPS, converter.getConversionType() );
		
		BigDecimal cf = converter.convertVolume( "225", VolumeType.CUBIC_FEET );
		Assert.assertEquals( "1.9", cf.toPlainString() );
			
		BigDecimal ci = converter.convertVolume( "225", VolumeType.CUBIC_INCHES );
		Assert.assertEquals( "3248.4", ci.toPlainString() );
			
		BigDecimal cu = converter.convertVolume( "225", VolumeType.CUPS );
		Assert.assertEquals( "225.0", cu.toPlainString() );
			
		BigDecimal ga = converter.convertVolume( "225", VolumeType.GALLONS );
		Assert.assertEquals( "12.1", ga.toPlainString() );
			
		BigDecimal li = converter.convertVolume( "225", VolumeType.LITERS );
		Assert.assertEquals( "53.2", li.toPlainString() );
			
		BigDecimal ta = converter.convertVolume( "225", VolumeType.TABLESPOONS );
		Assert.assertEquals( "3600.0", ta.toPlainString() );
	}
	
	@Test
	public void fromGallons()
	{
		Conversion converter = getConverter( VolumeType.GALLONS );
		Assert.assertNotNull( converter );
		Assert.assertEquals( VolumeType.GALLONS, converter.getConversionType() );
		
		BigDecimal cf = converter.convertVolume( "22.5", VolumeType.CUBIC_FEET );
		Assert.assertEquals( "3.5", cf.toPlainString() );
			
		BigDecimal ci = converter.convertVolume( "22.5", VolumeType.CUBIC_INCHES );
		Assert.assertEquals( "6048.1", ci.toPlainString() );
			
		BigDecimal cu = converter.convertVolume( "22.5", VolumeType.CUPS );
		Assert.assertEquals( "418.9", cu.toPlainString() );
			
		BigDecimal ga = converter.convertVolume( "22.5", VolumeType.GALLONS );
		Assert.assertEquals( "22.5", ga.toPlainString() );
			
		BigDecimal li = converter.convertVolume( "22.5", VolumeType.LITERS );
		Assert.assertEquals( "99.1", li.toPlainString() );
			
		BigDecimal ta = converter.convertVolume( "22.5", VolumeType.TABLESPOONS );
		Assert.assertEquals( "6702.6", ta.toPlainString() );
	}
	
	@Test
	public void fromLiters()
	{
		Conversion converter = getConverter( VolumeType.LITERS );
		Assert.assertNotNull( converter );
		Assert.assertEquals( VolumeType.LITERS, converter.getConversionType() );
		
		BigDecimal cf = converter.convertVolume( "22.5", VolumeType.CUBIC_FEET );
		Assert.assertEquals( "0.8", cf.toPlainString() );
			
		BigDecimal ci = converter.convertVolume( "22.5", VolumeType.CUBIC_INCHES );
		Assert.assertEquals( "1373.0", ci.toPlainString() );
			
		BigDecimal cu = converter.convertVolume( "22.5", VolumeType.CUPS );
		Assert.assertEquals( "95.1", cu.toPlainString() );
			
		BigDecimal ga = converter.convertVolume( "22.5", VolumeType.GALLONS );
		Assert.assertEquals( "5.1", ga.toPlainString() );
			
		BigDecimal li = converter.convertVolume( "22.5", VolumeType.LITERS );
		Assert.assertEquals( "22.5", li.toPlainString() );
			
		BigDecimal ta = converter.convertVolume( "22.5", VolumeType.TABLESPOONS );
		Assert.assertEquals( "1521.6", ta.toPlainString() );
	}
	
	@Test
	public void fromTablespoons()
	{
		Conversion converter = getConverter( VolumeType.TABLESPOONS );
		Assert.assertNotNull( converter );
		Assert.assertEquals( VolumeType.TABLESPOONS, converter.getConversionType() );
		
		BigDecimal cf = converter.convertVolume( "2250", VolumeType.CUBIC_FEET );
		Assert.assertEquals( "1.2", cf.toPlainString() );
			
		BigDecimal ci = converter.convertVolume( "2250", VolumeType.CUBIC_INCHES );
		Assert.assertEquals( "2030.3", ci.toPlainString() );
			
		BigDecimal cu = converter.convertVolume( "2250", VolumeType.CUPS );
		Assert.assertEquals( "140.6", cu.toPlainString() );
			
		BigDecimal ga = converter.convertVolume( "2250", VolumeType.GALLONS );
		Assert.assertEquals( "7.6", ga.toPlainString() );
			
		BigDecimal li = converter.convertVolume( "2250", VolumeType.LITERS );
		Assert.assertEquals( "33.3", li.toPlainString() );
			
		BigDecimal ta = converter.convertVolume( "2250", VolumeType.TABLESPOONS );
		Assert.assertEquals( "2250.0", ta.toPlainString() );
	}
	
	@Test
	public void fromPony()
	{
		try {
			getConverter( VolumeType.valueOf("PONY") );
			Assert.fail();
		}
		catch( IllegalArgumentException exc ) {
			Assert.assertTrue( true );
		}
	}
}
