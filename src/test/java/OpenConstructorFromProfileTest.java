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

import static PageObjects.AuthorizationPage.URL_PAGE_AUTHORIZATION;


public class OpenConstructorFromProfileTest {
    private WebDriver driver;
    private StellarBurgerClientService client;
    private String token;
    private UserSteps userSteps;
    private RegistrationTestData registrationTestData;;
    private UserData userData;
    private MainPage mainPage;
    private AuthorizationPage authorizationPage;
    private ProfilePage profilePage;

    @BeforeEach
    public void setUp(){
        driver = WebDrivers.createDriver();
        authorizationPage = new AuthorizationPage(driver);
        mainPage = new MainPage(driver);
        profilePage = new ProfilePage(driver);
        userSteps = new UserSteps();
        client = userSteps.createClient();

        registrationTestData = RegistrationTestData.getValidUser();
        userData = new UserData(registrationTestData.email, registrationTestData.name, registrationTestData.password);
        ValidatableResponse response = userSteps.requestCreateUser(client, userData);
        token = userSteps.getAccessToken(response);

        mainPage.openWebDrivers(URL_PAGE_AUTHORIZATION);
        authorizationPage.inputEmail(registrationTestData.email);
        authorizationPage.inputPassword(registrationTestData.password);
        authorizationPage.clickButtonAuth();
        mainPage.clickProfileButton();
    }

    static Stream<Object[]> pagesToTest() {
        return Stream.of(
                new Object[]{MainPage.BANNER_STELLAR_BURGERS},
                new Object[]{MainPage.BUTTON_DESIGNER}
        );
    }

    @DisplayName("Проверка перехода по баннеру и кнопке Конструктор из ЛК")
    @MethodSource("pagesToTest")
    @ParameterizedTest
    public void goingToConstructorPage(By locator){
        profilePage.switchDesignerPage(locator);
        mainPage.checkOpenMainPage();
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
