package com.sexyguys.suhang.controller.account;

import com.sexyguys.suhang.domain.User;
import com.sexyguys.suhang.domain.models.APIResult;
import com.sexyguys.suhang.service.UserService;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import static com.sexyguys.suhang.controller.AuthController.encryptPassword;
import static com.sexyguys.suhang.utility.StringUtility.generateString;

@RestController
public class registerUser {
    private final UserService userService;
    @Value("${auth.user.basic:}")
    private String userBasicAuth;

    public registerUser(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/api/account/new")
    @ResponseBody
    public APIResult getPost(@RequestHeader String Authorization, @ModelAttribute User params) {
        APIResult registerResult = new APIResult();
        if (!Authorization.equals(userBasicAuth.isBlank() ? Environment.getProperties().getProperty("AUTH_USER_BASIC", "Basic userAuthKeyBase64Encoded") : userBasicAuth)) {
            registerResult.statusCode = 403;
            registerResult.bodyMsg = "ERROR : Not Authorized";
            return registerResult;
        }

        if (params.getEmail().isEmpty() || params.getPassword().isEmpty() || params.getSchool().isEmpty()) {
            registerResult.statusCode = 412;
            registerResult.bodyMsg = "ERROR : INVALID POST DATA";
            return registerResult;
        }
        User result = userService.findOneMember(params.getEmail());
        if (result != null) {
            registerResult.statusCode = 409;
            registerResult.bodyMsg = "ERROR : DUPLICATE EMAIL";
            return registerResult;
        }
        params.setSalt(generateString((int) (Math.random() % 3 + 10)));
        params.setPassword(encryptPassword(params.getPassword(),params.getSalt()));

        result = userService.register(params);
        if (result == null) {
            registerResult.statusCode = 500;
            registerResult.bodyMsg = "ERROR : failed to save user on Database.";
            return registerResult;
        }
        registerResult.statusCode = 200;
        registerResult.bodyMsg = "SUCCEED: user saved on database.";
        registerResult.output = result;
        return registerResult;
    }


}
