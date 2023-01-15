package com.demo.pokepb.controller;

import com.demo.pokepb.dto.UserDto;
import com.demo.pokepb.entity.Pokemon;
import com.demo.pokepb.service.UserService;
import jakarta.validation.Valid;
import com.demo.pokepb.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /***
     * トップページ表示
     */
    @GetMapping("/")
    public String home(){
        return "index";
    }

    /***
     * ログイン画面表示
     */
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    /***
     * 登録画面表示
     */
    @GetMapping("register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    /***
     * 登録画面からの登録処理
     */
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }

    /***
     * ログイン後のポケモン一覧表示画面
     */
    @GetMapping("/lists")
    public String listRegisteredPokemon(Model model){
        model.addAttribute("lists", userService.findAllPokemon());
        return "lists";
    }

    /***
     * ログイン後のポケモン詳細画面
     */
    @GetMapping("/lists/{id}")
    public String detailEditPokemon(@PathVariable("id") int id, Model model) {
        model.addAttribute("details", userService.findIdPokemon(id));
        return "details";
    }

    /***
     * ログイン後のポケモン編集画面
     */
    @GetMapping("/lists/{id}/edit")
    public String editPokemon(@PathVariable("id") int id, Model model) {
        model.addAttribute("edit", userService.findIdPokemon(id));
        return "edit";
    }

    /***
     * ログイン後のポケモン編集の更新確定
     * 成功すれば詳細画面へ戻る
     * 失敗の場合は編集画面へ戻る
     */
    @RequestMapping(value = "/lists/update", method = RequestMethod.POST)
    public String updatePokemon(@Validated @ModelAttribute Pokemon pokemon, Model model) {
        // ポケモンの更新
        int update = userService.updateIdPokemon(pokemon.getId(), pokemon.getType1(), pokemon.getType2());
        if(update == 1){
            /* 更新成功した場合はポケモン詳細画面に遷移 */
            return "redirect:/lists/" + pokemon.getId();
        }else{
            /* 更新失敗した場合は編集画面を再度表示させる */
            return "redirect:/lists/" + pokemon.getId() + "/edit";
        }
    }
}
