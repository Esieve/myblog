package com.esieve.user.dao;

import com.esieve.user.bean.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by 77239 on 2017/3/28/0028.
 */
public interface UserDao {

    @Select("SELECT * FROM user")
    List<User> getUsers();

    @Select("SELECT user_id,username,image,background,biography FROM user WHERE user_id=#{userId}")
    User getUserByUserId(int userId);

    @Select("SELECT user_id,username,image,background,biography FROM user WHERE username=#{username} AND password=#{password}")
    User getUserByUser(User user);

    @Insert("INSERT INTO user VALUES (NULL,#{username},#{password},#{image},#{background},#{biography})")
    int insertUser(User user);

    @Update("UPDATE user SET username=#{username}, password=#{password}, image=#{image}, background=#{background}, biography=#{biography} WHERE user_id=#{userId}")
    int updateUser(User user);

    @Delete("DELETE FROM user WHERE user_id=#{userId} and user_id!=1")
    int deleteUser(int userId);

}
