package com.alissonpedrina.services.process;

import java.io.IOException;
import java.util.Map;

public abstract class ProcessTemplateMethod {
    private String host;

    public ProcessResponse run(Map<String, Boolean> table, Map<String, ProcessResponse> response) throws IOException, InterruptedException {
        ProcessResponse processResponse = new ProcessResponse();
        if (!this.isRunning(table)) {
            this.enterMonitor(table);

            processResponse = call();
            this.persist(processResponse, response);

            this.exitMonitor(table);
        }
        return processResponse;

    }

    private void persist(ProcessResponse processResponse, Map<String, ProcessResponse> response) {
        if (response.containsKey(getHost())) {
            response.replace(getHost(), processResponse);

        } else {
            response.put(getHost(), processResponse);

        }
    }

    private boolean isRunning(Map<String, Boolean> table) {
        if (table.containsKey(getHost())) {
            return table.get(getHost());

        } else {
            return false;

        }

    }

    private void exitMonitor(Map<String, Boolean> table) {
        if (!table.containsKey(getHost())) {
            table.put(getHost(), Boolean.FALSE);

        } else {
            table.replace(getHost(), false);
        }
    }

    private void enterMonitor(Map<String, Boolean> table) {
        if (!table.containsKey(getHost())) {
            table.put(getHost(), Boolean.TRUE);

        } else {
            table.replace(getHost(), true);
        }
    }

    public String getHost() {
        if (host != null) {
            return host;

        } else {
            return "";

        }
    }

    public void setHost(String host) {
        this.host = host;
    }

    abstract public ProcessResponse call() throws IOException;

}
