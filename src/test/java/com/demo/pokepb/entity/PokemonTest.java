package com.demo.pokepb.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
@SpringBootTest
public class PokemonTest {
    @Test
    @DisplayName("ポケモンクラスを作成してgetメソッド実行")
    void Create_pokemon_class_and_execute_get_method() {
        Pokemon pokemon = new Pokemon(1,"name", "type1", "type2");
        assertEquals(pokemon.getId(), 1);
        assertEquals(pokemon.getName(), "name");
        assertEquals(pokemon.getType1(), "type1");
        assertEquals(pokemon.getType2(), "type2");
    }
}
