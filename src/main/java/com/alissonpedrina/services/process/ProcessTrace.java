package com.alissonpedrina.services.process;

import com.alissonpedrina.core.error.RouteException;
import com.alissonpedrina.core.error.TCPException;
import com.alissonpedrina.services.ReportService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class ProcessTrace extends ProcessTemplateMethod {
    private String[] args;
    private ReportService reportService;

    public ProcessTrace(String[] args, String host) {
        this.args = args;
        this.setHost(host);
        this.reportService = new ReportService();

    }

    @Override
    public ProcessResponse call() throws IOException {
        ProcessResponse processResponse = new ProcessResponse();

        int newSize = args.length + 1;
        args = Arrays.copyOf(args, newSize);
        args[newSize - 1] = getHost();

        processResponse.setHost(getHost());
        processResponse.setTime(new Date());
        processResponse.setType("trace");
        try {
            String result = ReadStreamUtil.exec(args);
            if (result.contains("bad value")){
                this.reportService.see(processResponse);
                throw new RouteException(result);

            }
            processResponse.setRaw(result);

        } catch (Exception e) {
            reportService.see(processResponse);
            throw new RouteException(e.getMessage());

        }
        return processResponse;

    }

}
