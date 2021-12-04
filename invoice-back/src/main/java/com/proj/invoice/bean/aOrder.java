package com.proj.invoice.bean;


import com.baomidou.mybatisplus.annotation.TableId;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class aOrder {
  @TableId
  private long orderId;
  private String state;
  private LocalDateTime time;
  private long customerId;
  private double profit;

  public aOrder(long orderId, String state, LocalDateTime time, long customerId, double profit) {
    this.orderId = orderId;
    this.state = state;
    this.time = time;
    this.customerId = customerId;
    this.profit = profit;
  }

  public aOrder()
  {

  }


  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }


  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }


  public LocalDateTime getTime() {
    return time;
  }

  public void setTime(LocalDateTime time) {
    this.time = time;
  }


  public long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(long customerId) {
    this.customerId = customerId;
  }


  public double getProfit() {
    return profit;
  }

  public void setProfit(double profit) {
    this.profit = profit;
  }

}
