package com.qamanager.angular.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {
	
	public static Properties getProperties() throws FileNotFoundException, IOException{
		String filePath = System.getProperty("user.dir")+"/bin/application.properties";
		Properties prop = new Properties();
		prop.load(new FileInputStream(new File(filePath)));
		return prop;
		
	}

}
