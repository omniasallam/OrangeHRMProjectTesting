package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SaveSystemUserPage {
    private WebDriver driver;
    private By addUserForm=By.xpath("//h6[text()='Add User']");
    private By userRole=By.xpath("//label[text()='User Role']");
    private By employeeName=By.xpath("//label[text()='Employee Name']");
    private By userName=By.xpath("//label[text()='Username']");
    private By passwordUser=By.xpath("//label[text()='Password']");
    public SaveSystemUserPage(WebDriver driver){
        this.driver=driver;
    }
  @Step("Read the Add User header")
  public String getHeaderAddUser(){
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      wait.until(ExpectedConditions.visibilityOfElementLocated(addUserForm));
       return driver.findElement(addUserForm).getText();
  }
  public boolean userRoleIsDisplayed(){
      return   driver.findElement(userRole).isDisplayed();
  }
    public boolean employeeNameIsDisplayed(){
        return   driver.findElement(employeeName).isDisplayed();
    }
    public boolean userNameIsDisplayed(){
        return   driver.findElement(userName).isDisplayed();
    }
    public boolean passwordIsDisplayed(){
        return  driver.findElement(passwordUser).isDisplayed();
    }
}
