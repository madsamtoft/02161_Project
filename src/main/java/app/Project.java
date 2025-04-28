package app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Project {
    private String name;
    private int id; // Read only
    private Calendar startDate;
    private Calendar endDate;
    private String customer;
    private Employee projectLeader;
    private List<Activity> activityList;

    public Project(String name, int id) throws SystemAppException {
        if (name.isEmpty()) {
            throw new SystemAppException("Project Name cannot be empty");
        }
        this.name = name;
        this.id = id;
        this.activityList = new ArrayList<>();
    }

    private boolean isProjectLeader(Employee employee) {
        return employee.equals(this.projectLeader);
    }

    public void assignProjectLeader(Employee employee) {
        this.projectLeader = employee;
    }

    public String getName() {
        return this.name;
    }
    public void setName(Employee employee, String name) throws SystemAppException {
        if (!isProjectLeader(employee)) {
            throw new SystemAppException("Employee is not Project Leader");
        } else if (name.isEmpty()) {
            throw new SystemAppException("Project Name cannot be empty");
        } else {
            this.name = name;
        }
    }
    public int getId() {
        return this.id;
    }
    public Calendar getStartDate() {
        return startDate;
    }
    public void setStartDate(Employee employee, Calendar startDate) throws SystemAppException {
        if (!isProjectLeader(employee)) {
            throw new SystemAppException("Employee is not Project Leader");
        }
        this.startDate = startDate;
    }
    public Calendar getEndDate() {
        return endDate;
    }
    public void setEndDate(Employee employee, Calendar endDate) throws SystemAppException {
        if (!isProjectLeader(employee)) {
            throw new SystemAppException("Employee is not Project Leader");
        }
        this.endDate = endDate;
    }
    public String getCustomer() {
        return customer;
    }
    public void setCustomer(Employee employee, String customer) throws SystemAppException {
        if (!isProjectLeader(employee)) {
            throw new SystemAppException("Employee is not Project Leader");
        }
        this.customer = customer;
    }
    public Employee getProjectLeader() {
        return projectLeader;
    }

    public void createActivity(Employee employee, String activityName) throws SystemAppException {
        if (!isProjectLeader(employee)) {
            throw new SystemAppException("Employee is not Project Leader");
        } else if (activityName.isEmpty()) {
            throw new SystemAppException("Activity Name cannot be empty");
        } else if (activityExists(activityName)) {
            throw new SystemAppException("Activity Name already taken");
        }
        else {
            activityList.add(new Activity(activityName));
        }
    }

    private boolean activityExists(String activityName) {
        try {
            getActivity(activityName);
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    public Activity getActivity(String activityName) throws SystemAppException {
        for (Activity activity : activityList) {
            if (activityName.equals(activity.getName())) {
                return activity;
            }
        }
        throw new SystemAppException("No such activity found");
    }

//    public void setProjectLeader(Employee projectLeader) {
//        this.projectLeader = projectLeader;
//    }
}
