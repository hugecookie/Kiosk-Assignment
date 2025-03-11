package com.example.kiosk;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cart {
    private List<MenuItem> cartItems;

    // ✅ 생성자
    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    // ✅ 장바구니에 메뉴 추가
    public void addItem(MenuItem item) {
        cartItems.add(item);
        System.out.println("🛒 " + item.getName() + "가(이) 장바구니에 추가되었습니다!");
    }

    // ✅ 총 주문 금액 계산
    public double calculateTotal() {
        double total = 0;
        for (MenuItem item : cartItems) {
            total += item.getPrice();
        }
        return total;
    }

    // ✅ 장바구니 출력 및 삭제 / 구매 확정 기능
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

        // ✅ 총 주문 금액 출력
        System.out.println("\n💰 총 주문 금액: W " + calculateTotal());
        System.out.println("\n1. 💳 구매 확정");
        System.out.println("2. 🗑️ 아이템 삭제");
        System.out.println("0. 🔙 뒤로가기");

        System.out.print("옵션을 선택하세요: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1:
                    checkout(); // ✅ 구매 확정 기능 호출
                    break;
                case 2:
                    removeItemPrompt(scanner); // ✅ 아이템 삭제 기능 호출
                    break;
                case 0:
                    return; // ✅ 뒤로가기
                default:
                    System.out.println("❌ 올바른 번호를 입력하세요.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ 잘못된 입력입니다. 숫자를 입력하세요.");
        }
    }

    // ✅ 구매 확정 (결제)
    public void checkout() {
        if (cartItems.isEmpty()) {
            System.out.println("❌ 장바구니가 비어 있어 결제가 불가능합니다.");
            return;
        }

        System.out.println("\n💳 결제가 완료되었습니다! 주문이 접수되었습니다.");
        System.out.println("📦 주문 내역:");

        for (MenuItem item : cartItems) {
            System.out.println(" - " + item.getName() + " | W " + item.getPrice());
        }

        System.out.println("\n💰 총 결제 금액: W " + calculateTotal());
        System.out.println("🛒 장바구니를 초기화합니다.");

        cartItems.clear(); // ✅ 결제 후 장바구니 초기화
    }

    // ✅ 아이템 삭제 기능 (삭제할 아이템 선택)
    private void removeItemPrompt(Scanner scanner) {
        System.out.print("삭제할 아이템 번호를 입력하세요 (0: 취소): ");

        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());

            if (choice == 0) {
                return; // ✅ 삭제 취소
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
