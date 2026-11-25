package page;

import Utlis.TestLogger;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class dashboardPage {
    private WebDriver driver;
    private By headerDashboard= By.xpath("//h6[text()='Dashboard']");
    private By pimLink=By.xpath("//span[text()='PIM']");
    private By adminLink=By.xpath("//span[text()='Admin']");
    private By scrollFooter=By.cssSelector(".oxd-layout-footer");
    private By orangeIncLink=By.linkText("OrangeHRM, Inc");
  private By sideMenu=By.cssSelector(".oxd-main-menu-item .oxd-main-menu-item--name");


    public dashboardPage(WebDriver driver){
        this.driver=driver;
    }
    @Step("Wait for the dashboard URL and read it")
    public String  getUrlOfDashboard(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("dashboard/index"));

        return driver.getCurrentUrl();
    }
    @Step("Check the Dashboard header is displayed")
    public boolean headerIsDisplayed(){
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       wait.until(ExpectedConditions.visibilityOfElementLocated(headerDashboard));
       return  driver.findElement(headerDashboard).isDisplayed();
    }
    @Step("Open the PIM module")
    public EmployeeListPage clickPimLink(){
        TestLogger.logger.info("Opening PIM");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(pimLink));
        driver.findElement(pimLink).click();
        return new EmployeeListPage(driver);
    }

    @Step("Open the Admin module")
    public ViewSystemUsersPage clickAdmin(){
        TestLogger.logger.info("Opening Admin");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(adminLink));
      driver.findElement(adminLink).click();
      return new ViewSystemUsersPage(driver);
    }

    /**
     * Footer for DashBoardHome
     */
    @Step("Scroll to the footer")
    public void scrollInToFooter(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(scrollFooter));
        var js= (JavascriptExecutor)driver;
        WebElement footer=driver.findElement(scrollFooter);
        String script="arguments[0].scrollIntoView(true);";
        js.executeScript(script,footer);
    }
    public String getOrangeText(){
        return driver.findElement(orangeIncLink).getText();
    }

    @Step("Click the OrangeHRM, Inc link")
    public OrangehrmPage clickOrangeLink(){
        driver.findElement(orangeIncLink).click();
        return new OrangehrmPage(driver);
    }

    /**
     * SidePanelMenu
     */
    @Step("Read the sidebar menu items")
    public List<String> getItemOfSideMenu(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(sideMenu));
      List<WebElement> menuItems = driver.findElements(sideMenu);
      List<String> menuNames=new ArrayList<>();
      for ( WebElement item:menuItems){
          menuNames.add(item.getText().trim());
      }
      return menuNames;
    }

}
