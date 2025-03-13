package com.example.kiosk.service;

import com.example.kiosk.model.Menu;
import com.example.kiosk.model.MenuItem;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Kiosk {
    private List<Menu> menus; // ✅ 여러 개의 메뉴 카테고리를 관리하는 리스트
    private Scanner scanner; // ✅ 사용자 입력을 받기 위한 Scanner 객체
    private Cart cart; // ✅ 장바구니 목록을 관리할 카트

    // ✅ 생성자: Kiosk 객체를 생성하고 초기화
    public Kiosk(List<Menu> menus) {
        this.menus = menus;
        this.scanner = new Scanner(System.in);
        this.cart = new Cart(); // ✅ 장바구니 초기화
    }

    // ✅ 키오스크 프로그램 실행 메서드
    public void start() {
        System.out.println("✅ 키오스크 프로그램을 시작합니다.");
        run(); // ✅ 실행 루프 시작
    }

    // ✅ 메인 메뉴를 출력하고 사용자의 입력을 받아 실행하는 메서드
    public void run() {
        int choice;

        do {
            displayMainOptions(); // ✅ 메뉴 선택 / 장바구니 보기 선택 화면 출력

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        selectCategory(); // ✅ 메뉴 카테고리 선택 화면으로 이동
                        break;
                    case 2:
                        cart.displayCart(scanner); // ✅ 장바구니 보기 및 삭제 기능
                        break;
                    case 0:
                        System.out.println("🔚 키오스크를 종료합니다.");
                        break;
                    default:
                        System.out.println("❌ 올바른 옵션을 선택하세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ 잘못된 입력입니다. 숫자를 입력하세요.");
                choice = -1;
            }

        } while (choice != 0);
    }

    // ✅ 메인 옵션 출력 (메뉴 선택 / 장바구니 보기)
    private void displayMainOptions() {
        System.out.println("\n[ KIOSK MAIN MENU ]");
        System.out.println("1. 🍔 메뉴 선택");
        System.out.println("2. 🛒 장바구니 보기");
        System.out.println("0. 🔚 종료");
        System.out.println("번호를 입력하세요:");
    }

    // ✅ 카테고리 선택 화면
    private void selectCategory() {
        int categoryChoice;

        do {
            displayMainMenu();
            System.out.print("카테고리를 선택하세요 (0: 뒤로가기): ");

            try {
                categoryChoice = Integer.parseInt(scanner.nextLine().trim());

                if (categoryChoice >= 1 && categoryChoice <= menus.size()) {
                    Menu selectedMenu = menus.get(categoryChoice - 1);
                    handleMenuSelection(selectedMenu);
                } else if (categoryChoice != 0) {
                    System.out.println("❌ 올바른 카테고리 번호를 입력하세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ 잘못된 입력입니다. 숫자를 입력하세요.");
                categoryChoice = -1;
            }

        } while (categoryChoice != 0);
    }

    // ✅ 메인 메뉴(카테고리 목록)를 출력하는 메서드
    private void displayMainMenu() {
        System.out.println("\n[ MAIN MENU ]");
        IntStream.range(0, menus.size())
                .mapToObj(i -> (i + 1) + ". " + menus.get(i).getCategoryName()) // ✅ 카테고리 출력
                .forEach(System.out::println);
        System.out.println("0. 🔚 뒤로가기"); // ✅ 뒤로가기
    }

    // ✅ 특정 카테고리를 선택했을 때 실행되는 메서드
    private void handleMenuSelection(Menu menu) {
        int menuChoice; // ✅ 사용자의 입력값 저장 변수

        do {
            menu.displayMenuItems(); // ✅ 선택한 카테고리의 메뉴 출력
            System.out.print("메뉴를 선택하세요 (0: 뒤로가기): ");

            try {
                menuChoice = scanner.nextInt();
                scanner.nextLine(); // ✅ 입력 버퍼 비우기

                if (menuChoice >= 1 && menuChoice <= menu.getMenuItems().size()) {
                    menu.displaySelectedItem(menuChoice - 1); // ✅ 선택한 메뉴 출력

                    MenuItem selectedItem = menu.getMenuItems().get(menuChoice - 1);
                    handleOrderOptions(selectedItem); // ✅ 메뉴 선택 후 옵션 제공
                } else if (menuChoice != 0) {
                    System.out.println("❌ 올바른 메뉴 번호를 입력하세요.");
                }
            } catch (Exception e) { // ✅ 숫자가 아닌 값 입력 시 예외 처리
                System.out.println("❌ 잘못된 입력입니다. 숫자를 입력하세요.");
                scanner.nextLine(); // ✅ 입력 버퍼 비우기
                menuChoice = -1; // ✅ 유효하지 않은 값으로 설정하여 루프 유지
            }

        } while (menuChoice != 0); // ✅ 사용자가 0을 입력하면 뒤로가기
    }

    // ✅ 메뉴 선택 후 장바구니 담기 / 즉시 구매 / 취소 옵션 제공
    private void handleOrderOptions(MenuItem item) {
        System.out.println("\n[ " + item.getName() + " | W " + item.getPrice() + " ]");
        System.out.println("1. 🛒 장바구니에 담기");
        System.out.println("2. 💳 바로 구매");
        System.out.println("3. ❌ 취소");
        System.out.print("원하는 옵션을 선택하세요: ");

        try {
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    cart.addItem(item); // ✅ 장바구니에 추가
                    break;
                case 2:
                    completePurchase(item); // ✅ 즉시 구매 처리
                    break;
                case 3:
                    System.out.println("❌ 주문을 취소하였습니다.");
                    break;
                default:
                    System.out.println("❌ 올바른 옵션을 선택하세요.");
            }
        } catch (Exception e) {
            System.out.println("❌ 잘못된 입력입니다. 숫자를 입력하세요.");
            scanner.nextLine();
        }
    }

    // ✅ 즉시 구매 처리
    private void completePurchase(MenuItem item) {
        System.out.println("\n💳 결제가 완료되었습니다! 주문하신 " + item.getName() + "이(가) 준비됩니다.");
        System.out.println("🛒 키오스크를 종료합니다. 감사합니다!");
        System.exit(0); // ✅ 프로그램 종료
    }
}
