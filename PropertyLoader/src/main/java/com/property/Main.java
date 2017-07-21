package com.property;

import org.apache.log4j.Logger;

import com.property.loader.MyProperties;

public class Main {
	
	final static Logger LOGGER = Logger.getLogger(Main.class);
	
	public static MyProperties myProps;
	public static void main(String args[]) {
		myProps = MyProperties.getInstance();
		LOGGER.info(myProps.getProperty("myname"));
		
	}
}
