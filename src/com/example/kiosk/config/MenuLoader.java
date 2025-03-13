package com.example.kiosk.config;

import com.example.kiosk.model.Menu;
import com.example.kiosk.model.MenuItem;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MenuLoader {
    // ✅ JSON 파일 경로 설정 (resource 폴더 내 위치해야 함)
    private static final String MENU_FILE_PATH = "/com/example/kiosk/resources/menu.json";

    /**
     * JSON 파일을 읽어 메뉴 리스트를 반환하는 메서드
     *
     * @return List<Menu> - JSON에서 로드된 메뉴 리스트
     */
    public static List<Menu> loadMenus() {
        try (InputStream inputStream = MenuLoader.class.getResourceAsStream(MENU_FILE_PATH)) {
            // ❌ 파일이 존재하지 않을 경우 예외 발생
            if (inputStream == null) {
                throw new RuntimeException("❌ menu.json 파일을 찾을 수 없습니다! 경로를 확인하세요.");
            }

            // ✅ JSON 데이터를 읽을 Reader 생성
            Reader reader = new InputStreamReader(inputStream);

            // ✅ Gson 라이브러리를 사용하여 JSON을 객체로 변환
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            // ✅ "menus" 키를 통해 JSON 배열 가져오기
            JsonArray menuArray = jsonObject.getAsJsonArray("menus");

            // ✅ Stream API를 사용하여 Json 파일을 List<Menu>로 변환
            return StreamSupport.stream(menuArray.spliterator(), false)
                    .map(JsonElement::getAsJsonObject) // JsonElement → JsonObject 변환
                    .map(menuObj -> new Menu(
                            menuObj.get("category").getAsString(), // 메뉴 카테고리 추출
                            StreamSupport.stream(menuObj.getAsJsonArray("items").spliterator(), false)
                                    .map(itemObj -> { // 메뉴 아이템을 MenuItem 객체로 변환
                                        JsonObject obj = itemObj.getAsJsonObject();
                                        return new MenuItem(
                                                obj.get("name").getAsString(),      // 아이템명
                                                obj.get("price").getAsDouble(),     // 가격
                                                obj.get("description").getAsString() // 설명
                                        );
                                    })
                                    .collect(Collectors.toList()) // 변환된 리스트 수집
                    ))
                    .collect(Collectors.toList()); // 최종 리스트 수집

        } catch (Exception e) {
            System.out.println("❌ 메뉴 데이터를 불러오는 중 오류 발생: " + e.getMessage());
            return List.of(); // 오류 발생 시 빈 리스트 반환
        }
    }
}
