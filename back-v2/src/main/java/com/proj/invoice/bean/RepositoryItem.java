package com.proj.invoice.bean;


import com.baomidou.mybatisplus.annotation.TableId;

public class RepositoryItem {
  @TableId
  private long itemId;
  private long repositoryId;
  private long goodId;
  private long num;


  public long getItemId() {
    return itemId;
  }

  public void setItemId(long itemId) {
    this.itemId = itemId;
  }


  public long getRepositoryId() {
    return repositoryId;
  }

  public void setRepositoryId(long repositoryId) {
    this.repositoryId = repositoryId;
  }


  public long getGoodId() {
    return goodId;
  }

  public void setGoodId(long goodId) {
    this.goodId = goodId;
  }


  public long getNum() {
    return num;
  }

  public void setNum(long num) {
    this.num = num;
  }

}
