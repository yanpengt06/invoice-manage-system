package com.proj.invoice.bean;


import com.baomidou.mybatisplus.annotation.TableId;

public class Repository {
  @TableId
  private long repositoryId;
  private String classification;
  private String address;
  private String phone;

  public Repository(long repositoryId, String classification, String address, String phone) {
    this.repositoryId = repositoryId;
    this.classification = classification;
    this.address = address;
    this.phone = phone;
  }

  public Repository()
  {

  }

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

  @Override
  public String toString() {
    return "Repository{" +
            "repositoryId=" + repositoryId +
            ", classification='" + classification + '\'' +
            ", address='" + address + '\'' +
            ", phone='" + phone + '\'' +
            '}';
  }
}
