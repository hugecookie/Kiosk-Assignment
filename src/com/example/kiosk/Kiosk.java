package com.example.kiosk;

import java.util.InputMismatchException;
import java.util.Scanner;

// ✅ 키오스크 클래스: 사용자 입력을 받아 메뉴를 선택하는 역할
public class Kiosk {
    private Menu menu;       // 메뉴 객체 (여러 개의 `MenuItem`을 관리)
    private Scanner scanner; // 사용자 입력을 위한 Scanner

    // ✅ 생성자: 메뉴 초기화 및 Scanner 생성
    public Kiosk() {
        this.menu = new Menu();
        this.scanner = new Scanner(System.in);
        initializeMenu(); // 기본 메뉴 설정
    }

    // ✅ 기본 메뉴 등록 (샘플 메뉴 추가)
    private void initializeMenu() {
        menu.addMenuItem(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menu.addMenuItem(new MenuItem("SmokeShack", 8.9, "베이컨과 체리 페퍼가 추가된 치즈버거"));
        menu.addMenuItem(new MenuItem("Shroom Burger", 9.4, "포토벨로 버섯 패티와 치즈가 토핑된 버거"));
    }

    // ✅ 키오스크 실행 메서드: 사용자 입력을 처리하는 루프
    public void run() {
        while (true) { // 프로그램이 종료될 때까지 반복
            try {
                menu.displayMenu(); // 현재 메뉴 출력
                System.out.print("메뉴 번호를 선택하세요: ");

                // 사용자 입력 받기
                int choice = scanner.nextInt();
                scanner.nextLine(); // 버퍼 비우기

                // ✅ 0 입력 시 프로그램 종료
                if (choice == 0) {
                    System.out.println("키오스크를 종료합니다.");
                    break;
                }

                // ✅ 입력값이 메뉴 개수를 벗어나면 예외 발생
                if (choice < 1 || choice > menu.getMenuSize()) {
                    throw new IndexOutOfBoundsException("✅ 올바른 메뉴 번호를 입력하세요.");
                }

                // ✅ 선택한 메뉴 가져오기 및 출력
                MenuItem selectedItem = menu.getMenuItem(choice - 1);
                System.out.println("✅ 선택한 메뉴: " + selectedItem.getName() + " - " + selectedItem.getPrice() + " USD");

            } catch (InputMismatchException e) {
                // ✅ 숫자가 아닌 입력 예외 처리
                System.out.println("✅ 숫자를 입력하세요.");
                scanner.nextLine(); // 입력 버퍼 비우기
            } catch (IndexOutOfBoundsException e) {
                // ✅ 메뉴 번호가 범위를 벗어났을 때 예외 처리
                System.out.println(e.getMessage());
            } catch (Exception e) {
                // ✅ 예상치 못한 오류 발생 시
                System.out.println("✅ 예상치 못한 오류가 발생했습니다.");
            }
        }
        scanner.close(); // 프로그램 종료 시 Scanner 닫기
    }
}
