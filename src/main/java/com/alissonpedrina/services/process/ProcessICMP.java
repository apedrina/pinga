package com.alissonpedrina.services.process;

import com.alissonpedrina.core.error.ICMPException;
import com.alissonpedrina.services.ReportService;
import com.alissonpedrina.services.ValidationICMP;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProcessICMP extends ProcessTemplateMethod {
    private String[] args;
    private ReportService reportService;
    private ValidationICMP validationICMP;

    public ProcessICMP(String[] args, String host) {
        this.args = args;
        this.setHost(host);
        this.reportService = new ReportService();
        this.validationICMP = new ValidationICMP();

    }

    @Override
    public ProcessResponse call() throws IOException {
        ProcessResponse processResponse = new ProcessResponse();

        Stream<String> stream = Arrays.stream(args);
        List<String> listFromArgs = stream.collect(Collectors.toList());

        listFromArgs.add(getHost());
        String[] argsWithHost = new String[listFromArgs.size()];
        processResponse.setHost(getHost());
        processResponse.setTime(new Date());
        processResponse.setType("icmp");
        try {
            String result = ReadStreamUtil.exec(listFromArgs.toArray(argsWithHost));
            processResponse.setRaw(result);
            validateICMPResult(processResponse);

        } catch (InterruptedException e) {
            reportService.see(processResponse);

        }
        return processResponse;

    }

    private void validateICMPResult(ProcessResponse processResponse) {
        String result = processResponse.getRaw();
        if (validationICMP.unknownHost(result)) {
            reportService.see(processResponse);
            throw new ICMPException(String.format(ICMPException.UNKNOWN_HOST, processResponse.getHost()));

        }
        if (validationICMP.unreachable(result)) {
            reportService.see(processResponse);
            throw new ICMPException(String.format(ICMPException.UNREACHABLE_HOST, processResponse.getHost()));

        }
        if (validationICMP.timedOut(result)) {
            reportService.see(processResponse);
            throw new ICMPException(String.format(ICMPException.TIMEOUT, processResponse.getHost()));

        }

    }

}
