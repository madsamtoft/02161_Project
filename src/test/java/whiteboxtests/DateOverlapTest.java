package whiteboxtests;

import app.SystemCalendar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

public class DateOverlapTest {
    Calendar start1 = null;
    Calendar end1 = null;
    Calendar start2 = null;
    Calendar end2 = null;
    boolean overlap;

    private String errorMessage;

    @Test
    void testDateOverlapA() {
        overlap = SystemCalendar.dateOverlap(start1, end1, start2, end2);
        Assertions.assertTrue(overlap);
    }

    @Test
    void testDateOverlapB() {
        try {
            end1 = SystemCalendar.getCalendar(1, 2, 2000);
            start2 = SystemCalendar.getCalendar(1, 1, 2000);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

        overlap = SystemCalendar.dateOverlap(start1, end1, start2, end2);
        Assertions.assertTrue(overlap);
    }

    @Test
    void testDateOverlapC() {
        try {
            end1 = SystemCalendar.getCalendar(1, 1, 2000);
            start2 = SystemCalendar.getCalendar(1, 2, 2000);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
        overlap = SystemCalendar.dateOverlap(start1, end1, start2, end2);
        Assertions.assertFalse(overlap);
    }

    @Test
    void testDateOverlapD() {
        try {
            start1 = SystemCalendar.getCalendar(1, 1, 2000);
            end2 = SystemCalendar.getCalendar(1, 2, 2000);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
        overlap = SystemCalendar.dateOverlap(start1, end1, start2, end2);
        Assertions.assertTrue(overlap);
    }

    @Test
    void testDateOverlapE() {
        try {
            start1 = SystemCalendar.getCalendar(1, 2, 2000);
            end2 = SystemCalendar.getCalendar(1, 1, 2000);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
        overlap = SystemCalendar.dateOverlap(start1, end1, start2, end2);
        Assertions.assertFalse(overlap);
    }

    @Test
    void testDateOverlapF() {
        try {
            start1 = SystemCalendar.getCalendar(1, 1, 2000);
            end1 = SystemCalendar.getCalendar(30, 6, 2000);
            start2 = SystemCalendar.getCalendar(1, 6, 2000);
            end2 = SystemCalendar.getCalendar(1, 12, 2000);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
        overlap = SystemCalendar.dateOverlap(start1, end1, start2, end2);
        Assertions.assertTrue(overlap);
    }

    @Test
    void testDateOverlapG() {
        try {
            start1 = SystemCalendar.getCalendar(1, 1, 2000);
            end1 = SystemCalendar.getCalendar(30, 6, 2000);
            start2 = SystemCalendar.getCalendar(1, 7, 2000);
            end2 = SystemCalendar.getCalendar(1, 12, 2000);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
        overlap = SystemCalendar.dateOverlap(start1, end1, start2, end2);
        Assertions.assertFalse(overlap);
    }

    @Test
    void testDateOverlapH() {
        try {
            start1 = SystemCalendar.getCalendar(1, 7, 2000);
            end1 = SystemCalendar.getCalendar(1, 12, 2000);
            start2 = SystemCalendar.getCalendar(1, 1, 2000);
            end2 = SystemCalendar.getCalendar(30, 6, 2000);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
        overlap = SystemCalendar.dateOverlap(start1, end1, start2, end2);
        Assertions.assertFalse(overlap);
    }
}
