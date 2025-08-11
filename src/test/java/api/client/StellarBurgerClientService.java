package api.client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class StellarBurgerClientService {

    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    //регистрация
    private static final String CREATE_USER_URL_PATH = "/api/auth/register";
    //удаление пользователя
    private static final String DELETE_USER_URL_PATH = "/api/auth/user";
    //авторизация пользователя
    private static final String LOGIN_USER_URL_PATH = "/api/auth/login";

    private static final RequestSpecification REQUEST_SPECIFICATION_TEMPLATE =
            new RequestSpecBuilder()
                    .setBaseUri(BASE_URL)
                    .addHeader("Content-Type", "application/json")
                    .log(LogDetail.ALL)
                    .build();

    private static final ResponseSpecification RESPONSE_SPECIFICATION_TEMPLATE =
            new ResponseSpecBuilder()
                    .log(LogDetail.ALL)
                    .build();

    public ValidatableResponse createUser(UserData user) {
        return
                given()
                        .spec(REQUEST_SPECIFICATION_TEMPLATE)
                        .when()
                        .body(user)
                        .post(CREATE_USER_URL_PATH)
                        .then()
                        .spec(RESPONSE_SPECIFICATION_TEMPLATE);
    }

    // Удаление пользователя
    public ValidatableResponse deleteUser(String accessToken) {
        return
                given()
                        .spec(REQUEST_SPECIFICATION_TEMPLATE)
                        .header("Authorization", "Bearer " + accessToken)
                        .when()
                        .delete(DELETE_USER_URL_PATH)
                        .then()
                        .spec(RESPONSE_SPECIFICATION_TEMPLATE);
    }

    //Авторизация пользователя
    public ValidatableResponse loginUser(Credentials user) {
        return
                given()
                        .spec(REQUEST_SPECIFICATION_TEMPLATE)
                        .when()
                        .body(user)
                        .post(LOGIN_USER_URL_PATH)
                        .then()
                        .spec(RESPONSE_SPECIFICATION_TEMPLATE);
    }
}
