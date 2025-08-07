package PageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationPage {
    private WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    //URL страницы регистрации
    public static final String URL_PAGE_REGISTRATION = "https://stellarburgers.nomoreparties.site/register";


    //Поле имя
    public static final By INPUT_NAME = By.xpath(".//label[contains(text(), 'Имя')]/parent::div/input");

    //Поле email
    public static final By INPUT_EMAIL = By.xpath(".//label[contains(text(), 'Email')]/parent::div/input");

    //Поле password
    public static final By INPUT_PASSWORD = By.xpath(".//label[contains(text(), 'Пароль')]/parent::div/input");

    //Кнопка зарегистрироваться
    public static final By BUTTON_REGISTRATION = By.xpath(".//button[contains(text(), 'Зарегистрироваться')]");

    //Кнопка войти
    public static final By BUTTON_LOGIN = By.cssSelector("a.Auth_link__1fOlj");

    //Локатор ошибки при пароле < 6 символов
    public static final By ERROR_SHORT_PASSWORD = By.cssSelector("p.input__error.text_type_main-default");

    //Тексты ошибок
    public static final String TEXT_ERROR_SHORT_PASSWORD = "Некорректный пароль";


    //Регистрация
    @Step("Пользователь заполняет поле имя")
    public void inputName(String name) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(INPUT_NAME)).sendKeys(name);
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

    @Step("Пользователь нажимает на кнопку Зарегистрироваться")
    public void clickButtonReg() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(BUTTON_REGISTRATION)).click();
    }

    //Проверки
    @Step("Проверка отображения ошибки при вводе пароля меньше 6 символов")
    public void checkErrorShortPassword(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String actual = wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_SHORT_PASSWORD)).getText();
        assertEquals(TEXT_ERROR_SHORT_PASSWORD, actual, "Текст ошибки не соответствует ожидаемому");

    }
}
