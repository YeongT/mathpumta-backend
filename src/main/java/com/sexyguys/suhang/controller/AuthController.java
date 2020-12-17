package com.sexyguys.suhang.controller;

import com.sexyguys.suhang.domain.User;
import com.sexyguys.suhang.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class AuthController {
    private static final String LIST_USER = "userList";
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("account/sign")
    public String RegisterUser(User user, Model model) {
        User result = userService.register(user);

        //TODO: PLANTSTOEN - IMPLEMENT VIEW PAGE
        return "page";
    }

    @GetMapping("/account/list")
    public String ListUser(Model model) {
        ArrayList<User> userArrayList = userService.loadUsers();
        model.addAttribute(LIST_USER, userArrayList);
        return LIST_USER;
    }
}

