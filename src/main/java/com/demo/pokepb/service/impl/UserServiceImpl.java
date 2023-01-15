package com.demo.pokepb.service.impl;

import com.demo.pokepb.dto.UserDto;
import com.demo.pokepb.entity.Pokemon;
import com.demo.pokepb.entity.Role;
import com.demo.pokepb.entity.User;
import com.demo.pokepb.mapper.PokemonMapper;
import com.demo.pokepb.repository.RoleRepository;
import com.demo.pokepb.repository.UserRepository;
import com.demo.pokepb.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private PokemonMapper pokemonMapper;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           PokemonMapper pokemonMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.pokemonMapper = pokemonMapper;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());

        //encrypt the password once we integrate spring security
        //user.setPassword(userDto.getPassword());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<Pokemon> findAllPokemon() {
        return pokemonMapper.findAllPokemon();
    }

    private UserDto convertEntityToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}
