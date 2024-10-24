package com.example.kinobackend.service;


import com.example.kinobackend.model.User;

import java.util.List;

public interface IUserService extends ICrudService<User,Long>{
    List<User> findByName(String name);
}
