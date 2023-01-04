package com.alanyang.springbootmall.service.impl;

import com.alanyang.springbootmall.dao.UserDao;
import com.alanyang.springbootmall.dto.UserRegisterRequest;
import com.alanyang.springbootmall.model.User;
import com.alanyang.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {
    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());
//check email register or not
        if (user != null) {
            log.warn("User already register: {}", userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Email already exists");
        }
// create account 
        return userDao.createUser(userRegisterRequest);
    }
}

