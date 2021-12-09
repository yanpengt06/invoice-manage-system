package com.proj.invoice.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class aUser {
  @TableId(type = IdType.AUTO)
  private long account;
  private String pwd;

  public long getAccount() {
    return account;
  }

  public void setAccount(long account) {
    this.account = account;
  }

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }


}
