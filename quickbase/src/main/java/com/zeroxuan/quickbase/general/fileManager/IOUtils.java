package com.zeroxuan.quickbase.general.fileManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class IOUtils {

    /**
     * The default buffer size to use.
     */
    private final static int DEFAULT_BUFFER_SIZE = 1024 * 8;

    private IOUtils() {
        super();
    }


    public static String toString(StringBuffer input) {
        return input.toString();
    }

    public static String toString(byte[] input) {
        if (input == null) {
            return null;
        }
        return new String(input);
    }

    public static String toString(byte[] input, String charsetName)
            throws UnsupportedEncodingException {
        if (charsetName == null) {
            return toString(input);
        }
        else {
            return new String(input, charsetName);
        }
    }

    public static String toString(Reader input) throws IOException {
        StringWriter out = new StringWriter();
        copy(input, out);
        return out.toString();
    }

    public static String toString(InputStream input) throws IOException {
        StringWriter out = new StringWriter();
        copy(input, out);
        return out.toString();
    }

    public static String toString(InputStream input, String charsetName) throws IOException {
        if (charsetName == null) {
            return toString(input);
        }
        else {
            StringWriter out = new StringWriter();
            copy(input, out, charsetName);
            return out.toString();
        }
    }


    public static byte[] toByteArray(String input) {
        if (input == null) {
            return null;
        }
        return input.getBytes();
    }

    public static byte[] toByteArray(StringBuffer input) {
        if (input == null) {
            return null;
        }
        else {
            return toByteArray(input.toString());
        }
    }

    public static byte[] toByteArray(char[] input) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        CharArrayReader in = new CharArrayReader(input);
        copy(in, out);
        return out.toByteArray();
    }

    public static byte[] toByteArray(Reader input) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        copy(input, out);
        return out.toByteArray();
    }

    public static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(input, output);
        return output.toByteArray();
    }


    public static char[] toCharArray(String input) {
        if (input == null) {
            return null;
        }
        return input.toCharArray();
    }

    public static char[] toCharArray(StringBuffer input) {
        if (input == null) {
            return null;
        }
        else {
            return toCharArray(input.toString());
        }
    }

    public static char[] toCharArray(Reader input) throws IOException {
        CharArrayWriter output = new CharArrayWriter();
        copy(input, output);
        return output.toCharArray();
    }

    public static char[] toCharArray(InputStream input) throws IOException {
        CharArrayWriter output = new CharArrayWriter();
        copy(input, output);
        return output.toCharArray();
    }


    public static InputStream toInputStream(String input) {
        if (input == null) {
            return null;
        }

        byte[] bytes = input.getBytes();
        return new ByteArrayInputStream(bytes);
    }

    public static InputStream toInputStream(StringBuffer input) {
        if (input == null) {
            return null;
        }
        return toInputStream(input.toString());
    }

    public static InputStream toInputStream(Reader input) throws IOException {
        return new ByteArrayInputStream(toByteArray(input));
    }


    public static void write(String data, Writer output) throws IOException {
        if (data == null) {
            return;
        }
        output.write(data);
    }

    public static void write(String data, OutputStream output) throws IOException {
        if (data == null) {
            return;
        }
        output.write(data.getBytes());
    }

    public static void write(StringBuffer data, Writer output) throws IOException {
        write(data.toString(), output);
    }

    public static void write(StringBuffer data, OutputStream output) throws IOException {
        write(data.toString(), output);
    }

    public static void write(byte[] data, Writer output) throws IOException {
        output.write(toString(data));
    }

    public static void write(byte[] data, OutputStream output) throws IOException {
        output.write(data);
    }

    public static void write(char[] data, Writer output) throws IOException {
        output.write(data);
    }

    public static void write(char[] data, OutputStream output) throws IOException {
        output.write(toByteArray(data));
    }

    public static void write(Reader data, Writer output) throws IOException {
        copy(data, output);
    }

    public static void write(Reader data, OutputStream output) throws IOException {
        copy(data, output);
    }

    public static void write(InputStream data, Writer output) throws IOException {
        copy(data, output);
    }

    public static void write(InputStream data, OutputStream output) throws IOException {
        copy(data, output);
    }


    public static void closeQuietly(Reader input) {
        try {
            if (input != null) {
                input.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeQuietly(InputStream input) {
        try {
            if (input != null) {
                input.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeQuietly(Writer output) {
        try {
            if (output != null) {
                output.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeQuietly(OutputStream output) {
        try {
            if (output != null) {
                output.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void copy(InputStream input, Writer output) throws IOException {
        InputStreamReader in = new InputStreamReader(input);
        copy(in, output);
    }

    public static void copy(InputStream input, Writer output, String charsetName)
            throws IOException {
        if (charsetName == null) {
            copy(input, output);
        }
        else {
            InputStreamReader in = new InputStreamReader(input, charsetName);
            copy(in, output);
        }
    }

    public static int copy(Reader input, Writer output) throws IOException {
        long inputLen
                = copyLarge(input, output);
        if (inputLen > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) inputLen;
    }


    public static long copyLarge(Reader input, Writer output) throws IOException {
        char[] buf = new char[DEFAULT_BUFFER_SIZE];
        long inputLen = 0;
        int readLen = 0;
        while ((readLen = input.read(buf, 0, DEFAULT_BUFFER_SIZE)) != -1) {
            output.write(buf, 0, readLen);
            inputLen += readLen;
        }
        return inputLen;
    }

    public static void copy(Reader input, OutputStream output) throws IOException {
        OutputStreamWriter out = new OutputStreamWriter(output);
        copy(input, out);
        out.flush();
    }

    public static void copy(Reader input, OutputStream output, String charsetName)
            throws IOException {
        if (charsetName == null) {
            copy(input, output);
        }
        else {
            OutputStreamWriter out = new OutputStreamWriter(output, charsetName);
            copy(input, out);
            out.flush();
        }

    }


    public static int copy(InputStream input, OutputStream output) throws IOException {
        long inputLen = copyLarge(input, output);
        if (inputLen > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) inputLen;
    }

    public static long copyLarge(InputStream input, OutputStream output) throws IOException {
        byte[] buf = new byte[DEFAULT_BUFFER_SIZE];
        long inputLen = 0;
        int readLen = 0;
        if ((readLen = input.read(buf, 0, readLen)) != -1) {
            output.write(buf, 0, readLen);
            inputLen += readLen;
        }
        return inputLen;
    }

}
