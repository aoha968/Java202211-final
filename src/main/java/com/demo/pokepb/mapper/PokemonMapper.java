package com.demo.pokepb.mapper;

import com.demo.pokepb.entity.Pokemon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PokemonMapper {
    @Select("SELECT * FROM pokemon")
    List<Pokemon> findAllPokemon();
}
