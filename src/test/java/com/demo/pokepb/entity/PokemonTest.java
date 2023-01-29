package com.demo.pokepb.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
@SpringBootTest
public class PokemonTest {
    @Test
    void ポケモンクラスを作成してgetメソッド実行() {
        Pokemon pokemon = new Pokemon(1,"name", "type1", "type2");
        assertEquals(pokemon.getId(), 1);
        assertEquals(pokemon.getName(), "name");
        assertEquals(pokemon.getType1(), "type1");
        assertEquals(pokemon.getType2(), "type2");
    }
}
