package com.demo.pokepb.service;

import com.demo.pokepb.entity.Pokemon;
import com.demo.pokepb.mapper.PokemonMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest
public class PokemonServiceImpl {
    PokemonService pokemonService;
    PokemonMapper pokemonMapper;

    @BeforeEach
    void setUp() {
        // PokemonMapperのモックを作成
        pokemonMapper = mock(PokemonMapper.class);
        // PokemonMapperのモックを利用してPokemonServiceImplインスタンスを作成
        pokemonService = new com.demo.pokepb.service.impl.PokemonServiceImpl(pokemonMapper);
    }

    @Test
    void findAllPokemonメソッドで3件取得できる(){
        // PokemonMapperのfindAllPokemon()に仮の戻り値を設定
        when(pokemonMapper.findAllPokemon()).thenReturn(List.of(
                new Pokemon(1, "name1", "type1-1", "type1-2"),
                new Pokemon(2, "name2", "type2-1", "type2-2"),
                new Pokemon(3, "name3", "type3-1", "type3-2")
        ));
        // テスト対象のメソッドを実行
        List<Pokemon> pokemonList = pokemonService.findAllPokemon();
        // テスト対象の戻り値を検証
        assertEquals(3, pokemonList.size());
        // PokemonMapperのfindAllPokemon()が1回呼ばれていることをチェック
        verify(pokemonMapper, times(1)).findAllPokemon();
    }

    @Test
    void findPokemonByIdメソッドで1件取得できる(){
        // PokemonMapperのfindPokemonById()に仮の戻り値を設定
        when(pokemonMapper.findIdPokemon(1)).thenReturn(
                new Pokemon(1, "name1", "type1-1", "type1-2")
        );
        // テスト対象のメソッドを実行
        Pokemon pokemon = pokemonService.findPokemonById(1);
        // テスト対象の戻り値を検証
        assertEquals(1, pokemon.getId());
        assertEquals("name1", pokemon.getName());
        assertEquals("type1-1", pokemon.getType1());
        assertEquals("type1-2", pokemon.getType2());
        // PokemonMapperのfindIdPokemon()が1回呼ばれていることをチェック
        verify(pokemonMapper, times(1)).findIdPokemon(1);
    }

    /**
     *
    @Test
    void updatePokemonByIdメソッドで更新できる(){
    }
     */
}
