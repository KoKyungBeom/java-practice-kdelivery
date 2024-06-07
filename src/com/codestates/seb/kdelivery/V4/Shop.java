package com.codestates.seb.kdelivery.V4;

import java.util.Arrays;
import java.util.HashMap;

public class Shop {
  private String shopName;
  private HashMap<String,Integer> menuItem;
  public Shop(String shopName){
    this.shopName = shopName;
    this.menuItem = new HashMap<>();
  }

  public HashMap<String, Integer> getMenuItem() {
    return menuItem;
  }

  public String getShopName() {
    return shopName;
  }

  public boolean addFood(String foodName, int price) {
    if(menuItem.containsKey(foodName)){
      System.out.println("[안내] 이미 존재하는 메뉴입니다");
      return false;
    }else{
      menuItem.put(foodName,price);
      return true;
    }
  }
}
