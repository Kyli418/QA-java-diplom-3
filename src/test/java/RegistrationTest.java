import PageObjects.MainPage;
import PageObjects.RegistrationPage;
import PageObjects.RegistrationTestData;
import api.client.Credentials;
import api.client.StellarBurgerClientService;
import api.steps.UserSteps;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import web.WebDrivers;

import static PageObjects.RegistrationPage.URL_PAGE_REGISTRATION;


public class RegistrationTest {
    private WebDriver driver;
    private StellarBurgerClientService client;
    private String token;
    private UserSteps userSteps;
    private RegistrationPage registrationPage;
    private RegistrationTestData registrationTestData;
    private Credentials credentials;


    @BeforeEach
    public void setUp(){
        driver = WebDrivers.createDriver();
        MainPage mainPage = new MainPage(driver);
        userSteps = new UserSteps();
        registrationPage = new RegistrationPage(driver);
        mainPage.openWebDrivers(URL_PAGE_REGISTRATION);
    }

    @DisplayName("Проверка успешной регистрации")
    @Test
    public void registrationSuccess(){
        client = userSteps.createClient();
        registrationTestData = RegistrationTestData.getValidUser();
        registrationPage.inputName(registrationTestData.name);
        registrationPage.inputEmail(registrationTestData.email);
        registrationPage.inputPassword(registrationTestData.password);
        registrationPage.clickButtonReg();

        credentials = new Credentials(registrationTestData.email, registrationTestData.password);

        ValidatableResponse response = userSteps.requestLoginUser(client, credentials);
        token = userSteps.getAccessToken(response);
        userSteps.checkLoginUserCode(response);
    }

    @DisplayName("Проверка ошибки при пароле менее 6 символов")
    @Test
    public void registerWithShortPassword(){
        registrationTestData = RegistrationTestData.getNoValidUser();
        registrationPage.inputName(registrationTestData.name);
        registrationPage.inputEmail(registrationTestData.email);
        registrationPage.inputPassword(registrationTestData.password);
        registrationPage.clickButtonReg();
        registrationPage.checkErrorShortPassword();
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

