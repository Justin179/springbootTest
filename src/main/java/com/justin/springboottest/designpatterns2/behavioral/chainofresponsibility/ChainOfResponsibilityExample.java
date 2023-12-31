package com.justin.springboottest.designpatterns2.behavioral.chainofresponsibility;
/*
構建一個將請求的發送者與接收者解耦的方式。
在這種模式下，請求被傳遞沿著一個鏈式結構，每個處理者都有機會處理請求，或將其傳遞給下一個處理者。
假設我們有一個抽象處理者Handler和幾個具體處理者，每個處理者都有自己的處理邏輯。請求首先發送給第一個處理者，
如果它無法處理，則將請求傳遞給下一個處理者，直到找到能夠處理該請求的處理者。
請求將在鏈上依次傳遞，直到找到合適的處理者處理為止。
這種模式能夠有效地降低發送者和接收者之間的耦合度
 */
public class ChainOfResponsibilityExample {
    public static void main(String[] args) {
        // 創建具體處理者
        Handler handlerA = new ConcreteHandlerA();
        Handler handlerB = new ConcreteHandlerB();

        // 建立處理者鏈
        handlerA.setNextHandler(handlerB);

        // 創建請求
        Request requestA = new Request(RequestLevel.LEVEL_A);
        Request requestB = new Request(RequestLevel.LEVEL_B);
        Request requestC = new Request(RequestLevel.LEVEL_C);

        // 發送請求到處理者鏈
        handlerA.handleRequest(requestA); // ConcreteHandlerA handles the request.
        handlerA.handleRequest(requestB); // ConcreteHandlerB handles the request.
        handlerA.handleRequest(requestC); // No handler found for the request.
    }
}


// 抽象處理者
interface Handler {
    void handleRequest(Request request);
    void setNextHandler(Handler nextHandler);
}

// 具體處理者A
class ConcreteHandlerA implements Handler {
    private Handler nextHandler;

    @Override
    public void handleRequest(Request request) {
        if (request.getLevel() == RequestLevel.LEVEL_A) {
            System.out.println("ConcreteHandlerA handles the request.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }
}

// 具體處理者B
class ConcreteHandlerB implements Handler {
    private Handler nextHandler;

    @Override
    public void handleRequest(Request request) {
        if (request.getLevel() == RequestLevel.LEVEL_B) {
            System.out.println("ConcreteHandlerB handles the request.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }
}

// 請求類型
class Request {
    private RequestLevel level;

    public Request(RequestLevel level) {
        this.level = level;
    }

    public RequestLevel getLevel() {
        return level;
    }
}

// 請求等級
enum RequestLevel {
    LEVEL_A, LEVEL_B, LEVEL_C
}
