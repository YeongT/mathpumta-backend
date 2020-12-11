package com.sexyguys.suhang.controller.account;

import com.sexyguys.suhang.domain.models.APIResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class registerUser {
    @Value("${userBasicAuth}")
    private String userBasicAuth;

    @PostMapping("/api/account/new")
    @ResponseBody
    public APIResult postBody(@RequestHeader String Authorization, @RequestBody String email, @RequestBody String password, @RequestBody String school) {
        APIResult registerResult = new APIResult(500, "ERROR: Unknown Error Occurred", null, null);
        if (!Authorization.equals(userBasicAuth.isEmpty() ? System.getenv("BASIC_AUTH_USER") : userBasicAuth)) {
            registerResult.setStatusCode(403);
            registerResult.setBodyMsg("ERROR : Not Authorized");
        }
        return registerResult;
    }


}
