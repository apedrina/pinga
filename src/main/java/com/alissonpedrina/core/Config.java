package com.alissonpedrina.core;

import com.alissonpedrina.core.error.ConfigException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

public final class Config {

    public static final String PROPERTIES_FILE_NAME = "pinga.properties";
    public static final String CONFIG_FILE = "config.file";

    public static final String SERVER_REPORT_ENDPOINT = "pinga.server.report.endpoint";
    public static final String SERVER_PORT = "pinga.server.port";
    public static final String LOG_LEVEL = "pinga.log.level";
    public static final String HOSTS = "pinga.hosts";
    public static final String TCP_TYPE = "pinga.tcp.type";
    public static final String LOG_DIR = "pinga.log.dir";
    public static final String TCP_DELAY = "pinga.tcp.delay";
    public static final String ICMP_DELAY = "pinga.icmp.delay";
    public static final String ICMP_COMMAND = "pinga.icmp.command";
    public static final String TRACE_COMMAND = "ping.trace.command";
    public static final String TRACE_DELAY = "pinga.trace.delay";

    public static final String CONFIG_ERROR_FORMATTER = "The config key: %s, is missing.";

    private static Map<String, String> properties = new HashMap<>();

    public static synchronized void setProperties() throws IOException {
        Properties prop = loadProperties();
        for (Map.Entry<Object, Object> entry : prop.entrySet()) {
            properties.put((String) entry.getKey(), (String) entry.getValue());

        }

    }

    public static synchronized String getProperty(String key) {
        return Optional.of(properties.get(key)).orElseThrow(() ->
                new ConfigException(String.format(CONFIG_ERROR_FORMATTER, key)));

    }

    private static Properties loadProperties() throws IOException {
        String pathConfigFile = null;
        if (System.getProperty(CONFIG_FILE) != null) {
            pathConfigFile = System.getProperty(CONFIG_FILE);

        } else {
            pathConfigFile = System.getProperty("user.dir")
                    + File.separator
                    + PROPERTIES_FILE_NAME;

        }
        Properties prop = new Properties();
        prop.load(new FileInputStream(pathConfigFile));
        return prop;

    }

}
