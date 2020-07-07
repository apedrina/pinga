package com.alissonpedrina.core.error;

public class ICMPException extends RuntimeException {

    public static final String UNKNOWN_HOST = "Unknown host";
    public static final String TIMEOUT = "Request timed out";
    public static final String UNREACHABLE = "unreachable";
    public static final String LOST = "Lost = 0";

    public ICMPException(String message) {
        super(message);

    }

}
