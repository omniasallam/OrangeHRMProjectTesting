package tests;

import Utlis.DataDriven;
import base.BaseTest;
import base.TestDataProviders;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Feature("PIM")
public class EmployeeListTest extends BaseTest {

    @Test(dataProvider = "knownEmployee", dataProviderClass = TestDataProviders.class)
    @Description("TC4: Search for an employee")
    public void testSearchOfEmployee(String employeeName){
        var testData= DataDriven.jsonReader();
        loginPage.setUserName(testData.get("validLogin").get("username").asText());
        loginPage.setPassword(testData.get("validLogin").get("password").asText());
        var dashboardPage=loginPage.clickLoginButton();
        var employeeListPage=dashboardPage.clickPimLink();
        employeeListPage.clickLinkEmployeeList();
        employeeListPage.setEmployeeName(employeeName);
        employeeListPage.clickButtonSearch();
        // the results table must contain the searched employee's name
        assertTrue(employeeListPage.tableResultContainsName(employeeName),
                "Employee is not exist");
    }
    @Test(dataProvider = "unknownEmployee", dataProviderClass = TestDataProviders.class)
    @Description("TC5: Search for a non-existing employee")
    public void testNoExistingEmployee(String employeeName){
        var testData= DataDriven.jsonReader();
        loginPage.setUserName(testData.get("validLogin").get("username").asText());
        loginPage.setPassword(testData.get("validLogin").get("password").asText());
        var dashboardPage=loginPage.clickLoginButton();
      var employeeListPage=  dashboardPage.clickPimLink();
      employeeListPage.clickLinkEmployeeList();
     employeeListPage.setEmployeeName(employeeName);
      employeeListPage.clickButtonSearch();

      assertEquals(employeeListPage.getMessageNoRecordaFound(),
              "No Records Found");

    }
}
