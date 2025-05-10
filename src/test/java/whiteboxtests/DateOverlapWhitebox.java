package whiteboxtests;

import app.CalendarConverter;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import java.util.Calendar;

public class DateOverlapWhitebox {
    Calendar start1 = null;
    Calendar end1 = null;
    Calendar start2 = null;
    Calendar end2 = null;
    boolean overlap;

    private String errorMessage;

    @Test
    void testDateOverlapA() {
        overlap = CalendarConverter.dateOverlap(start1, end1, start2, end2);
        assertTrue(overlap);
    }

    @Test
    void testDateOverlapB() {
        try {
            end1 = CalendarConverter.getCalendar(1, 2, 2000);
            start2 = CalendarConverter.getCalendar(1, 1, 2000);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }

        overlap = CalendarConverter.dateOverlap(start1, end1, start2, end2);
        assertTrue(overlap);
    }

    @Test
    void testDateOverlapC() {
        try {
            end1 = CalendarConverter.getCalendar(1, 1, 2000);
            start2 = CalendarConverter.getCalendar(1, 2, 2000);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        overlap = CalendarConverter.dateOverlap(start1, end1, start2, end2);
        assertFalse(overlap);
    }

    @Test
    void testDateOverlapD() {
        try {
            start1 = CalendarConverter.getCalendar(1, 1, 2000);
            end2 = CalendarConverter.getCalendar(1, 2, 2000);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        overlap = CalendarConverter.dateOverlap(start1, end1, start2, end2);
        assertTrue(overlap);
    }

    @Test
    void testDateOverlapE() {
        try {
            start1 = CalendarConverter.getCalendar(1, 2, 2000);
            end2 = CalendarConverter.getCalendar(1, 1, 2000);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        overlap = CalendarConverter.dateOverlap(start1, end1, start2, end2);
        assertFalse(overlap);
    }

    @Test
    void testDateOverlapF() {
        try {
            start1 = CalendarConverter.getCalendar(1, 1, 2000);
            end1 = CalendarConverter.getCalendar(30, 6, 2000);
            start2 = CalendarConverter.getCalendar(1, 6, 2000);
            end2 = CalendarConverter.getCalendar(1, 12, 2000);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        overlap = CalendarConverter.dateOverlap(start1, end1, start2, end2);
        assertTrue(overlap);
    }

    @Test
    void testDateOverlapG() {
        try {
            start1 = CalendarConverter.getCalendar(1, 1, 2000);
            end1 = CalendarConverter.getCalendar(30, 6, 2000);
            start2 = CalendarConverter.getCalendar(1, 7, 2000);
            end2 = CalendarConverter.getCalendar(1, 12, 2000);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        overlap = CalendarConverter.dateOverlap(start1, end1, start2, end2);
        assertFalse(overlap);
    }

    @Test
    void testDateOverlapH() {
        try {
            start2 = CalendarConverter.getCalendar(1, 1, 2000);
            end2 = CalendarConverter.getCalendar(30, 6, 2000);
            start1 = CalendarConverter.getCalendar(1, 7, 2000);
            end1 = CalendarConverter.getCalendar(1, 12, 2000);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        overlap = CalendarConverter.dateOverlap(start1, end1, start2, end2);
        assertFalse(overlap);
    }

}
