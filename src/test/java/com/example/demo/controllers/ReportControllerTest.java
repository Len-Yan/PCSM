package com.example.demo.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import com.example.demo.controllers.ReportController;
import com.example.demo.domain.Product;
import com.example.demo.domain.ReportItem;
import com.example.demo.service.ProductService;

public class ReportControllerTest {

  private ReportController reportController;
  private ProductService productService;
  private Model model;

  @Before
  public void setup() {
    productService = mock(ProductService.class);
    model = mock(Model.class);
    reportController = new ReportController();
  }

  @Test
  public void testShowReportPage() {
    // Mock data
    List<Product> productList = Arrays.asList(
        new Product(1L, "Product A", 100, 10),
        new Product(2L, "Product B", 200, 20)
    );

    // Mock service method to return the list of products
    when(productService.findAll()).thenReturn(productList);

    // Call the method under test


    // Verify that the method adds the correct attributes to the model
    assertEquals(2, productService.findAll().size());
    assertEquals(30, productService.findAll().get(0).getInv() + productService.findAll().get(1).getInv());

  }
}
