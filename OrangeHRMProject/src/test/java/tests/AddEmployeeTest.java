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
public class AddEmployeeTest extends BaseTest {
    @Test
    @Description("TC6: Open Add Employee page")
    public void testAddEmployee(){
        var testData= DataDriven.jsonReader();
        loginPage.setUserName(testData.get("validLogin").get("username").asText());
        loginPage.setPassword(testData.get("validLogin").get("password").asText());
       var dashBoardPage= loginPage.clickLoginButton();
      var employeeListPage= dashBoardPage.clickPimLink();
      var addEmployeePage=employeeListPage.clickAddEmployee();
      assertTrue(addEmployeePage.getUrlOfAddEmployee().contains("pim/addEmployee"));
      assertTrue(addEmployeePage.firstNameIsDisplayed());
      assertTrue(addEmployeePage.lastNameIsDisplayed());
    }
    @Test
    @Description("TC7: Add employee with an empty required field")
    public void testAddEmployeeWithEmptyField(){
        var testData= DataDriven.jsonReader();
        loginPage.setUserName(testData.get("validLogin").get("username").asText());
        loginPage.setPassword(testData.get("validLogin").get("password").asText());
        var dashBoardPage= loginPage.clickLoginButton();
        var employeeListPage= dashBoardPage.clickPimLink();
        var addEmployee= employeeListPage.clickAddEmployee();
        addEmployee.setFirstNameField("");
        addEmployee.setLastNameField(testData.get("newEmployee").get("lastName").asText());
        addEmployee.clickButtonSave();
        String firstNameRequired=addEmployee.getRequiredMessageOfFirstName();
        assertEquals(firstNameRequired,"Required");

    }
    @Test(dataProvider = "newEmployee", dataProviderClass = TestDataProviders.class)
    @Description("TC8: End-to-end add a new employee")
    public void testSuccessfulAddNewEmployee(String first, String last){
        var testData= DataDriven.jsonReader();
        loginPage.setUserName(testData.get("validLogin").get("username").asText());
        loginPage.setPassword(testData.get("validLogin").get("password").asText());
        var dashboardPage=loginPage.clickLoginButton();
        var employeeListPage= dashboardPage.clickPimLink();
        var addEmployeePage=employeeListPage.clickAddEmployee();
        // unique last name so the final search finds exactly the employee created in this run
        String firstName=first;
        String lastName=last;
        addEmployeePage.setFirstNameField(firstName);
        addEmployeePage.setLastNameField(lastName);
        var personalDetailsPage=addEmployeePage.clickButtonSave();
        String title= personalDetailsPage.getPersonalDetails();
        assertEquals(title,"Personal Details");
        var employeeListPageAfterAdd =personalDetailsPage.clickEmployeeList();
        employeeListPageAfterAdd.setEmployeeName(firstName + " " +lastName);
        employeeListPageAfterAdd.clickButtonSearch();
        assertTrue(employeeListPageAfterAdd.tableResultContainsName(firstName+ " " +
                        lastName),
                "Employee is not added in table");
        //System.out.println(employeeListPageAfterAdd.tableResultIsDisplayed(firstName + " " +lastName));
    }

}
