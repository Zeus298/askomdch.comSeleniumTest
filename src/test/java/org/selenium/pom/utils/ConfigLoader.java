package org.selenium.pom.utils;

import org.selenium.pom.constants.EnviromentRun;
import org.selenium.pom.constants.MultiBrowsers;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader () {

        //Parameter come with run project command line : mvn clean test -Denv=STAGE
        String env = System.getProperty("env", String.valueOf(EnviromentRun.STAGE));
                switch(EnviromentRun.valueOf(env)) {
                    case STAGE :
                        properties = PropertiesUtils.propertiesLoader("src/test/resources/config.properties");
                        break;
                    case PRODUCTION :
                        properties = PropertiesUtils.propertiesLoader("src/test/resources/config-prod.properties");
                        break;
                    default:
                        throw new IllegalStateException("Invaild enviroment type" + env);
                }
    }

    public static ConfigLoader getInstance() {
        if (configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getBaseUrl() {
        String url = properties.getProperty("baseURL");
        if (url!= null) return url;
        else throw new RuntimeException ("Property baseUrl not specified in the config.properties file");
    }
}
