package com.example.springboot.backend.user.service;


import com.example.springboot.backend.user.domain.User;
import org.springframework.stereotype.Service;


@Service
public interface UpdateUserService
{
    boolean updateUser(User user);
}
