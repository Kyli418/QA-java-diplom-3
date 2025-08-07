package PageObjects;

import web.BrowserEnum;

import java.util.stream.Stream;

public class RegistrationTestData {
    public final String name;
    public final String email;
    public final String password;


    public RegistrationTestData(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    //Генерируем уникальный email
    private static String generateUniqueEmail() {
        long timestamp = System.currentTimeMillis();
        String email = "usermail" + timestamp + "@mail.com";
        return email;
    }

    //Получаем валидные данные пользователя
    public static RegistrationTestData getValidUser(){
        return new RegistrationTestData("Тестировщик", generateUniqueEmail(), "123456");
    }

    public static RegistrationTestData getNoValidUser(){
        return new RegistrationTestData("Тестировщик", generateUniqueEmail(), "12345");
    }
}
