package com.example.demo.controllers;

import org.junit.*;
import org.junit.Test;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import static org.junit.Assert.*;

public class LoginControllerTest {

  private LoginController loginController;

  @Before
  public void setup() {
    loginController = new LoginController();
  }

  @Test
  public void testLoginPage() {
    String result = loginController.loginPage();
    assertEquals("login", result);
  }

  @Test
  public void testSuccessfulLogin() {
    RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
    String result = loginController.login("tester", "test123", redirectAttributes);
    assertEquals("redirect:/mainscreen", result);
  }

  @Test
  public void testUnsuccessfulLogin() {
    RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
    String result = loginController.login("invalid", "invalid", redirectAttributes);
    assertEquals("redirect:/login", result);
  }

  @Test
  public void testLogout() {
    String result = loginController.logout();
    assertEquals("redirect:/login", result);
  }
}
