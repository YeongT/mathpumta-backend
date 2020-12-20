package com.sexyguys.suhang.controller;

import com.sexyguys.suhang.domain.User;
import com.sexyguys.suhang.domain.vo.DeleteAccountVO;
import com.sexyguys.suhang.domain.vo.ModifyAccountVO;
import com.sexyguys.suhang.domain.vo.RegisterVO;
import com.sexyguys.suhang.domain.vo.SearchVO;
import com.sexyguys.suhang.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;

@Controller
public class AuthController {
    private static final String ACCOUNT_LIST = "account-list";
    private static final String ACCOUNT_REGISTER = "account-register";
    private static final String ACCOUNT_MODIFY = "account-modify";
    private static final String ACCOUNT_DELETE = "account-delete";
    private static final String ACCOUNT_SEARCH = "account-search";
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    //SHA512방식으로 Base64 해시 해주는 함수 입니다.
    @SneakyThrows
    public static String encryptPassword(String password, String salt) {
        MessageDigest encoder = MessageDigest.getInstance("SHA-512");
        encoder.update(salt.getBytes());
        encoder.update(password.getBytes());
        return Base64.getEncoder().encodeToString(encoder.digest());
    }

    //Get으로 요청을 받으면 MVC로 폼을 띄운뒤
    @GetMapping("/account/register")
    public String getAccountNew(Model model) {
        model.addAttribute("registerVO", new RegisterVO());
        return ACCOUNT_REGISTER;
    }

    @GetMapping("/account/list")
    public String getAccountList(Model model) {
        ArrayList<User> userArrayList = userService.loadUsers();
        model.addAttribute("UserList", userArrayList);
        return ACCOUNT_LIST;
    }

    @GetMapping("/account/modify")
    public String getAccountModify(Model model) {
        model.addAttribute("modifyAccountVO", new ModifyAccountVO());
        return ACCOUNT_MODIFY;
    }

    @GetMapping("/account/search")
    public String getAccountSearch(Model model) {
        model.addAttribute("searchAccountVO", new SearchVO());
        return ACCOUNT_SEARCH;
    }

    @PostMapping("account/search.do")
    public String postAccountSearch(SearchVO searchVO, Model model) {
        User user = userService.findOneMember(searchVO.getEmail());
        model.addAttribute("UserList", user);
        return ACCOUNT_LIST;
    }

    //Post로 리다이렉트하여서 UserService의 함수를 통해 실행한후 조회 페이지로 이동시킨다.
    @PostMapping("/account/register.do")
    public String postAccountRegister(RegisterVO registerVO) {
        User user = new User();
        user.initialize(registerVO.getEmail(), registerVO.getPassword(), registerVO.getSchool());
        userService.register(user);
        return "redirect:/account/list";
    }


    @GetMapping("/account/delete")
    public String getAccountDelete(Model model) {
        model.addAttribute("deleteAccountVO", new DeleteAccountVO());
        return ACCOUNT_DELETE;
    }

    @PostMapping("/account/delete.do")
    public String postAccountDelete(DeleteAccountVO deleteAccountVO) {
        User target = new User();
        target.setEmail(deleteAccountVO.getEmail());
        target.setPassword(deleteAccountVO.getPassword());
        userService.deleteMember(target);
        return "redirect:/account/list";
    }

    //수정 페이지는 이전 객체, 새로운 객체로 두개를 넘겨서 이전의 객체를 덮어써 새로 저장하는 방식으로 구현하였다.
    @PostMapping("/account/modify.do")
    public String postAccountModify(ModifyAccountVO modifyAccountVO) {
        User previous = new User(), target = new User();
        previous.initialize(modifyAccountVO.getTarget_email(), modifyAccountVO.getTarget_password(), "");
        target.initialize(modifyAccountVO.getNew_email(), modifyAccountVO.getNew_password(), modifyAccountVO.getNew_school());
        userService.updateMember(previous, target);
        return "redirect:/account/list";
    }
}
