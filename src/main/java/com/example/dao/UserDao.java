package com.example.dao;

import com.example.entity.User;

public interface UserDao {
    User getUsersByUsername(String username);
}