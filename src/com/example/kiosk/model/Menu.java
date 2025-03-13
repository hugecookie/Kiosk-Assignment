package com.example.kiosk.model;

import java.util.List;
import java.util.stream.IntStream;

// ✅ 특정 카테고리에 속하는 메뉴들을 관리하는 클래스
public class Menu {
    private String categoryName; // 카테고리 이름 (ex. Burgers, Drinks, Desserts)
    private List<MenuItem> menuItems; // 해당 카테고리의 메뉴 리스트

    // ✅ 새로운 생성자 (List<MenuItem> 포함)
    public Menu(String categoryName, List<MenuItem> menuItems) {
        this.categoryName = categoryName;
        this.menuItems = menuItems;
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
        IntStream.range(0, menuItems.size())
                .mapToObj(i -> (i + 1) + ". " + menuItems.get(i).getName() + " | W " + menuItems.get(i).getPrice() +
                        "\n   " + menuItems.get(i).getDescription())
                .forEach(System.out::println);
        System.out.println("0. 🔙 뒤로가기");
    }

    // ✅ 선택한 메뉴 출력 메서드
    public void displaySelectedItem(int index) {
        if (index >= 0 && index < menuItems.size()) {
            MenuItem selectedItem = menuItems.get(index);
            System.out.println("\n✅ 선택한 메뉴: " + selectedItem.getName() + " | W " + selectedItem.getPrice());
            System.out.println("   " + selectedItem.getDescription());
        } else {
            System.out.println("❌ 잘못된 메뉴 번호입니다.");
        }
    }
}
