package com.example.kiosk;

import java.util.List;

// 메뉴 아이템 클래스
class MenuItem {
    private String name;
    private double price;

    // 생성자
    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getter
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
