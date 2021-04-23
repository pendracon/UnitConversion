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
package com.veetech.unitconversion.domain;


/**
 * Global constants used by Unit Conversion classes.
 * 
 * @author a.cook@veetechis.com
 */
public interface Constants {
	// Shared
	public final static String CLASSES = "classes.properties";
	public final static String MESSAGES = "messages.properties";
	public final static String VERSION = "${project.name} ${project.version}";
	public final static String DEFAULT_MESSAGE_TEXT = "Message text not found.";
	public final static String NO_VALUE = "NONE";
	
	// CLI
	public final static String USAGE_FILE = "usage.txt";
	public final static String FROM_TYPE_ARG = "--convertFrom";
	public final static String TO_TYPE_ARG = "--convertTo";
	public final static String UNIT_VALUE_ARG = "--units";
	public final static String VALIDATE_VALUE_ARG = "--validate";

	// Web
	// - query parameters
	public final static String FROM_TYPE_PARAM = "convertFrom";
	public final static String TO_TYPE_PARAM = "convertTo";
	public final static String UNIT_VALUE_PARAM = "unitValue";
	public final static String VALIDATE_VALUE_PARAM = "validateValue";
	// - response template tags
	public final static String APP_RESULT_TAG = "@appResult@";
	public final static String INPUT_TYPE_TAG = "@inputType@";
	public final static String INPUT_VALUE_TAG = "@inputValue@";
	public final static String OUTPUT_TYPE_TAG = "@outputType@";
	public final static String OUTPUT_VALUE_TAG = "@outputValue@";
	public final static String VALIDATION_VALUE_TAG = "@validationValue@";
	public final static String VALIDATION_RESULT_TAG = "@validation@";
}
