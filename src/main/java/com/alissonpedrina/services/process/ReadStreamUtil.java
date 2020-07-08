package com.alissonpedrina.services.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class ReadStreamUtil {
    public static String exec(String[] args) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder(args);
        builder.redirectErrorStream(true);
        java.lang.Process process = builder.start();

        StringBuilder outputTxt = new StringBuilder();

        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            outputTxt.append(line + "\n");

        }
        process.waitFor();
        in.close();

        return outputTxt.toString();

    }

}
