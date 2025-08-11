package web;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDrivers {
    private WebDriver driver;

    public static WebDriver createDriver() {
        String browserName = System.getProperty("browser", "CHROME").toUpperCase();
        BrowserEnum browser = BrowserEnum.valueOf(browserName);

        switch (browser) {
            case CHROME:
                return new ChromeDriver();
            case YANDEX:
                System.setProperty("webdriver.chrome.driver", "drivers/yandexdriver.exe");
                return new ChromeDriver();
            default:
                throw new IllegalArgumentException("Неизвестный браузер: " + browser);
        }
    }
}
/*

Для запуска тестов в браузере Яндекс - mvn test -Dbrowser=YANDEX
Для запуска тестов в браузере ГуглХром - mvn test -Dbrowser=CHROME

 */