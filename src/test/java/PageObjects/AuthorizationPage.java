package PageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthorizationPage {
    private WebDriver driver;

    //Создание драйвера
    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
    }

    //URL страницы авторизации
    public static final String URL_PAGE_AUTHORIZATION = "https://stellarburgers.nomoreparties.site/login";

    //Поле email
    public static final By INPUT_EMAIL = By.cssSelector("input[name='name'][type='text']");

    //Поле password
    public static final By INPUT_PASSWORD = By.cssSelector("input[name='Пароль'][type='password']");

    //Кнопка войти
    public static final By BUTTON_LOGIN = By.cssSelector("button.button_button__33qZ0");

    @Step("Переход на страницу авторизации")
    public void switchAuthorizationPage(By locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).click();
    }

    @Step("Пользователь заполняет поле email")
    public void inputEmail(String email) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(INPUT_EMAIL)).sendKeys(email);
    }

    @Step("Пользователь заполняет поле пароль")
    public void inputPassword(String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(INPUT_PASSWORD)).sendKeys(password);
    }

    @Step("Пользователь нажимает на кнопку войти")
    public void clickButtonAuth() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(BUTTON_LOGIN)).click();
    }

    @Step("Проверка, что страница профиля авторизации")
    public void checkOpenAuthorizationPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(URL_PAGE_AUTHORIZATION));
    }
}
