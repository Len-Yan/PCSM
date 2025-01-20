package com.example.demo.domain;

import java.util.Date;

public class ReportItem {
  private long id;
  private String name;
  private int stock;
  int sold;
  private double revenue;
  Date lastupdate;

  public ReportItem(long id, String name, int stock, int sold, double revenue, Date lastupdate) {
    this.id = id;
    this.name = name;
    this.stock = stock;
    this.sold = sold;
    this.revenue = revenue;
    this.lastupdate = lastupdate;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public int getSold() {
    return sold;
  }

  public void setSold(int sold) {
    this.sold = sold;
  }

  public double getRevenue() {
    return revenue;
  }

  public void setRevenue(double revenue) {
    this.revenue = revenue;
  }

  public Date getLastupdate() {
    return lastupdate;
  }

  public void setLastupdate(Date lastupdate) {
    this.lastupdate = lastupdate;
  }
}
