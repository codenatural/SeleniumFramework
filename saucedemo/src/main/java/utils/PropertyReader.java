package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import base.LoggerSetup;

public class PropertyReader extends LoggerSetup{
	private static Logger log = LogManager.getLogger(PropertyReader.class);
	public static String readProperty(String fileName, String property) throws Exception {
		log.info("Reading Config Properties  - "+"file : "+fileName+ " : Property : "+property);
		FileInputStream fis = new FileInputStream(fileName);
		Properties prop = new Properties();
		prop.load(fis);
		return prop.getProperty(property);
	}
	
	
	public static void writeProperty(String outputFile, String property, String propertyValue) throws Exception{
		FileOutputStream fis = new FileOutputStream(outputFile);
		Properties prop = new Properties();
		prop.setProperty(property, propertyValue);
		prop.store(fis, null);
	}
	
}
