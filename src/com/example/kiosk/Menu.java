package com.example.kiosk;

import java.util.ArrayList;
import java.util.List;

// ✅ 특정 카테고리에 속하는 메뉴들을 관리하는 클래스
public class Menu {
    private String categoryName; // 카테고리 이름 (ex. Burgers, Drinks, Desserts)
    private List<MenuItem> menuItems; // 해당 카테고리의 메뉴 리스트

    // ✅ 생성자: 카테고리 이름 설정 및 리스트 초기화
    public Menu(String categoryName) {
        this.categoryName = categoryName;
        this.menuItems = new ArrayList<>();
    }

    // ✅ 메뉴 아이템 추가 메서드
    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }

    // ✅ 메뉴 아이템 getter 메서드
    public List<MenuItem> getMenuItems() {
        return menuItems;
    }


    // ✅ 카테고리 이름 반환 메서드
    public String getCategoryName() {
        return categoryName;
    }

    // ✅ 카테고리 내 메뉴 출력 메서드
    public void displayMenuItems() {
        System.out.println("\n[ " + categoryName.toUpperCase() + " MENU ]");
        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem item = menuItems.get(i);
            System.out.println((i + 1) + ". " + item.getName() + " | W " + item.getPrice());
            System.out.println("   " + item.getDescription());
        }
        System.out.println("0. 🔙 뒤로가기");
    }

    // ✅ 특정 인덱스의 메뉴 아이템 반환
    public MenuItem getMenuItem(int index) {
        if (index >= 0 && index < menuItems.size()) {
            return menuItems.get(index);
        }
        return null; // 잘못된 입력이면 null 반환
    }
}
