package app;

import java.util.*;

public class Project {
    private String name;
    private final int id; // Read-only
    private Calendar startDate;
    private Calendar endDate;
    private String customer;
    private Employee projectLeader;
    private final List<Activity> activities;

    public Project(String name, int id) throws SystemAppException {
        if (name.isEmpty()) {
            throw new SystemAppException("Project Name cannot be empty");
        }
        this.name = name;
        this.id = id;
        this.activities = new ArrayList<>();
    }

    private boolean isProjectLeader(String employee) {
        return this.projectLeader == null || employee.equals(this.projectLeader.name());
    }

    public void assignProjectLeader(String actor, Employee employee) throws SystemAppException {
        if (isProjectLeader(actor)) {
            this.projectLeader = employee;
        } else {
            throw new SystemAppException("Employee is not Project Leader");
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String actor, String name) throws SystemAppException {
        if (!isProjectLeader(actor)) {
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

    public void setStartDate(String actor, Calendar startDate) throws SystemAppException {
        if (!isProjectLeader(actor)) {
            throw new SystemAppException("Employee is not Project Leader");
        }
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(String actor, Calendar endDate) throws SystemAppException {
        if (!isProjectLeader(actor)) {
            throw new SystemAppException("Employee is not Project Leader");
        }
        this.endDate = endDate;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String actor, String customer) throws SystemAppException {
        if (!isProjectLeader(actor)) {
            throw new SystemAppException("User not authorized to add customer to project \"" + getName() + "\"");
        }
        this.customer = customer;
    }

    public Employee getProjectLeader() {
        return projectLeader;
    }

    public void createActivity(String actor, String activityName) throws SystemAppException {
        if (!isProjectLeader(actor)) {
            throw new SystemAppException("Employee is not Project Leader");
//        } else if (activities.stream().anyMatch(a -> activityName.equals(a.getName()))) {
        } else if (activityExists(activityName)) {
            throw new SystemAppException("Activity Name already taken");
        }
        else {
            activities.add(new Activity(activityName));
        }
    }


    public void assignEmployeeToActivity(String actor, String activityName, Employee employee) throws SystemAppException {
        if (!isProjectLeader(actor)) {
            throw new SystemAppException("Employee is not Project Leader");
        } else {
            getActivity(activityName).assignEmployee(employee);
        }
    }

    private Activity getActivity(String activityName) throws SystemAppException {
        for (Activity activity : activities) {
            if (activityName.equals(activity.getName())) {
                return activity;
            }
        }
        throw new SystemAppException("No such activity found");
    }

    public List<String> listActivities() {
        List<String> activityNames = new LinkedList<>();
        for(Activity activity: activities) {
            activityNames.add(activity.getName());
        }
        return activityNames;
    }

    public boolean hasActivity(String activity) {
        return activities.stream().anyMatch(a -> a.getName().equals(activity));
    }

    public void registerTimeDaily(String activityName, Employee employee, int fullHours, int minutes) throws SystemAppException {
        getActivity(activityName).registerTimeDaily(employee, fullHours, minutes);
    }

    public double checkRegisteredDaily(String activityName, Employee employee) throws SystemAppException {
        Activity activity = getActivity(activityName);
        return activity.checkRegisteredDaily(employee);
    }

    public void registerTimeActivity(String activityName, Employee employee, int hours, int minutes, Calendar date) throws SystemAppException {
        Activity activity = getActivity(activityName);
        activity.registerTime(employee,hours,minutes,date);
    }

    public double checkRegisteredActivity(String activityName,Employee employee, Calendar date) throws SystemAppException{
        Activity activity = getActivity(activityName);
        return activity.checkRegistered(employee,date);
    }

    public double checkRegisteredTotalActivity(String activityName, Employee employee) throws SystemAppException {
        return getActivity(activityName).checkRegisteredTotal(employee);
    }

    public void setActivityName(String actor, String activity, String newName) throws SystemAppException {
        if (!isProjectLeader(actor)) {
            throw new SystemAppException("Employee is not Project Leader");
        } else if (activityExists(newName)) {
            throw new SystemAppException("Activity Name already taken");
        }
        getActivity(activity).setName(newName);
    }

    public boolean activityExists(String activityName) {
        return activities.stream().anyMatch(a -> activityName.equals(a.getName()));
    }

    public void setActivityStartWeek(String actor, String activity, Calendar startWeek) throws SystemAppException {
        if (isProjectLeader(actor)) {
            getActivity(activity).setStartWeek(startWeek);
        } else {
            throw new SystemAppException("Employee is not Project Leader");
        }
    }

    public void setActivityEndWeek(String actor, String activity, Calendar endWeek) throws SystemAppException {
        if (isProjectLeader(actor)) {
            getActivity(activity).setEndWeek(endWeek);
        } else {
            throw new SystemAppException("Employee is not Project Leader");
        }
    }

    public void setActivityEstimatedHours(String actor, String activity, int hours) throws SystemAppException {
        if (isProjectLeader(actor)) {
            getActivity(activity).setEstimatedHours(hours);
        } else {
            throw new SystemAppException("Employee is not Project Leader");
        }
    }

    public Calendar getActivityStartWeek(String activity) throws SystemAppException {
        return getActivity(activity).getStartWeek();
    }

    public Calendar getActivityEndWeek(String activity) throws SystemAppException {
        return getActivity(activity).getEndWeek();
    }

    public int getActivityEstimatedHours(String activity) throws SystemAppException {
        return getActivity(activity).getEstimatedHours();
    }

    public double getActivityTotalHours(String activity) throws SystemAppException {
        return getActivity(activity).getTotalHours();
    }

    public boolean hasEmployeeAssignedToActivity(String activity, Employee employee) throws SystemAppException {
        return getActivity(activity).employeeAssigned(employee);
    }

    public int numberOfAssignedActivities(Employee employee, Calendar start, Calendar end) {
        int sum = 0;

        for (Activity activity: activities) {
            if(activity.employeeAssigned(employee)) {
                if(CalendarConverter.dateOverlap(start, end, activity.getStartWeek(), activity.getEndWeek())) {
                    sum += 1;
                }
            }
        }
        return sum;
    }

    public List<String> getOccupiedEmployees() {
        ArrayList<String> occupiedEmployeeNames = new ArrayList<>();
        for (Activity activity : activities) {
            occupiedEmployeeNames.addAll(activity.getOccupiedEmployees());
        }
        return occupiedEmployeeNames;
    }
}
