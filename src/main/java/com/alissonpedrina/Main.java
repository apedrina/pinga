package com.alissonpedrina;

import com.alissonpedrina.core.Config;
import com.alissonpedrina.http.server.PingaHttpServer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Config.setProperties();
        new PingaHttpServer().run();

        Monitoring.startAll();

    }

}
