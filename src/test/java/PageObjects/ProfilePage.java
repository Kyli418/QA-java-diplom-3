package PageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ProfilePage {
    private WebDriver driver;

    //Создание драйвера
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }
    //URL страницы профиля
    public static final String PROFILE_PAGE_URL = "https://stellarburgers.nomoreparties.site/account";

    //Локатор кнопки выхода
    public static final By BUTTON_LOGOUT = By.xpath("//button[text()='Выход']");

    @Step("Проверка, что страница профиля открылась")
    public void checkOpenProfilePage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(PROFILE_PAGE_URL));
    }

    @Step("Пользователь нажимает на кнопку выход")
    public void clickButtonLogout(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(BUTTON_LOGOUT)).click();
    }

    @Step("Переход на страницу конструктора")
    public void switchDesignerPage(By locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).click();
    }
}
