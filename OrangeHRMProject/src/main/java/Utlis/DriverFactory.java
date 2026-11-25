package Utlis;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver =
           new ThreadLocal<>();

   public static void initDriver() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();


        driver.set(new ChromeDriver(options));
    }

    public static WebDriver getDriver() {
        return driver.get();
   }

    public static void quitDriver() {
        WebDriver d = driver.get();
        if (d != null) {
            d.quit();
            driver.remove();
        }
    }

}
