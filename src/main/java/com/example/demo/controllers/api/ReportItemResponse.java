package com.example.demo.controllers.api;

import com.example.demo.domain.Product;

import java.util.Date;

public class ReportItemResponse {
    private final long id;
    private final String name;
    private final int stock;
    private final int sold;
    private final double revenue;
    private final Date lastUpdate;

    public ReportItemResponse(long id, String name, int stock, int sold, double revenue, Date lastUpdate) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.sold = sold;
        this.revenue = revenue;
        this.lastUpdate = lastUpdate;
    }

    public static ReportItemResponse fromProduct(Product product) {
        return new ReportItemResponse(
                product.getId(),
                product.getName(),
                product.getInv(),
                product.getSale(),
                product.getPrice() * product.getSale(),
                product.getLastUpdate()
        );
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public int getSold() {
        return sold;
    }

    public double getRevenue() {
        return revenue;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }
}
