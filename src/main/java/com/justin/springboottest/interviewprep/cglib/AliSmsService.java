package com.justin.springboottest.interviewprep.cglib;

public class AliSmsService {
    public AliSmsService() {
    }

    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}
