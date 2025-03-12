package com.example.kiosk;

import java.util.ArrayList;
import java.util.List;

// ✅ 프로그램 실행 클래스
public class Main {
    public static void main(String[] args) {
        List<Menu> menus = MenuLoader.loadMenus(); // ✅ JSON에서 메뉴 로드

        // ✅ Kiosk 실행
        Kiosk kiosk = new Kiosk(menus);
        kiosk.start();
    }
}
