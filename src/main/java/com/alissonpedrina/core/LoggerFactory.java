package com.alissonpedrina.core;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;

public class LoggerFactory {

    public static Logger getLogger(Class clazz) {
        String levelStr = Config.getProperty(Config.LOG_LEVEL);
        Level level = null;
        switch (levelStr) {
            case "WARNING":
                level = Level.WARNING;
            case "INFO":
                level = Level.INFO;
            case "SEVERE":
                level = Level.SEVERE;
            case "ALL":
                level = Level.ALL;


        }
        return getLogger(clazz, level);

    }

    public static Logger getLogger(Class clazz, Level level) {
        String logPath = Config.getProperty(Config.LOG_DIR) != null ? Config.getProperty(Config.LOG_DIR) : System.getProperty("user.dir");
        Logger log = Logger.getLogger(clazz.getName());

        String logFile = logPath + File.separator + "pinga.%u.%g.log";
        try {
            Handler handler = new FileHandler(logFile, 1024 * 1024, 10, true);
            handler.setFormatter(new SimpleFormatter());
            log.addHandler(handler);

        } catch (IOException e) {
            e.printStackTrace();

        }
        log.setLevel(level);

        return log;

    }

}
