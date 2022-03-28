package com.lrtech.consensus.user;

import com.lrtech.consensus.BasicController;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class LoginController extends BasicController {

    @GetMapping("/login")
    public String login() throws IOException {
        Authentication a = authentication();
        if (a instanceof AnonymousAuthenticationToken || !a.isAuthenticated()) {
            return "login";
        }
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register() throws IOException {
        Authentication a = authentication();
        if (a instanceof AnonymousAuthenticationToken || !a.isAuthenticated()) {
            return "login-register";
        }
        return "redirect:/";
    }
}