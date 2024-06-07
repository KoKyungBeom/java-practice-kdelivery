package com.codestates.seb.kdelivery.V4;

public class Order {
  private String customerName;
  private String shopName;
  private String foodName;
  public Order (String customerName,String shopName, String foodName){
    this.customerName = customerName;
    this.shopName = shopName;
    this.foodName = foodName;
  }
  public String getCustomerName() {
    return customerName;
  }
  public String getShopName() {
    return shopName;
  }
  public String getFoodName() {
    return foodName;
  }
}
