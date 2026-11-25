package tests;

import Utlis.DataDriven;
import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Feature("Admin")
public class AdminTest extends BaseTest {
    @Test
    @Description("TC9: Verify Admin > Add User page")
    public void testAdmniUer(){
        var testData= DataDriven.jsonReader();
        loginPage.setUserName(testData.get("validLogin").get("username").asText());
        loginPage.setPassword(testData.get("validLogin").get("password").asText());
      var dashboardPage=  loginPage.clickLoginButton();
      var viewSystemPage=dashboardPage.clickAdmin();
      viewSystemPage.getTitle();
      var saveSystemPage=viewSystemPage.clickAddButton();
      assertEquals(saveSystemPage.getHeaderAddUser(),"Add User");
     assertTrue( saveSystemPage.userRoleIsDisplayed());
     assertTrue( saveSystemPage.employeeNameIsDisplayed());
     assertTrue( saveSystemPage.userNameIsDisplayed());
      assertTrue(saveSystemPage.passwordIsDisplayed());
    }
}
