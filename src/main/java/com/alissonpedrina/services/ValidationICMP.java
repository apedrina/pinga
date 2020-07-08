package com.alissonpedrina.services;

import com.alissonpedrina.core.Config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationICMP {

    public boolean unknownHost(String statement) {
        Pattern pattern = Pattern.compile(Config.getProperty(Config.UNKNOWN_HOST), Pattern.CASE_INSENSITIVE);
        Matcher m = pattern.matcher(statement);
        return m.find();

    }

    public boolean unreachable(String statement) {
        Pattern pattern = Pattern.compile(Config.getProperty(Config.UNREACHABLE), Pattern.CASE_INSENSITIVE);
        Matcher m = pattern.matcher(statement);
        return m.find();

    }

    public boolean timedOut(String statement) {
        Pattern pattern = Pattern.compile(Config.getProperty(Config.TIMEOUT), Pattern.CASE_INSENSITIVE);
        Matcher m = pattern.matcher(statement);
        return m.find();

    }
}
