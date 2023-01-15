package com.demo.pokepb.mapper;

import com.demo.pokepb.entity.Pokemon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface PokemonMapper {
    @Select("SELECT * FROM pokemon")
    List<Pokemon> findAllPokemon();

    @Select("SELECT * FROM pokemon WHERE id=#{id}")
    Pokemon findIdPokemon(@Param("id") int id);

    @Update("UPDATE pokemon SET type1=#{type1}, type2=#{type2} WHERE id=#{id}")
    int updateIdPokemon(@Param("id") int id, @Param("type1") String type1, @Param("type2") String type2);
}
