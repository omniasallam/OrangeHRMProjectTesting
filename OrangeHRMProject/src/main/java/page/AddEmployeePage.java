package page;

import Utlis.TestLogger;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddEmployeePage {
    private WebDriver driver;
    private By firstNameField=By.name("firstName");
    private By lastNameField=By.name("lastName");
    private By requiredMessage=By.xpath("//span[text()='Required']");
    private By buttonSave=By.cssSelector("button[type='submit']");

    public AddEmployeePage(WebDriver driver){
        this.driver=driver;
    }
    @Step("Read the Add Employee URL")
    public String  getUrlOfAddEmployee(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.urlContains("pim/addEmployee"));

        return driver.getCurrentUrl();
    }
    public boolean firstNameIsDisplayed(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
       return driver.findElement(firstNameField).isDisplayed();
    }
    public boolean lastNameIsDisplayed(){
        return driver.findElement(lastNameField).isDisplayed();
    }
    @Step("Enter first name: {name}")
    public void setFirstNameField(String name){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        driver.findElement(firstNameField).sendKeys(name);
    }
    @Step("Enter last name: {last}")
    public void setLastNameField(String last){

        driver.findElement(lastNameField).sendKeys(last);
    }
    @Step("Read the Required message under First Name")
    public String getRequiredMessageOfFirstName(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(requiredMessage));
       return driver.findElement(requiredMessage).getText();
    }
    @Step("Click Save")
    public PersonalDetailsPage clickButtonSave(){
        TestLogger.logger.info("Clicking Save");
        driver.findElement(buttonSave).click();
        return new PersonalDetailsPage(driver);
    }

}
