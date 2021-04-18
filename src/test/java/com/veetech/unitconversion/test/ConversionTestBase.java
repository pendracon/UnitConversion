package com.veetech.unitconversion.test;

import com.veetech.unitconversion.domain.Conversion;
import com.veetech.unitconversion.domain.ConversionType;
import com.veetech.unitconversion.factory.ObjectFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public abstract class ConversionTestBase
{
	public Conversion getConverter( ConversionType type )
	{
		Conversion converter = null;
		
		try {
			return (Conversion) ObjectFactory.getInstance().createObject( type.toString() );
		}
		catch( ClassNotFoundException | InstantiationException exc ) {
			log.error( String.format("Failed to create converter for units type %s.", type), exc );
		}
		
		return converter;
	}
	
	
	private static final Log log = LogFactory.getLog( ConversionTestBase.class );
}
