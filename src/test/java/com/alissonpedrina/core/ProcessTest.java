package com.alissonpedrina.core;

import com.alissonpedrina.Monitoring;
import com.alissonpedrina.PingaContext;
import com.alissonpedrina.core.error.ICMPException;
import com.alissonpedrina.core.error.RouteException;
import com.alissonpedrina.core.error.TCPException;
import com.alissonpedrina.services.process.*;
import com.alissonpedrina.util.TestEnvironmentUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.IOException;

public class ProcessTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @BeforeClass
    public static void setUpBefore() throws IOException {
        TestEnvironmentUtil.config();

    }

    @Before
    public void setUp() {
        TestEnvironmentUtil.cleanContext();

    }


    @After
    public void down() {
        TestEnvironmentUtil.cleanContext();

    }

    @Test
    public void should_run_a_ping_process() throws IOException, InterruptedException {
        String[] hosts = Config.getProperty(Config.HOSTS).split(Monitoring.HOSTS_DELIMITER);
        String[] args = Config.getProperty(Config.ICMP_COMMAND).split(Monitoring.COMMAND_DELIMITER);

        ProcessTemplateMethod templateMethod = new ProcessICMP(args, hosts[0]);
        ProcessResponse response = templateMethod.run(PingaContext.icmpTable, PingaContext.icmpResponse);

        Assert.assertTrue(response.getRaw() != null);

    }

    @Test
    public void should_get_unknown_host_exception_on_ping_process() throws IOException, InterruptedException {
        expectedEx.expect(ICMPException.class);
        expectedEx.expectMessage(String.format(ICMPException.UNKNOWN_HOST, "urlwitherror.com"));
        String[] hosts = {"urlwitherror.com"};
        String[] args = Config.getProperty(Config.ICMP_COMMAND).split(Monitoring.COMMAND_DELIMITER);

        ProcessTemplateMethod templateMethod = new ProcessICMP(args, hosts[0]);
        templateMethod.run(PingaContext.icmpTable, PingaContext.icmpResponse);

    }

    @Test(expected = TCPException.class)
    public void should_get_an_error_running_tcp_process() throws IOException, InterruptedException {
        String[] hosts = {"urlwitherror.com"};

        ProcessTemplateMethod templateMethod = new ProcessTCP(hosts[0]);
        templateMethod.run(PingaContext.tcpTable, PingaContext.tcpResponse);

    }

    @Test(expected = RouteException.class)
    public void should_not_run_an_invalid_trace_command() throws IOException, InterruptedException {
        String[] hosts = Config.getProperty(Config.HOSTS).split(Monitoring.HOSTS_DELIMITER);
        String[] args = {"traceroute", "-qw", "1"};

        ProcessTemplateMethod templateMethod = new ProcessTrace(args, hosts[0]);
        templateMethod.run(PingaContext.traceTable, PingaContext.traceResponse);

    }

    @Test
    public void should_run_a_tcp_process() throws IOException, InterruptedException {
        String[] hosts = Config.getProperty(Config.HOSTS).split(Monitoring.HOSTS_DELIMITER);

        ProcessTemplateMethod templateMethod = new ProcessTCP(hosts[0]);
        ProcessResponse response = templateMethod.run(PingaContext.tcpTable, PingaContext.tcpResponse);

        Assert.assertTrue(response.getRaw() != null);

    }

}
