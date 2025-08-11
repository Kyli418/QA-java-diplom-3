package PageObjects;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MainPage {
    private WebDriver driver;

    private String bun = "Булки";
    private String sauce = "Соусы";
    private String filling = "Начинки";

    public String getBun() {
        return bun;
    }

    public String getSauce() {
        return sauce;
    }

    public String getFilling() {
        return filling;
    }

    //Создание драйвера
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //URL главной страницы
    public static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    //Локатор кнопки "Войти в аккаунт"
    public static final By BUTTON_LOGIN_ACCOUNT = By.xpath("//div[@class = 'BurgerConstructor_basket__container__2fUl3 mt-10']//button");

    // Локатор кнопки "Личный кабинет"
    public static final By BUTTON_PERSONAL_ACCOUNT = By.xpath("//p[text()='Личный Кабинет']");

    //Кнопка оформления заказа на главной
    public static final By BUTTON_CREATE_ORDER = By.xpath("//button[text()='Оформить заказ']");

    //Локатор кнопки "Конструктор"
    public static final By BUTTON_DESIGNER = By.xpath("//p[text() = 'Конструктор']");

    //Локатор логотипа Stellar Burgers
    public static final By BANNER_STELLAR_BURGERS = By.xpath("//div[@class = 'AppHeader_header__logo__2D0X2']");

    //Локатор таба Булки
    public static final By TAB_BUN = By.xpath("//span[text()='Булки']");

    //Локатор таба Соусы
    public static final By TAB_SAUCE = By.xpath("//span[text()='Соусы']");

    //Локатор таба Начинки
    public static final By TAB_FILLING = By.xpath("//span[text()='Начинки']");

    //Локатор активного (Выбранного таба)
    public static final By ACTIVE_TAB = By.xpath(".//div[contains(@class, 'tab_tab_type_current__2BEPc')]//span");

    //Открытие страницы
    @Step("Открытие страницы")
    public void openWebDrivers(String page) {
        driver.get(page);
    }
    //Переход на страницу авторизации
    @Step("Переход по страницу авторизации")
    public void switchAuthorizationPage(By locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).click();
    }

    //Проверка отображения кнопки оформить заказ
    @Step("Отображение кнопки оформить заказ")
    public void checkButtonCreateOrder(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(BUTTON_CREATE_ORDER));
    }

    @Step("Открытие страницы профиля")
    public void clickProfileButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(BUTTON_PERSONAL_ACCOUNT)).click();
    }

    @Step("Проверка, что главная страница открылась")
    public void checkOpenMainPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(MAIN_PAGE_URL));
    }

    @Step("Пользователь кликает по табу Булка")
    public void clickTabBun(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(TAB_BUN)).click();
    }

    @Step("Пользователь кликает по табу соус")
    public void clickTabSauce(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(TAB_SAUCE)).click();
    }

    @Step("Пользователь кликает по табу начинка")
    public void clickTabFilling(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(TAB_FILLING)).click();
    }

    @Step("Пользователь проверяет, что таб успешно открыт")
    public void checkActiveTab(String expectedTab) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(ACTIVE_TAB));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(ACTIVE_TAB, expectedTab));
    }
}
