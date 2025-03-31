package hellocucumber;

import app.SystemApp;
import app.SystemAppException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectSteps {
    private SystemApp systemApp = new SystemApp();
    private String errorMessage;


    @When("creating a new project named {string}")
    public void creatingANewProjectNamed(String string) {
        try {
            systemApp.createProject(string);
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("error message {string} is given")
    public void errorMessageIsGiven(String string) {
        assertEquals(string, errorMessage);
    }

    @Then("{int} unique project\\(s) with name {string} exist\\(s)")
    public void uniqueProjectSWithNameExistS(int amount, String name) {
        // Check exists
        List<Integer> ids = systemApp.getProjectIds(name);
        assertEquals(amount, ids.size());

        // Check duplicates
        Set<Integer> idSet = new HashSet<>(ids);
        assertEquals(ids.size(), idSet.size());
    }
}
