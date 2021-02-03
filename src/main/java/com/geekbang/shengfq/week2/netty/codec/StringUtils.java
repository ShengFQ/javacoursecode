package com.geekbang.shengfq.week2.netty.codec;

public class StringUtils {
    static final String HEX_FORMAT = "%02x";

    public StringUtils() {
    }

    public static String bytesToHexStr(byte[] b) {
        return bytesToHexStr(b, 0, b.length);
    }

    public static String bytesToHexStr(byte[] b, int start, int len) {
        StringBuffer str = new StringBuffer();

        for(int i = start; i < start + len; ++i) {
            str.append(String.format("%02x", b[i]));
        }

        return str.toString();
    }

    public static byte[] hexStrToBytes(String str) {
        if (str.length() % 2 != 0) {
            str = "0" + str;
        }

        byte[] temp = new byte[str.length() / 2];

        for(int i = 0; i < str.length(); i += 2) {
            temp[i / 2] = (byte)(Byte.parseByte(str.substring(i, i + 1), 16) * 16 + Byte.parseByte(str.substring(i + 1, i + 2), 16));
        }

        return temp;
    }

    public static void hexStrToBytes(String str, byte[] b, int from) {
        if (str.length() % 2 != 0) {
            str = "0" + str;
        }

        hexStrToBytes(str, b, from, str.length() / 2);
    }

    public static void hexStrToBytes(String str, byte[] b, int from, int length) {
        if (str.length() % 2 != 0) {
            str = "0" + str;
        }

        for(int i = 0; i < Math.min(str.length(), length * 2) && from + i / 2 < b.length; i += 2) {
            b[from + i / 2] = (byte)(Byte.parseByte(str.substring(i, i + 1), 16) * 16 + Byte.parseByte(str.substring(i + 1, i + 2), 16));
        }

    }

    public static byte[] hexStrToInverseBytes(String str) {
        if (str.length() % 2 != 0) {
            str = "0" + str;
        }

        byte[] temp = new byte[str.length() / 2];

        for(int i = 0; i < str.length(); i += 2) {
            temp[temp.length - 1 - i / 2] = (byte)(Byte.parseByte(str.substring(i, i + 1), 16) * 16 + Byte.parseByte(str.substring(i + 1, i + 2), 16));
        }

        return temp;
    }

    public static String inverseBytesToHexStr(byte[] b, int start, int len) {
        StringBuffer str = new StringBuffer();

        for(int i = start + len - 1; i >= start; --i) {
            str.append(String.format("%02x", b[i]));
        }

        return str.toString();
    }

    public static String lengthFix(String text, int length, char ch, boolean end) {
        if (text == null) {
            text = "";
        }

        int tempLength = text.getBytes().length;
        if (length == tempLength) {
            return text;
        } else if (length <= tempLength) {
            return end ? new String(text.getBytes(), 0, length) : new String(text.getBytes(), tempLength - length, length);
        } else {
            char[] fix = new char[length - tempLength];

            for(int i = 0; i < fix.length; ++i) {
                fix[i] = ch;
            }

            StringBuffer buffer = new StringBuffer(text);
            if (end) {
                buffer = buffer.append(fix);
            } else {
                buffer = buffer.insert(0, fix);
            }

            return buffer.toString();
        }
    }

    public static String lengthFix(String text, int length, String ch, boolean end) {
        return lengthFix(text, length, ch.charAt(0), end);
    }
}
