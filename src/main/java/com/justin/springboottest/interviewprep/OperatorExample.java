package com.justin.springboottest.interviewprep;
/*
在 Java 代码里使用 <<、 >> 和>>>转换成的指令码运行起来会更高效些。
掌握最基本的移位运算符知识还是很有必要的，这不光可以帮助我们在代码中使用，还可以帮助我们理解源码中涉及到移位运算符的代码。

 */
public class OperatorExample {
    public static void main(String[] args) {
        /*
        在 Java 中，<<、>> 和 >>> 是位移運算符，它們用於對二進制位進行移動操作。
        這些運算符通常用於對整數類型的數值進行位運算。
         */
        int x = 10;  // 二進制表示為 1010
        String binaryString = Integer.toBinaryString(x);
        System.out.println(binaryString); // 1010

        // 左移 2 位: 將一個數的二進制位向左移動指定的位數，右邊補 0
        int leftShift = x << 2;  // 結果為 40，二進制表示為 101000
        System.out.println(leftShift);

        // 右移 2 位: 將一個數的二進制位向右移動指定的位數，左邊補符號位。對於正數，補 0；對於負數，補 1
        int rightShift = x >> 2;  // 結果為 2，二進制表示為 10

        // 無符號右移 2 位: 將一個數的二進制位向右移動指定的位數，左邊補 0。不考慮符號位的正負，將所有數字看作無符號數來處理
        int unsignedRightShift = x >>> 2;  // 結果為 2，二進制表示為 10

    }
}
