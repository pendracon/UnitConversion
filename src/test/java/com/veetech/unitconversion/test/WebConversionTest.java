package com.veetech.unitconversion.test;

import com.veetech.unitconversion.domain.Constants;
import com.veetech.unitconversion.service.WebCommand;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;


/**
 * Unit tests for web service command processor.
 * 
 * @author a.cook@veetechis.com
 */
public class WebConversionTest
{
	// "Professor Frink" doesn't allow a submission without
	// at least the three required parameters: from, to, and
	// amount. So we're just concerned about good and bad
	// validation, no validation, and a bad input response
	// like type mismatch...

	public WebConversionTest()
	{
		parser = new JSONParser();
	}
	
	
	// Request:
	// convertFrom=celsius, convertTo=fahrenheit, unitValue=100,
	// validateValue=212
	//
	// Response
	// inputValue=100.0, outputValue=212.0, validationValue=212.0,
	// validation=Correct
	@Test
	public void sendGoodValidation()
	{
		WebCommand web = new WebCommand( "celsius", "fahrenheit", "100", "212" );
		web.execute();
		
		String response = web.getResponseBody();
		web.cleanup();
		
		verify( response, "100.0", "212.0", "212.0", "Correct" );
	}
	
	// Request:
	// convertFrom=celsius, convertTo=fahrenheit, unitValue=100,
	// validateValue=200
	//
	// Response:
	// inputValue=100.0, outputValue=212.0, validationValue=200.0,
	// validation=Incorrect
	@Test
	public void sendBadValidation()
	{
		WebCommand web = new WebCommand( "celsius", "fahrenheit", "100", "200" );
		web.execute();
		
		String response = web.getResponseBody();
		web.cleanup();
		
		verify( response, "100.0", "212.0", "200.0", "Incorrect" );
	}

	// Request:
	// convertFrom=celsius, convertTo=fahrenheit, unitValue=100
	//
	// Response:
	// inputValue=100.0, outputValue=212.0, validationValue=NONE,
	// validation=NONE
	@Test
	public void sendNoValidation()
	{
		WebCommand web = new WebCommand( "celsius", "fahrenheit", "100", Constants.NO_VALUE );
		web.execute();
		
		String response = web.getResponseBody();
		web.cleanup();
		
		verify( response, "100.0", "212.0", Constants.NO_VALUE, Constants.NO_VALUE );
	}

	// Request:
	// convertFrom=celsius, convertTo=fahrenheit, unitValue=some,
	//
	// Response:
	// inputValue=some, outputValue=NONE, validationValue=NONE,
	// validation=Incorrect
	@Test
	public void sendBadValue()
	{
		WebCommand web = new WebCommand( "celsius", "fahrenheit", "some", Constants.NO_VALUE );
		web.execute();
		
		String response = web.getResponseBody();
		web.cleanup();
		
		verify( response, "some", Constants.NO_VALUE, Constants.NO_VALUE, "Invalid" );
	}
	
	// Request:
	// convertFrom=celsius, convertTo=cups, unitValue=100,
	//
	// Response:
	// inputValue=100.0, outputValue=NONE, validationValue=NONE,
	// validation=Incorrect
	@Test
	public void sendTypeMismatch()
	{
		WebCommand web = new WebCommand( "celsius", "cups", "100", Constants.NO_VALUE );
		web.execute();
		
		String response = web.getResponseBody();
		web.cleanup();
		
		verify( response, "100.0", Constants.NO_VALUE, Constants.NO_VALUE, "Invalid" );
	}
	
	// Request:
	// null, null, null, null
	//
	// Response:
	// inputValue=NONE, outputValue=NONE, validationValue=NONE,
	// validation=Incorrect
	@Test
	public void testNull()
	{
		WebCommand web = new WebCommand( null, null, null, null );
		web.execute();
		
		String response = web.getResponseBody();
		web.cleanup();
		
		verify( response, Constants.NO_VALUE, Constants.NO_VALUE, Constants.NO_VALUE, "Invalid" );
	}
	
	
	/*
	 * Verifies the given JSON response message has the specified values.
	 * 
	 * @param response The response message.
	 * @param inputValue The input (from type) value.
	 * @param outputValue The output (to type) value.
	 * @param validationValue The validation value.
	 * @param validation The validation result.
	 */
	void verify( String response, String inputValue, String outputValue, String validationValue, String validation )
	{
		try {
			JSONObject json = (JSONObject) parser.parse( response );
			Assert.assertEquals( inputValue, json.get("inputValue") );
			Assert.assertEquals( outputValue, json.get("outputValue") );
			Assert.assertEquals( validationValue, json.get("validationValue") );
			Assert.assertEquals( validation, json.get("validation") );
			Assert.assertNotEquals( null, json.get("appResult") );
			
			Assert.assertEquals( Constants.VERSION, json.get("appVersion") );
		}
		catch (ParseException exc) {
			Assert.fail( String.format("JSON parsing error for response message\n%s", response) );
		}
	}
	
	
	JSONParser parser;
}
