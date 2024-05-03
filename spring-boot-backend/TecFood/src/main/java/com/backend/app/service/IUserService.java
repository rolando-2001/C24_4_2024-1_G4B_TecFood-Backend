package com.backend.app.service;

import com.backend.app.dto.UserDto;
import com.backend.app.model.User;

import java.util.List;

public interface IUserService {

    List<User> allUser();
    User save(UserDto userDto);

    User findById(Integer id);



    void delete(User user);


    boolean existsById(Integer id);


   
}
