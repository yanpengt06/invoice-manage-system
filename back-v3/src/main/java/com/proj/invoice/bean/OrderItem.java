package com.proj.invoice.bean;


import com.baomidou.mybatisplus.annotation.TableId;

public class OrderItem {
  @TableId
  private long itemId;
  private long orderId;
  private long goodId;
  private long num;


  public long getItemId() {
    return itemId;
  }

  public void setItemId(long itemId) {
    this.itemId = itemId;
  }


  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
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
