package com.example.service.impl;

import com.example.dao.UserDao;
import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao usersDao;
    @Override
    public User getUserByUsername(String username){
        return usersDao.getUsersByUsername(username);
    }
}