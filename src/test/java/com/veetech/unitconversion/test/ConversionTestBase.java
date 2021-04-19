package com.veetech.unitconversion.test;

import com.veetech.unitconversion.ConversionUtil;
import com.veetech.unitconversion.domain.Conversion;
import com.veetech.unitconversion.domain.ConversionType;


public abstract class ConversionTestBase
{
	public Conversion getConverter( ConversionType type )
	{
		return ConversionUtil.getConverter( type );
	}
}
