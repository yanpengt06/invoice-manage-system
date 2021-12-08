package com.proj.invoice.bean;


import com.baomidou.mybatisplus.annotation.TableId;

public class Customer {
  @TableId
  private long id;
  private String name;
  private String phone;
  private String address;
  private String classification;


  public Customer(long id, String name, String phone, String address, String classification) {
    this.id = id;
    this.name = name;
    this.phone = phone;
    this.address = address;
    this.classification = classification;
  }

  public Customer()
  {

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


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getClassification() {
    return classification;
  }

  public void setClassification(String classification) {
    this.classification = classification;
  }

  @Override
  public String toString() {
    return "Customer{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", phone='" + phone + '\'' +
            ", address='" + address + '\'' +
            ", classification='" + classification + '\'' +
            '}';
  }
}
