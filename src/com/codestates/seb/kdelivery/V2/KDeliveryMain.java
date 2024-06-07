package com.codestates.seb.kdelivery.V2;

import java.util.Objects;
import java.util.Scanner;

// 클래스를 정의 합니다.
public class KDeliveryMain {

  private static int SHOP_MAX = 5;
  private static int ORDER_MAX= 5;
  private static int FEEDBACK_MAX = ORDER_MAX;

  private Shop[] shops;
  private Order[] orders;
  private Feedback[] feedbacks;

  private Scanner sc = new Scanner(System.in);

  public KDeliveryMain(){
    initValues();
  }
  public void initValues() {
    this.shops = new Shop[SHOP_MAX];
    this.orders = new Order[ORDER_MAX];
    this.feedbacks = new Feedback[FEEDBACK_MAX];
  }
  public void close() {
    sc.close();
  }
  //원하는 기능을 입력받는 메서드
  public int selectMainMenu() {
    System.out.println("[치킨의 민족 프로그램 V2]\n" +
            "-------------------------\n" +
            "1) [사장님 용] 음식점 등록하기\n" +
            "2) [고객님과 사장님 용] 음식점 별점 조회하기\n" +
            "3) [고객님 용] 음식 주문하기\n" +
            "4) [고객님 용] 별점 등록하기\n" +
            "5) 프로그램 종료하기\n" +
            "-------------------------\n" +
            "[시스템] 무엇을 도와드릴까요?");
    return Integer.parseInt(sc.nextLine());
  }
  //음식점을 추가하는 메서드
  //shopIdx 변수를 선언하고 shops를 순회하며 값이 null인 인덱스가 있다면 해당 인덱스에 객체를 추가하고 shopIdx도 해당 인덱스로 재할당해준다
  //shops를 순회하며 null값인 인덱스가 없다면 shops는 비어있는 인덱스가 없으니 더이상 음식점을 추가 할 수 없다
  public void selectAddShopMenu() {
    System.out.println("[안내] 반갑습니다. 가맹주님!\n" +
            "[안내] 음식점 상호는 무엇인가요?");
    String shopName = sc.nextLine();
    System.out.println("[안내] 대표 메뉴 이름은 무엇인가요?");
    String foodName = sc.nextLine();
    System.out.println("[안내] 해당 메뉴 가격은 얼마인가요?");
    int price = Integer.parseInt(sc.nextLine());

    int shopIdx = -1;
    Shop newShop = new Shop(shopName);
    newShop.addFood(foodName,price);

    for(int i = 0; i < shops.length; i++) {
      if (shops[i] == null) {
        shopIdx = i;
        shops[i] = newShop;
        System.out.printf("[안내] %s에 음식(%s, %d) 추가되었습니다.\n", shopName, foodName, price);
        break;
        }
    }if(shopIdx == -1){
      System.out.println("[안내] 더이상 가게를 등록할 수 없습니다");
    }
  }
  //현재 shops,orders,feedbacks의 길이를 확인하는 메서드
  //배열들을 순회하며 null값이 없다면 -1을 반환하고 null값이 있다면 해당 index를 반환한다
  public int getArrayLength(Object[] arr){
    int currentIndex = -1;
    for(int i=0; i < arr.length; i++){
      if(arr[i] == null){
        return i;
      }
    }
    return currentIndex;
  }
  //매개변수로 받는 shopName의 index를 구하는 메서드
  //shops.length 만큼 순회한다면 null값이 존재할때 npe오류가 발생하여 shops에서 null값이 아닌 인덱스를 따로 구한뒤 그 인덱스까지만 순회하도록한다
  public int getShopIndex(Shop[] arr,int length,String shopName){
    int currentIndex = -1;
    for(int i=0; i < length; i++){
      if(arr[i].getShopName().equals(shopName)){
        currentIndex = i;
      }
    }
    return currentIndex;
  }
  //음식점에 존재하는 메뉴인지 확인하는 메서드
  //음식점의 인덱스를 구한뒤 그 인덱스에 foodNames 배열을 순회하며 foodName이 있다면 true 없다면 false를 반환
  public boolean isExistMenu (int index, String foodName){
    for(int i=0; i < shops[index].getFoodNames().length; i++){
      if(shops[index].getFoodNames()[i].equals(foodName)){
        return true;
      }
    }
    return false;
  }
  //주문한 기록이 있는지 확인하는 메서드
  //orders 배열을 순회하며 주문자의 이름과 같은 이름이 있다면 true 없다면 false를 반환
  public boolean isExistOrder (int length,String customerName){
    for(int i=0; i < length; i++){
      if(orders[i].getCustomerName().equals(customerName)){
        return true;
      }
    }
    return false;
  }
  //리뷰가 있는지 확인하고 리뷰를 출력하는 메서드
  //feedbacks 배열을 순회하며 null값일 경우 순회를 멈추며 값이 있다면 printInfo() 메서드 실행
  public void selectDashboardMenu(){
    if(feedbacks[0] == null){
      System.out.println("[안내] 현재 등록된 리뷰가 없습니다");
    }else {
      for(Feedback Feed : feedbacks){
        if(Feed == null){
          break;
        }
        Feed.printInfo();
      }
    }
  }
  //주문을 등록하는 메서드
  //먼저 shops의 길이를 확인한뒤 해당 음식점의 인덱스를 구하고 메뉴까지 확인한뒤 orders 배열에 객체를 추가하고 orderIdx 해당 인덱스로 재할당해준다
  //orders에 null값이 없다면 orders에 비어있는 인덱스가 없으니 더 이상 주문을 추가 할 수 없다
  public void selectOrderMenu(){
    System.out.println("[안내] 고객님! 메뉴 주문을 진행하겠습니다!\n" +
            "[안내] 주문자 이름을 알려주세요!");
    String customerName = sc.nextLine();
    System.out.println("[안내] 주문할 음식점 상호는 무엇인가요?");
    String shopName = sc.nextLine();
    System.out.println("[안내] 주문할 메뉴 이름을 알려주세요!");
    String foodName = sc.nextLine();

    int orderIdx = -1;

    int arrLength = getArrayLength(shops);

    int currentIndex = getShopIndex(shops,arrLength,shopName);

    if(currentIndex == -1){
      System.out.println("[안내] 시스템에 등록되지 않은 가게입니다.");
    } else if (!isExistMenu(currentIndex,foodName)){
      System.out.println("[안내] 가게에 없는 메뉴 입니다.");
    } else {
      Order newOrder = new Order(customerName,shopName,foodName);
      for (int i = 0; i < orders.length; i++) {
        if (orders[i] == null) {
          orderIdx = i;
          orders[i] = newOrder;
          System.out.printf("[안내] %s님!\n" +
                  "[안내] %s 매장에 %s 주문이 완료되었습니다.",customerName,shopName,foodName);
          break;
        }
      }if(orderIdx == -1){
        System.out.println("[안내] 더이상 주문할 수 없습니다.");
      }
    }
  }
  //리뷰를 등록하는 메서드
  //먼저 orders에 길이를 구한뒤 orders를 순회하며 주문한 고객 이름과 같은 이름이 있다면 feedbacks에 객체를 추가하고 feedIdx를 재할당해준다
  //feedbacks에 null값이 없다면 feedbacks에 비어있는 인덱스가 없으니 더 이상 리뷰를 추가 할 수 없다
  public void selectFeedbackMenu() {
    System.out.println("[안내] 고객님! 별점 등록을 진행합니다.\n" +
            "[안내] 주문자 이름은 무엇인가요?");
    String customerName = sc.nextLine();
    System.out.println("[안내] 음식점 상호는 무엇인가요?");
    String shopName = sc.nextLine();
    System.out.println("[안내] 주문하신 음식 이름은 무엇인가요?");
    String foodName = sc.nextLine();
    System.out.println("[안내] 음식 맛은 어떠셨나요? (1점 ~ 5점)");
    int grade = Integer.parseInt(sc.nextLine());

    int feedIdx = -1;

    int arrLength = getArrayLength(orders);

    if(!isExistOrder(arrLength,customerName)){
      System.out.println("[안내] 주문을 다시 확인해주세요");
    }else {
      Feedback newFeed = new Feedback(customerName, shopName, foodName, grade);
      for (int i = 0; i < feedbacks.length; i++) {
        if (feedbacks[i] == null) {
          feedIdx = i;
          feedbacks[i] = newFeed;
          System.out.println("[안내] 리뷰 등록이 완료되었습니다.");
          break;
        }
      }
      if (feedIdx == -1) {
        System.out.println("[안내] 더이상 리뷰를 등록할 수 없습니다.");
      }
    }
  }
  public static void main(String[] args) {
    KDeliveryMain test = new KDeliveryMain();
    int num;
    do {
      num = test.selectMainMenu();
      switch (num) {
        case 1: {
          test.selectAddShopMenu();
          break;
        }
        case 2: {
          test.selectDashboardMenu();
          break;
        }
        case 3: {
          test.selectOrderMenu();
          break;
        }
        case 4: {
          test.selectFeedbackMenu();
          break;
        }
      }
    }while(num != 5);
    test.close();
    System.out.println("[안내] 이용해주셔서 감사합니다.");
  }
}
//음식점을 등록한뒤 해당 음식점에 메뉴를 추가할 수 있어야하며 주문이 있는지 확인할 때도 고객이름외에 음식점 이름과 메뉴까지 확인해야함