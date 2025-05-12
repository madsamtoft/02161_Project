package app;

import java.util.*;

import static app.SystemCalendar.*;

public class Activity {
    private String name;
    private final Map<Employee, Map<Calendar, Double>> employeeDateHours;
    private final List<Employee> assignedEmployees;
    private Calendar startWeek;
    private Calendar endWeek;
    private int estimatedHours;

    public Activity(String name) throws SystemAppException {
        if (name.isEmpty()) {
            throw new SystemAppException("Activity Name cannot be empty");
        }
        this.name = name.toLowerCase();
        this.employeeDateHours = new HashMap<>();
        this.assignedEmployees = new ArrayList<>();
        this.startWeek = null;
        this.endWeek = null;
    }

    public boolean isEmployeeAssigned(Employee employee) {
        return this.assignedEmployees.contains(employee);
    }

    public void assignEmployee(Employee employee) throws SystemAppException {
        if(isEmployeeAssigned(employee)) {
            throw new SystemAppException("Employee is already assigned to this activity");
        }
        this.assignedEmployees.add(employee);
    }

    public void registerTimeToday(Employee employee, int fullHours, int minutes) throws SystemAppException {
        if (fullHours < 0 || minutes < 0) {
            throw new SystemAppException("Hours and minutes can't be negative");
        }

        Map<Calendar, Double> dateHours;
        if (employeeDateHours.containsKey(employee)) {
            dateHours = employeeDateHours.get(employee);
        } else {
            dateHours = new HashMap<>();
            employeeDateHours.put(employee, dateHours);
        }
        Calendar today = getToday();

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

    public double getHoursToday(Employee employee) {
        return getHours(employee, getToday());
    }

    public void registerTime(Employee employee, int fullHours, int minutes, Calendar date) throws SystemAppException {

        //assert employee != null && fullHours >= 0 && minutes >= 0 && date !=null:"precondition";

        Map<Calendar, Double> dateHours;
        if (employeeDateHours.containsKey(employee)) {
            dateHours = employeeDateHours.get(employee);
        } else {
            dateHours = new HashMap<>();
            employeeDateHours.put(employee, dateHours);
        }

        double hours = fullHours + (minutes/60.);
        double putHours = Math.ceil(hours*2) / 2.;
        if (putHours > 24.) {
            throw new SystemAppException("Cannot work more than 24 hours a day");
        }
        dateHours.put(date,putHours);

        //assert employeeDateHours.containsKey(employee) && (employeeDateHours.get(employee).get(date) == putHours) && putHours <= 24:"postcondition" ;
    }

    public double getHours(Employee employee, Calendar date ) {
        if (!employeeDateHours.containsKey(employee)) {
            return 0;
        }
        Map<Calendar, Double> dateHours = employeeDateHours.get(employee);
        if (!dateHours.containsKey(date)) {
            return 0;
        }
        return dateHours.get(date);
    }

    public double getRegisteredHoursTotal(Employee employee) {
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
    public void setName(String name) {
        this.name = name.toLowerCase();
    }

    public Calendar getStartWeek() {
        return startWeek;
    }
    public void setStartWeek(Calendar startWeek) throws SystemAppException {
        if (endWeek == null || startWeek.before(endWeek)) {
            this.startWeek = startWeek;
        } else {
            throw new SystemAppException("Start date must be before end date");
        }
    }

    public Calendar getEndWeek() {

        return endWeek;
    }
    public void setEndWeek(Calendar endWeek) throws SystemAppException {
        if (startWeek == null || startWeek.before(endWeek)) {
            this.endWeek = endWeek;
        } else {
            throw new SystemAppException("End date must be after start date");
        }
    }

    public int getEstimatedHours() {
        return estimatedHours;
    }
    public void setEstimatedHours(int estimatedHours) throws SystemAppException {
        if (estimatedHours < 0) {
            throw new SystemAppException("Cannot set negative estimated hours");
        }
        this.estimatedHours = estimatedHours;
    }

    public List<String> getEmployeesThisWeekList() {
        Calendar thisWeek = SystemCalendar.getThisWeek();
        if (SystemCalendar.dateOverlap(startWeek, endWeek, thisWeek, thisWeek)) {
            return assignedEmployees.stream().map(Employee::name).toList();
        } else {
            return new ArrayList<>();
        }

    }

    public double getTotalHours() {
        double totalHours = 0;
        for (Map<Calendar, Double> dateHours : employeeDateHours.values()) {
            for (double hours : dateHours.values()) {
                totalHours += hours;
            }
        }
        return totalHours;
    }
}
