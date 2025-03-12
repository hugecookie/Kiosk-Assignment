package com.example.kiosk;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        return cartItems.stream().mapToDouble(MenuItem::getPrice).sum();
    }

    // ✅ 할인율을 적용한 최종 금액 계산
    public double calculateDiscountedTotal(UserType userType) {
        return userType.applyDiscount(calculateTotal()); // ✅ UserType에서 직접 계산
    }

    // ✅ 장바구니 출력 및 삭제 / 구매 확정 기능
    public void displayCart(Scanner scanner) {
        if (cartItems.isEmpty()) {
            System.out.println("\n🛒 장바구니가 비어 있습니다.");
            return;
        }

        System.out.println("\n[ 장바구니 목록 ]");
        cartItems.forEach(item -> System.out.println("- " + item.getName() + " | W " + item.getPrice()));

        System.out.println("\n💰 총 주문 금액: W " + calculateTotal());
        System.out.println("\n1. 💳 구매 확정");
        System.out.println("2. 🗑️ 아이템 삭제");
        System.out.println("0. 🔙 뒤로가기");

        System.out.print("옵션을 선택하세요: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1:
                    checkout(scanner); // ✅ 구매 확정 기능 호출 (할인 적용)
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

    // ✅ 사용자 유형 선택 후 할인 적용
    private UserType selectUserType(Scanner scanner) {

        // ✅ 할인율 메뉴 출력
        UserType.displayDiscountMenu();

        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            return (choice >= 1 && choice <= UserType.values().length) ? UserType.values()[choice - 1] : UserType.GENERAL;
        } catch (NumberFormatException e) {
            System.out.println("❌ 잘못된 입력입니다. 기본값(일반 고객)으로 설정합니다.");
            return UserType.GENERAL;
        }
    }

    // ✅ 구매 확정 (할인 적용)
    public void checkout(Scanner scanner) {
        if (cartItems.isEmpty()) {
            System.out.println("❌ 장바구니가 비어 있어 결제가 불가능합니다.");
            return;
        }

        UserType userType = selectUserType(scanner); // ✅ 사용자 유형 선택
        double finalAmount = calculateDiscountedTotal(userType); // ✅ 할인 적용된 최종 금액 계산

        System.out.println("\n💳 결제가 완료되었습니다! 주문이 접수되었습니다.");
        System.out.println("📦 주문 내역:");
        cartItems.forEach(item -> System.out.println("- " + item.getName() + " | W " + item.getPrice()));

        System.out.println("\n💰 총 결제 금액 (할인 적용): W " + finalAmount);
        System.out.println("🛒 장바구니를 초기화합니다.");

        cartItems.clear(); // ✅ 결제 후 장바구니 초기화
    }

    // ✅ 특정 메뉴 삭제 기능 (stream().filter() 사용)
    public void removeItemByName(String itemName) {
        long initialSize = cartItems.size();

        cartItems = cartItems.stream()
                .filter(item -> !item.getName().equalsIgnoreCase(itemName)) // ✅ 해당 이름이 아닌 아이템만 유지
                .collect(Collectors.toList()); // ✅ 새로운 리스트로 변환

        if (cartItems.size() < initialSize) {
            System.out.println("🗑️ " + itemName + "가(이) 장바구니에서 삭제되었습니다!");
        } else {
            System.out.println("❌ 해당 메뉴가 장바구니에 없습니다.");
        }
    }

    // ✅ 장바구니에서 아이템 삭제 요청 처리
    private void removeItemPrompt(Scanner scanner) {
        System.out.print("삭제할 메뉴를 입력하세요 (0: 취소): ");

        try {
            String itemName = scanner.nextLine().trim();

            if ("0".equals(itemName)) {
                return; // ✅ 취소 가능
            }

            removeItemByName(itemName);
        } catch (Exception e) {
            System.out.println("❌ 삭제 중 오류가 발생했습니다.");
        }
    }
}
