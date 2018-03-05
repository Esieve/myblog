package com.esieve.user.dao;

import com.esieve.user.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by 77239 on 2017/3/28/0028.
 */
public interface UserDao {

    @Select("SELECT * FROM user")
    List<User> getUsers();

    @Select("SELECT user_id,username,image FROM user WHERE user_id=#{userId}")
    User getUserByUserId(int userId);

    @Select("SELECT user_id,username,image FROM user WHERE username=#{username} AND password=#{password}")
    User getUserByUser(User user);

    @Insert("INSERT INTO user VALUES (NULL,#{username},#{password},#{image})")
    int insertUser(User user);

    @Insert("UPDATE user SET username=#{username}, password=#{password}, image=#{image} WHERE user_id=#{userId}")
    int updateUser(User user);

    @Insert("DELETE FROM user WHERE user_id=#{userId}")
    int deleteUser(int userId);

}
