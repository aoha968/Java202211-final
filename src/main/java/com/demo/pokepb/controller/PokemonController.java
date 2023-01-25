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
    @RequestMapping(value = "/pictorial/lists", method = RequestMethod.GET)
    public String listRegisteredPokemon(Model model){
        model.addAttribute("lists", pokemonService.findAllPokemon());
        return "pictorial/lists";
    }

    /***
     * ログイン後のポケモン詳細画面
     */
    @RequestMapping(value = "/pictorial/lists/{id}", method = RequestMethod.GET)
    public String detailEditPokemon(@PathVariable("id") int id, Model model) {
        try {
            if (id == 0 || id >= 152) {
                throw new MyException("想定外のidが指定されました");
            } else {
                /* 初代ポケモンは151匹。増えることはない。 */
                model.addAttribute("details", pokemonService.findIdPokemon(id));
                return "pictorial/details";
            }
        } catch(MyException e) {
            return "failsafe/failsafe";
        }
    }

    /***
     * ログイン後のポケモン編集画面
     */
    @RequestMapping(value = "/pictorial/lists/{id}/edit", method = RequestMethod.GET)
    public String editPokemon(@PathVariable("id") int id, Model model) {
        try {
            if (id == 0 || id >= 152) {
                throw new MyException("想定外のidが指定されました");
            } else {
                /* 初代ポケモンは151匹。増えることはない。 */
                model.addAttribute("edit", pokemonService.findIdPokemon(id));
                return "pictorial/edit";
            }
        } catch(MyException e) {
            return "failsafe/failsafe";
        }
    }

    /***
     * ログイン後のポケモン編集の更新確定
     * 成功すれば詳細画面へ戻る
     * 失敗の場合は編集画面へ戻る
     */
    @RequestMapping(value = "/pictorial/lists/update", method = RequestMethod.POST)
    public String updatePokemon(@Validated @ModelAttribute Pokemon pokemon) {
        int retVal;
        try {
            retVal = pokemonService.updateIdPokemon(pokemon.getId(), pokemon.getType1(), pokemon.getType2());
            if(retVal != 1) {
                throw new MyException("更新処理失敗");
            } else {
                return "redirect:/pictorial/lists/" + pokemon.getId();
            }
        } catch(MyException e) {
            return "failsafe/failsafe";
        }
    }
}
