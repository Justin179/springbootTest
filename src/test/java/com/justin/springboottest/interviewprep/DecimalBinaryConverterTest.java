package com.justin.springboottest.interviewprep;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecimalBinaryConverterTest {

    @Test
    void decimalToBinary() {
        String binary = DecimalBinaryConverter.decimalToBinary(10);
        assertEquals("1010",binary);
    }

    @Test
    void binaryToDecimal() {
        int decimal = DecimalBinaryConverter.binaryToDecimal("1010");
        assertEquals(10,decimal);
    }

    @Test
    void playWzIt(){
        // << :左移运算符
        int x = 10;
        String binary = DecimalBinaryConverter.decimalToBinary(10);
        assertEquals("1010",binary);

        int leftShifted = DecimalBinaryConverter.leftShift(x, 2);
        String leftShiftedBinary = DecimalBinaryConverter.decimalToBinary(leftShifted);
        assertEquals("101000",leftShiftedBinary); // 右邊補 0

        // >> :带符号右移  向右移若干位，高位补符号位(正数高位补 0,负数高位补 1)，低位丢弃。
        /*
        1010 右移2位 變成 0010
        0010 就是整數 2(如下)
         */
        int rightShifted = DecimalBinaryConverter.rightShift(x, 2);
        assertEquals(2,rightShifted);
        int temp = DecimalBinaryConverter.binaryToDecimal("0010");
        assertEquals(2,temp);

        String rightShiftedBinary = DecimalBinaryConverter.decimalToBinary(rightShifted);
        assertEquals("10",rightShiftedBinary); // 因為前兩碼是00，可以省略

        // >>> :无符号右移，忽略符号位，空位都以 0 补齐。
        int unsignedRightShift = DecimalBinaryConverter.unsignedRightShift(x, 2);
        assertEquals(2,unsignedRightShift);
        /*
        1010 -> 10
         */
    }

    /*
    当 int 类型左移/右移位数大于等于 32 位操作时，会先求余（%）后再进行左移/右移操作。
    也就是说左移/右移 32 位相当于不进行移位操作（32%32=0），左移/右移 42 位相当于左移/右移 10 位（42%32=10）。
    当 long 类型进行左移/右移操作时，由于 long 对应的二进制是 64 位，因此求余操作的基数也变成了 64。
    也就是说：x<<42等同于x<<10，x>>42等同于x>>10，x >>>42等同于x >>> 10。
     */
    @Test
    void mod32(){
        int x = -1;
        assertEquals(x<<10, x<<42);
    }
}