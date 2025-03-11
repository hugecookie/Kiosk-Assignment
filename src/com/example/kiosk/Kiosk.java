package com.example.kiosk;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    private List<Menu> menus; // ✅ 여러 개의 메뉴 카테고리를 관리하는 리스트
    private Scanner scanner; // ✅ 사용자 입력을 받기 위한 Scanner 객체

    // ✅ 생성자: Kiosk 객체 생성 시 메뉴 리스트를 받아 초기화
    public Kiosk(List<Menu> menus) {
        this.menus = menus;
        this.scanner = new Scanner(System.in);
    }

    // ✅ 프로그램 시작 메서드
    public void start() {
        System.out.println("✅ 키오스크 프로그램을 시작합니다.");
        run(); // ✅ 실행 루프 시작
    }

    // ✅ 키오스크 실행 메서드 (메인 메뉴 출력 및 카테고리 선택)
    public void run() {
        int categoryChoice; // ✅ 사용자의 입력값 저장 변수

        do {
            displayMainMenu(); // ✅ 메인 메뉴(카테고리 목록) 출력
            System.out.print("카테고리를 선택하세요 (0: 종료): ");

            categoryChoice = scanner.nextInt();
            scanner.nextLine(); // ✅ 입력 버퍼 비우기

            if (categoryChoice >= 1 && categoryChoice <= menus.size()) {
                Menu selectedMenu = menus.get(categoryChoice - 1); // ✅ 선택한 카테고리의 메뉴 불러오기
                handleMenuSelection(selectedMenu);
            } else if (categoryChoice != 0) {
                System.out.println("❌ 올바른 카테고리 번호를 입력하세요.");
            }

        } while (categoryChoice != 0); // ✅ 사용자가 0을 입력하면 종료

        System.out.println("키오스크를 종료합니다."); // ✅ 프로그램 종료 메시지 출력
    }

    // ✅ 메인 메뉴(카테고리) 출력 메서드
    private void displayMainMenu() {
        System.out.println("\n[ MAIN MENU ]");
        for (int i = 0; i < menus.size(); i++) {
            System.out.println((i + 1) + ". " + menus.get(i).getCategoryName()); // ✅ 카테고리 출력
        }
        System.out.println("0. 🔚 종료"); // ✅ 종료 옵션 추가
    }

    // ✅ 특정 메뉴 카테고리를 선택했을 때 실행되는 메서드
    private void handleMenuSelection(Menu menu) {
        int menuChoice; // ✅ 사용자의 입력값 저장 변수

        do {
            menu.displayMenuItems(); // ✅ 선택한 카테고리의 메뉴 출력
            System.out.print("메뉴를 선택하세요 (0: 뒤로가기): ");

            menuChoice = scanner.nextInt();
            scanner.nextLine(); // ✅ 입력 버퍼 비우기

            if (menuChoice >= 1 && menuChoice <= menu.getMenuItems().size()) {
                menu.displaySelectedItem(menuChoice - 1); // ✅ 선택한 메뉴 출력
            } else if (menuChoice != 0) {
                System.out.println("❌ 올바른 메뉴 번호를 입력하세요.");
            }

        } while (menuChoice != 0); // ✅ 사용자가 0을 입력하면 뒤로가기
    }
}
