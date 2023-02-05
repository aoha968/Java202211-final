package com.demo.pokepb.service.impl;

import com.demo.pokepb.entity.Pokemon;
import com.demo.pokepb.mapper.PokemonMapper;
import com.demo.pokepb.service.PokemonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PokemonServiceImplTest {
    PokemonService pokemonService;
    PokemonMapper pokemonMapper;

    @BeforeEach
    void setUp() {
        // PokemonMapperのモックを作成
        pokemonMapper = mock(PokemonMapper.class);
        // PokemonMapperのモックを利用してPokemonServiceImplインスタンスを作成
        pokemonService = new PokemonServiceImpl(pokemonMapper);
    }

    @Test
    @DisplayName("findAllPokemonメソッドで3件取得できる")
    void Three_cases_can_be_retrieved_with_the_findAllPokemon_method(){
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
    @DisplayName("findPokemonByIdメソッドで1件取得できる")
    void One_case_can_be_retrieved_with_the_findPokemonById_method(){
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

    @Test
    @Transactional
    @DisplayName("updatePokemonByIdメソッドで更新できる")
    void It_can_be_updated_with_the_updatePokemonById_method(){
        // PokemonMapperのupdateIdPokemon()に仮の戻り値を設定
        when(pokemonMapper.updateIdPokemon(1, "更新1", "更新2")).thenReturn(1);

        // テスト対象のメソッドを実行
        int count = pokemonService.updatePokemonById(1, "更新1", "更新2");
        // テスト対象の戻り値を検証
        assertEquals(1, count);
        // PokemonMapperのupdateIdPokemon()が1回呼ばれていることをチェック
        verify(pokemonMapper, times(1)).updateIdPokemon(1, "更新1", "更新2");
    }
}
