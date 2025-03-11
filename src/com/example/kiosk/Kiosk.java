package com.example.kiosk;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ✅ 키오스크 클래스: 사용자 입력을 받아 메뉴를 선택하는 역할
public class Kiosk {
    private List<Menu> menus; // ✅ 여러 개의 메뉴 카테고리를 관리
    private Scanner scanner; // ✅ 사용자 입력을 위한 Scanner

    // ✅ 생성자: `List<Menu>`를 받아 초기화
    public Kiosk(List<Menu> menus) {
        this.menus = menus;
        this.scanner = new Scanner(System.in);
    }

    // ✅ 프로그램 실행 메서드
    public void start() {
        System.out.println("✅ 키오스크 프로그램을 시작합니다.");
        run(); // 실행 루프 시작
    }

    // ✅ 키오스크 실행 메서드: 사용자 입력 처리
    public void run() {
        while (true) {
            displayMainMenu(); // 메인 메뉴 출력
            System.out.print("카테고리를 선택하세요 (0: 종료): ");

            int categoryChoice = scanner.nextInt();
            scanner.nextLine(); // 입력 버퍼 비우기

            if (categoryChoice == 0) {
                System.out.println("키오스크를 종료합니다.");
                break;
            }

            if (categoryChoice < 1 || categoryChoice > menus.size()) {
                System.out.println("❌ 올바른 카테고리 번호를 입력하세요.");
                continue;
            }

            // 선택한 카테고리의 메뉴 출력
            Menu selectedMenu = menus.get(categoryChoice - 1);
            handleMenuSelection(selectedMenu);
        }
    }

    // ✅ 메인 메뉴(카테고리) 출력 메서드
    private void displayMainMenu() {
        System.out.println("\n[ MAIN MENU ]");
        for (int i = 0; i < menus.size(); i++) {
            System.out.println((i + 1) + ". " + menus.get(i).getCategoryName());
        }
        System.out.println("0. 🔚 종료");
    }

    // ✅ 특정 메뉴 카테고리를 선택했을 때 처리하는 메서드
    private void handleMenuSelection(Menu menu) {
        while (true) {
            menu.displayMenuItems();
            System.out.print("메뉴를 선택하세요 (0: 뒤로가기): ");

            int menuChoice = scanner.nextInt();
            scanner.nextLine(); // 입력 버퍼 비우기

            if (menuChoice == 0) {
                break;
            }

            if (menuChoice < 1 || menuChoice > menu.getMenuItems().size()) {
                System.out.println("❌ 올바른 메뉴 번호를 입력하세요.");
                continue;
            }

            MenuItem selectedItem = menu.getMenuItem(menuChoice - 1);
            System.out.println("\n✅ 선택한 메뉴: " + selectedItem.getName() + " | W " + selectedItem.getPrice());
            System.out.println("   " + selectedItem.getDescription());
        }
    }
}
