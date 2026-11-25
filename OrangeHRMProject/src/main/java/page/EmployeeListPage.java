package page;

import Utlis.TestLogger;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class EmployeeListPage {
    private WebDriver driver;
    private By employeeList=By.linkText("Employee List");
    private By inputEmployeeName=By.cssSelector(".oxd-autocomplete-text-input > input");
    private By nameFromDropdown=By.xpath("//div[@role='option']");
    private By searchButton=By.xpath("//button[normalize-space()='Search']");
    private By tableResult=By.cssSelector(".oxd-table-body .oxd-table-row");
    private By noRecordFound=By.xpath("//span[text()='No Records Found']");
    private By addEmployeeLink=By.linkText("Add Employee");

    public EmployeeListPage(WebDriver driver){

        this.driver=driver;
    }

    @Step("Open Employee List")
    public void clickLinkEmployeeList(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(employeeList));
        driver.findElement(employeeList).click();
    }

    @Step("Type employee name: {name}")
    public void setEmployeeName(String name){
        TestLogger.logger.info("Searching employee " + name);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputEmployeeName));
       driver.findElement(inputEmployeeName).sendKeys(name);
   }
    public void selectEmployeeName(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameFromDropdown));
        driver.findElement(nameFromDropdown).click();
    }
    @Step("Click Search")
    public void clickButtonSearch(){

        driver.findElement(searchButton).click();
    }
    @Step("Check the results table contains: {employeeName}")
    public boolean tableResultContainsName(String employeeName){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tableResult));
     List<WebElement> rows =driver.findElements(tableResult);
     for (WebElement row : rows){
         if (row.getText().replaceAll("\\s+", " ").trim()
                 .contains(employeeName)){
             return true;
         }
     }
     return false;
   }
   public String getEmployeeNmeFromTable(){
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       wait.until(ExpectedConditions.visibilityOfElementLocated(tableResult));
      List<WebElement> rows=  driver.findElements(tableResult);
        return rows.get(0).getText();
   }

    /**
     * scenario  #4 Search for a non-existing employee
     * method of getMessageNoRecordaFound part of NonExistingEmployee
     * @return
     */
    @Step("Read the No Records Found message")
    public String getMessageNoRecordaFound(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(noRecordFound));
      return   driver.findElement(noRecordFound).getText();
    }


    @Step("Click Add Employee")
    public AddEmployeePage clickAddEmployee(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(addEmployeeLink));
        driver.findElement(addEmployeeLink).click();
        return new AddEmployeePage(driver);
    }
}
