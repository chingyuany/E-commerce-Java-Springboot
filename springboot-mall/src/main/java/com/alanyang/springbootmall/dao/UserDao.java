package com.alanyang.springbootmall.dao;

import com.alanyang.springbootmall.dto.UserRegisterRequest;
import com.alanyang.springbootmall.model.User;

public interface UserDao {
    User getUserById(Integer userId);
    Integer createUser(UserRegisterRequest userRegisterRequest);
}
