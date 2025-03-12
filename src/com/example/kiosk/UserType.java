package com.example.kiosk;

import java.util.function.Function;

public enum UserType {
    VETERAN("국가유공자", total -> total * 0.90), // 10% 할인
    SOLDIER("군인", total -> total * 0.95), // 5% 할인
    STUDENT("학생", total -> total * 0.97), // 3% 할인
    GENERAL("일반", total -> total); // 할인 없음

    private final String jobTitle;
    private final Function<Double, Double> discountFunction;

    UserType(String jobTitle, Function<Double, Double> discountFunction) {
        this.jobTitle = jobTitle;
        this.discountFunction = discountFunction;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    // ✅ 할인율 적용 메서드
    public double applyDiscount(double total) {
        return discountFunction.apply(total);
    }

    // ✅ 할인율 메뉴 출력 메서드 추가
    public static void displayDiscountMenu() {
        System.out.println("\n할인 정보를 입력해주세요.");
        for (UserType userType : UserType.values()) {
            double discountPercentage = 100 - userType.applyDiscount(100); // ✅ 100원을 기준으로 할인율 계산
            System.out.println((userType.ordinal() + 1) + ". " + userType.getJobTitle() + " : " + (int) discountPercentage + "%");
        }
        System.out.print("사용자 유형을 선택하세요: ");
    }
}

