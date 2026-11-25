package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalDetailsPage {
    private WebDriver driver;
    private By personalDetails=By.xpath("//h6[text()='Personal Details']");
    private By employeelist=By.linkText("Employee List");
    public PersonalDetailsPage(WebDriver driver){
        this.driver=driver;
    }
    @Step("Read the Personal Details header")
    public String getPersonalDetails(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(personalDetails));
       return driver.findElement(personalDetails).getText();
    }
    @Step("Go back to Employee List")
    public EmployeeListPage clickEmployeeList(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(employeelist));
        driver.findElement(employeelist).click();
        return new EmployeeListPage(driver);
    }
}
