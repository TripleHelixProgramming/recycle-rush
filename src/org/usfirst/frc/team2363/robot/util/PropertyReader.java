package org.usfirst.frc.team2363.robot.util;

import java.io.BufferedReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class PropertyReader {
	
	private static Map<String, Integer> propertyMap = new HashMap<>();
	
	public static void loadProperties(String fileName) {
		Path path = FileSystems.getDefault().getPath("properties", fileName);
		System.out.println("Loading property file from: " + path.toString());
		try {
			BufferedReader reader = Files.newBufferedReader(path);
			String line;
			while ((line = reader.readLine()) != null) {
				String property;
				int value;
				String[] splitProperty = line.split(" ");
				property = splitProperty[0];
				value = Integer.parseInt(splitProperty[1]);
				propertyMap.put(property, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int getProperty(String name, int defaultValue) {
		Integer value = propertyMap.get(name);
		if (value != null) {
			return value;
		}
		return defaultValue; 
	}

}
