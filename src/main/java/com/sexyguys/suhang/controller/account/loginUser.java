package com.sexyguys.suhang.controller.account;

import com.sexyguys.suhang.domain.User;
import com.sexyguys.suhang.domain.models.APIResult;
import com.sexyguys.suhang.service.UserService;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import static com.sexyguys.suhang.controller.AuthController.encryptPassword;


@RestController
public class loginUser {
    private final UserService userService;
    @Value("${auth.user.basic:}")
    private String userBasicAuth;

    public loginUser(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/api/account/new")
    @ResponseBody
    public APIResult getPost(@RequestHeader String Authorization, @ModelAttribute User params) {
        APIResult loginResult = new APIResult();
        if (!Authorization.equals(userBasicAuth.isBlank() ? Environment.getProperties().getProperty("AUTH_USER_BASIC", "Basic userAuthKeyBase64Encoded") : userBasicAuth)) {
            loginResult.statusCode = 403;
            loginResult.bodyMsg = "ERROR : Not Authorized";
            return loginResult;
        }

        if (!params.getEmail().isEmpty() || params.getPassword().isEmpty()) {
            loginResult.statusCode = 412;
            loginResult.bodyMsg = "ERROR : INVALID POST DATA";
        }

        User target = userService.findOneMember(params.getEmail());
        if (target == null) {
            loginResult.statusCode = 409;
            loginResult.bodyMsg = "ERROR : INVALID ID/PW";
        }

        if (!target.getPassword().equals(encryptPassword(params.getPassword(),target.getSalt()))) {
            loginResult.statusCode = 409;
            loginResult.bodyMsg = "ERROR : INVALID ID/PW";
        }
        loginResult.statusCode = 200;
        loginResult.bodyMsg = "SUCCEED : logined";
        loginResult.output = target;
        return loginResult;
    }
}
