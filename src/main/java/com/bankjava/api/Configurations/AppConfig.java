package com.bankjava.api.Configurations;

import java.util.Properties;

public class AppConfig {

    // Properties
    private final Properties properties;

    // Environment File
    private final String applicationEnvironment;

    public AppConfig() {

        // app properties
        properties = new Properties();

        // Environment File
        applicationEnvironment = "application.properties";
    }


    // Get App Properties
    public String getProperty(String keyName) {
        try {
            this.properties.load(
                getClass()
                    .getClassLoader()
                    .getResourceAsStream(
                        this.applicationEnvironment
                    )
            );

            return properties.getProperty(
                keyName, 
                "not found"
            );

        } catch (Exception e) {
            return "not found";
        }
    }
    
}
