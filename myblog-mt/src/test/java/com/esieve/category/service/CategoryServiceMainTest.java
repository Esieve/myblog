package com.esieve.category.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.esieve.common.bean.OperationResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:service.xml", "classpath:consumer.xml"})
public class CategoryServiceMainTest {
    @Reference
    private CategoryService categoryService;

//    @Before
//    public void setUp() throws Exception {
//        ClassPathXmlApplicationContext remoteContext =
//            new ClassPathXmlApplicationContext("CategoryService.xml");
//        remoteContext.start();
//    }

    @Test
    public void main() throws InterruptedException {
        OperationResult result = categoryService.insertCategory("test");
        assertNull(result);
//        categoryService.getCategories();
    }
}