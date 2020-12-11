package com.sexyguys.suhang.controller;

import com.sexyguys.suhang.domain.User;
import com.sexyguys.suhang.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class restfulAPIController {
    private static final String LIST_USER = "userList";
    private final UserService userService;

    public restfulAPIController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/account/new")
    public String NotAuthorized() {
        return "notAuthorized";
    }

    @GetMapping("/api/account/list")
    public String ListUser(Model model) {
        ArrayList<User> userArrayList = userService.loadUsers();
        model.addAttribute(LIST_USER, userArrayList);
        return LIST_USER;
    }

}