package com.alissonpedrina;

import com.alissonpedrina.core.Config;
import com.alissonpedrina.core.LoggerFactory;
import com.alissonpedrina.services.ScheduleService;
import com.alissonpedrina.services.process.ProcessICMP;
import com.alissonpedrina.services.process.ProcessTCP;
import com.alissonpedrina.services.process.ProcessTemplateMethod;

import java.util.logging.Logger;

public class Monitoring {

    public static final String HOSTS_DELIMITER = ";";
    public static final String COMMAND_DELIMITER = " ";

    private static final Logger logger = LoggerFactory.getLogger(Monitoring.class);

    public static void startAll() {
        try {
            String[] hosts = Config.getProperty(Config.HOSTS).split(HOSTS_DELIMITER);
            for (String host : hosts) {
                startPingICMP(host);
                startPingTCP(host);
                startTraceRoute(host);

            }
        } catch (Exception e) {
            logger.severe(e.getMessage());

        }

    }

    private static void startPingICMP(String host) {
        ProcessTemplateMethod pingICMP = new ProcessICMP(Config.getProperty(Config.ICMP_COMMAND).split(COMMAND_DELIMITER), host);
        int delay = Integer.valueOf(Config.getProperty(Config.ICMP_DELAY));
        new ScheduleService(delay, pingICMP, PingaContext.icmpTable, PingaContext.icmpResponse).start();

    }

    private static void startPingTCP(String host) {
        ProcessTemplateMethod pingTCP = new ProcessTCP(host);
        int delay = Integer.valueOf(Config.getProperty(Config.TCP_DELAY));
        new ScheduleService(delay, pingTCP, PingaContext.tcpTable, PingaContext.tcpResponse).start();

    }

    private static void startTraceRoute(String host) {
        ProcessTemplateMethod traceRoute = new ProcessICMP(Config.getProperty(Config.TRACE_COMMAND).split(COMMAND_DELIMITER), host);
        int delay = Integer.valueOf(Config.getProperty(Config.TRACE_DELAY));
        new ScheduleService(delay, traceRoute, PingaContext.traceTable, PingaContext.traceResponse).start();

    }

}
