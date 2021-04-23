package com.veetech.unitconversion.test;

import com.veetech.unitconversion.domain.Conversion;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;


public class TempsConversionTest
		extends ConversionTestBase
{
	@Test
	public void nullValue()
	{
		Conversion converter = getConverter( getConversionType("fahrenheit") );
		Assert.assertNotNull( converter );
		
		try {
			converter.convertUnits( null, getConversionType("celsius") );
			Assert.fail( "Unexpected successful conversion from type 'null'." );
		}
		catch (NullPointerException exc) {
			Assert.assertTrue( true );
		}

		try {
			converter.convertUnits( "100", null );
			Assert.fail( "Unexpected successful conversion to type 'null'." );
		}
		catch (NullPointerException exc) {
			Assert.assertTrue( true );
		}
	}
	
	@Test
	public void toVolume()
	{
		Conversion converter = getConverter( getConversionType("fahrenheit") );
		Assert.assertNotNull( converter );

		try {
			converter.convertUnits( "100",  getConversionType("gallons")  );
			Assert.fail( "Unexpected successful conversion from fahrenheit to gallons." );
		}
		catch (UnsupportedOperationException exc) {
			Assert.assertTrue( true );
		}
	}
	
	@Test
	public void fromCelcius()
	{
		Conversion converter = getConverter( getConversionType("celsius") );
		Assert.assertNotNull( converter );
		Assert.assertEquals( getConversionType("celsius"), converter.getConversionType() );
		
		BigDecimal c = converter.convertUnits( "100", getConversionType("celsius") );
		Assert.assertEquals( "100.0", c.toPlainString() );
			
		BigDecimal f = converter.convertUnits( "100", getConversionType("fahrenheit") );
		Assert.assertEquals( "212.0", f.toPlainString() );
			
		BigDecimal k = converter.convertUnits( "100", getConversionType("kelvin") );
		Assert.assertEquals( "373.2", k.toPlainString() );
			
		BigDecimal r = converter.convertUnits( "100", getConversionType("rankine") );
		Assert.assertEquals( "671.7", r.toPlainString() );
	}

	@Test
	public void fromFahrenheit()
	{
		Conversion converter = getConverter( getConversionType("fahrenheit") );
		Assert.assertNotNull( converter );
		Assert.assertEquals( getConversionType("fahrenheit"), converter.getConversionType() );

		BigDecimal c = converter.convertUnits( "100", getConversionType("celsius") );
		Assert.assertEquals( "37.8", c.toPlainString() );
			
		BigDecimal f = converter.convertUnits( "100", getConversionType("fahrenheit") );
		Assert.assertEquals( "100.0", f.toPlainString() );
			
		BigDecimal k = converter.convertUnits( "100", getConversionType("kelvin") );
		Assert.assertEquals( "310.9", k.toPlainString() );
			
		BigDecimal r = converter.convertUnits( "100", getConversionType("rankine") );
		Assert.assertEquals( "559.7", r.toPlainString() );
	}

	@Test
	public void fromKelvin()
	{
		Conversion converter = getConverter( getConversionType("kelvin") );
		Assert.assertNotNull( converter );
		Assert.assertEquals( getConversionType("kelvin"), converter.getConversionType() );

		BigDecimal c = converter.convertUnits( "100", getConversionType("celsius") );
		Assert.assertEquals( "-173.1", c.toPlainString() );
			
		BigDecimal f = converter.convertUnits( "100", getConversionType("fahrenheit") );
		Assert.assertEquals( "-279.7", f.toPlainString() );
			
		BigDecimal k = converter.convertUnits( "100", getConversionType("kelvin") );
		Assert.assertEquals( "100.0", k.toPlainString() );
			
		BigDecimal r = converter.convertUnits( "100", getConversionType("rankine") );
		Assert.assertEquals( "180.0", r.toPlainString() );
	}

	@Test
	public void fromRankine()
	{
		Conversion converter = getConverter( getConversionType("rankine") );
		Assert.assertNotNull( converter );
		Assert.assertEquals( getConversionType("rankine"), converter.getConversionType() );

		BigDecimal c = converter.convertUnits( "100", getConversionType("celsius") );
		Assert.assertEquals( "-217.6", c.toPlainString() );
			
		BigDecimal f = converter.convertUnits( "100", getConversionType("fahrenheit") );
		Assert.assertEquals( "-359.7", f.toPlainString() );
			
		BigDecimal k = converter.convertUnits( "100", getConversionType("kelvin") );
		Assert.assertEquals( "55.6", k.toPlainString() );
			
		BigDecimal r = converter.convertUnits( "100", getConversionType("rankine") );
		Assert.assertEquals( "100.0", r.toPlainString() );
	}
	
	@Test
	public void typeDog()
	{
		try {
			getConverter( getConversionType("DOG") );
			Assert.fail( "Got unexpected conversion type 'DOG'." );
		}
		catch (IllegalArgumentException exc) {
			Assert.assertTrue( true );
		}
	}
}
