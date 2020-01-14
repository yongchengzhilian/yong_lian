package com.aidou.util.tool;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ExceptionUtil {


    public static String getExceptionAllinformation_01(Exception ex) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream pout = new PrintStream(out);
        ex.printStackTrace(pout);
        String ret = new String(out.toByteArray());
        pout.close();
        try {
            out.close();
        } catch (Exception e) {
        }
        return ret;
    }
}