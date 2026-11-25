package tests;

import Utlis.DataDriven;
import Utlis.WindowManager;
import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Feature("UI")
public class FooterTest extends BaseTest {
    @Test
    @Description("TC10: Verify the OrangeHRM footer link")
    public void testOrangeHRMFooter(){
        var testData= DataDriven.jsonReader();
        loginPage.setUserName(testData.get("validLogin").get("username").asText());
        loginPage.setPassword(testData.get("validLogin").get("password").asText());
        var dashboardPage=loginPage.clickLoginButton();
        dashboardPage.scrollInToFooter();
        String textFooter =dashboardPage.getOrangeText();
        System.out.println(textFooter);
        assertTrue(textFooter.contains("OrangeHRM, Inc"));
        var orangehrmPage=dashboardPage.clickOrangeLink();
        WindowManager windowManager=new WindowManager(driver);
        windowManager.switchTab("orangehrm.com");
      String orangeUrl=  orangehrmPage.getUrlOrangeHrm();
      assertTrue(orangeUrl.contains("orangehrm.com"));

    }
}
