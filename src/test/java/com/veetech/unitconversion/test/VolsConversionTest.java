package com.veetech.unitconversion.test;

import com.veetech.unitconversion.domain.Conversion;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;


public class VolsConversionTest
		extends ConversionTestBase
{
	@Test
	public void nullValue()
	{
		Conversion converter = getConverter( getConversionType("cubic_feet") );
		Assert.assertNotNull( converter );

		try {
			converter.convertUnits( null, getConversionType("liters") );
			Assert.fail( "Unexpected successful conversion from type 'null'." );
		}
		catch (NullPointerException exc) {
			Assert.assertTrue( true );
		}

		try {
			converter.convertUnits( "10", null );
			Assert.fail( "Unexpected successful conversion to type 'null'." );
		}
		catch (NullPointerException exc) {
			Assert.assertTrue( true );
		}
	}
	
	@Test
	public void toTemperature()
	{
		Conversion converter = getConverter( getConversionType("cubic_feet") );
		Assert.assertNotNull( converter );

		try {
			converter.convertUnits( "10", getConversionType("celsius") );
			Assert.fail( "Unexpected successful conversion from cubic feet to celsius." );
		}
		catch (UnsupportedOperationException exc) {
			Assert.assertTrue( true );
		}
	}
	
	@Test
	public void fromCubicFeet()
	{
		Conversion converter = getConverter( getConversionType("cubic_feet") );
		Assert.assertNotNull( converter );
		Assert.assertEquals( getConversionType("cubic_feet"), converter.getConversionType() );
		
		BigDecimal cf = converter.convertUnits( "2.25", getConversionType("cubic_feet") );
		Assert.assertEquals( "2.3", cf.toPlainString() );
			
		BigDecimal ci = converter.convertUnits( "2.25", getConversionType("cubic_inches") );
		Assert.assertEquals( "3888.0", ci.toPlainString() );
			
		BigDecimal cu = converter.convertUnits( "2.25", getConversionType("cups") );
		Assert.assertEquals( "269.3", cu.toPlainString() );
			
		BigDecimal ga = converter.convertUnits( "2.25", getConversionType("gallons") );
		Assert.assertEquals( "14.5", ga.toPlainString() );
			
		BigDecimal li = converter.convertUnits( "2.25", getConversionType("liters") );
		Assert.assertEquals( "63.7", li.toPlainString() );
			
		BigDecimal ta = converter.convertUnits( "2.25", getConversionType("tablespoons") );
		Assert.assertEquals( "4308.8", ta.toPlainString() );
	}
	
	@Test
	public void fromCubicInches()
	{
		Conversion converter = getConverter( getConversionType("cubic_inches") );
		Assert.assertNotNull( converter );
		Assert.assertEquals( getConversionType("cubic_inches"), converter.getConversionType() );
		
		BigDecimal cf = converter.convertUnits( "2250", getConversionType("cubic_feet") );
		Assert.assertEquals( "1.3", cf.toPlainString() );
			
		BigDecimal ci = converter.convertUnits( "2250", getConversionType("cubic_inches") );
		Assert.assertEquals( "2250.0", ci.toPlainString() );
			
		BigDecimal cu = converter.convertUnits( "2250", getConversionType("cups") );
		Assert.assertEquals( "155.8", cu.toPlainString() );
			
		BigDecimal ga = converter.convertUnits( "2250", getConversionType("gallons") );
		Assert.assertEquals( "8.4", ga.toPlainString() );
			
		BigDecimal li = converter.convertUnits( "2250", getConversionType("liters") );
		Assert.assertEquals( "36.9", li.toPlainString() );
			
		BigDecimal ta = converter.convertUnits( "2250", getConversionType("tablespoons") );
		Assert.assertEquals( "2493.5", ta.toPlainString() );
	}
	
	@Test
	public void fromCups()
	{
		Conversion converter = getConverter( getConversionType("cups") );
		Assert.assertNotNull( converter );
		Assert.assertEquals( getConversionType("cups"), converter.getConversionType() );
		
		BigDecimal cf = converter.convertUnits( "225", getConversionType("cubic_feet") );
		Assert.assertEquals( "1.9", cf.toPlainString() );
			
		BigDecimal ci = converter.convertUnits( "225", getConversionType("cubic_inches") );
		Assert.assertEquals( "3248.4", ci.toPlainString() );
			
		BigDecimal cu = converter.convertUnits( "225", getConversionType("cups") );
		Assert.assertEquals( "225.0", cu.toPlainString() );
			
		BigDecimal ga = converter.convertUnits( "225", getConversionType("gallons") );
		Assert.assertEquals( "12.1", ga.toPlainString() );
			
		BigDecimal li = converter.convertUnits( "225", getConversionType("liters") );
		Assert.assertEquals( "53.2", li.toPlainString() );
			
		BigDecimal ta = converter.convertUnits( "225", getConversionType("tablespoons") );
		Assert.assertEquals( "3600.0", ta.toPlainString() );
	}
	
	@Test
	public void fromGallons()
	{
		Conversion converter = getConverter( getConversionType("gallons") );
		Assert.assertNotNull( converter );
		Assert.assertEquals( getConversionType("gallons"), converter.getConversionType() );
		
		BigDecimal cf = converter.convertUnits( "22.5", getConversionType("cubic_feet") );
		Assert.assertEquals( "3.5", cf.toPlainString() );
			
		BigDecimal ci = converter.convertUnits( "22.5", getConversionType("cubic_inches") );
		Assert.assertEquals( "6048.1", ci.toPlainString() );
			
		BigDecimal cu = converter.convertUnits( "22.5", getConversionType("cups") );
		Assert.assertEquals( "418.9", cu.toPlainString() );
			
		BigDecimal ga = converter.convertUnits( "22.5", getConversionType("gallons") );
		Assert.assertEquals( "22.5", ga.toPlainString() );
			
		BigDecimal li = converter.convertUnits( "22.5", getConversionType("liters") );
		Assert.assertEquals( "99.1", li.toPlainString() );
			
		BigDecimal ta = converter.convertUnits( "22.5", getConversionType("tablespoons") );
		Assert.assertEquals( "6702.6", ta.toPlainString() );
	}
	
	@Test
	public void fromLiters()
	{
		Conversion converter = getConverter( getConversionType("liters") );
		Assert.assertNotNull( converter );
		Assert.assertEquals( getConversionType("liters"), converter.getConversionType() );
		
		BigDecimal cf = converter.convertUnits( "22.5", getConversionType("cubic_feet") );
		Assert.assertEquals( "0.8", cf.toPlainString() );
			
		BigDecimal ci = converter.convertUnits( "22.5", getConversionType("cubic_inches") );
		Assert.assertEquals( "1373.0", ci.toPlainString() );
			
		BigDecimal cu = converter.convertUnits( "22.5", getConversionType("cups") );
		Assert.assertEquals( "95.1", cu.toPlainString() );
			
		BigDecimal ga = converter.convertUnits( "22.5", getConversionType("gallons") );
		Assert.assertEquals( "5.1", ga.toPlainString() );
			
		BigDecimal li = converter.convertUnits( "22.5", getConversionType("liters") );
		Assert.assertEquals( "22.5", li.toPlainString() );
			
		BigDecimal ta = converter.convertUnits( "22.5", getConversionType("tablespoons") );
		Assert.assertEquals( "1521.6", ta.toPlainString() );
	}
	
	@Test
	public void fromTablespoons()
	{
		Conversion converter = getConverter( getConversionType("tablespoons") );
		Assert.assertNotNull( converter );
		Assert.assertEquals( getConversionType("tablespoons"), converter.getConversionType() );
		
		BigDecimal cf = converter.convertUnits( "2250", getConversionType("cubic_feet") );
		Assert.assertEquals( "1.2", cf.toPlainString() );
			
		BigDecimal ci = converter.convertUnits( "2250", getConversionType("cubic_inches") );
		Assert.assertEquals( "2030.3", ci.toPlainString() );
			
		BigDecimal cu = converter.convertUnits( "2250", getConversionType("cups") );
		Assert.assertEquals( "140.6", cu.toPlainString() );
			
		BigDecimal ga = converter.convertUnits( "2250", getConversionType("gallons") );
		Assert.assertEquals( "7.6", ga.toPlainString() );
			
		BigDecimal li = converter.convertUnits( "2250", getConversionType("liters") );
		Assert.assertEquals( "33.3", li.toPlainString() );
			
		BigDecimal ta = converter.convertUnits( "2250", getConversionType("tablespoons") );
		Assert.assertEquals( "2250.0", ta.toPlainString() );
	}
	
	@Test
	public void typePony()
	{
		try {
			getConverter( getConversionType("PONY") );
			Assert.fail( "Got unexpected conversion type 'PONY'." );
		}
		catch (IllegalArgumentException exc) {
			Assert.assertTrue( true );
		}
	}
}
