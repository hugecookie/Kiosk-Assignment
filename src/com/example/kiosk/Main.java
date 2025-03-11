package com.example.kiosk;

import java.util.ArrayList;
import java.util.List;

// ✅ 프로그램 실행 클래스
public class Main {
    public static void main(String[] args) {
        // ✅ 카테고리별 메뉴 생성
        Menu burgers = new Menu("Burgers");
        burgers.addMenuItem(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        burgers.addMenuItem(new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));

        Menu drinks = new Menu("Drinks");
        drinks.addMenuItem(new MenuItem("Coke", 2.5, "시원한 코카콜라"));
        drinks.addMenuItem(new MenuItem("Lemonade", 3.0, "상큼한 레몬에이드"));

        List<Menu> menus = new ArrayList<>();
        menus.add(burgers);
        menus.add(drinks);

        // ✅ Kiosk 실행
        Kiosk kiosk = new Kiosk(menus);
        kiosk.start();
    }
}
