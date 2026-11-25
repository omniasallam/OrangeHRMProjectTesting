package base;

import Utlis.ConfigReader;
import Utlis.DriverFactory;
import Utlis.TestLogger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page.LoginPage;

public class BaseTest {
   protected WebDriver driver;
   protected LoginPage loginPage;

  @BeforeMethod
    public void setUp(){
      ConfigReader.loadProperties();
      DriverFactory.initDriver();
      driver= DriverFactory.getDriver();
      driver.get(ConfigReader.getProperty("base.url")+"auth/login");

          driver.manage().window().maximize();

      loginPage= new LoginPage(driver);
      TestLogger.logger.info("Browser started and login page opened");
  }
    @AfterMethod(alwaysRun = true)
    public  void tearDown(){
      DriverFactory.quitDriver();
      TestLogger.logger.info("Browser closed");
    }


}
