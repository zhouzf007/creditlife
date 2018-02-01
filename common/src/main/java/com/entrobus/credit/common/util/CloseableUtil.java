package com.entrobus.credit.common.util;

import java.io.Closeable;
import java.io.IOException;

public class CloseableUtil {
    public static void close(Closeable in) {
        if (in != null) {
            try {
                in.close();
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }
}
