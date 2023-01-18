package com.demo.pokepb.controller;

import com.demo.pokepb.dto.UserDto;
import com.demo.pokepb.entity.User;
import com.demo.pokepb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        return "login/index";
    }

    /***
     * ログイン画面表示
     */
    @GetMapping("/login")
    public String loginForm() {
        return "login/login";
    }

    /***
     * 登録画面表示
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "login/register";
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
            return "login/register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }
}
