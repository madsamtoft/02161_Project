package whiteboxtests;

import app.SystemApp;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;


public class RegisterTimeTest {
    private SystemApp systemApp = new SystemApp();
    private String errorMessage;

    @Test
    void testRegisterTimeA() {
        try {
            systemApp.createFirmActivity("a");
        } catch (Exception e){
            errorMessage = e.getMessage();
        }

        try {
            systemApp.registerTimeFirmActivity("a", "huba", 25, 0, 1, 1, 2025);
        } catch (Exception e){
            errorMessage = e.getMessage();
        }
        assertEquals("Cannot work more than 24 hours a day", errorMessage);
    }
    @Test
    void testRegisterTimeB() {
        try {
            systemApp.createFirmActivity("a");
        } catch (Exception e){
            errorMessage = e.getMessage();
        }

        try {
            systemApp.registerTimeFirmActivity("a", "huba", 4, 0, 1, 1, 2025);
        } catch (Exception e){
            errorMessage = e.getMessage();
        }
        try {
            assertEquals(4.0,systemApp.getFirmActivityHours("a","huba",1,1,2025),0);
        }
        catch (Exception e){
            errorMessage = e.getMessage();
        }
    }
    @Test
    void testRegisterTimeC() {
        try {
            systemApp.createFirmActivity("a");
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }

        try {
            systemApp.registerTimeFirmActivity( "a", "huba",25, 0, 1, 1, 2025);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Cannot work more than 24 hours a day", errorMessage);
    }
    @Test
    void testRegisterTimeD() {
        try {
            systemApp.createFirmActivity("a");
        } catch (Exception e){
            errorMessage = e.getMessage();
        }

        try {
            systemApp.registerTimeFirmActivity("a", "huba", 4, 0, 1, 1, 2025);
        } catch (Exception e){
            errorMessage = e.getMessage();
        }
        try {
            assertEquals(4.0,systemApp.getFirmActivityHours("a","huba",1,1,2025),0);
        }
        catch (Exception e){
            errorMessage = e.getMessage();
        }
    }
}
