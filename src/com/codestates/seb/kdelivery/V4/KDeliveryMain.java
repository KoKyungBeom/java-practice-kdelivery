package com.codestates.seb.kdelivery.V4;

import java.util.ArrayList;
import java.util.Scanner;

//음식점,주문,리뷰 모두 크기 제한이 없는 ArrayList로 선언
public class KDeliveryMain {
  private ArrayList<Shop> shops;
  private ArrayList<Order> orders;
  private ArrayList<Feedback> feedbacks;
  private Scanner sc = new Scanner(System.in);
  public KDeliveryMain(){
    this.shops = new ArrayList<>();
    this.orders = new ArrayList<>();
    this.feedbacks = new ArrayList<>();
  }
  //원하는 기능을 선택하는 메서드 등록된 음식점과 해당 음식점에 메뉴를 확인하는 기능 추가
  //입력받은 문자열이 숫자로 변환할때 문제가 없는지 확인하는 isValidNumber 메서드를 통해 true일 경우와 false일 경우를 나눠서 처리
  public int selectMainMenu() {
    int settingNumber = -1;
    String inputNumber;
    System.out.println("[치킨의 민족 프로그램 V4]\n" +
              "-------------------------\n" +
              "1) [사장님 용] 음식점 등록하기\n" +
              "2) [고객님과 사장님 용] 음식점 별점 조회하기\n" +
              "3) [고객님 용] 음식 주문하기\n" +
              "4) [고객님 용] 별점 등록하기\n" +
              "5) [아무나써]등록된 음식점 조회하기\n" +
              "6) [아무나써]해당 음식점의 메뉴 조회하기\n" +
              "7) 프로그램 종료하기\n" +
              "-------------------------\n" +
              "[시스템] 무엇을 도와드릴까요?");
    inputNumber = sc.nextLine();
    if(isValidNumber(inputNumber)){
      return Integer.parseInt(inputNumber);
    }else{
      System.out.println("[안내] 리스트에 있는 숫자를 입력해주세요");
      return settingNumber;
    }
  }
  //음식점을 추가하는 메서드
  //ArrayList인 shops를 순회하며 입력받은 shopName이 존재하는지 확인한뒤 있다면 해당 음식점에 메뉴를 추가하고 없다면 리스트에 음식점을 추가한다
  public void selectAddShopMenu() {
    System.out.println("[안내] 반갑습니다. 가맹주님!\n" +
            "[안내] 음식점 상호는 무엇인가요?");
    String shopName = sc.nextLine();
    System.out.println("[안내] 대표 메뉴 이름은 무엇인가요?");
    String foodName = sc.nextLine();
    System.out.println("[안내] 해당 메뉴 가격은 얼마인가요?");
    int price = Integer.parseInt(sc.nextLine());

    int currentIndex = getShopIndex(shops,shopName);

    if(currentIndex != -1){
      shops.get(currentIndex).addFood(foodName,price);
      System.out.printf("[안내] 이미 등록된 %s에 (메뉴 : %s 가격 : %d)이 추가되었습니다.", shopName, foodName, price);
    }else {
      Shop newShop = new Shop(shopName);
      shops.add(newShop);
      newShop.addFood(foodName, price);
      System.out.printf("[안내] %s에 (메뉴 : %s 가격 : %d)이 추가되었습니다.", shopName, foodName, price);
    }
  }
  //매개변수로 받는 shopName의 index를 구하는 메서드
  //ArrayList를 순회하며 shopName의 인덱스를 반환한다
  public int getShopIndex(ArrayList<Shop> arr,String shopName){
    int currentIndex = -1;
    for(int i=0; i < arr.size(); i++){
      if(arr.get(i).getShopName().equals(shopName)){
        currentIndex = i;
      }
    }
    return currentIndex;
  }
  //주문한 기록이 있는지 확인하는 메서드
  //주문자의 이름과 음식점이름 메뉴이름까지 확인하고 모두 다 존재하는 경우 ture를 반환
  public boolean isExistOrder (String customerName,String shopName,String foodName){
    for(int i=0; i < orders.size(); i++){
      if(orders.get(i).getCustomerName().equals(customerName)&&orders.get(i).getShopName().equals(shopName)&&orders.get(i).getFoodName().equals(foodName)){
        return true;
      }
    }
    return false;
  }
  //리뷰가 있는지 확인하고 리뷰를 출력하는 메서드
  //feedbacks 배열을 순회하며 null값일 경우 순회를 멈추며 값이 있다면 printInfo() 메서드 실행
  public void selectDashboardMenu(){
    if(feedbacks.isEmpty()){
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
  //ArrayList인 shops를 순회하며 입력받은 shopName이 존재하는지 확인한뒤 있다면 음식점에 메뉴가 있는지 확인 둘다 존재한다면 리스트에 주문을 추가한다
  public void selectOrderMenu() {
    System.out.println("[안내] 고객님! 메뉴 주문을 진행하겠습니다!\n" +
            "[안내] 주문자 이름을 알려주세요!");
    String customerName = sc.nextLine();
    System.out.println("[안내] 주문할 음식점 상호는 무엇인가요?");
    String shopName = sc.nextLine();
    System.out.println("[안내] 주문할 메뉴 이름을 알려주세요!");
    String foodName = sc.nextLine();

    int currentIndex = getShopIndex(shops, shopName);

    if (currentIndex == -1) {
      System.out.println("[안내] 시스템에 등록되지 않은 가게입니다.");
    } else if (!shops.get(currentIndex).getMenuItem().containsKey(foodName)) {
      System.out.println("[안내] 가게에 없는 메뉴 입니다.");
    } else {
      Order newOrder = new Order(customerName, shopName, foodName);
      orders.add(newOrder);
      System.out.printf("[안내] %s님!\n" +
              "[안내] %s 매장에 %s 주문이 완료되었습니다.", customerName, shopName, foodName);
    }
  }
  //리뷰를 등록하는 메서드
  //isExistOrder 메서드를 통해 입력받은 고객이름,음식점,메뉴이름을 확인한뒤 true라면 리스트에 리뷰를 추가한다
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

    if (!isExistOrder(customerName, shopName, foodName)) {
      System.out.println("[안내] 주문을 다시 확인해주세요");
    } else {
      Feedback newFeed = new Feedback(customerName, shopName, foodName, grade);
      feedbacks.add(newFeed);
      System.out.println("[안내] 리뷰 등록이 완료되었습니다.");
    }
  }
  //등록된 음식점을 전부 출력하는 메서드
  //shops를 순회하며 음식점 이름을 출력
  public void printAllShop(){
    for(int i=0; i < shops.size(); i++){
      System.out.printf("[안내] 등록된 음식점 : %s\n",shops.get(i).getShopName());
    }
  }
  //해당 음식점에 메뉴를 전부 출력하는 메서드
  //음식점의 이름을 받고 음식점이 존재한다면  해당 음식점 메뉴들인 키값을 순회하며 전부 출력
  public void printShopAllMenu(String shopName){
    int currentIndex = -1;
    currentIndex = getShopIndex(shops,shopName);
    if(currentIndex == -1){
      System.out.println("[안내] 시스템에 등록되지 않은 음식점입니다.");
    }
    System.out.printf("[안내] %s에 등록된 메뉴입니다.\n",shopName);
    for (String foodName : shops.get(currentIndex).getMenuItem().keySet()){
      System.out.printf("[안내] %s\n",foodName);
    }
  }
  //입력받은 문자열을 숫자로 변환할 수 있는지 확인하는 메서드
  //문자열을 문자 배열로 바꾼뒤 해당 문자들이 lookUpTable에 존재하는지 확인 -1이 나온다면 lookUpTable에 없는 문자열이 존재하는 경우
  public boolean isValidNumber(String str) {
    String lookUpTable = "1234567890";
    for(char c : str.toCharArray()){
      if(lookUpTable.indexOf(c) == -1){
        return false;
      }
    }
    return true;
  }
  public static void main(String[] args) {
    KDeliveryMain test = new KDeliveryMain();
    Scanner sc = new Scanner(System.in);
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
        case 5: {
          test.printAllShop();
          break;
        }
        case 6: {
          System.out.println("[안내] 음식점 이름을 입력해주세요");
          String shopName = sc.nextLine();
          test.printShopAllMenu(shopName);
          break;
        }
      }
    }while(num != 7);
    System.out.println("[안내] 이용해주셔서 감사합니다.");
  }
}
