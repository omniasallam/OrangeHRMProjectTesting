package page;

import Utlis.TestLogger;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
   private By userNameInput=By.name("username");
   private By passwordInput=By.name("password");
   private By loginButton=By.cssSelector("button[type='submit']");
   private By invalidError= By.xpath("//p[text()='Invalid credentials']");
   private By requiredMessageUser= By.xpath("//input[@name='username']/ancestor::div[contains(@class,'oxd-input-group')]//span[text()='Required']");
   private By requiredMessagePass= By.xpath("//input[@name='password']/ancestor::div[contains(@class,'oxd-input-group')]//span[text()='Required']");
    public LoginPage(WebDriver driver){
        this.driver=driver;
    }
    @Step("Enter username: {name}")
    public void setUserName(String name){
        TestLogger.logger.info("Entering username");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameInput));
        driver.findElement(userNameInput).sendKeys(name);
    }
    @Step("Enter password")
    public void setPassword(String password){
        TestLogger.logger.info("Entering password");

        driver.findElement(passwordInput).sendKeys(password);
    }
    @Step("Click Login button")
    public dashboardPage clickLoginButton(){
        TestLogger.logger.info("Clicking Login");
        driver.findElement(loginButton).click();
        return new dashboardPage(driver);
    }
    @Step("Read the error message")
    public String getErrorMessage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(invalidError));
       return driver.findElement(invalidError).getText();
    }
    @Step("Read the 'Required' message under Username")
    public String getRequiredMessageOfUserName(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(requiredMessageUser));
      return  driver.findElement(requiredMessageUser).getText();
    }
    @Step("Read the 'Required' message under Password")
    public String getRequiredMessageOfPassword(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(requiredMessagePass));
        return  driver.findElement(requiredMessagePass).getText();
    }
}
