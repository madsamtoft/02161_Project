package app;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Activity {
    private String name;
    private Map<Employee, Map<Calendar, Integer>> employeeDateHours;
    private Calendar startWeek;
    private Calendar endWeek;
    private int estimatedHours;

    public Activity(String name) throws SystemAppException {
        if (name.isEmpty()) {
            throw new SystemAppException("Activity Name cannot be empty");
        }
        this.name = name;
        this.employeeDateHours = new HashMap<>();
    }



    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Calendar getStartWeek() {
        return startWeek;
    }
    public void setStartWeek(Calendar startWeek) {
        this.startWeek = startWeek;
    }

    public Calendar getEndWeek() {
        return endWeek;
    }
    public void setEndWeek(Calendar endWeek) {
        this.endWeek = endWeek;
    }

    public int getEstimatedHours() {
        return estimatedHours;
    }
    public void setEstimatedHours(int estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    private Calendar getToday() {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        return today;
    }

    public void registerTimeDaily(Employee employee, int hours) {
        Map<Calendar, Integer> dateHours;
        if (employeeDateHours.containsKey(employee)) {
            dateHours = employeeDateHours.get(employee);
        } else {
            dateHours = new HashMap<>();
            employeeDateHours.put(employee, dateHours);
        }
        Calendar today = getToday();
        long time = today.getTime().getTime();
        System.out.println(time);
        System.out.println(today.hashCode());

        int alreadyRegistered = 0;
        if (dateHours.containsKey(today)) {
            alreadyRegistered = dateHours.get(today);
        }
        dateHours.put(today, hours + alreadyRegistered);
    }

    public int checkRegisteredDaily(Employee employee) {
        if (!employeeDateHours.containsKey(employee) || !employeeDateHours.get(employee).containsKey(getToday())) {
            return 0;
        }
        return employeeDateHours.get(employee).get(getToday());
    }
}
