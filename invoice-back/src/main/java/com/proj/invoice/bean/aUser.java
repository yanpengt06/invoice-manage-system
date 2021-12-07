package com.proj.invoice.bean;


import com.baomidou.mybatisplus.annotation.TableId;

public class aUser {
  @TableId
  private String account;
  private String pwd;
  private String position;


  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }


  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }


  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  @Override
  public String toString() {
    return "aUser{" +
            "account='" + account + '\'' +
            ", pwd='" + pwd + '\'' +
            ", position='" + position + '\'' +
            '}';
  }
}
