package whiteboxtests;

import app.Employee;
import app.SystemCalendar;
import app.SystemApp;
import app.SystemAppException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class SetNameTest {
    private final SystemApp systemApp = new SystemApp();
    private String errorMessage = "";



    @Test
    void testSetNameA() {
        // Actor is not a project leader
        try {
            systemApp.createEmployee( "bobi");
            systemApp.createProject("a");
            systemApp.setProjectLeader("bobi", "a", "bobi");
            systemApp.setProjectName("huba","a", "b");
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        Assertions.assertEquals("Employee is not Project Leader", errorMessage);
        Assertions.assertEquals(Map.of(25001,"a"), systemApp.listProjects());
    }
    @Test
    void testSetNameB() {
        // Set name to empty
        try {
            systemApp.createProject("a");
            systemApp.setProjectLeader("huba", "a", "huba");
            systemApp.setProjectName("huba", "a", "");
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        Assertions.assertEquals("Project Name cannot be empty", errorMessage);
        Assertions.assertEquals(Map.of(25001, "a"), systemApp.listProjects());
    }
    @Test
    void testSetNameC() {
        // Successfully set name
        try {
            systemApp.createProject("a");
            systemApp.setProjectLeader("huba","a", "huba");
            systemApp.setProjectName("huba","a", "b");
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        Assertions.assertEquals("", errorMessage);
        Assertions.assertEquals(Map.of(25001,"b"), systemApp.listProjects());
    }

}
