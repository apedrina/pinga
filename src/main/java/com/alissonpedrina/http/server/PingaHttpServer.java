package com.alissonpedrina.http.server;

import com.alissonpedrina.core.Config;
import com.alissonpedrina.core.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;

public class PingaHttpServer implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(PingaHttpServer.class);

    public static void httpServer() {
        try {
            int port = Integer.valueOf(Config.getProperty(Config.SERVER_PORT));
            com.sun.net.httpserver.HttpServer server = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress(port), 0);

            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
            server.setExecutor(executor);

            server.createContext(Config.getProperty(Config.SERVER_REPORT_ENDPOINT), new ReportHttpHandler());

            server.start();

        } catch (Exception e) {
            logger.severe(e.getMessage());

        }
    }

    @Override
    public void run() {
        httpServer();

    }

}