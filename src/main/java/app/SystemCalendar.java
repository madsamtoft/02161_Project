package app;

import java.util.Calendar;

public class SystemCalendar {
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
        calendar.set(Calendar.DAY_OF_WEEK, 0);
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

    public static Calendar getThisWeek() {
        Calendar thisWeek = getToday();
        thisWeek.set(Calendar.DAY_OF_WEEK, 0);
        return thisWeek;
    }

    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static boolean dateOverlap(Calendar start1, Calendar end1, Calendar start2, Calendar end2) {
        //assert ((start1 == null | end1 == null) || (start1.before(end1)) & ((start2 == null | end2 == null) || start2.before(end2)));
        boolean s1null = start1 == null;
        boolean e1null = end1 == null;
        boolean s2null = start2 == null;
        boolean e2null = end2 == null;
        boolean overlap = true;

        if(!(s1null | e1null | s2null | e2null)){
            //assert (start1.before(end1) & start2.before(end2));
            overlap = (start1.getTimeInMillis() <= end2.getTimeInMillis()) & (start2.getTimeInMillis() <= end1.getTimeInMillis());
        } else if(!(e1null | s2null)) {
            //assert (start1 == null || (start1.before(end1)));
            overlap = start2.getTimeInMillis() <= end1.getTimeInMillis();
        } else if(!(e2null | s1null)) {
            //assert (end1 == null || start1.before(end1));
            overlap = start1.getTimeInMillis() <= end2.getTimeInMillis();
        }
        //assert (true);
        return overlap;
    }
}
