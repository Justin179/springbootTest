package com.justin.springboottest.designpatterns2.behavioral.state2;


public class StateExample {
    public static void main(String[] args) {
        DeliveryContext ctx = new DeliveryContext(); // 被設為 ordered state
        ctx.updateState(ctx); // 被設為 InTransitState
        ctx.updateState(ctx); // 被設為 DeliveredState
        ctx.updateState(ctx); // 因為結束了，不會再被設狀態
    }
}

class DeliveryContext implements PackageState {
    private PackageState currentState; // 這個會一直被改狀態

    public DeliveryContext() {
        currentState = new OrderedState(); // 以第一個狀態為預設狀態
    }


    public void setCurrentState(PackageState state) {
        currentState = state;
    }

    @Override
    public void updateState(DeliveryContext ctx) {
        currentState.updateState(ctx);
    }
}


interface PackageState {
    void updateState(DeliveryContext ctx);
}

class OrderedState implements PackageState {
    public void updateState(DeliveryContext ctx) {
        System.out.println("Package is ordered. Next state - In Transit");
        ctx.setCurrentState(new InTransitState());
    }
}

class InTransitState implements PackageState {
    public void updateState(DeliveryContext ctx) {
        System.out.println("Package is in transit. Next state - Delivered");
        ctx.setCurrentState(new DeliveredState());
    }
}

class DeliveredState implements PackageState {
    public void updateState(DeliveryContext ctx) {
        System.out.println("Package is delivered.");
    }
}





