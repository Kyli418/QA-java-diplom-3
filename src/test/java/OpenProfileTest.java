import PageObjects.*;
import api.client.StellarBurgerClientService;
import api.client.UserData;
import api.steps.UserSteps;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import web.WebDrivers;

import static PageObjects.AuthorizationPage.URL_PAGE_AUTHORIZATION;

public class OpenProfileTest {

    private WebDriver driver;
    private StellarBurgerClientService client;
    private String token;
    private UserSteps userSteps;
    private RegistrationPage registrationPage;
    private RegistrationTestData registrationTestData;;
    private UserData userData;
    private MainPage mainPage;
    private AuthorizationPage authorizationPage;
    private ProfilePage profilePage;

    @BeforeEach
    public void setUp(){
        driver = WebDrivers.createDriver();
        registrationPage = new RegistrationPage(driver);
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
    }

    @DisplayName("Проверка перехода в Личный кабинет")
    @Test
    public void checkOpenProfile(){
        mainPage.clickProfileButton();
        profilePage.checkOpenProfilePage();
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
