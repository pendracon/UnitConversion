package com.veetech.unitconversion.test;

import com.veetech.unitconversion.CommandLine;
import com.veetech.unitconversion.CommandLine.ResultType;
import com.veetech.unitconversion.ConversionUtil;
import com.veetech.unitconversion.domain.Constants;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Assert;
import org.junit.Test;


/**
 * Unit tests for command-line processor.
 * 
 * @author a.cook@veetechis.com
 */
public class CLIConversionTest
{
	public CLIConversionTest()
	{
		if (cout == null) {
			cout = new ByteArrayOutputStream();
			System.setOut( new PrintStream(cout) );
		}
	}
	
	
	// --convertFrom=celsius --convertTo=fahrenheit --units=100
	// ResultType = CONVERTED
	@Test
	public void inputGoodNoValidation()
	{
		String[] args = {
			"--convertFrom=celsius",
			"--convertTo=fahrenheit",
			"--units=100"
		};
		
		CommandLine.main( args );
		verify( getOutput(), ResultType.CONVERTED );
	}
	
	// --convertFrom=celsius --convertTo=fahrenheit --units=100 --validate=212
	// ResultType = VALIDATED
	@Test
	public void inputGoodValidation()
	{
		String[] args = {
			"--convertFrom=celsius",
			"--convertTo=fahrenheit",
			"--units=100",
			"--validate=212"
		};
		
		CommandLine.main( args );
		verify( getOutput(), ResultType.VALIDATED );
	}
	
	// --convertFrom=celsius --convertTo=cups --units=100 --validate=200
	// ResultType = INCORRECT
	@Test
	public void inputWrongValidation()
	{
		String[] args = {
			"--convertFrom=celsius",
			"--convertTo=fahrenheit",
			"--units=100",
			"--validate=200"
		};
		
		CommandLine.main( args );
		verify( getOutput(), ResultType.INCORRECT );
	}

	// --convertFrom=celsius --convertTo=cups --units=100 --validate=dog
	// ResultType = INCORRECT
	@Test
	public void inputBadValidation()
	{
		String[] args = {
			"--convertFrom=celsius",
			"--convertTo=fahrenheit",
			"--units=100",
			"--validate=dog"
		};
		
		CommandLine.main( args );
		verify( getOutput(), ResultType.BAD_VALUE );
	}

	// --convertFrom=cups --convertTo=pints --units=100
	// ResultType = BAD_TYPE
	@Test
	public void inputBadType()
	{
		String[] args = {
			"--convertFrom=cups",
			"--convertTo=pints",
			"--units=100"
		};
		
		CommandLine.main( args );
		verify( getOutput(), ResultType.BAD_TYPE );
	}

	// --convertFrom=cups --convertTo=liters --units=lots
	// ResultType = BAD_UNITS
	@Test
	public void inputBadValue()
	{
		String[] args = {
			"--convertFrom=cups",
			"--convertTo=liters",
			"--units=lots"
		};
		
		CommandLine.main( args );
		verify( getOutput(), ResultType.BAD_UNITS );
	}
	
	// --convertFrom=celsius --convertTo=cups --units=100
	// ResultType = MISMATCH
	@Test
	public void inputTypeMismatch()
	{
		String[] args = {
			"--convertFrom=celsius",
			"--convertTo=cups",
			"--units=100"
		};
		
		CommandLine.main( args );
		verify( getOutput(), ResultType.MISMATCH );
	}

	// --convertTo=cups --units=100
	// ResultType = NO_FROM
	@Test
	public void inputMissingFromType()
	{
		String[] args = {
			"--convertTo=cups",
			"--units=100"
		};
		
		CommandLine.main( args );
		verify( getOutput(), ResultType.NO_FROM );
	}

	// --convertFrom=celsius --units=100
	// ResultType = NO_TO
	@Test
	public void inputMissingToType()
	{
		String[] args = {
			"--convertFrom=celsius",
			"--units=100"
		};
		
		CommandLine.main( args );
		verify( getOutput(), ResultType.NO_TO );
	}

	// --convertFrom=celsius --convertTo=fahrenheit
	// ResultType = NO_UNITS
	@Test
	public void inputMissingUnits()
	{
		String[] args = {
			"--convertFrom=celsius",
			"--convertTo=fahrenheit"
		};
		
		CommandLine.main( args );
		verify( getOutput(), ResultType.NO_UNITS );
	}

	
	void verify( String result, ResultType expectedType )
	{
		String expected = ConversionUtil.getMessageText( expectedType.toString() + "Test" );
		
		String[] lines = result.split( System.getProperty("line.separator") );
		if (lines.length == 2) {
			Assert.assertEquals( lines[0].trim(), String.format("%s:", Constants.VERSION) );
			Assert.assertEquals( lines[1].trim(), expected.trim() );
		}
		else {
			Assert.fail( String.format("Unexpected number of lines in result: %d.", lines.length) );
		}
	}
	
	String getOutput()
	{
		String sout = cout.toString();
		cout.reset();
		
		return sout;
	}

	
	private ByteArrayOutputStream cout;
}
