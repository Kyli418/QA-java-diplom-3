package api.steps;

import api.client.Credentials;
import api.client.StellarBurgerClientService;
import api.client.UserData;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;



public class UserSteps {
    private StellarBurgerClientService client;
    private UserData user;
    private ValidatableResponse response;

    @Step("Создание клиента API")
    public StellarBurgerClientService createClient() {
        client = new StellarBurgerClientService();
        return client;
    }

    @Step("Создание тестовых данных пользователя для регистрации")
    public UserData createRegTestData() {
        long timestamp = System.currentTimeMillis();
        String email = "usermail" + timestamp + "@mail.com";
        String name = "username" + timestamp;
        user = new UserData(email, name, "123456");
        return user;
    }

    @Step("Получение accessToken")
    public String getAccessToken(ValidatableResponse response) {
        String jwtToken = response.extract().jsonPath().getString("accessToken");
        return jwtToken.replace("Bearer ", "").trim();
    }

    @Step("Отправка запроса на создание пользователя")
    public ValidatableResponse requestCreateUser(StellarBurgerClientService client, UserData user) {
        response = client.createUser(user);
        return response;
    }

    @Step("Проверка кода ответа при создании пользователя")
    public void checkCreateUserCode(ValidatableResponse response) {
        response.assertThat().statusCode(200);
    }

    @Step("Авторизация пользователя")
    public ValidatableResponse requestLoginUser(StellarBurgerClientService client, Credentials user) {
        response = client.loginUser(user);
        return response;
    }

    @Step("Проверка кода ответа при авторизации пользователя")
    public void checkLoginUserCode(ValidatableResponse response) {
        response.assertThat().statusCode(200);
    }
}
