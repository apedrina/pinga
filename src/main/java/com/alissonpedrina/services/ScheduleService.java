package com.alissonpedrina.services;

import com.alissonpedrina.core.LoggerFactory;
import com.alissonpedrina.services.process.ProcessResponse;
import com.alissonpedrina.services.process.ProcessTemplateMethod;

import java.io.IOException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public class ScheduleService {

    private final Timer timer;
    private final long milliseconds;
    private final ProcessTemplateMethod process;
    private Logger logger = LoggerFactory.getLogger(ScheduleService.class);
    private Map<String, Boolean> table;
    private Map<String, ProcessResponse> response;

    public ScheduleService(long milliseconds, ProcessTemplateMethod process, Map<String, Boolean> table, Map<String, ProcessResponse> response) {
        this.milliseconds = milliseconds;
        this.process = process;
        this.table = table;
        this.response = response;
        this.timer = new Timer(false);

    }

    public void start() {
        timer.schedule(new TimerTask() {
            public void run() {
                try {
                    process.run(table, response);

                } catch (InterruptedException | IOException e) {
                    logger.severe(e.getMessage());

                }

            }

        }, 0l, milliseconds);
    }

}
