package app;

import java.util.Calendar;
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
}
