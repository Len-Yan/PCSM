package com.example.demo.controllers;

import com.example.demo.controllers.api.ProductResponse;
import com.example.demo.controllers.api.ReportItemResponse;
import com.example.demo.service.PartService;
import com.example.demo.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PublicSampleApiController {
    private final PartService partService;
    private final ProductService productService;

    public PublicSampleApiController(PartService partService, ProductService productService) {
        this.partService = partService;
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<ProductResponse> getProducts() {
        return productService.findAll().stream()
                .map(ProductResponse::fromProduct)
                .collect(Collectors.toList());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable int id) {
        try {
            return ResponseEntity.ok(ProductResponse.fromProduct(productService.findById(id)));
        } catch (RuntimeException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/reports/sales")
    public List<ReportItemResponse> getSalesReport() {
        return productService.findAll().stream()
                .map(ReportItemResponse::fromProduct)
                .collect(Collectors.toList());
    }
}
