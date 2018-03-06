package com.esieve.user.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class UserServiceMain {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("UserService.xml", "service.xml");
        context.start();

        System.in.read(); // 按任意键退出
    }
}
