package com.example.kiosk;

// ✅ 사용자 유형을 정의하는 Enum (한글 이름 필드 추가)
public enum UserType {
    VETERAN("국가유공자", 0.10), // 10% 할인
    SOLDIER("군인", 0.05), // 5% 할인
    STUDENT("학생", 0.03), // 3% 할인
    GENERAL("일반", 0.00); // 할인 없음

    private final String jobTitle; // 한글 이름 필드
    private final double discountRate; // 할인율

    // ✅ 생성자
    UserType(String jobTitle, double discountRate) {
        this.jobTitle = jobTitle;
        this.discountRate = discountRate;
    }

    // ✅ 한글 이름 반환 메서드
    public String getJobTitle() {
        return jobTitle;
    }

    // ✅ 할인율 반환 메서드
    public double getDiscountRate() {
        return discountRate;
    }
}
