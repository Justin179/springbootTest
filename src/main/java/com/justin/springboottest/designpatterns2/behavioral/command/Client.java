package com.justin.springboottest.designpatterns2.behavioral.command;

// 假設你有一個家電遙控器，可以控制不同的裝置（比如燈、風扇等）。
/*
Command Pattern 可以將一個請求封裝成一個物件，並將其傳遞給調用者對象。
調用者對象會尋找適當的對象來處理這個命令，並將命令傳遞給相應的對象，該對象執行命令
 */
public class Client {
    public static void main(String[] args) {
        // 创建命令的接收者
        Light light = new Light();

        // 创建命令
        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);

        // 创建遥控器
        RemoteControl remoteControl = new RemoteControl();

        // 指定命令
        remoteControl.setCommand(lightOn);
        // 按下按钮，执行命令
        remoteControl.pressButton(); // Output: Light is on

        // 更改命令
        remoteControl.setCommand(lightOff);
        // 再次按下按钮，执行命令
        remoteControl.pressButton(); // Output: Light is off
    }
}

