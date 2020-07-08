package com.alissonpedrina.services.process;

import com.alissonpedrina.core.Config;
import com.alissonpedrina.core.error.TCPException;
import com.alissonpedrina.services.ReportService;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class ProcessTCP extends ProcessTemplateMethod {
    private ReportService reportService;

    public ProcessTCP(String host) {
        this.setHost(host);
        this.reportService = new ReportService();

    }

    @Override
    public ProcessResponse call() throws IOException {
        ProcessResponse processResponse = new ProcessResponse();

        processResponse.setHost(getHost());
        processResponse.setTime(new Date());
        processResponse.setType("tcp");

        long startTime = System.nanoTime();

        long elapsedTime = System.nanoTime() - startTime;
        try {
            int responseCode = getResponseCode();
            processResponse.setRaw(
                    String.format("host: %s, code: %s, timeout: %s", getHost(), responseCode, elapsedTime));

        } catch (Exception e) {
            reportService.see(processResponse);
            throw new TCPException("respondeCode: " + e.getMessage());

        }
        return processResponse;

    }

    private int getResponseCode() throws IOException {
        if (Config.getProperty(Config.TCP_TYPE).equals("https")) {
            HttpsURLConnection connection = (HttpsURLConnection) new URL("https://" + getHost()).openConnection();
            return connection.getResponseCode();

        } else if (Config.getProperty(Config.TCP_TYPE).equals("http")) {
            HttpURLConnection connection = (HttpURLConnection) new URL("http://" + getHost()).openConnection();
            return connection.getResponseCode();

        }
        return 404;

    }

}
