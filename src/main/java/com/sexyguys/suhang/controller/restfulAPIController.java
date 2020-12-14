package com.sexyguys.suhang.controller;

import com.sexyguys.suhang.domain.User;
import com.sexyguys.suhang.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Random;

@Controller
public class restfulAPIController {
    private static final String LIST_USER = "userList";
    private final UserService userService;

    public restfulAPIController(UserService userService) {
        this.userService = userService;
    }

    public static String generateString(int length) {
        Random rand = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(3);
            switch (index) {
                case 0 -> sb.append((char) (rand.nextInt(26) + 97));
                case 1 -> sb.append((char) (rand.nextInt(26) + 65));
                case 2 -> sb.append(rand.nextInt(10));
            }
        }
        return sb.toString();
    }

    @PostMapping("/api/mvc/account/new")
    public String RegisterUser(User user, Model model) {
        User result = userService.register(user);

        //TODO: PLANTSTOEN - IMPLEMENT VIEW PAGE
        return "page";
    }

    @GetMapping("/api/account/list")
    public String ListUser(Model model) {
        ArrayList<User> userArrayList = userService.loadUsers();
        model.addAttribute(LIST_USER, userArrayList);
        return LIST_USER;
    }
}