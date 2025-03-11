package com.example.kiosk;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ✅ 장바구니 기능을 담당하는 Cart 클래스
public class Cart {
    private List<MenuItem> cartItems; // 장바구니에 담긴 메뉴 목록

    // ✅ 생성자: 리스트 초기화
    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    // ✅ 장바구니에 메뉴 추가
    public void addItem(MenuItem item) {
        cartItems.add(item);
        System.out.println("🛒 " + item.getName() + "가(이) 장바구니에 추가되었습니다!");
    }

    // ✅ 장바구니 출력 및 삭제 기능
    public void displayCart(Scanner scanner) {
        if (cartItems.isEmpty()) {
            System.out.println("\n🛒 장바구니가 비어 있습니다.");
            return;
        }

        System.out.println("\n[ 장바구니 목록 ]");
        for (int i = 0; i < cartItems.size(); i++) {
            MenuItem item = cartItems.get(i);
            System.out.println((i + 1) + ". " + item.getName() + " | W " + item.getPrice());
        }
        System.out.println("0. 🔙 뒤로가기");

        System.out.print("삭제할 아이템 번호를 입력하세요 (0: 뒤로가기): ");

        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());

            if (choice == 0) {
                return; // ✅ 뒤로가기
            }

            removeItem(choice - 1); // ✅ 아이템 삭제 수행
        } catch (NumberFormatException e) {
            System.out.println("❌ 잘못된 입력입니다. 숫자를 입력하세요.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("❌ 올바른 번호를 입력하세요.");
        }
    }

    // ✅ 장바구니에서 아이템 삭제
    public void removeItem(int index) {
        MenuItem removedItem = cartItems.remove(index);
        System.out.println("🗑️ " + removedItem.getName() + "가(이) 장바구니에서 삭제되었습니다!");
    }
}
