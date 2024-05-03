package com.backend.app.service.impl;

import com.backend.app.dto.UserDto;
import com.backend.app.model.User;
import com.backend.app.repository.UserRepository;
import com.backend.app.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserImplService implements IUserService {

   @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> allUser() {
       return (List)userRepository.findAll();
    }

    @Transactional
   @Override
    public User save(UserDto userDto) {
       User user= User.builder()
               .id(userDto.getId())
               .firstName(userDto.getFirstName())
               .lastName(userDto.getLastName())
               .phoneNumber(userDto.getPhoneNumber())
               .email(userDto.getEmail())
               .password(userDto.getPassword())
               .dni(userDto.getDni())
               .imgUrl(userDto.getImgUrl())
               .isGoogleAccount(userDto.isGoogleAccount())
               .isVerifiedEmail(userDto.isVerifiedEmail())
               .roleId(userDto.getRoleId())
               .build();
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }


    @Transactional
    @Override
    public void delete(User user) {

        userRepository.delete(user);

    }

    @Override
    public boolean existsById(Integer id) {
        return userRepository.existsById(id);
    }
}
