package com.example.demo.controllers.api;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ProductResponse {
    private final long id;
    private final String name;
    private final double price;
    private final int inventory;
    private final int sales;
    private final Date lastUpdate;
    private final List<Long> partIds;

    public ProductResponse(long id, String name, double price, int inventory, int sales, Date lastUpdate,
                           List<Long> partIds) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inventory = inventory;
        this.sales = sales;
        this.lastUpdate = lastUpdate;
        this.partIds = partIds;
    }

    public static ProductResponse fromProduct(Product product) {
        List<Long> partIds = product.getParts().stream()
                .map(Part::getId)
                .collect(Collectors.toList());

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getInv(),
                product.getSale(),
                product.getLastUpdate(),
                partIds
        );
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getInventory() {
        return inventory;
    }

    public int getSales() {
        return sales;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public List<Long> getPartIds() {
        return partIds;
    }
}
