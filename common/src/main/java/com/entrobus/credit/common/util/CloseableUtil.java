package com.entrobus.credit.common.util;

import java.io.Closeable;

public class CloseableUtil {
    public static void close(Closeable in) {
        if (in != null) {
            try {
                in.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
