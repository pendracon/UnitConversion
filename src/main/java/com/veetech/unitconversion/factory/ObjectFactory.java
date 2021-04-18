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
package com.veetech.unitconversion.factory;

import com.veetech.unitconversion.domain.Constants;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Object factory for Unit Conversion classes.
 *
 * @author a.cook@veetechis.com
 */
public class ObjectFactory
{
	/**
	 * Returns an instance of ObjectFactory for creating Unit Conversion
	 * objects.
	 * 
	 * @return The factory instance.
	 */
	public static ObjectFactory getInstance()
	{
		return instance;
	}

	/**
	 * Returns the given array of Objects as an array of matching class types.
	 * 
	 * @param parms The array of objects to type cast.
	 * @return					the array of class types.
	 */
	public static Class[] asClassArray( Object[] parms )
	{
		int numParms = (parms == null ? 0 : parms.length);
		Class[] types = new Class[numParms];
		
		for (int i = 0; i < numParms; i++) {
			types[i] = parms[i].getClass();
		}
		
		return types;
	}


	/**
	 * Creates an object with specified class name key. This method assumes that
	 * there is a null argument constructor for the requested class.
	 * 
	 * Throws an exception if the parameter value is null or if an instance of
	 * the specified class can not be created.
	 *
	 * @param key The class key of the object to create.
	 * @return The new instance.
	 * @throws java.lang.ClassNotFoundException A class for specified key isn't
	 * found.
	 * @throws java.lang.InstantiationException An instance of the class can't
	 * be created.
	 */
	public Object createObject( String key )
			throws ClassNotFoundException, InstantiationException, NullPointerException
	{
		return createObject( key, null );
	}

	/**
	 * Creates an object with a specific class name key and the specified
	 * parameters.
	 * 
	 * Throws an exception if the key value is null or if an instance of the
	 * specified class can not be created.
	 *
	 * @param key The class key of the object to create.
	 * @param parms Constructor parameters for the requested class.
	 * @return The new instance.
	 * @throws java.lang.ClassNotFoundException A class for specified key isn't
	 * found.ResourceBundle
	 * @throws java.lang.InstantiationException An instance of the class can't
	 * be created.
	 */
	public Object createObject( String key, Object[] parms )
			throws ClassNotFoundException, InstantiationException, NullPointerException
	{
		if (log.isDebugEnabled()) {
			log.debug( String.format("Creating object for key '%s' with parms: %s", key,
					(parms == null ? "[]" : Arrays.asList(parms))) );
		}

		String className = bundle.getProperty( key );
		if (className == null) {
			throw new ClassNotFoundException( String.format("Class for key '%s' not found.", key) );
		}
		else {
			return createObject( Class.forName(className), parms );
		}
	}

	
	/**
	 * Creates an object with the given class. The class is instantiated using
	 * the constructor whose parameter types match the types of the objects in
	 * the given parms array. If parms value is null then a default class
	 * instance is returned.
	 * 
	 * Throws an exception if the parameter cls is null or an instance can't be
	 * created because no matching constructor is found for the class.
	 *
	 * @param cls The class to create.
	 * @param parms	The constructor parameters for the class.
	 * @return The matching class instance.
	 * @throws java.lang.ClassNotFoundException A matching constructor for the
	 * class isn't found.
	 * @throws java.lang.InstantiationException An instance of the class can't
	 * be created.
	 */
	protected Object createObject( Class cls, Object[] parms )
			throws ClassNotFoundException, InstantiationException, NullPointerException
	{
		if (parms == null) parms = new Object[] {};
		String constructorKey = String.format( "%s%s", cls.getName(), Arrays.asList(parms) ).
				replaceFirst( "\\[", "\\(" ).replaceFirst( "\\]", "\\)" );
		
		if (log.isDebugEnabled()) {
			log.debug( String.format("Using object constructor %s", constructorKey) );
		}

		try {
			return findConstructor( constructorKey, cls, parms ).newInstance( parms );
		}
		catch( IllegalAccessException | IllegalArgumentException | InvocationTargetException exc ) {
			throw new InstantiationException( exc.getMessage() );
		}
	}
	
	/**
	 * Returns an object constructor for the specified constructor key and class
	 * and which has parameter types matching the object types in the given
	 * parms array.
	 * 
	 * Throws an exception if the class does not have a matching constructor.
	 * 
	 * @param key The constructor lookup and binding key.
	 * @param cls The class to find a matching constructor.
	 * @param parms	The parameter types to match.
	 * @return The matching constructor.
	 * @throws java.lang.ClassNotFoundException A matching constructor for the
	 * class isn't found.
	 */
	protected Constructor findConstructor( String key, Class cls, Object[] parms )
			throws ClassNotFoundException
	{
		synchronized (constructors) {
			Constructor ctor = constructors.get( key );

			if( ctor == null ) {
				try {
					ctor = cls.getConstructor( asClassArray(parms) );
					constructors.put( key, ctor );
				}
				catch( NoSuchMethodException exc ) {
					throw new ClassNotFoundException( exc.getMessage() );
				}
			}
		
			return ctor;
		}
	}
	
	
	/*
	 * Creates an instance of ObjectFactory.
	 */
	protected ObjectFactory()
	{
		constructors = new HashMap();
		bundle = new Properties();
		try {
			bundle.load( getClass().getClassLoader().getResourceAsStream(Constants.CLASSES) );
		}
		catch( IOException exc ) {
			if (log.isWarnEnabled() ) {
				log.warn( String.format("Could not load resource %s.", Constants.CLASSES) );
			}
		}
	}


	// instance variables
	private final HashMap<String,Constructor> constructors;
	private final Properties bundle;
	
	private final static ObjectFactory instance = new ObjectFactory();
	private final static Log log = LogFactory.getLog( ObjectFactory.class );
}
