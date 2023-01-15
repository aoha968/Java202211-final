package com.demo.pokepb.service;

import com.demo.pokepb.dto.UserDto;
import com.demo.pokepb.entity.Pokemon;
import com.demo.pokepb.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<Pokemon> findAllPokemon();

    Pokemon findIdPokemon(int id);

    int updateIdPokemon(int id, String type1, String type2);
}
