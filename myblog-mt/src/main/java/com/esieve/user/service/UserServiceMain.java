package com.esieve.user.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceMain {
    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("UserService.xml", "service.xml");
        context.start();

        while (true) {
            synchronized (lock) {
                lock.wait(); //等待，直到其它线程调用 lock.notify()
            }
        }
    }
}
