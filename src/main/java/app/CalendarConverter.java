package app;

import java.util.Calendar;

public class CalendarConverter {
    public static Calendar getCalendarFromString(String day, String month, String year) throws SystemAppException {
        Calendar calendar = Calendar.getInstance();
        try {
            int dayInt = Integer.parseInt(day);
            int monthInt = Integer.parseInt(month);
            int yearInt = Integer.parseInt(year);
            return getCalendar(dayInt, monthInt, yearInt);
        } catch (NumberFormatException e) {
            throw new SystemAppException("Invalid calendar date");
        }
    }

    public static Calendar getCalendar(int day, int month, int year) throws SystemAppException {
        Calendar calendar = getToday();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);

        int dayCal = calendar.get(Calendar.DAY_OF_MONTH);
        int monthCal = calendar.get(Calendar.MONTH);
        int yearCal = calendar.get(Calendar.YEAR);

        if((dayCal == day) & (monthCal == (month-1)) & (yearCal == year)) {
            return calendar;
        } else {
            throw new SystemAppException("Invalid calendar date");
        }
    }

    public static Calendar getCalendar(int week, int year) throws SystemAppException {
        Calendar calendar = getToday();
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        calendar.set(Calendar.YEAR, year);

        int weekCal = calendar.get(Calendar.WEEK_OF_YEAR);
        int yearCal = calendar.get(Calendar.YEAR);

        if((weekCal == week) & (yearCal == year)) {
            return calendar;
        } else {
            throw new SystemAppException("Invalid calendar date");
        }
    }

    public static Calendar getToday() {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        return today;
    }

    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }
}
