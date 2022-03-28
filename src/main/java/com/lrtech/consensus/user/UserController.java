package com.lrtech.consensus.user;

import com.lrtech.consensus.ExpireSessionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController extends ExpireSessionController {

    public UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}