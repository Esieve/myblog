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
    public OperationResult<List<User>> getUsers() {
        OperationResult<List<User>> result = new OperationResult<>();

        List<User> users = userDao.getUsers();
        if (users == null) {
            result.setInfo("无用户列表");
        } else {
            result.setSuccess(true);
            result.setData(users);
        }
        return result;
    }

    @Override
    public OperationResult<User> getUserByUserId(int userId) {
        OperationResult<User> result = new OperationResult<>();

        User user = userDao.getUserByUserId(userId);
        if (user == null) {
            LOGGER.warn("get user error, userId {}", userId);
            result.setInfo("未找到该用户");
        } else {
            result.setSuccess(true);
            result.setData(user);
        }
        return result;
    }

    @Override
    public OperationResult<User> checkUser(User user) {
        User result = userDao.getUserByUser(user);

        OperationResult<User> operationResult = new OperationResult();
        if (result == null) {
            LOGGER.warn("check user error, user {}", user);
            operationResult.setInfo("用户名或密码错误");
        } else {
            operationResult.setSuccess(true);
            operationResult.setData(user);
        }
        return operationResult;
    }

    @Override
    public OperationResult insertUser(User user) {
        int result = userDao.insertUser(user);

        OperationResult operationResult = new OperationResult();
        if (result == 1) {
            operationResult.setSuccess(true);
            operationResult.setInfo("插入成功");
        } else {
            LOGGER.warn("insert user error, user {}", user);
            operationResult.setInfo("插入失败");
        }
        return operationResult;
    }

    @Override
    public OperationResult updateUser(User user) {
        int result = userDao.updateUser(user);

        OperationResult operationResult = new OperationResult();
        if (result == 1) {
            operationResult.setSuccess(true);
            operationResult.setInfo("更新成功");
        } else {
            LOGGER.warn("update user error, user {}", user);
            operationResult.setInfo("更新失败");
        }
        return operationResult;
    }

    @Override
    public OperationResult deleteUser(int userId) {
        int result = userDao.deleteUser(userId);

        OperationResult operationResult = new OperationResult();
        if (result == 1) {
            operationResult.setSuccess(true);
            operationResult.setInfo("删除成功");
        } else {
            LOGGER.warn("delete user error, userId {}", userId);
            operationResult.setInfo("删除失败");
        }
        return operationResult;
    }
}
