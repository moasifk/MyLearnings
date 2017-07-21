package com.property.loader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

public class MyProperties {
	
	private static final String EMPTY_STRING = "";

	/** The value containing LOGGER. */
	final static Logger LOGGER = Logger.getLogger(MyProperties.class);
	
	/** The value containing instance. */
	private static MyProperties instance;

	/** The value containing properties. */
	private Properties prop = new Properties();

	/** The value containing environment. */
	private String environment;

	/**
	 * Default Constructor.
	 */
	private MyProperties(String environment) {

		this.environment = environment;
	}

	/**
	 * Method to get instance of NormsProperties.
	 * 
	 * @return NormsProperties
	 */
	public static MyProperties getInstance() {

		if (null == instance) {
			String env = System.getenv("myprop_env"); 	// getting value from the system env.
			if (null == env || EMPTY_STRING.equalsIgnoreCase(env)) {
				// Reading environment.properties from the class file
				String filename = "environment.properties";
				Properties prop = new Properties();
				try {
					prop.load(MyProperties.class.getClassLoader()
							.getResourceAsStream(filename));
				} catch (IOException e) {
					LOGGER.error("Exception in loading env value.");
					e.printStackTrace();
				}
				env = prop.getProperty("env");
			}
			instance = new MyProperties(env);
			instance.load();
		}
		return instance;
	}

	/**
	 * Method to load properties from Norms.properties.
	 * 
	 */
	private void load() {

		try {
			String propPath = this.environment + "/MyProperties.properties";
			InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(propPath);
			prop.load(inputStream);
			inputStream.close();
		} catch (FileNotFoundException e) {
			LOGGER.error("Exception in NormsProperties.load : Failed to locate property file");
		} catch (IOException e) {
			LOGGER.error("Exception in NormsProperties.load : Failed to read property file");
		}

	}

	/**
	 * Method to get property value.
	 * 
	 * @param key
	 * @return Property Value
	 */
	public String getProperty(String key) {

		String value = prop.getProperty(key);
		return value;
	}
}