package com.justin.springboottest.designpatterns2.behavioral.mediator;
/*
在這種模式中，類之間的通信不直接發生，而是透過中介者進行。
這有助於促進類之間的鬆散耦合，使系統更易於維護和擴展。
 */
public class Client {
    public static void main(String[] args) {
        ConcreteMediator mediator = new ConcreteMediator();

        ConcreteColleague1 colleague1 = new ConcreteColleague1(mediator);
        ConcreteColleague2 colleague2 = new ConcreteColleague2(mediator);

        mediator.setColleague1(colleague1);
        mediator.setColleague2(colleague2);

        colleague1.sendMessage("Hello, this is colleague1.");
        colleague2.sendMessage("Hi, colleague1! This is colleague2.");
    }
}
