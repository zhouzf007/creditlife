package com.entrobus.credit.common.util;

import java.io.Closeable;
import java.io.IOException;

public class CloseableUtil {
    public static void close(Closeable io) {
        if (io != null) {
            try {
                io.close();
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }
}
