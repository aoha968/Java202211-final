package com.demo.pokepb.controller;

import com.demo.pokepb.entity.Pokemon;
import com.demo.pokepb.exception.MyException;
import com.demo.pokepb.service.PokemonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class PokemonController {
    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    /***
     * ログイン後のポケモン一覧表示画面
     */
    @GetMapping("/pictorial/lists")
    public String listRegisteredPokemon(Model model){
        model.addAttribute("lists", pokemonService.findAllPokemon());
        return "pictorial/lists";
    }

    /***
     * ログイン後のポケモン詳細画面
     */
    @GetMapping("/pictorial/lists/{id}")
    public String detailEditPokemon(@PathVariable("id") int id, Model model) {
        return pokemonService.findIdPokemon(id, "details" ,model);
    }

    /***
     * ログイン後のポケモン編集画面
     */
    @GetMapping("/pictorial/lists/{id}/edit")
    public String editPokemon(@PathVariable("id") int id, Model model) {
        return pokemonService.findIdPokemon(id, "edit", model);
    }

    /***
     * ログイン後のポケモン編集の更新確定
     * 成功すれば詳細画面へ戻る
     * 失敗の場合は編集画面へ戻る
     */
    @RequestMapping(value = "/pictorial/lists/update", method = RequestMethod.POST)
    public String updatePokemon(@Validated @ModelAttribute Pokemon pokemon) {
        // ポケモンの更新
        return pokemonService.updateIdPokemon(pokemon.getId(), pokemon.getType1(), pokemon.getType2());
    }
}
