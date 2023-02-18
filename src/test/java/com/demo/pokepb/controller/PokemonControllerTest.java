package com.demo.pokepb.controller;

import com.demo.pokepb.entity.Pokemon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@SpringBootTest
public class PokemonControllerTest {

    /**
     * ポケモン一覧表示のテスト
     */
    @Test
    @DisplayName("異常系：ログインしていないかつポケモン一覧を表示")
    void Not_logged_in_and_Displaying_a_list_of_pokemon(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pictorial/lists"))
                .andExpect(MockMvcResultMatchers.status().is(302));
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    @DisplayName("ログイン中かつポケモン一覧を表示")
    void Logged_in_and_displaying_a_list_of_pokemon(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pictorial/lists"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("pictorial/lists"));

    }

    /**
     * ポケモン詳細画面のテスト
     */
    @Test
    @DisplayName("異常系：ログインしていないかつポケモンid1の詳細を表示")
    void Not_logged_in_and_displaying_pokemon_id1_details(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pictorial/lists/1"))
                .andExpect(MockMvcResultMatchers.status().is(302));
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    @DisplayName("正常系：ログイン中かつポケモンid0の詳細を表示")
    void Show_details_of_logged_in_and_pokemon_id0(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pictorial/lists/0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("failsafe/failsafe"));
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    @DisplayName("正常系：ログイン中かつポケモンid1の詳細を表示")
    void Show_details_of_logged_in_and_pokemon_id1(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pictorial/lists/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("pictorial/details"));
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    @DisplayName("正常系：ログイン中かつポケモンid151の詳細を表示")
    void Show_details_of_logged_in_and_pokemon_id151(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pictorial/lists/151"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("pictorial/details"));
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    @DisplayName("異常系：ログイン中かつポケモンid152の詳細を表示")
    void Show_details_of_logged_in_and_pokemon_id152(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pictorial/lists/152"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("failsafe/failsafe"));
    }

    /**
     * ポケモン編集画面のテスト
     */
    @Test
    @DisplayName("異常系：ログインしていないかつポケモンid1の編集画面")
    void Edit_screen_with_no_login_and_pokemon_id1(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pictorial/lists/1/edit"))
                .andExpect(MockMvcResultMatchers.status().is(302));
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    @DisplayName("異常系：ログイン中かつポケモンid0の編集画面")
    void Edit_screen_while_logged_in_and_with_pokemon_id0(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pictorial/lists/0/edit"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("failsafe/failsafe"));
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    @DisplayName("正常系：ログイン中かつポケモンid1の編集画面")
    void Edit_screen_while_logged_in_and_with_pokemon_id1(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pictorial/lists/1/edit"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("pictorial/edit"));
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    @DisplayName("正常系：ログイン中かつポケモンid151の編集画面")
    void Edit_screen_while_logged_in_and_with_pokemon_id151(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pictorial/lists/151/edit"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("pictorial/edit"));
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    @DisplayName("異常系：ログイン中かつポケモンid152の編集画面")
    void Edit_screen_while_logged_in_and_with_pokemon_id152(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pictorial/lists/152/edit"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("failsafe/failsafe"));
    }

    /**
     * ポケモン編集内容を更新のテスト
     */
    @Test
    @Transactional
    @DisplayName("異常系：ログインしていないかつポケモンid1の編集内容を更新")
    void Update_edits_to_not_logged_in_and_pokemon_id1(@Autowired MockMvc mvc) throws Exception {
        Pokemon pokemon = new Pokemon(1, "フシギダネ", "タイプ1", "タイプ2");
        mvc.perform(MockMvcRequestBuilders.post("/pictorial/lists/update")
                        .flashAttr("pokemon", pokemon))
                .andExpect(MockMvcResultMatchers.status().is(302));
    }

    @Test
    @Transactional
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    @DisplayName("異常系：ログイン中かつポケモンid0の編集内容を更新")
    void Update_edits_while_logged_in_and_with_pokemon_id0(@Autowired MockMvc mvc) throws Exception {
        Pokemon pokemon = new Pokemon(0, "該当ポケモンなし", "ダミー1", "ダミー2");
        mvc.perform(MockMvcRequestBuilders.post("/pictorial/lists/update")
                        .flashAttr("pokemon", pokemon))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("failsafe/failsafe"));
    }

    @Test
    @Transactional
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    @DisplayName("正常系：ログイン中かつポケモンid1の編集内容を更新")
    void Update_edits_while_logged_in_and_with_pokemon_id1(@Autowired MockMvc mvc) throws Exception {
        Pokemon pokemon = new Pokemon(1, "フシギダネ", "タイプ1", "タイプ2");
        mvc.perform(MockMvcRequestBuilders.post("/pictorial/lists/update")
                    .flashAttr("pokemon", pokemon))
                .andExpect(MockMvcResultMatchers.status().is(302))
                .andExpect(view().name("redirect:/pictorial/lists/1"));
    }

    @Test
    @Transactional
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    @DisplayName("正常系：ログイン中かつポケモンid151の編集内容を更新")
    void Update_edits_while_logged_in_and_with_pokemon_id151(@Autowired MockMvc mvc) throws Exception {
        Pokemon pokemon = new Pokemon(151, "ミュウ", "タイプ151", "タイプ152");
        mvc.perform(MockMvcRequestBuilders.post("/pictorial/lists/update")
                        .flashAttr("pokemon", pokemon))
                .andExpect(MockMvcResultMatchers.status().is(302))
                .andExpect(view().name("redirect:/pictorial/lists/151"));
    }

    @Test
    @Transactional
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    @DisplayName("異常系：ログイン中かつポケモンid152の編集内容を更新")
    void Update_edits_while_logged_in_and_with_pokemon_id152(@Autowired MockMvc mvc) throws Exception {
        Pokemon pokemon = new Pokemon(152, "該当ポケモンなし", "ダミー1", "ダミー2");
        mvc.perform(MockMvcRequestBuilders.post("/pictorial/lists/update")
                        .flashAttr("pokemon", pokemon))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("failsafe/failsafe"));
    }

}
