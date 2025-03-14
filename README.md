---

# **Java Console Kiosk**

### **1. 프로젝트 개요**
이 프로젝트는 **Java 기반 콘솔 키오스크 시스템**으로,  
사용자가 **메뉴를 선택하고 장바구니에 추가한 후 결제까지 진행할 수 있도록 구현**되었습니다.  
JSON 파일(`menu.json`)을 이용하여 **메뉴 데이터를 동적으로 로드**하며,  
각 기능은 객체 지향 원칙(OOP)을 준수하여 **모듈화된 구조로 설계**되었습니다.  

---

## **2. 주요 기능**
✔ **JSON 기반 동적 메뉴 로드 기능**  
✔ **장바구니 담기, 삭제, 전체 조회 기능**  
✔ **총 주문 금액 및 할인율 적용 기능**  
✔ **결제 기능 (할인율이 적용된 최종 금액 출력)**  
✔ **객체 지향 설계 (SRP, OCP, 캡슐화 적용)**  
✔ **`Stream API` 활용하여 코드 최적화**  

---

## **3. 프로젝트 구조**
```plaintext
📂 java-kiosk
 ┣ 📂 src
 ┃ ┣ 📂 com.example.kiosk
 ┃ ┃ ┣ 📂 config
 ┃ ┃ ┃ ┣ 📜 MenuLoader.java         # JSON을 이용한 메뉴 데이터 로드
 ┃ ┃ ┣ 📂 model
 ┃ ┃ ┃ ┣ 📜 Menu.java               # 카테고리별 메뉴 관리
 ┃ ┃ ┃ ┣ 📜 MenuItem.java           # 개별 메뉴 아이템 관리
 ┃ ┃ ┃ ┣ 📜 UserType.java           # 사용자 유형 및 할인율 관리
 ┃ ┃ ┣ 📂 service
 ┃ ┃ ┃ ┣ 📜 Cart.java               # 장바구니 관리
 ┃ ┃ ┃ ┣ 📜 Kiosk.java              # 키오스크 기능 및 사용자 입력 처리
 ┃ ┃ ┣ 📂 main
 ┃ ┃ ┃ ┣ 📜 Main.java               # 프로그램 실행 진입점
 ┃ ┃ ┣ 📂 resources
 ┃ ┃ ┃ ┣ 📜 menu.json               # 메뉴 데이터 저장 파일
 ┗ 📜  README.md                    # 프로젝트 설명 파일
```
**객체 지향적 구조를 유지하면서 역할별로 클래스가 명확히 분리됨**  
**JSON 파일을 활용하여 메뉴를 동적으로 로드할 수 있도록 설계됨**  

---

## **4. 개발 환경**
| 환경 | 버전 |
|------|------|
| **JDK** | 17+ |
| **IDE** | IntelliJ IDEA, VS Code 등 |
| **빌드 시스템** | 없음 (CLI 기반 실행) |
| **OS** | macOS, Windows, Linux 지원 |

> **⚠ 주의:** JDK 11 이하 버전에서는 일부 최신 문법이 지원되지 않을 수 있습니다.

---

## **5. 실행 방법**
### **1️⃣ 프로젝트 클론 및 실행**
```sh
# Git 저장소 초기화 및 프로젝트 클론
git init
git clone https://github.com/hugecookie/java-kiosk.git
cd java-kiosk

# 이후 Main.java 실행하면 끝!
```

---

## **6. 사용 예시**
```sh
========= 키오스크 =========
1. 카테고리별 메뉴 조회
2. 장바구니 확인 및 관리
3. 결제 진행
🚪 'exit' 입력 시 프로그램 종료
👉 원하시는 기능의 번호를 입력하세요: 1
```

---

## **7. 트러블슈팅**
### **✅ 1. JSON 파일 로드 문제**
- **문제:** `menu.json` 파일을 찾을 수 없음 (`getResourceAsStream()`이 `null` 반환)
- **해결:** `getClass().getClassLoader().getResourceAsStream("menu.json")` 방식으로 변경

```java
InputStream inputStream = getClass().getClassLoader().getResourceAsStream("menu.json");
```

### **✅ 2. 할인율 계산 로직 개선**
- **문제:** `Cart.java` 내부에서 할인율을 직접 계산 → `UserType` Enum에서 계산하도록 변경
- **해결:** `applyDiscount()`를 활용하여 결합도를 줄이고 유지보수성을 향상

```java
public double calculateDiscountedTotal(UserType userType) {
    return userType.applyDiscount(calculateTotal());
}
```

---

## **8. 객체 지향 원칙(OOP) 적용**
이 프로젝트는 **객체 지향 프로그래밍(OOP) 원칙**을 준수하여 설계되었습니다.

| 원칙 | 설명 |
|------|--------------------------------|
| **추상화** | `Menu`, `MenuItem`을 활용한 메뉴 데이터 구조화 |
| **다형성** | `Stream API`를 활용하여 데이터 변환 최적화 |
| **OCP 원칙 준수** | JSON을 활용하여 동적으로 메뉴 추가 가능 |
| **SRP 원칙 준수** | `MenuLoader`와 `Cart`를 분리하여 단일 책임 유지 |

---

## **9. 라이선스**
이 프로젝트는 **MIT 라이선스**를 따릅니다. 자유롭게 사용하고 개선할 수 있습니다.

---

## **10. 문의**
- **이메일**: `phrpp5770@gmail.com`
- **GitHub Issues**: `https://github.com/hugecookie/java-kiosk/issues`
- **Pull Request(PR) 환영합니다!** 😃

---

### **11. 마무리**
✔ **객체 지향 원칙(OCP, 다형성, 캡슐화)을 적용하여 유지보수성을 높였습니다.**  
✔ **JSON을 활용하여 동적인 메뉴 관리 가능하도록 설계되었습니다.**  
✔ **Stream API를 적극 활용하여 가독성 및 성능을 최적화했습니다.**  

🔗 **Velog 기술 블로그에서 더 자세한 내용을 확인하세요!**  
👉 [프로젝트 과정 확인하기]([https://velog.io/@hugecookie/java-kiosk](https://velog.io/@hyang_do/series/Spring6%EA%B8%B0%ED%82%A4%EC%98%A4%EC%8A%A4%ED%81%AC%EA%B3%BC%EC%A0%9C))
