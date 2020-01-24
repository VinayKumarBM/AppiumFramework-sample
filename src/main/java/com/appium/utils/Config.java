package com.appium.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

	private final static String CONFIG_FILE_NAME = "config.properties";
	private static Config INSTANCE = null;
	private final Properties properties = new Properties();
	
	private Config() {
		loadProperties(CONFIG_FILE_NAME);
	}

	private static Config getInstace() {
		if(Config.INSTANCE==null) {
			Config.INSTANCE = new Config();
		}
		return Config.INSTANCE;
	}
	
	public static int getIntegerProperty(String key) {
		return Integer.parseInt(Config.getInstace().properties.get(key).toString());
	}

	public static String getProperties(String key) {
		return Config.getInstace().properties.get(key).toString();
	}
	
	private void loadProperties(final String path) {
		FileInputStream reader = null;
		try {
			reader = new FileInputStream(new File(path));
			properties.load(reader);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
