package com.zeroxuan.quickbase.general.fileManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class IOUtils {

    /**
     * The default buffer size to use.
     */
    private final static int DEFAULT_BUFFER_SIZE=1024*8;

    public static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buff = new byte[DEFAULT_BUFFER_SIZE];
        int length=0;
        while ((length= input.read(buff,0,DEFAULT_BUFFER_SIZE)) != -1) {
output.write(buff,0,length);
        }


        return output.toByteArray();
    }
}
