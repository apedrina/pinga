package com.alissonpedrina.util;

import com.alissonpedrina.PingaContext;
import com.alissonpedrina.core.Config;

import java.io.File;
import java.io.IOException;

public class TestEnvironmentUtil {

    public static void config() throws IOException {
        String path = "";
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.indexOf("win") >= 0) {
            path = "pinga-win-test.properties";

        } else if (OS.indexOf("mac") >= 0) {
            path = "pinga-mac-test.properties";

        } else {
            path = "pinga-mac-test.properties";

        }
        String pathProperties = System.getProperty("user.dir")
                + File.separator
                + "src"
                + File.separator
                + "main"
                + File.separator
                + "resources"
                + File.separator + path;
        System.setProperty("config.file", pathProperties);

        Config.setProperties();

    }

    public static void cleanContext() {
        PingaContext.icmpResponse.clear();
        PingaContext.traceResponse.clear();
        PingaContext.tcpResponse.clear();

        PingaContext.icmpTable.clear();
        PingaContext.tcpTable.clear();
        PingaContext.traceTable.clear();

    }

}
