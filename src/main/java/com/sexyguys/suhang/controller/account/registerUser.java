package com.sexyguys.suhang.controller.account;

import com.sexyguys.suhang.domain.models.APIResult;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class registerUser {
    @Value("${auth.user.basic:}")
    private String userBasicAuth;

    @PostMapping("/api/account/new")
    @ResponseBody
    public APIResult postBody(@RequestHeader String Authorization, @RequestBody String email, @RequestBody String password, @RequestBody String school) {
        APIResult registerResult = new APIResult(500, "ERROR: Unknown Error Occurred", null, null);
        if (!Authorization.equals(userBasicAuth.isBlank() ? Environment.getProperties().getProperty("AUTH_USER_BASIC","Basic userAuthKeyBase64Encoded") : userBasicAuth)) {
            registerResult.setStatusCode(403);
            registerResult.setBodyMsg("ERROR : Not Authorized");
        }
        return registerResult;
    }

}
