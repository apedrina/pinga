package com.alissonpedrina.services;

import com.alissonpedrina.Monitoring;
import com.alissonpedrina.PingaContext;
import com.alissonpedrina.core.Config;
import com.alissonpedrina.core.LoggerFactory;
import com.alissonpedrina.services.process.ProcessResponse;

import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

public class ReportService {

    private Logger logger = LoggerFactory.getLogger(ReportService.class);

    private String template = "" +
            "{\"host\":\"%s\",\n " +
            "\"icmp_ping\":\"%s\",\n" +
            "\"tcp_ping\":\"%s\",\n" +
            "\"trace\":\"%s\"}";

    public String see(final ProcessResponse processResponse) {
        String result = bind2Template(processResponse.getHost());
        return result;

    }

    public String getAll() {
        String[] hosts = Config.getProperty(Config.HOSTS).split(Monitoring.HOSTS_DELIMITER);
        StringBuilder result = new StringBuilder("[");
        for (String host : hosts) {
            result.append(bind2Template(host) + ",");

        }
        result.append("{}]");
        logger.info(result.toString());

        return result.toString();

    }

    private String bind2Template(String host) {
        StringBuilder result = new StringBuilder();
        Optional<Map.Entry<String, ProcessResponse>> lastICMPResponse = PingaContext.icmpResponse.entrySet().stream().filter(response ->
                response.getKey().equals(host)
        ).findFirst();

        Optional<Map.Entry<String, ProcessResponse>> lastTraceResponse = PingaContext.traceResponse.entrySet().stream().filter(response ->
                response.getKey().equals(host)
        ).findFirst();

        Optional<Map.Entry<String, ProcessResponse>> lastTCPResponse = PingaContext.tcpResponse.entrySet().stream().filter(response ->
                response.getKey().equals(host)
        ).findFirst();

        ProcessResponse icmp = new ProcessResponse();
        ProcessResponse trace = new ProcessResponse();
        ProcessResponse tcp = new ProcessResponse();

        if (lastICMPResponse.isPresent()) {
            icmp = lastICMPResponse.get().getValue();

        }
        if (lastTraceResponse.isPresent()) {
            trace = lastTraceResponse.get().getValue();

        }
        if (lastTCPResponse.isPresent()) {
            tcp = lastTCPResponse.get().getValue();

        }
        String formatted = String.format(template, host, icmp.getRaw(), tcp.getRaw(), trace.getRaw());
        result.append(formatted);

        return result.toString();

    }

}
