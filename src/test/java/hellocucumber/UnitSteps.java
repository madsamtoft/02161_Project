package hellocucumber;

import app.*;

import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class UnitSteps {
    private boolean boolResult;
    private final Employee testEmployee = new Employee("test");
    @When("comparing if an employee object equals null")
    public void comparingIfAnEmployeeObjectEqualsNull() {
        //noinspection ConstantValue
        boolResult = testEmployee.equals(null);
    }

    @When("comparing if an employee object equals an activity object")
    public void comparingIfAnEmployeeObjectEqualsAnActivityObject() {
        try {
            //noinspection EqualsBetweenInconvertibleTypes
            boolResult = testEmployee.equals(new Activity("test"));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Then("the boolean result is false")
    public void theBooleanResultIsFalse() {
        assertFalse(boolResult);
    }
}
