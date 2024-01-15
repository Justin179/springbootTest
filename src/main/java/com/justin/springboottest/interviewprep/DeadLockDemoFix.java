package com.justin.springboottest.interviewprep;

public class DeadLockDemoFix {
    private static Object resource1 = new Object();//资源 1
    private static Object resource2 = new Object();//资源 2

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread() + "get resource1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 线程 A 和线程 B 休眠结束了都开始企图请求获取对方的资源，
                // 然后这两个线程就会陷入互相等待的状态，这也就产生了死锁。
                // 一个线程因请求资源而阻塞时，对已获得的资源保持不放。
                // 解決: 占用部分资源的线程进一步申请其他资源时，如果申请不到，可以主动释放它占有的资源。
                System.out.println(Thread.currentThread() + "waiting get resource2");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread() + "get resource2");
                }
            }
        }, "线程 1").start();

        // 对线程 2 的代码修改成下面这样就不会产生死锁了。
        new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread() + "get resource1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource2");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread() + "get resource2");
                }
            }
        }, "线程 2").start();
        /*
        上面的代码为什么避免了死锁的发生?线程 1 首先获得到 resource1 的监视器锁,这时候线程 2 就获取不到了。
        然后线程 1 再去获取 resource2 的监视器锁，可以获取到。
        然后线程 1 释放了对 resource1、resource2 的监视器锁的占用，线程 2 获取到就可以执行了。这样就破坏了破坏循环等待条件，因此避免了死锁
         */
    }
}

