import PageObjects.*;
import api.client.StellarBurgerClientService;
import api.client.UserData;
import api.steps.UserSteps;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import web.WebDrivers;

import java.util.stream.Stream;


public class AuthorizationTest {
    private WebDriver driver;
    private StellarBurgerClientService client;
    private String token;
    private UserSteps userSteps;
    private RegistrationTestData registrationTestData;;
    private UserData userData;
    private MainPage mainPage;
    private AuthorizationPage authorizationPage;

    static Stream<Object[]> pagesToTest() {
        return Stream.of(
                new Object[]{MainPage.MAIN_PAGE_URL, MainPage.BUTTON_LOGIN_ACCOUNT},
                new Object[]{MainPage.MAIN_PAGE_URL, MainPage.BUTTON_PERSONAL_ACCOUNT},
                new Object[]{RegistrationPage.URL_PAGE_REGISTRATION, RegistrationPage.BUTTON_LOGIN},
                new Object[]{ForgotPasswordPage.URL_PAGE_FORGOT_PASSWORD, ForgotPasswordPage.BUTTON_LOGIN}

        );
    }

    @BeforeEach
    public void setUp(){
        driver = WebDrivers.createDriver();
        authorizationPage = new AuthorizationPage(driver);
        mainPage = new MainPage(driver);
        userSteps = new UserSteps();
        client = userSteps.createClient();

        registrationTestData = RegistrationTestData.getValidUser();
        userData = new UserData(registrationTestData.email, registrationTestData.name, registrationTestData.password);
        ValidatableResponse response = userSteps.requestCreateUser(client, userData);
        token = userSteps.getAccessToken(response);

    }

    @DisplayName("Проверка авторизации")
    @ParameterizedTest
    @MethodSource("pagesToTest")
    public void checkAuthorizationUser(String URL, By locator){
        mainPage.openWebDrivers(URL);
        authorizationPage.switchAuthorizationPage(locator);
        authorizationPage.inputEmail(registrationTestData.email);
        authorizationPage.inputPassword(registrationTestData.password);
        authorizationPage.clickButtonAuth();
        mainPage.checkButtonCreateOrder();
    }

    @AfterEach
    public void after(){
        driver.quit();
        if (token == null){
            System.out.println("Пользователь не был создан. Или токен не получен. Удаление невозможно.");
            return;
        }
        client.deleteUser(token);
    }
}

