package whiteboxtests;

import app.SystemApp;
import app.SystemAppException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CreateEmployeeTest {
    private final SystemApp systemApp = new SystemApp();
    private String errorMessage = "";

    @Test
    void testCreateEmployeeA() {
        try {
            systemApp.createEmployee("seba");
        } catch (SystemAppException ignored) {

        }
        assertEquals(List.of("huba", "seba"),systemApp.getEmployeeList());
        try {
            systemApp.createEmployee("Sebastian1");
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Employee username must be 1-4 letters", errorMessage);
    }

    @Test
    void testCreateEmployeeB() {
        try {
            systemApp.createEmployee("seba");
        } catch (SystemAppException ignored) {

        }
        assertEquals(List.of("huba", "seba"),systemApp.getEmployeeList());
        try {
            systemApp.createEmployee("Seb1");
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Employee username must be 1-4 letters", errorMessage);
    }

    @Test
    void testCreateEmployeeC() {
        try {
            systemApp.createEmployee("seba");
        } catch (SystemAppException ignored) {

        }
        assertEquals(List.of("huba", "seba"),systemApp.getEmployeeList());
        try {
            systemApp.createEmployee("Seba");
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("An employee with that username already exists", errorMessage);
    }

    @Test
    void testCreateEmployeeD() {
        assertEquals(List.of("huba"),systemApp.getEmployeeList());
        try {
            systemApp.createEmployee("Seba");
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
        assertEquals(List.of("huba", "seba"),systemApp.getEmployeeList());
    }
}
