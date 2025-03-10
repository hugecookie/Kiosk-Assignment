package com.example.kiosk;

// ✅ 메인 실행 클래스
public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();  // ✅ `Menu` 객체 생성
        Kiosk kiosk = new Kiosk(menu);  // ✅ `Kiosk` 생성 시 `menu` 객체를 넘겨줌
        kiosk.start();  // ✅ 프로그램 실행
    }
}
