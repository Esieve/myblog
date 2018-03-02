package com.esieve.category.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class CategoryServiceMain {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(new String[]{"CategoryService.xml", "service.xml"});
        context.start();

        System.in.read(); // 按任意键退出
    }
}
