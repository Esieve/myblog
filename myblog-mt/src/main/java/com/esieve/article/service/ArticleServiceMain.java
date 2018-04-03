package com.esieve.article.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ArticleServiceMain {
    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("ArticleService.xml", "service.xml");
        context.start();

        while (true) {
            synchronized (lock) {
                lock.wait(); //等待，直到其它线程调用 lock.notify()
            }
        }
    }
}
