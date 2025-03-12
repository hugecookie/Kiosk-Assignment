package com.example.kiosk.model;

// ✅ 메뉴 아이템 클래스: 개별 메뉴 정보를 저장하는 역할
public class MenuItem {
    private String name;        // ✅ 메뉴 이름 (외부에서 직접 접근 불가능)
    private double price;       // ✅ 메뉴 가격 (외부에서 직접 접근 불가능)
    private String description; // ✅ 메뉴 설명 (외부에서 직접 접근 불가능)

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

    // ❌ Setter 미제공: 메뉴 정보는 한 번 설정되면 변경 불가능하도록 유지
}
