package com.demo.pokepb.service;

import com.demo.pokepb.dto.UserDto;
import com.demo.pokepb.entity.User;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);
}
