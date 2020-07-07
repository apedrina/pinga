package com.alissonpedrina.services.process;

import com.alissonpedrina.core.error.ICMPException;
import com.alissonpedrina.services.ReportService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class ProcessICMP extends ProcessTemplateMethod {
    private String[] args;
    private ReportService reportService;

    public ProcessICMP(String[] args, String host) {
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
        processResponse.setType("icmp");
        try {
            String result = ReadStreamUtil.exec(args);
            processResponse.setRaw(result);
            if (result.toLowerCase().contains(ICMPException.UNKNOWN_HOST.toLowerCase())) {
                reportService.see(processResponse);
                throw new ICMPException(ICMPException.UNKNOWN_HOST);

            }
            if (result.toLowerCase().contains(ICMPException.TIMEOUT.toLowerCase())) {
                reportService.see(processResponse);
                throw new ICMPException(ICMPException.TIMEOUT);

            }
            if (result.toLowerCase().contains(ICMPException.UNREACHABLE.toLowerCase())) {
                reportService.see(processResponse);
                throw new ICMPException(ICMPException.UNREACHABLE);

            }
            if (!result.toLowerCase().contains(ICMPException.LOST.toLowerCase())) {
                reportService.see(processResponse);
                throw new ICMPException(ICMPException.LOST);

            }

        } catch (InterruptedException e) {
            reportService.see(processResponse);

        }
        return processResponse;

    }

}
