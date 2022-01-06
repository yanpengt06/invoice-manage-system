package com.proj.invoice.bean;


import com.baomidou.mybatisplus.annotation.TableId;

public class Good {
  @TableId
  private long id;
  private String name;
  private String description;
  private double wholesalePrice;
  private double inputPrice;
  private double retailPrice;


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

}
