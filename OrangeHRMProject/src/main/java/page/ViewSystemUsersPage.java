package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ViewSystemUsersPage {
    private WebDriver driver;
    private By title=By.cssSelector(".oxd-topbar-header-breadcrumb");
    private By buttonAdd=By.cssSelector(".orangehrm-header-container > button");
    private By userlink=By.linkText("Users");
    private By userManagement=By.cssSelector(".oxd-topbar-body-nav-tab-item");
    public ViewSystemUsersPage(WebDriver driver){
        this.driver=driver;
    }
    public String getTitle(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(title));
       return driver.findElement(title).getText();
    }
    public void clickUserManagement(){
        driver.findElement(userManagement).click();
    }
    public void clickUser(){
        driver.findElement(userlink).click();
    }
    @Step("Click Add (new user)")
    public SaveSystemUserPage clickAddButton(){
        driver.findElement(buttonAdd).click();
        return new SaveSystemUserPage(driver);
    }
}
