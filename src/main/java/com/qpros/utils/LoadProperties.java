package com.qpros.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {
    public static Properties envProperties = loadProperties(System.getProperty("user.dir") + "/src/test/java/properties/env.properties");
    public static Properties userCredentialsProperties = loadProperties(System.getProperty("user.dir") + "/src/test/java/properties/userCredentials.properties");
    private static Properties loadProperties(String filePath){
        Properties properties = new Properties();
        try {
            FileInputStream stream = new FileInputStream(filePath);
            properties.load(stream);
        } catch (FileNotFoundException e) {
            System.out.println("Error Occurred" +e.getMessage());
        } catch (IOException e) {
            System.out.println("Error Occurred" +e.getMessage());
        }
        return properties;
    }
}
