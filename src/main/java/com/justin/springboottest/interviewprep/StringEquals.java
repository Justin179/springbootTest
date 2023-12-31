package com.justin.springboottest.interviewprep;

public class StringEquals {
    public static void main(String[] args) {

        String str1 = "str"; // xx0
        String str2 = "ing"; // xx1
        String str3 = "str" + "ing"; // xx2
        String str4 = str1 + str2; // xx3?
        String str5 = "string"; // xx2

        System.out.println(str3 == str4); // f
        System.out.println(str3 == str5); // t
        System.out.println(str4 == str5); // f
        // 对于编译期可以确定值的字符串，也就是常量字符串 ，jvm 会将其存入字符串常量池。
        // 這說明了為什麼str3 & str5 直接進池了
        // 对于 String str3 = "str" + "ing"; 编译器会给你优化成 String str3 = "string"; 。
        // 引用的值在程序编译期是无法确定的，编译器无法对其进行优化。

        // 不过，字符串使用 final 关键字声明之后，可以让编译器当做常量来处理。
        // 编译器在程序编译期就可以确定它的值，其效果就相当于访问常量。
        final String a = "str";
        final String b = "ing";
        // 下面两个表达式其实是等价的
        String c = "str" + "ing";// 常量池中的对象
        String d = a + b; // 常量池中的对象
        System.out.println(c == d);// true

        // 如果 ，编译器在运行时才能知道其确切值的话，就无法对其优化。
//        final String x = "str";
//        final String y = getStr();
//        String c1 = "str" + "ing";// 常量池中的对象
//        String d1 = x + y; // 在堆上创建的新的对象
//        System.out.println(c1 == d1);// false
//        public static String getStr() {
//            return "ing";
//        }





        // 在堆中创建字符串对象”Java“
//// 将字符串对象”Java“的引用保存在字符串常量池中
//        String s1 = "Java";
//// 直接返回字符串常量池中字符串对象”Java“对应的引用
//        String s2 = s1.intern(); // 池裡找Java
//// 会在堆中在单独创建一个字符串对象
//        String s3 = new String("Java");
//// 直接返回字符串常量池中字符串对象”Java“对应的引用
//        String s4 = s3.intern(); // 池裡找Java
//
//// s1 和 s2 指向的是堆中的同一个对象
//        System.out.println(s1 == s2);
//// s3 和 s4 指向的是堆中不同的对象
//        System.out.println(s3 == s4); // false
//// s1 和 s4 指向的是堆中的同一个对象
//        System.out.println(s1 == s4);


//        String a = new String("ab"); // a 为一个引用
//        String b = new String("ab"); // b为另一个引用,对象的内容一样
//        System.out.println(a == b); // false
//        System.out.println(a.equals(b)); // true
//
//        String aa = "ab"; // 放在常量池中
//        String bb = "ab"; // 从常量池中查找
//        System.out.println(aa == bb); // true
//        System.out.println(42 == 42.0); // 這個是true喔


//        String str1 = "hello";
//        String str2 = new String("hello");
//        String str3 = "hello";
//
//        // 使用 == 比较字符串的引用相等
//        System.out.println(str1 == str2); // false
//        System.out.println(str1 == str3); // true
//
//        // 使用 equals 方法比较字符串的相等
//        System.out.println(str1.equals(str2)); // true
//        System.out.println(str1.equals(str3)); // true
    }
}
