package com.veetech.unitconversion.test;

import com.veetech.unitconversion.domain.Conversion;
import com.veetech.unitconversion.domain.temperature.TemperatureType;
import com.veetech.unitconversion.domain.volume.VolumeType;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;


public class TempsConversionTest
		extends ConversionTestBase
{
	@Test
	public void nullValue()
	{
		Conversion converter = getConverter( TemperatureType.FAHRENHEIT );
		Assert.assertNotNull( converter );
		
		try {
			converter.convertTemperature( null, TemperatureType.CELSIUS );
			Assert.fail();
		}
		catch( NullPointerException exc ) {
			Assert.assertTrue( true );
		}

		try {
			converter.convertTemperature( "100", null );
			Assert.fail();
		}
		catch( NullPointerException exc ) {
			Assert.assertTrue( true );
		}
	}
	
	@Test
	public void toVolume()
	{
		Conversion converter = getConverter( TemperatureType.FAHRENHEIT );
		Assert.assertNotNull( converter );

		try {
			converter.convertVolume( "100", VolumeType.GALLONS );
			Assert.fail();
		}
		catch( UnsupportedOperationException exc ) {
			Assert.assertTrue( true );
		}
	}
	
	@Test
	public void fromCelcius()
	{
		Conversion converter = getConverter( TemperatureType.CELSIUS );
		Assert.assertNotNull( converter );
		Assert.assertEquals( TemperatureType.CELSIUS, converter.getConversionType() );
		
		BigDecimal c = converter.convertTemperature( "100", TemperatureType.CELSIUS );
		Assert.assertEquals( "100.0", c.toPlainString() );
			
		BigDecimal f = converter.convertTemperature( "100", TemperatureType.FAHRENHEIT );
		Assert.assertEquals( "212.0", f.toPlainString() );
			
		BigDecimal k = converter.convertTemperature( "100", TemperatureType.KELVIN );
		Assert.assertEquals( "373.2", k.toPlainString() );
			
		BigDecimal r = converter.convertTemperature( "100", TemperatureType.RANKINE );
		Assert.assertEquals( "671.7", r.toPlainString() );
	}

	@Test
	public void fromFahrenheit()
	{
		Conversion converter = getConverter( TemperatureType.FAHRENHEIT );
		Assert.assertNotNull( converter );
		Assert.assertEquals( TemperatureType.FAHRENHEIT, converter.getConversionType() );

		BigDecimal c = converter.convertTemperature( "100", TemperatureType.CELSIUS );
		Assert.assertEquals( "37.8", c.toPlainString() );
			
		BigDecimal f = converter.convertTemperature( "100", TemperatureType.FAHRENHEIT );
		Assert.assertEquals( "100.0", f.toPlainString() );
			
		BigDecimal k = converter.convertTemperature( "100", TemperatureType.KELVIN );
		Assert.assertEquals( "310.9", k.toPlainString() );
			
		BigDecimal r = converter.convertTemperature( "100", TemperatureType.RANKINE );
		Assert.assertEquals( "559.7", r.toPlainString() );
	}

	@Test
	public void fromKelvin()
	{
		Conversion converter = getConverter( TemperatureType.KELVIN );
		Assert.assertNotNull( converter );
		Assert.assertEquals( TemperatureType.KELVIN, converter.getConversionType() );

		BigDecimal c = converter.convertTemperature( "100", TemperatureType.CELSIUS );
		Assert.assertEquals( "-173.1", c.toPlainString() );
			
		BigDecimal f = converter.convertTemperature( "100", TemperatureType.FAHRENHEIT );
		Assert.assertEquals( "-279.7", f.toPlainString() );
			
		BigDecimal k = converter.convertTemperature( "100", TemperatureType.KELVIN );
		Assert.assertEquals( "100.0", k.toPlainString() );
			
		BigDecimal r = converter.convertTemperature( "100", TemperatureType.RANKINE );
		Assert.assertEquals( "180.0", r.toPlainString() );
	}

	@Test
	public void fromRankine()
	{
		Conversion converter = getConverter( TemperatureType.RANKINE );
		Assert.assertNotNull( converter );
		Assert.assertEquals( TemperatureType.RANKINE, converter.getConversionType() );

		BigDecimal c = converter.convertTemperature( "100", TemperatureType.CELSIUS );
		Assert.assertEquals( "-217.6", c.toPlainString() );
			
		BigDecimal f = converter.convertTemperature( "100", TemperatureType.FAHRENHEIT );
		Assert.assertEquals( "-359.7", f.toPlainString() );
			
		BigDecimal k = converter.convertTemperature( "100", TemperatureType.KELVIN );
		Assert.assertEquals( "55.6", k.toPlainString() );
			
		BigDecimal r = converter.convertTemperature( "100", TemperatureType.RANKINE );
		Assert.assertEquals( "100.0", r.toPlainString() );
	}
	
	@Test
	public void fromDog()
	{
		try {
			getConverter( TemperatureType.valueOf("DOG") );
			Assert.fail();
		}
		catch( IllegalArgumentException exc ) {
			Assert.assertTrue( true );
		}
	}
}
