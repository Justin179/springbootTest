package com.justin.springboottest.designpatterns2.structural.bridger;

// 抽象訊息類
public abstract class Message {
    protected MessageSender sender;

    public Message(MessageSender sender) {
        this.sender = sender;
    }

    public abstract void send();
}

// 擴展不同類型的訊息
class TextMessage extends Message {
    public TextMessage(MessageSender sender) {
        super(sender);
    }

    @Override
    public void send() {
        System.out.print("Text message: ");
        sender.sendMessage("This is a text message");
    }
}

class EmailMessage extends Message {
    public EmailMessage(MessageSender sender) {
        super(sender);
    }

    @Override
    public void send() {
        System.out.print("Email message: ");
        sender.sendMessage("This is an email message");
    }
}
