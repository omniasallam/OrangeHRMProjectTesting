package tests;

import base.BaseTest;
import base.TestDataProviders;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Feature("Login")
public class LoginTest extends BaseTest {
    @Test(dataProvider = "validLogin", dataProviderClass = TestDataProviders.class)
    @Description("TC1: Login with valid credentials")
    public void testLoginWithValidCredentials(String username, String password){
        loginPage.setUserName(username);
        loginPage.setPassword(password);
        var dashboardPage=loginPage.clickLoginButton();
        assertTrue(dashboardPage.getUrlOfDashboard().contains("dashboard/index"));
        assertTrue(dashboardPage.headerIsDisplayed(),
                "Dashboard header is not  displayed");
    }
    @Test(dataProvider = "invalidLogin", dataProviderClass = TestDataProviders.class)
    @Description("TC2: Login with invalid credentials")
    public void testInvalidLogin(String username, String password){
       loginPage.setUserName(username);
       loginPage.setPassword(password);
       loginPage.clickLoginButton();
      String error= loginPage.getErrorMessage();
      assertEquals(error,"Invalid credentials");
    }
    @Test
    @Description("TC3: Login with empty fields")
    public void testLoginWitheEmptyField(){
        loginPage.setUserName("");
        loginPage.setPassword("");
        loginPage.clickLoginButton();
       String usernameRequired= loginPage.getRequiredMessageOfUserName();
      String passwordRequired= loginPage.getRequiredMessageOfPassword();
       assertEquals(usernameRequired,"Required");
       assertEquals(passwordRequired,"Required");
    }
}
