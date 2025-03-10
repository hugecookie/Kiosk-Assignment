package com.example.kiosk;

// ✅ 메뉴 아이템 클래스: 개별 메뉴 정보를 저장하는 역할
public class MenuItem {
    private String name;        // 메뉴 이름
    private double price;       // 가격
    private String description; // 메뉴 설명

    // ✅ 생성자: 객체를 생성할 때 필수 값 설정
    public MenuItem(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    // ✅ Getter 메서드: 각 필드 값을 읽을 수 있도록 제공
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
}
