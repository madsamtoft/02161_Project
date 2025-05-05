package app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

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
        } else if (activityList.stream().anyMatch(a -> activityName.equals(a.getName()))) {
            throw new SystemAppException("Activity Name already taken");
        }
        else {
            activityList.add(new Activity(activityName));
        }
    }


    public Activity getActivity(String activityName) throws SystemAppException {
        for (Activity activity : activityList) {
            if (activityName.equals(activity.getName())) {
                return activity;
            }
        }
        throw new SystemAppException("No such activity found");
    }

    public void registerTimeDaily(Activity activity, Employee employee, double hours) throws SystemAppException {
        if (activity == null) {
            throw new SystemAppException("No Activity To Registers Hours To");
        } else {
            activity.registerTimeDaily(employee, hours);
        }
    }

    public double checkRegisteredDaily(Activity activity, Employee employee) {
        return activity.checkRegisteredDaily(employee);
    }

//    public Map<Activity, Integer> checkRegisteredDaily(Employee employee) {
//        return null;
//    }

//    public void setProjectLeader(Employee projectLeader) {
//        this.projectLeader = projectLeader;
//    }

//    public int getWeeklyActivityAmount(Employee employee) {
//        return 0;
//    }
}
