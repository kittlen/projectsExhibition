package com.schoolProjects.service.impl;

import com.schoolProjects.entity.User;
import com.schoolProjects.mapper.UserDao;
import com.schoolProjects.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author kittlen
 * @version 1.0
 * @date 2020/8/26 16:33
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;



    @Override
    public List<User> selectUserBy(User user) {
        return userDao.selectUserBy(user);
    }

    @Override
    public List<User> login(User user) {
        return userDao.login(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }
}
