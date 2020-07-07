package com.alissonpedrina;

import com.alissonpedrina.services.process.ProcessResponse;

import java.util.Hashtable;
import java.util.Map;

public class PingaContext {
    public static Map<String, Boolean> icmpTable = new Hashtable<String, Boolean>();
    public static Map<String, Boolean> tcpTable = new Hashtable<String, Boolean>();
    public static Map<String, Boolean> traceTable = new Hashtable<String, Boolean>();

    public static Map<String, ProcessResponse> icmpResponse = new Hashtable<String, ProcessResponse>();
    public static Map<String, ProcessResponse> tcpResponse = new Hashtable<String, ProcessResponse>();
    public static Map<String, ProcessResponse> traceResponse = new Hashtable<String, ProcessResponse>();

}

