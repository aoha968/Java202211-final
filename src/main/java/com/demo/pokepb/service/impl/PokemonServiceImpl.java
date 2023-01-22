package com.demo.pokepb.service.impl;

import com.demo.pokepb.entity.Pokemon;
import com.demo.pokepb.exception.MyException;
import com.demo.pokepb.mapper.PokemonMapper;
import com.demo.pokepb.service.PokemonService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class PokemonServiceImpl implements PokemonService {
    private PokemonMapper pokemonMapper;

    public PokemonServiceImpl(PokemonMapper pokemonMapper) {
        this.pokemonMapper = pokemonMapper;
    }
    @Override
    public List<Pokemon> findAllPokemon() {
        return pokemonMapper.findAllPokemon();
    }

    @Override
    public String findIdPokemon(int id, String request, Model model) {
        try {
            if (id == 0 || id >= 152) {
                throw new MyException("想定外のidが指定されました");
            } else {
                /* 初代ポケモンは151匹。増えることはない。 */
                model.addAttribute(request, pokemonMapper.findIdPokemon(id));
                return "pictorial/" + request;
            }
        } catch(MyException e) {
            return "failsafe/failsafe";
        }
    }

    @Override
    public String updateIdPokemon(int id, String type1, String type2) {
        int retVal = 0;
        try {
            retVal = pokemonMapper.updateIdPokemon(id, type1, type2);
            if(retVal != 1) {
                throw new MyException("更新処理失敗");
            } else {
                return "redirect:/pictorial/lists/" + id;
            }
        } catch(MyException e) {
            return "failsafe/failsafe";
        }
    };
}
