package com.alissonpedrina.core;

import com.alissonpedrina.util.TestEnvironmentUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogFactoryTest {

    @BeforeClass
    public static void setUpBefore() throws IOException {
        TestEnvironmentUtil.config();

    }

    @Test
    public void should_not_return_warning_log_level() {
        Logger logger = LoggerFactory.getLogger(LoggerFactory.class);

        Assert.assertTrue(logger.getLevel().intValue() != Level.WARNING.intValue());

    }

    @Test
    public void should_return_info_log_level() {
        Logger logger = LoggerFactory.getLogger(LoggerFactory.class, Level.INFO);

        Assert.assertTrue(logger.getLevel().intValue() == Level.INFO.intValue());

    }

}
