package com.justin.springboottest.designpatterns2.structural.bridger;

// 訊息發送者介面
public interface MessageSender {
    void sendMessage(String message);
}

// 不同類型的訊息發送者實現
class EmailSender implements MessageSender {
    @Override
    public void sendMessage(String message) {
        System.out.println("Sending email: " + message);
    }
}

class SMSSender implements MessageSender {
    @Override
    public void sendMessage(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

