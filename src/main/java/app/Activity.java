package app;

import java.util.*;

import static app.CalendarConverter.getCalendar;
import static app.CalendarConverter.getToday;

public class Activity {
    private String name;
    private Map<Employee, Map<Calendar, Double>> employeeDateHours;
    private List<Employee> assignedEmployees;
    private Calendar startWeek;
    private Calendar endWeek;
    private int estimatedHours;

    public Activity(String name) throws SystemAppException {
        if (name.isEmpty()) {
            throw new SystemAppException("Activity Name cannot be empty");
        }
        this.name = name;
        this.employeeDateHours = new HashMap<>();
        this.assignedEmployees = new ArrayList<>();
    }



    public boolean employeeAssigned(Employee employee) {
        return this.assignedEmployees.contains(employee);
    }

    public void assignEmployee(Employee employee) throws SystemAppException {
        if(employeeAssigned(employee)) {
            throw new SystemAppException("Employee is already assigned to this activity");
        }
        this.assignedEmployees.add(employee);
    }

    public void registerTimeDaily(Employee employee, int fullHours, int minutes) throws SystemAppException {
        Map<Calendar, Double> dateHours;
        if (employeeDateHours.containsKey(employee)) {
            dateHours = employeeDateHours.get(employee);
        } else {
            dateHours = new HashMap<>();
            employeeDateHours.put(employee, dateHours);
        }
        Calendar today = getToday();
//        long time = today.getTime().getTime();
//        System.out.println(time);
//        System.out.println(today.hashCode());

        double alreadyRegistered = 0;
        if (dateHours.containsKey(today)) {
            alreadyRegistered = dateHours.get(today);
        }

        double hours = fullHours + (minutes/60.);
        double putHours = Math.ceil(hours*2) / 2.;
        if ((putHours + alreadyRegistered) > 24.) {
            throw new SystemAppException("Cannot work more than 24 hours a day");
        }
        dateHours.put(today, putHours + alreadyRegistered);
    }

    public double checkRegisteredDaily(Employee employee) {
        if (!employeeDateHours.containsKey(employee) || !employeeDateHours.get(employee).containsKey(getToday())) {
            return 0;
        }
        return employeeDateHours.get(employee).get(getToday());
    }

    // not done yet (karl)
    public void registerTime(Employee employee, int fullHours, int minutes, int day, int month, int year) throws SystemAppException {
        Map<Calendar, Double> dateHours;
        if (employeeDateHours.containsKey(employee)) {
            dateHours = employeeDateHours.get(employee);
        } else {
            dateHours = new HashMap<>();
            employeeDateHours.put(employee, dateHours);
        }

        Calendar date = getCalendar(day,month,year);

        double hours = fullHours + (minutes/60.);
        double putHours = Math.ceil(hours*2) / 2.;
        if (putHours > 24.) {
            throw new SystemAppException("Cannot work more than 24 hours a day");
        }
        dateHours.put(date,putHours);
    }

    public double checkRegistered(Employee employee, int day, int month, int year) throws SystemAppException {
        Calendar date = getCalendar(day, month, year);
        if (!employeeDateHours.containsKey(employee) || !employeeDateHours.get(employee).containsKey(date)) {
            return 0;
        }
        return employeeDateHours.get(employee).get(date);
    }

    public double checkRegisteredTotal(Employee employee) {
        if (!employeeDateHours.containsKey(employee)) {
            return 0;
        }

        double hoursTotal = 0;
        for (double hours : employeeDateHours.get(employee).values()) {
            hoursTotal += hours;
        }
        return hoursTotal;
    }



    public String getName() {
        return name;
    }
    public void setName(String actor, String name) {
        // TODO: check actor
        this.name = name;
    }

    public Calendar getStartWeek() {
        return startWeek;
    }
    public void setStartWeek(String actor, Calendar startWeek) {
        // TODO: check actor
        this.startWeek = startWeek;
    }

    public Calendar getEndWeek() {
        return endWeek;
    }
    public void setEndWeek(String actor, Calendar endWeek) {
        // TODO: check actor
        this.endWeek = endWeek;
    }

    public int getEstimatedHours() {
        return estimatedHours;
    }
    public void setEstimatedHours(String actor, int estimatedHours) {
        // TODO: check actor
        this.estimatedHours = estimatedHours;
    }
}
