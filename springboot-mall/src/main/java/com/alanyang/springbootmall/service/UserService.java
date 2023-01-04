package com.alanyang.springbootmall.service;

import com.alanyang.springbootmall.dto.UserRegisterRequest;
import com.alanyang.springbootmall.model.User;

public interface UserService {
    User getUserById(Integer userId);
    Integer register(UserRegisterRequest userRegisterRequest);
}
