package com.example.kiosk;

// ✅ 메인 시작 페이지 실행 화면
public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Kiosk kiosk = new Kiosk(menu.getMenuItems());
        kiosk.start(); // ✅ main()에서 직접 실행하지 않고, Kiosk에서 실행 흐름을 제어
    }
}
