package com.esieve.user.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.esieve.common.bean.OperationResult;
import com.esieve.user.bean.User;
import com.esieve.user.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(timeout = 500, loadbalance = "leastactive")
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public User getUserByUserId(int userId) {
        User user = userDao.getUserByUserId(userId);
        if (user == null) {
            LOGGER.warn("get user error, userId {}", userId);
        }
        return user;
    }

    @Override
    public OperationResult checkUser(User user) {
        User result = userDao.getUserByUser(user);

        OperationResult operationResult = new OperationResult();
        if (result == null) {
            operationResult.setSuccess(false);
            LOGGER.warn("check user error, user {}", user);
        } else {
            operationResult.setSuccess(true);
        }
        return operationResult;
    }

    @Override
    public OperationResult insertUser(User user) {
        int result = userDao.insertUser(user);

        OperationResult operationResult = new OperationResult();
        if (result == 1) {
            operationResult.setSuccess(true);
        } else {
            operationResult.setSuccess(false);
            LOGGER.warn("insert user error, user {}", user);
        }
        return operationResult;
    }

    @Override
    public OperationResult updateUser(User user) {
        int result = userDao.updateUser(user);

        OperationResult operationResult = new OperationResult();
        if (result == 1) {
            operationResult.setSuccess(true);
        } else {
            operationResult.setSuccess(false);
            LOGGER.warn("update user error, user {}", user);
        }
        return operationResult;
    }

    @Override
    public OperationResult deleteUser(int userId) {
        int result = userDao.deleteUser(userId);

        OperationResult operationResult = new OperationResult();
        if (result == 1) {
            operationResult.setSuccess(true);
        } else {
            operationResult.setSuccess(false);
            LOGGER.warn("delete user error, userId {}", userId);
        }
        return operationResult;
    }
}
