package tests;

import Utlis.DataDriven;
import Utlis.TestLogger;
import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

@Feature("UI")
public class SideMenuTest extends BaseTest {
    @Test
    @Description("TC11: Verify sidebar menu UI")
    public void testSidebarMenu(){
        var testData= DataDriven.jsonReader();
        loginPage.setUserName(testData.get("validLogin").get("username").asText());
        loginPage.setPassword(testData.get("validLogin").get("password").asText());
       var dashboard= loginPage.clickLoginButton();
      List<String> sidebarNames= dashboard.getItemOfSideMenu();
        //System.out.println(sidebarNames);
        assertTrue(sidebarNames.contains("Admin"));
        assertTrue(sidebarNames.contains("PIM"));
        assertTrue(sidebarNames.contains("Leave"));
        assertTrue(sidebarNames.contains("Time"));
        assertTrue(sidebarNames.contains("Recruitment"));
        assertTrue(sidebarNames.contains("My Info"));
        assertTrue(sidebarNames.contains("Performance"));
        assertTrue(sidebarNames.contains("Directory"));
        assertTrue(sidebarNames.contains("Dashboard"));


        TestLogger.logger.info("Sidebar Test Passed");

    }
}
