package com.example.kiosk.model;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public enum UserType {
    VETERAN("국가유공자", total -> total * 0.90), // 10% 할인
    SOLDIER("군인", total -> total * 0.95), // 5% 할인
    STUDENT("학생", total -> total * 0.97), // 3% 할인
    GENERAL("일반", total -> total), // 할인 없음
    KID("어린이", total -> total * 0.5);

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

    // ✅ 할인율 메뉴 출력 메서드
    public static void displayDiscountMenu() {
        System.out.println("\n할인 정보를 입력해주세요.");
        AtomicInteger index = new AtomicInteger(1); // ✅ 순번을 관리하기 위한 AtomicInteger 사용

        Arrays.stream(UserType.values())
                .map(userType -> index.getAndIncrement() + ". "
                        + userType.getJobTitle()
                        + " : " + (int) (100 - userType.applyDiscount(100)) + "%") // ✅ 할인율 계산 후 출력 문자열 생성
                .forEach(System.out::println);

        System.out.print("사용자 유형을 선택하세요: ");
    }
}

