package com.justin.springboottest.interviewprep.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

public class Client {
    public static void main(String[] args) {

        // 創建Enhancer對象，用於生成代理類
        Enhancer enhancer = new Enhancer(); // 目前有問題，先不管
        // 設置目標類的父類型，CGLIB會動態生成一個子類來代理目標類
        enhancer.setSuperclass(AliSmsService.class);
        // 設置回調函數，用於攔截方法調用
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                // 在方法執行前後添加自己的操作
                System.out.println("Before method execution.");
                Object result = proxy.invokeSuper(obj, args); // 調用父類的方法
                System.out.println("After method execution.");
                return result;
            }
        });

        // 創建代理類的實例
        AliSmsService proxy = (AliSmsService) enhancer.create();

        // 使用代理類執行方法
        proxy.send("xxx");
    }
}
