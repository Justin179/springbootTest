package com.justin.springboottest.interviewprep.dynamicproxy;

public interface SmsService {
    String send(String message);
}

class SmsServiceImpl implements SmsService {
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}
