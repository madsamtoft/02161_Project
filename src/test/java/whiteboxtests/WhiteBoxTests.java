package whiteboxtests;

import app.SystemApp;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;


public class WhiteBoxTests {
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
            systemApp.registerTimeFirmActivity("huba", "a", 25, 0, 1, 1, 2025);
        } catch (Exception e){
            errorMessage = e.getMessage();
        }
        assertEquals("Cannot work more than 24 hours a day", errorMessage);
    }
//    @Test
//    void testRegisterTimeB() {
//        try {
//            systemApp.createFirmActivity("a");
//        } catch (Exception e){
//            errorMessage = e.getMessage();
//        }
//
//        try {
//            systemApp.registerTimeFirmActivity("huba", "a", 4, 0, 1, 1, 2025);
//        } catch (Exception e){
//            errorMessage = e.getMessage();
//        }
//        assertEquals(4,systemApp.checkRegisteredFirmActivity("huba","a",1,1,2025));
//    }



}
