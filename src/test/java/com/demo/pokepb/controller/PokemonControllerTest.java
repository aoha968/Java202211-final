package com.demo.pokepb.controller;

import com.demo.pokepb.entity.Pokemon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@SpringBootTest
public class PokemonControllerTest {

    /**
     * ポケモン一覧表示のテスト
     */
    @Test
    void ログインしていないかつポケモン一覧を表示(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pictorial/lists"))
                .andExpect(MockMvcResultMatchers.status().is(302));
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void ログイン中かつポケモン一覧を表示(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pictorial/lists"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("pictorial/lists"));

    }

    /**
     * ポケモン詳細画面のテスト
     */
    @Test
    void ログインしていないかつポケモンid1の詳細を表示(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pictorial/lists/1"))
                .andExpect(MockMvcResultMatchers.status().is(302));
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void ログイン中かつポケモンid0の詳細を表示(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pictorial/lists/0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("failsafe/failsafe"));
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void ログイン中かつポケモンid1の詳細を表示(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pictorial/lists/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("pictorial/details"));
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void ログイン中かつポケモンid151の詳細を表示(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pictorial/lists/151"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("pictorial/details"));
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void ログイン中かつポケモンid152の詳細を表示(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pictorial/lists/152"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("failsafe/failsafe"));
    }

    /**
     * ポケモン編集画面のテスト
     */
    @Test
    void ログインしていないかつポケモンid1の編集画面(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pictorial/lists/1/edit"))
                .andExpect(MockMvcResultMatchers.status().is(302));
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void ログイン中かつポケモンid0の編集画面(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pictorial/lists/0/edit"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("failsafe/failsafe"));
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void ログイン中かつポケモンid1の編集画面(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pictorial/lists/1/edit"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("pictorial/edit"));
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void ログイン中かつポケモンid151の編集画面(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pictorial/lists/151/edit"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("pictorial/edit"));
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void ログイン中かつポケモンid152の編集画面(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pictorial/lists/152/edit"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("failsafe/failsafe"));
    }

    /**
     * ポケモン編集内容を更新のテスト
     */
    @Test
    void ログインしていないかつポケモンid1の編集内容を更新(@Autowired MockMvc mvc) throws Exception {
        Pokemon pokemon = new Pokemon(1, "フシギダネ", "タイプ1", "タイプ2");
        mvc.perform(MockMvcRequestBuilders.post("/pictorial/lists/update")
                        .flashAttr("pokemon", pokemon))
                .andExpect(MockMvcResultMatchers.status().is(302));
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void ログイン中かつポケモンid0の編集内容を更新(@Autowired MockMvc mvc) throws Exception {
        Pokemon pokemon = new Pokemon(0, "該当ポケモンなし", "ダミー1", "ダミー2");
        mvc.perform(MockMvcRequestBuilders.post("/pictorial/lists/update")
                        .flashAttr("pokemon", pokemon))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("failsafe/failsafe"));
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void ログイン中かつポケモンid1の編集内容を更新(@Autowired MockMvc mvc) throws Exception {
        Pokemon pokemon = new Pokemon(1, "フシギダネ", "タイプ1", "タイプ2");
        mvc.perform(MockMvcRequestBuilders.post("/pictorial/lists/update")
                    .flashAttr("pokemon", pokemon))
                .andExpect(MockMvcResultMatchers.status().is(302))
                .andExpect(view().name("redirect:/pictorial/lists/1"));
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void ログイン中かつポケモンid151の編集内容を更新(@Autowired MockMvc mvc) throws Exception {
        Pokemon pokemon = new Pokemon(151, "ミュウ", "タイプ151", "タイプ152");
        mvc.perform(MockMvcRequestBuilders.post("/pictorial/lists/update")
                        .flashAttr("pokemon", pokemon))
                .andExpect(MockMvcResultMatchers.status().is(302))
                .andExpect(view().name("redirect:/pictorial/lists/151"));
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void ログイン中かつポケモンid152の編集内容を更新(@Autowired MockMvc mvc) throws Exception {
        Pokemon pokemon = new Pokemon(152, "該当ポケモンなし", "ダミー1", "ダミー2");
        mvc.perform(MockMvcRequestBuilders.post("/pictorial/lists/update")
                        .flashAttr("pokemon", pokemon))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("failsafe/failsafe"));
    }

}
