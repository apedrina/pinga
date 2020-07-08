package com.alissonpedrina.core.error;

public class ICMPException extends RuntimeException {

    public static final String UNKNOWN_HOST = "%s: Unknown host";
    public static final String UNREACHABLE_HOST = "%s: Unreachable";
    public static final String TIMEOUT = "%: Request Timed out";

    public ICMPException(String message) {
        super(message);

    }

}
