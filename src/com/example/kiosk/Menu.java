package com.example.kiosk;

import java.util.ArrayList;
import java.util.List;

// ✅ 메뉴 관리 클래스: 여러 개의 `MenuItem`을 저장하고 관리
public class Menu {
    private List<MenuItem> menuItems; // 메뉴 리스트

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

    // ✅ 메뉴 추가 메서드: 새로운 메뉴 아이템을 리스트에 추가
    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }

    // ✅ 메뉴 삭제 메서드: 이름을 기준으로 메뉴를 삭제
    public void removeMenuItem(String name) {
        menuItems.removeIf(item -> item.getName().equalsIgnoreCase(name));
    }

    // ✅ 메뉴 수정 메서드: 특정 메뉴의 정보를 변경
    public void updateMenuItem(String name, double newPrice, String newDescription) {
        for (MenuItem item : menuItems) {
            if (item.getName().equalsIgnoreCase(name)) {
                item.setPrice(newPrice);
                item.setDescription(newDescription);
                return;
            }
        }
        System.out.println("✅ 해당 메뉴를 찾을 수 없습니다.");
    }

    // ✅ 메뉴 출력 메서드: 등록된 메뉴 리스트를 화면에 표시
    public void displayMenu() {
        System.out.println("\n=== 메뉴 목록 ===");
        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem item = menuItems.get(i);
            System.out.println((i + 1) + ". " + item.getName() + " - " + item.getPrice() + " USD");
            System.out.println("   " + item.getDescription());
        }
        System.out.println("0. 종료"); // 프로그램 종료 옵션
    }

    // ✅ 특정 인덱스의 메뉴 가져오기 (없는 경우 `null` 반환)
    public MenuItem getMenuItem(int index) {
        if (index >= 0 && index < menuItems.size()) {
            return menuItems.get(index);
        }
        return null; // 존재하지 않는 경우 null 반환
    }

    // ✅ 메뉴 개수를 반환하는 메서드 (외부에서 접근 가능)
    public int getMenuSize() {
        return menuItems.size();
    }

    // ✅ 모든 메뉴 리스트 반환 (외부에서 메뉴 확인 가능)
    public List<MenuItem> getMenuItems() {
        return new ArrayList<>(menuItems); // ✅ 외부에서 리스트 변경되지 않도록 새로운 리스트 반환
    }
}
