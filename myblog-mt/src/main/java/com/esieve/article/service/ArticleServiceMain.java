package com.esieve.article.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class ArticleServiceMain {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("ArticleService.xml", "service.xml");
        context.start();

        System.in.read(); // 按任意键退出
    }
}
