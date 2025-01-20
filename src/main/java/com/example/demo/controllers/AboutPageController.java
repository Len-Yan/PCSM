package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.domain.InhousePart;

@Controller
public class AboutPageController {

  @Autowired
  private ApplicationContext context;

  @GetMapping("/aboutPage")
  public String showAboutPage(Model model) {
    model.addAttribute("aboutPage");

    return "aboutPage";
  }

}
