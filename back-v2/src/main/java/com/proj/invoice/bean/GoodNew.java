package com.proj.invoice.bean;

public class GoodNew{

    private long id;
    private String name;
    private String description;
    private double inputPrice;
    private double retailPrice;
    private double wholesalePrice;
    private long totalNum;

    public GoodNew(long id, String name, String description, double inputPrice, double retailPrice, double wholesalePrice, long totalNum) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.inputPrice = inputPrice;
        this.retailPrice = retailPrice;
        this.wholesalePrice = wholesalePrice;
        this.totalNum = totalNum;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getInputPrice() {
        return inputPrice;
    }

    public void setInputPrice(double inputPrice) {
        this.inputPrice = inputPrice;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public double getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(double wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }
}