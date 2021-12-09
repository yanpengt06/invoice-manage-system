package com.proj.invoice.bean;


import com.baomidou.mybatisplus.annotation.TableId;

public class Repository {
  @TableId
  private long repositoryId;
  private String classification;
  private String address;
  private String phone;


  public long getRepositoryId() {
    return repositoryId;
  }

  public void setRepositoryId(long repositoryId) {
    this.repositoryId = repositoryId;
  }


  public String getClassification() {
    return classification;
  }

  public void setClassification(String classification) {
    this.classification = classification;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

}
