package com.alissonpedrina.core;

import com.alissonpedrina.util.TestEnvironmentUtil;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConfigTest {

    @BeforeClass
    public static void setUpBefore() throws IOException {
        TestEnvironmentUtil.config();

    }

    @Test
    public void should_read_property_from_properties_file() {
        String expected = "/report";

        String endpoint = Config.getProperty("pinga.server.report.endpoint");

        assertThat(endpoint, equalTo(expected));

    }

}
