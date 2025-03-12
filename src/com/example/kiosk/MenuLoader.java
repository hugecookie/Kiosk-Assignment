package com.example.kiosk;

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
    private static final String MENU_FILE_PATH = "/com/example/kiosk/resources/menu.json"; // ✅ JSON 파일 경로

    public static List<Menu> loadMenus() {
        try (InputStream inputStream = MenuLoader.class.getResourceAsStream(MENU_FILE_PATH)) {
            if (inputStream == null) {
                throw new RuntimeException("❌ menu.json 파일을 찾을 수 없습니다! 경로를 확인하세요.");
            }
            Reader reader = new InputStreamReader(inputStream);
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            JsonArray menuArray = jsonObject.getAsJsonArray("menus");

            // ✅ Stream API를 활용하여 변환
            return StreamSupport.stream(menuArray.spliterator(), false)
                    .map(JsonElement::getAsJsonObject)
                    .map(menuObj -> new Menu(
                            menuObj.get("category").getAsString(),
                            StreamSupport.stream(menuObj.getAsJsonArray("items").spliterator(), false)
                                    .map(itemObj -> {
                                        JsonObject obj = itemObj.getAsJsonObject();
                                        return new MenuItem(
                                                obj.get("name").getAsString(),
                                                obj.get("price").getAsDouble(),
                                                obj.get("description").getAsString()
                                        );
                                    })
                                    .collect(Collectors.toList())
                    ))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            System.out.println("❌ 메뉴 데이터를 불러오는 중 오류 발생: " + e.getMessage());
            return List.of(); // 오류 발생 시 빈 리스트 반환
        }
    }
}
