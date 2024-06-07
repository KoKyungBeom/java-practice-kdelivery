package com.codestates.seb.kdelivery.V4;

public class Feedback {
  private String customerName;
  private String shopName;
  private String foodName;
  private int grade;
  public Feedback(String customerName, String shopName, String foodName, int grade){
    this.customerName = customerName;
    this.shopName = shopName;
    this.foodName = foodName;
    this.grade = grade;
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
  public String getStars() {
    String star = "";
    for(int i=1; i <= grade; i++){
      star += "*";
    }
    return star;
  }
  public void printInfo(){
    System.out.printf("%s [고객님]\n",this.customerName);
    System.out.println("-------------------------");
    System.out.printf("주문 매장 : %s\n",this.shopName);
    System.out.printf("주문 메뉴 : %s\n",this.foodName);
    System.out.println("평점 : " + getStars());
  }
}
