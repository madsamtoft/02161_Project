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

    public Project(String name, int id) {
        this.name = name.toLowerCase();
        this.id = id;
        this.activities = new ArrayList<>();
    }

    private boolean isProjectLeader(String employee) {
        return this.projectLeader == null || employee.toLowerCase().equals(this.projectLeader.name());
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
        assert actor != null && name != null;
        if (!isProjectLeader(actor)) {
            throw new SystemAppException("Employee is not Project Leader");
        } else if (name.isEmpty()) {
            throw new SystemAppException("Project Name cannot be empty");
        } else {
            this.name = name.toLowerCase();
        }
        assert this.name.equals(name.toLowerCase());
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
        } else if (endDate == null || startDate.before(endDate)) {
            this.startDate = startDate;
        } else {
            throw new SystemAppException("Start date must be before end date");
        }

    }

    public Calendar getEndDate() {
        return endDate;
    }
    public void setEndDate(String actor, Calendar endDate) throws SystemAppException {
        if (!isProjectLeader(actor)) {
            throw new SystemAppException("Employee is not Project Leader");
        } else if (startDate == null || startDate.before(endDate)) {
            this.endDate = endDate;
        } else {
            throw new SystemAppException("End date must be after start date");
        }
    }

    public String getCustomer() {
        return customer;
    }
    public void setCustomer(String actor, String customer) throws SystemAppException {
        if (!isProjectLeader(actor)) {
            throw new SystemAppException("User not authorized to add customer to project \"" + getName() + "\"");
        }
        this.customer = customer.toLowerCase();
    }

    public Employee getProjectLeader() {
        return projectLeader;
    }

    public void createActivity(String actor, String activityName) throws SystemAppException {
        if (!isProjectLeader(actor)) {
            throw new SystemAppException("Employee is not Project Leader");
        } else if (activityExists(activityName.toLowerCase())) {
            throw new SystemAppException("Activity Name already taken");
        }
        else {
            activities.add(new Activity(activityName.toLowerCase()));
        }
    }

    public void assignEmployee(String actor, String activityName, Employee employee) throws SystemAppException {
        if (!isProjectLeader(actor)) {
            throw new SystemAppException("Employee is not Project Leader");
        } else {
            getActivity(activityName).assignEmployee(employee);
        }
    }

    private Activity getActivity(String activityName) throws SystemAppException {
        for (Activity activity : activities) {
            if (activityName.toLowerCase().equals(activity.getName())) {
                return activity;
            }
        }
        throw new SystemAppException("No such activity found");
    }

    public List<String> getActivityNameList() {
        List<String> activityNames = new LinkedList<>();
        for(Activity activity: activities) {
            activityNames.add(activity.getName());
        }
        return activityNames;
    }

    public boolean hasActivity(String activity) {
        return activities.stream().anyMatch(a -> a.getName().equals(activity.toLowerCase()));
    }

    public void registerTimeToday(String activityName, Employee employee, int fullHours, int minutes) throws SystemAppException {
        getActivity(activityName).registerTimeToday(employee, fullHours, minutes);
    }

    public double getActivityHoursToday(String activityName, Employee employee) throws SystemAppException {
        Activity activity = getActivity(activityName);
        return activity.getHoursToday(employee);
    }

    public void setTimeActivity(String activityName, Employee employee, int hours, int minutes, Calendar date) throws SystemAppException {
        Activity activity = getActivity(activityName);
        activity.registerTime(employee,hours,minutes,date);
    }

    public double getActivityHours(String activityName, Employee employee, Calendar date) throws SystemAppException{
        Activity activity = getActivity(activityName);
        return activity.getHours(employee,date);
    }

    public double getActivityTotalHoursEmployee(String activityName, Employee employee) throws SystemAppException {
        return getActivity(activityName).getRegisteredHoursTotal(employee);
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
        return activities.stream().anyMatch(a -> activityName.toLowerCase().equals(a.getName()));
    }

    public Calendar getActivityStartWeek(String activity) throws SystemAppException {
        return getActivity(activity).getStartWeek();
    }
    public void setActivityStartWeek(String actor, String activity, Calendar startWeek) throws SystemAppException {
        if (isProjectLeader(actor)) {
            getActivity(activity).setStartWeek(startWeek);
        } else {
            throw new SystemAppException("Employee is not Project Leader");
        }
    }

    public Calendar getActivityEndWeek(String activity) throws SystemAppException {
        return getActivity(activity).getEndWeek();
    }
    public void setActivityEndWeek(String actor, String activity, Calendar endWeek) throws SystemAppException {
        if (isProjectLeader(actor)) {
            getActivity(activity).setEndWeek(endWeek);
        } else {
            throw new SystemAppException("Employee is not Project Leader");
        }
    }

    public int getActivityEstimatedHours(String activity) throws SystemAppException {
        return getActivity(activity).getEstimatedHours();
    }
    public void setActivityEstimatedHours(String actor, String activity, int hours) throws SystemAppException {
        if (isProjectLeader(actor)) {
            getActivity(activity).setEstimatedHours(hours);
        } else {
            throw new SystemAppException("Employee is not Project Leader");
        }
    }

    public double getActivityTotalHours(String activity) throws SystemAppException {
        return getActivity(activity).getTotalHours();
    }

    public boolean hasEmployeeAssignedToActivity(String activity, Employee employee) throws SystemAppException {
        return getActivity(activity).isEmployeeAssigned(employee);
    }

    public int assignedActivityAmount(Employee employee, Calendar start, Calendar end) {
        int sum = 0;
        for (Activity activity: activities) {
            if(activity.isEmployeeAssigned(employee)) {
                if(SystemCalendar.dateOverlap(start, end, activity.getStartWeek(), activity.getEndWeek())) {
                    sum += 1;
                }
            }
        }
        return sum;
    }

    public List<String> getOccupiedEmployees() {
        ArrayList<String> occupiedEmployeeNames = new ArrayList<>();
        for (Activity activity : activities) {
            occupiedEmployeeNames.addAll(activity.getEmployeesThisWeekList());
        }
        return occupiedEmployeeNames;
    }
}
