package com.proj.invoice.bean;


import com.baomidou.mybatisplus.annotation.TableId;

public class Employee {
  @TableId
  private long id;
  private long account;
  private String position;
  private String name;
  private String phone;
  private long age;
  private String sex;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getAccount() {
    return account;
  }

  public void setAccount(long account) {
    this.account = account;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
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


  public long getAge() {
    return age;
  }

  public void setAge(long age) {
    this.age = age;
  }


  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

}
