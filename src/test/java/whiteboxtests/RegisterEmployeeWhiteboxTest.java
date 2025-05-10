package whiteboxtests;

import app.SystemApp;
import app.SystemAppException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class RegisterEmployeeWhiteboxTest {
    private final SystemApp systemApp = new SystemApp();
    private String errorMessage = "";

    @Test
    void testRegisterEmployeeA() {
        try {
            systemApp.registerEmployee("seba");
        } catch (SystemAppException ignored) {

        }
        assertEquals(List.of("huba", "seba"),systemApp.listEmployees());
        try {
            systemApp.registerEmployee("Sebastian1");
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Employee username must be 1-4 letters", errorMessage);
    }

    @Test
    void testRegisterEmployeeB() {
        try {
            systemApp.registerEmployee("seba");
        } catch (SystemAppException ignored) {

        }
        assertEquals(List.of("huba", "seba"),systemApp.listEmployees());
        try {
            systemApp.registerEmployee("Seb1");
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Employee username must be 1-4 letters", errorMessage);
    }

    @Test
    void testRegisterEmployeeC() {
        try {
            systemApp.registerEmployee("seba");
        } catch (SystemAppException ignored) {

        }
        assertEquals(List.of("huba", "seba"),systemApp.listEmployees());
        try {
            systemApp.registerEmployee("Seba");
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("An employee with that username already exists", errorMessage);
    }

    @Test
    void testRegisterEmployeeD() {
        assertEquals(List.of("huba"),systemApp.listEmployees());
        try {
            systemApp.registerEmployee("Seba");
        } catch (SystemAppException e) {
            errorMessage = e.getMessage();
        }
        assertEquals(List.of("huba", "seba"),systemApp.listEmployees());
    }
}
