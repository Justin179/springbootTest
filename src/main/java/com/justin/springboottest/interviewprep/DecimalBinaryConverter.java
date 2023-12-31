package com.justin.springboottest.interviewprep;
public class DecimalBinaryConverter {
/*
十進制轉換如何寫（二，八，十六）不用算法
Integer.toBinaryString
Integer.toOctalString
Integer.toHexString
 */
    public static String decimalToBinary(int x) {
        return Integer.toBinaryString(x);
    }

    public static int binaryToDecimal(String binaryString) {
        // 使用parseInt方法将二进制转换为十进制
        // binaryString 是一个代表二进制数的字符串，而 2 是指定这是一个二进制数。
        return Integer.parseInt(binaryString, 2);
    }

    public static int leftShift(int decimal, int shift) {
        return decimal << shift;
    }

    public static int rightShift(int decimal, int shift) {
        return decimal >> shift;
    }

    public static int unsignedRightShift(int decimal, int shift) {
        return decimal >>> shift;
    }

}