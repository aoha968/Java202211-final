package com.demo.pokepb.service;

import com.demo.pokepb.entity.Pokemon;

import java.util.List;

public interface PokemonService {
    List<Pokemon> findAllPokemon();

    Pokemon findPokemonById(int id);

    int updatePokemonById(int id, String type1, String type2);
}
