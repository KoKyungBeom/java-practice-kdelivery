package com.codestates.seb.kdelivery.V3;

public class Feedback {
  private String customerName;
  private String shopName;
  private String foodName;
  private int grade;
  /**
   * @Feedback() : 정보를 저장합니다
   */
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
  /**
   * @getStars() : 사용자가 입력한 점수가 별점으로 전환
   */
  public String getStars() {
    String star = "";
    for(int i=1; i <= grade; i++){
      star += "*";
    }
    return star;
  }
  /**
   * @printInfo() : 출력
   */
  public void printInfo(){
    System.out.printf("%s [고객님]\n",this.customerName);
    System.out.println("-------------------------");
    System.out.printf("주문 매장 : %s\n",this.shopName);
    System.out.printf("주문 메뉴 : %s\n",this.foodName);
    System.out.println("평점 : \n" + getStars());
  }

}
