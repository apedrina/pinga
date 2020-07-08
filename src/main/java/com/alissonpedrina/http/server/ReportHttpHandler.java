package com.alissonpedrina.http.server;

import com.alissonpedrina.core.LoggerFactory;
import com.alissonpedrina.services.ReportService;
import com.alissonpedrina.services.process.ProcessResponse;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReportHttpHandler implements HttpHandler {

    private final String ASP_AT_BEGIN = "^\\'";
    private final String DOUBLE_ASP_AT_BEGIN = "^\\\"";
    private final ReportService reportService;
    private Logger logger = LoggerFactory.getLogger(ReportHttpHandler.class);

    public ReportHttpHandler() {
        reportService = new ReportService();

    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            if ("POST".equals(httpExchange.getRequestMethod())) {
                handlePostRequest(httpExchange);

            }

        } catch (Exception e) {
            OutputStream outputStream = httpExchange.getResponseBody();
            String error = "error";
            httpExchange.sendResponseHeaders(500, error.length());
            outputStream.write(error.getBytes());
            outputStream.flush();

        }

    }

    private String handlePostRequest(HttpExchange httpExchange) throws IOException, ScriptException {
        String response = "";
        try {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            StringBuilder query = new StringBuilder();

            String line = null;
            while ((line = br.readLine()) != null) {
                query.append(line);

            }

            String host = null;
            if (query.toString().contains("\"host\"") || query.toString().contains("'host'")) {
                String[] param = query.toString().split(":");
                host = param[1];
                Pattern pattern = Pattern.compile(ASP_AT_BEGIN);
                Matcher matcher = pattern.matcher(host);
                if (matcher.find()) {
                    host = host.substring(host.indexOf("'") + 1, host.lastIndexOf("'"));

                } else {
                    pattern = Pattern.compile(DOUBLE_ASP_AT_BEGIN);
                    matcher = pattern.matcher(host);
                    if (matcher.find()) {
                        host = host.substring(host.indexOf("\"") + 1, host.lastIndexOf("\""));

                    }
                }

            }

            if (host != null) {
                ProcessResponse processResponse = new ProcessResponse();
                processResponse.setHost(host);
                response = reportService.see(processResponse);

            } else {
                response = reportService.getAll();
            }

            httpExchange.getResponseHeaders().set("Content-Type", "application/json");
            httpExchange.getRequestBody().close();
            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();

        } catch (Exception e) {
            logger.severe(e.getMessage());
            httpExchange.getRequestBody().close();
            httpExchange.sendResponseHeaders(500, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes("UTF8"));
            os.close();

        }
        return response;

    }

    private String getParam(String title, Map<String, String> params) {
        for (Map.Entry<String, String> param : params.entrySet()) {
            if (title.equals(param.getKey())) {
                return param.getValue();

            }

        }
        return null;

    }

}