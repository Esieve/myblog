package com.esieve.link.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class LinkServiceMain {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("LinkService.xml", "service.xml");
        context.start();

        System.in.read(); // 按任意键退出
    }
}
