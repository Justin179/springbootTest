package com.justin.springboottest.designpatterns2.structural.bridger;

public class Client {
    /*
    通過這種方法，你可以輕鬆地新增新的訊息類型或者訊息發送者，而無需修改現有的代碼。
    Bridge 模式是一種設計模式，它用於將抽象部分和實際實現部分分開，從而使它們可以獨立地變化。
     */
    public static void main(String[] args) {
        // 使用不同的訊息發送者
        MessageSender emailSender = new EmailSender();
        MessageSender smsSender = new SMSSender();

        // 創建不同類型的訊息並傳入不同的訊息發送者
        Message textMessage = new TextMessage(emailSender); // 可以接收各種sender
        Message emailMessage = new EmailMessage(smsSender); // 可以接收各種sender

        // 發送訊息
        textMessage.send();
        emailMessage.send();
    }
}

