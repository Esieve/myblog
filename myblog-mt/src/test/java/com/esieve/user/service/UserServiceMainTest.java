package com.esieve.user.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.esieve.common.bean.OperationResult;
import com.esieve.user.bean.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:service.xml", "classpath:consumer.xml"})
public class UserServiceMainTest {
    @Reference
    private UserService userService;

    @Test
    public void main() {
        int userId = 10;

        User user = new User("test", "test");

        OperationResult result = userService.insertUser(user);
        Assert.assertTrue(result.isSuccess());

        List<User> users = userService.getUsers();
        Assert.assertNotNull(users);
        Assert.assertEquals(1, users.size());

        result = userService.checkUser(user);
        Assert.assertTrue(result.isSuccess());

        result = userService.updateUser(user);
        Assert.assertTrue(result.isSuccess());

        user = userService.getUserByUserId(userId);
        Assert.assertNotNull(user);

        result = userService.deleteUser(userId);
        Assert.assertTrue(result.isSuccess());

        result = userService.deleteUser(-1);
        Assert.assertFalse(result.isSuccess());
    }
}