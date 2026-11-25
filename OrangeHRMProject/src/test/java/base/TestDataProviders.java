package base;

import Utlis.DataDriven;
import com.fasterxml.jackson.databind.JsonNode;
import org.testng.annotations.DataProvider;

public class TestDataProviders {

    @DataProvider(name = "validLogin")
    public static Object[][] validLogin() {
        JsonNode n = DataDriven.jsonReader().get("validLogin");
        return new Object[][]{{n.get("username").asText(), n.get("password").asText()}};
    }

    @DataProvider(name = "invalidLogin")
    public static Object[][] invalidLogin() {
        JsonNode n = DataDriven.jsonReader().get("invalidLogin");
        return new Object[][]{{n.get("username").asText(), n.get("password").asText()}};
    }

    @DataProvider(name = "knownEmployee")
    public static Object[][] knownEmployee() {
        return new Object[][]{{DataDriven.jsonReader().get("employeeSearch").get("knownEmployeeName").asText()}};
    }

    @DataProvider(name = "unknownEmployee")
    public static Object[][] unknownEmployee() {
        return new Object[][]{{DataDriven.jsonReader().get("employeeSearch").get("nonExistingEmployeeName").asText()}};
    }

    @DataProvider(name = "newEmployee")
    public static Object[][] newEmployee() {
        JsonNode n = DataDriven.jsonReader().get("newEmployee");
        return new Object[][]{{n.get("firstName").asText(), n.get("lastName").asText()}};
    }
}
