package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private ApplicationContext context;

    public LoginController(){

    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // 处理

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
        @RequestParam("password") String password, RedirectAttributes redirectAttributes) {
        if( username.equals("tester") && password.equals("test123")) {
          return "redirect:/mainscreen";
        }
        else{
          redirectAttributes.addFlashAttribute("error", "Invalid username or password");
          return "redirect:/login";
        }
    }

    @GetMapping("/Logout")
    public String logout(){

      return  "redirect:/login";
    }


}
