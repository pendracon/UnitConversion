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

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author pendracon
 */
@WebService(serviceName = "ConversionService")
@Stateless()
public class ConversionService
{

	/**
	 * This is a sample web service operation
	 */
	@WebMethod(operationName = "hello")
	public String hello(@WebParam(name = "name") String txt) {
		return "Hello " + txt + " !";
	}
}
