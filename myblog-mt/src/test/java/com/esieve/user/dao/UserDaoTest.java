package com.esieve.user.dao;

import com.esieve.user.bean.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:service.xml")
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    private int userId = 2;

    @Test
    public void insertUser() {
        User user = new User("test", "test");
        int result = userDao.insertUser(user);
        Assert.assertEquals(1, result);
    }

    @Test
    public void getUsers() {
        List<User> users = userDao.getUsers();
        Assert.assertNotNull(users);
        Assert.assertEquals(1, users.size());
    }

    @Test
    public void getUserByUserId() {
        User user = userDao.getUserByUserId(userId);
        Assert.assertNotNull(user);
        Assert.assertEquals(userId, user.getUserId());

        user = userDao.getUserByUserId(-1);
        Assert.assertNull(user);
    }

    @Test
    public void getUserByUser() {
        User user = new User("test", "test");

        user = userDao.getUserByUser(user);
        Assert.assertNotNull(user);
        Assert.assertEquals(userId, user.getUserId());

        user = userDao.getUserByUser(null);
        Assert.assertNull(user);
    }

    @Test
    public void updateUser() {
        User user = new User(userId, "test", "test", "test", "test", "test");
        int result = userDao.updateUser(user);
        Assert.assertEquals(1, result);

        result = userDao.updateUser(null);
        Assert.assertEquals(0, result);
    }

    @Test
    public void deleteUser() {
        int result = userDao.deleteUser(userId);
        Assert.assertEquals(1, result);

        result = userDao.deleteUser(-1);
        Assert.assertEquals(0, result);
    }
}