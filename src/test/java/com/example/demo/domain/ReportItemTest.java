package com.example.demo.domain;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class ReportItemTest {

  private ReportItem reportItem;

  @Before
  public void setUp() {
    // Initialize a ReportItem object with sample data
    reportItem = new ReportItem(1L, "Item A", 100, 50, 500.0, new Date());
  }

  @Test
  public void testGettersAndSetters() {
    // Test getters
    assertEquals(1L, reportItem.getId());
    assertEquals("Item A", reportItem.getName());
    assertEquals(100, reportItem.getStock());
    assertEquals(50, reportItem.getSold());
    assertEquals(500.0, reportItem.getRevenue(), 0.01);
    // Test setters
    reportItem.setId(2L);
    reportItem.setName("Item B");
    reportItem.setStock(200);
    reportItem.setSold(100);
    reportItem.setRevenue(1000.0);
    // Check if values are updated
    assertEquals(2L, reportItem.getId());
    assertEquals("Item B", reportItem.getName());
    assertEquals(200, reportItem.getStock());
    assertEquals(100, reportItem.getSold());
    assertEquals(1000.0, reportItem.getRevenue(), 0.01);
  }
}

