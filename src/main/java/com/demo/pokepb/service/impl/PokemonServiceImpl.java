package com.demo.pokepb.service.impl;

import com.demo.pokepb.entity.Pokemon;
import com.demo.pokepb.mapper.PokemonMapper;
import com.demo.pokepb.service.PokemonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonServiceImpl implements PokemonService {
    private PokemonMapper pokemonMapper;

    public PokemonServiceImpl(PokemonMapper pokemonMapper) {
        this.pokemonMapper = pokemonMapper;
    }
    @Override
    public List<Pokemon> findAllPokemon() {
        return pokemonMapper.findAllPokemon();
    }

    @Override
    public Pokemon findPokemonById(int id) {
        return pokemonMapper.findIdPokemon(id);
    }

    @Override
    public int updatePokemonById(int id, String type1, String type2) {
        return pokemonMapper.updateIdPokemon(id, type1, type2);
    }
}
