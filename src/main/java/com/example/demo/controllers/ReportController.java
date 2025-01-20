package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.domain.ReportItem;
import com.example.demo.service.PartService;
import com.example.demo.service.ProductService;

@Controller
public class ReportController {

  @Autowired
  private ApplicationContext context;

  @Autowired
  private ProductService productService;

  @GetMapping("/report")
  public String showReportPage(Model model) {
    //model.addAttribute("report");

    List<Product> productList = productService.findAll();
    List<ReportItem> reportItems = new ArrayList<>();

    for (Product product : productList) {
      ReportItem reportItem = new ReportItem(product.getId(), product.getName(), product.getInv(), product.getSale() ,product.getPrice() * product.getSale(), product.getLastUpdate());
      reportItems.add(reportItem);
    }

    model.addAttribute("items", reportItems);
    model.addAttribute("totalRevenue", new Object());


    return "report";
  }

/*
  @GetMapping("/partlist")
  public String getPartList(Model model) {
    List<Product> productList = productService.findAll();
    List<ReportItem> reportItems = new ArrayList<>();

    for (Product product : productList) {
      ReportItem reportItem = new ReportItem(product.getId(), product.getName(), product.getInv(), product.getPrice() * product.getSale(), product.getLastUpdate());
      reportItems.add(reportItem);
    }

    model.addAttribute("reprotItems", productList);

    return "report";
  }
*/

}
