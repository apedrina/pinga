package com.alissonpedrina.core;

import com.alissonpedrina.PingaContext;
import com.alissonpedrina.services.ScheduleService;
import com.alissonpedrina.services.process.ProcessICMP;
import com.alissonpedrina.util.TestEnvironmentUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

public class ScheduleTest {

    @BeforeClass
    public static void setUpBefore() throws IOException {
        TestEnvironmentUtil.config();

    }

    @Test
    public void should_run_at_least_one_ping_process() throws IOException, InterruptedException {
        String host = "jasmin.com";
        String[] args = {"ping", "-c", "2"};
        ProcessICMP processService = new ProcessICMP(args, host);
        ScheduleService scheduleService = new ScheduleService(10, processService, PingaContext.icmpTable, PingaContext.icmpResponse);

        scheduleService.start();

        Thread.sleep(10000);

        Assert.assertTrue(PingaContext.icmpResponse.size() > 0);

    }

}
