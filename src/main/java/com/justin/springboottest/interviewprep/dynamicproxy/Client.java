package com.justin.springboottest.interviewprep.dynamicproxy;

public class Client {
    public static void main(String[] args) {
        SmsService smsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        smsService.send("java");

    }
}
