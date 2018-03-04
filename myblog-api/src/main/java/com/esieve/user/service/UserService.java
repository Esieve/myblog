package com.esieve.user.service;

import com.esieve.common.bean.OperationResult;
import com.esieve.user.bean.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User getUserByUserId(int userId);

    User getUserByUser(User user);

    OperationResult insertUser(User user);

    OperationResult updateUser(User user);

    OperationResult deleteUser(int userId);
}
