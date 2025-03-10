package com.example.kiosk;

import java.util.ArrayList;
import java.util.List;

// ✅ 메뉴 관리 클래스: 여러 개의 `MenuItem`을 저장하고 관리
public class Menu {
    private List<MenuItem> menuItems; // ✅ 메뉴 리스트

    // ✅ 생성자: 빈 리스트로 초기화
    public Menu() {
        this.menuItems = new ArrayList<>();
        initializeMenu(); // ✅ 기본 메뉴 자동 추가
    }

    // ✅ 기본 메뉴 추가 메서드
    private void initializeMenu() {
        menuItems.add(new Burger("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new Burger("SmokeShack", 8.9, "베이컨과 체리 페퍼가 추가된 치즈버거"));
        menuItems.add(new Burger("Shroom Burger", 9.4, "포토벨로 버섯 패티와 치즈가 토핑된 버거"));
    }

    // ✅ 메뉴 출력 메서드
    public void displayMenu() {
        System.out.println("\n=== 📜 메뉴 목록 ===");
        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem item = menuItems.get(i);
            System.out.println((i + 1) + ". " + item.getName() + " - " + item.getPrice() + " USD");
            System.out.println("   " + item.getDescription());
        }
        System.out.println("\n0. 🔚 종료");
    }

    // ✅ 특정 인덱스의 메뉴 가져오기
    public MenuItem getMenuItem(int index) {
        if (index >= 0 && index < menuItems.size()) {
            return menuItems.get(index);
        }
        return null; // 존재하지 않는 경우 null 반환
    }

    // ✅ 선택한 메뉴를 출력하는 메서드
    public void displaySelectedItem(int index) {
        MenuItem selectedItem = getMenuItem(index);
        if (selectedItem != null) {
            System.out.println("✅ 선택한 메뉴: " + selectedItem.getName() + " - " + selectedItem.getPrice() + " USD");
            System.out.println("   " + selectedItem.getDescription());
        } else {
            System.out.println("❌ 잘못된 메뉴 번호입니다.");
        }
    }

    // ✅ 메뉴 개수를 반환하는 메서드
    public int getMenuSize() {
        return menuItems.size();
    }
}
