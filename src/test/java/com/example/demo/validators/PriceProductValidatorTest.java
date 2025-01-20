package com.example.demo.validators;

import javax.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.*;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

 class mPriceProductValidatorTest {

  private PriceProductValidator validator;
  private ProductService productService;

  @Before
  void setup() {
    validator = new PriceProductValidator();
    productService = mock(ProductService.class);
    validator.myContext = mock(ApplicationContext.class);
    when(validator.myContext.getBean(ProductService.class)).thenReturn(productService);
  }

  @Test
  void testValidProductPrice() {
    Product product = new Product();
    product.setId(1L); // Existing product
    product.setPrice(100); // Product price

    InhousePart part1 = new InhousePart();
    part1.setPrice(30); // Part 1 price
    OutsourcedPart part2 = new OutsourcedPart();
    part2.setPrice(40); // Part 2 price

    Set<Part> parts = new HashSet<>(Arrays.asList(part1, part2));

    product.setParts(parts);

    when(productService.findById(1)).thenReturn(product);

    assertTrue(validator.isValid(product, null)); // Should return true since product price is >= sum of parts' prices
  }


  @Test
  public void testNewProduct() {
    Product product = new Product();
    product.setId(0L); // New product
    product.setPrice(50); // Product price

    assertTrue(validator.isValid(product, null));
  }
}
