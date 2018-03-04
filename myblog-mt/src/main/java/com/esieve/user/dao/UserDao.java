package com.esieve.user.dao;

import com.esieve.user.bean.User;

import java.util.List;

/**
 * Created by 77239 on 2017/3/28/0028.
 */
public interface UserDao {

    List<User> getUsers();

    User getUserByUserId(int userId);

    User getUserByUser(User user);

    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(int userId);

}
