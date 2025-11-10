package com.example.module1.service;


import com.example.module1.dto.UserDto;
import com.example.module1.entity.User;

public interface UserService {

    User createUser(UserDto userDto);

    User findUserById(int id);

    User findUserByEmail(String email);

    User deleteUser(int id);

    User updateUser(int id, UserDto userDto);

}
