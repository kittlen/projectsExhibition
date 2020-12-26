package com.schoolProjects.service;



import com.schoolProjects.entity.User;

import java.util.List;

/**
 * @author kittlen
 * @version 1.0
 * @date 2020/8/26 16:33
 */
public interface UserService {


    //根据条件查询用户信息
    List<User> selectUserBy(User user);
    //登陆验证账号
    List<User> login(User user);
    //修改用户信息
    void updateUser(User user);
    //添加用户
    void addUser(User user);

}
