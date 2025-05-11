package whiteboxtests;

import app.SystemCalendar;
import app.SystemApp;
import app.SystemAppException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class CreateProjectTest {
    private final SystemApp systemApp = new SystemApp();
    private String errorMessage = "";

    @Test
    void testCreateProjectA() {
        // Empty name
        try {
            systemApp.createProject("");
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
        Assertions.assertEquals("Project Name cannot be empty", errorMessage);
        Assertions.assertEquals(Map.of(), systemApp.listProjects());
    }

    @Test
    void testCreateProjectB() {
        // Successfully create project
        int referenceProjectId = 0;
        try {
            systemApp.createProject("a");
            referenceProjectId = (SystemCalendar.getCurrentYear() % 100) * 1000 + 1;
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
        Assertions.assertEquals("", errorMessage);
        Assertions.assertEquals(Map.of(referenceProjectId, "a"), systemApp.listProjects());
    }

    @Test
    void testCreateProjectC() {
        // Project with given name already exists
        int referenceProjectId = 0;
        try {
            systemApp.createProject("a");
            referenceProjectId = (SystemCalendar.getCurrentYear() % 100) * 1000 + 1;
            systemApp.createProject("a");
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
        Assertions.assertEquals("Project with that name already exists", errorMessage);
        Assertions.assertEquals(Map.of(referenceProjectId, "a"), systemApp.listProjects());


    }
}
