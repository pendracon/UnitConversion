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
package com.veetech.unitconversion.service;

import com.veetech.unitconversion.domain.Constants;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * REST Web Service
 *
 * @author a.cook@veetechis.com
 */
@Path("/validate")
@Produces(MediaType.APPLICATION_JSON)
public class GenericResource
{
	@Context
	private UriInfo context;

	/**
	 * Creates a new instance of GenericResource
	 */
	public GenericResource()
	{
	}

	/**
	 * Retrieves representation of an instance of
	 * com.veetech.unitconversion.service.GenericResource.
	 * 
	 * @return an instance of java.lang.String
	 */
	@GET
	public String getConversion()
	{
		MultivaluedMap<String,String> query = context.getQueryParameters();
		String fromType = Constants.NO_VALUE;
		String toType = Constants.NO_VALUE;
		String units = Constants.NO_VALUE;
		String validate = Constants.NO_VALUE;

		for (String parm : query.keySet()) {
			if (parm.equalsIgnoreCase(Constants.FROM_TYPE_PARAM)) {
				fromType = query.getFirst( parm );
			}
			if (parm.equalsIgnoreCase(Constants.TO_TYPE_PARAM)) {
				toType = query.getFirst( parm );
			}
			if (parm.equalsIgnoreCase(Constants.UNIT_VALUE_PARAM)) {
				units = query.getFirst( parm );
			}
			if (parm.equalsIgnoreCase(Constants.VALIDATE_VALUE_PARAM)) {
				validate = query.getFirst( parm );
			}
		}
		
		if( log.isDebugEnabled() ) {
			log.debug( String.format("Executing web command with parameters: fromType = %s, toType = %s, units = %s, validate = %s.",
					new Object[] {fromType, toType, units, validate} ));
		}
		
		WebCommand command = new WebCommand( fromType, toType, units, validate );
		command.execute();
		
		String response = command.getResponseBody();
		command.cleanup();  // good citizen :)
		
		return response;
	}
	
	
	private final static Log log = LogFactory.getLog( GenericResource.class );
}
