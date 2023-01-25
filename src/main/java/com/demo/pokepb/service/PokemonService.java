package com.demo.pokepb.service;

import com.demo.pokepb.entity.Pokemon;

import java.util.List;

public interface PokemonService {
    List<Pokemon> findAllPokemon();

    Pokemon findIdPokemon(int id);

    int updateIdPokemon(int id, String type1, String type2);
}
