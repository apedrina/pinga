package com.alissonpedrina.core;

import com.alissonpedrina.services.ValidationICMP;
import com.alissonpedrina.util.OSValidator;
import com.alissonpedrina.util.TestEnvironmentUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

public class ValidationICMPTest {

    @BeforeClass
    public static void setUpBefore() throws IOException {
        TestEnvironmentUtil.config();

    }

    @Before
    public void setUp() {
        TestEnvironmentUtil.cleanContext();

    }

    @Test
    public void should_find_request_timed_out() {
        ValidationICMP validationICMP = new ValidationICMP();
        String text = getTimedOutMessage();

        boolean result = validationICMP.timedOut(text);

        Assert.assertTrue(result);

    }

    @Test
    public void should_find_unknown_host() {
        ValidationICMP validationICMP = new ValidationICMP();
        String text = getUnknownHostMessage();

        boolean result = validationICMP.unknownHost(text);

        Assert.assertTrue(result);

    }

    @Test
    public void should_find_unreachable_host() {
        ValidationICMP validationICMP = new ValidationICMP();
        String text = getUnReachableHostMessage();

        boolean result = validationICMP.unreachable(text);

        Assert.assertTrue(result);

    }

    private String getTimedOutMessage() {
        return "2 packets transmitted, 2 packets received, 0.0% packet loss\n" +
                "round-trip min/avg/max/stddev =Request timed out 12.121/12.221/12.320/0.099 ms\n";

    }

    private String getUnReachableHostMessage() {
        return "2 packets transmitted, 2 packets received, 0.0% packet loss\n" +
                "round-trip min/avg/max/stddev = 12.121/12.221/12.320/0.099 ms\n" +
                "unreachable";

    }

    private String getUnknownHostMessage() {
        if (OSValidator.isMac()) {
            return "2 packets transmitted, 2 packets received, 0.0% packet loss\n" +
                    "round-trip min/avg/max/stddev = 12.121/12.221/12.320/0.099 ms\n" +
                    "Air:pinga alissonpedrina$ ping -c 2 xxx.cox\n" +
                    "ping: cannot resolve xxx.cox: Unknown host";

        } else if (OSValidator.isWindows()) {
            return "";
        } else {
            return "2 packets transmitted, 2 packets received, 0.0% packet loss\n" +
                    "round-trip min/avg/max/stddev = 12.121/12.221/12.320/0.099 ms\n" +
                    "Air:pinga alissonpedrina$ ping -c 2 xxx.cox\n" +
                    "ping: cannot resolve xxx.cox: Unknown host";

        }
    }

}
