package com.example.dao.impl;

import com.example.dao.UserDao;
import com.example.entity.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UsersDaoImpl extends SqlSessionDaoSupport implements UserDao {
    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
    @Override
    public User getUsersByUsername(String username){
        //下面这里要修改
        return this.getSqlSession().selectOne("com.example.mapper.UsersMapper.selectUserByUsername", username);
    }
}