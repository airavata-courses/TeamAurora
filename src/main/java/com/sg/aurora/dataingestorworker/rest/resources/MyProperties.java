package com.sg.aurora.dataingestorworker.rest.resources;

import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;

public class MyProperties {
	
	
	public String readPropertiesFile(String key) throws IOException
	{
		Properties prop=new Properties();
		InputStream hh=this.getClass().getClassLoader().getResourceAsStream("dataconfig.properties");
		prop.load(hh);
		return prop.getProperty(key);
	}

}

