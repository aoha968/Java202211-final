package com.demo.pokepb.service;

import com.demo.pokepb.entity.Pokemon;
import org.springframework.ui.Model;

import java.util.List;

public interface PokemonService {
    List<Pokemon> findAllPokemon();

    String findIdPokemon(int id, String request, Model model);

    String updateIdPokemon(int id, String type1, String type2);
}
