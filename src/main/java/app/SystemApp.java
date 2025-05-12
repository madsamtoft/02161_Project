package app;

import java.util.*;

public class SystemApp {
    private final List<Project> projects = new LinkedList<>();
    private final List<Activity> firmActivityList = new ArrayList<>();
    private int projectIdCounter = 1;
    private int currentYear = 0;
    private final List<Employee> employees = new ArrayList<>();

    public SystemApp() {
        employees.add(new Employee("huba"));
    }

    private boolean projectExists(String name) {
        return projects.stream().anyMatch(p -> p.getName().equals(name));
    }

    private int getNewProjectId() {
        int currentYear = SystemCalendar.getCurrentYear();
        if (this.currentYear != currentYear) {
            projectIdCounter = 1;
            this.currentYear = currentYear;
        }
        return (currentYear % 100) * 1000 + projectIdCounter++;
    }

    public void createProject(String name) throws SystemAppException {
        if (name.isEmpty()) {
            throw new SystemAppException("Project Name cannot be empty");
        }
        if (projectExists(name))  {
            throw new SystemAppException("Project with that name already exists");
        }
        projects.add(new Project(name, getNewProjectId()));
    }

    public void setProjectName(String actor, String project, String newName) throws SystemAppException {
        if (projectExists(newName)) {
            throw new SystemAppException("Project with that name already exists");
        }
        getProject(project).setName(actor, newName);
    }

    public void setProjectStartDate(String actor, String project, Calendar startDate) throws SystemAppException {
        getProject(project).setStartDate(actor, startDate);
    }

    public void setProjectEndDate(String actor, String project, Calendar endDate) throws SystemAppException {
        getProject(project).setEndDate(actor, endDate);
    }

    public void setProjectCustomer(String actor, String project, String customer) throws SystemAppException {
        getProject(project).setCustomer(actor, customer);
    }

    public void createEmployee(String name) throws SystemAppException {
        if (name.isEmpty() || name.length() > 4) {
            throw new SystemAppException("Employee username must be 1-4 letters");
        }
        if (!name.toLowerCase().matches("[a-z]+")) {
            throw new SystemAppException("Employee username must be 1-4 letters");
        }
        if (employeeExists(name)) {
            throw new SystemAppException("An employee with that username already exists");
        }
        employees.add(new Employee(name.toLowerCase()));
    }

    public void createFirmActivity(String activityName) throws SystemAppException {
        if (firmActivityList.stream().anyMatch(a -> activityName.equals(a.getName()))) {
            throw new SystemAppException("Firm Activity Name already taken");
        } else {
            firmActivityList.add(new Activity(activityName));
        }
    }

    private Activity getFirmActivity(String name) throws SystemAppException{
        for (Activity activity : firmActivityList) {
            if (name.equals(activity.getName())) {
                return activity;
            }
        }
        throw new SystemAppException("Firm activity " + name + " does not exist");
    }

    public List<String> getFirmActivityList() {
        List<String> firmActivityNames = new LinkedList<>();
        for(Activity activity: firmActivityList) {
            firmActivityNames.add(activity.getName());
        }
        return firmActivityNames;
    }

    public boolean firmActivityExists(String name) {
        return firmActivityList.stream().anyMatch(a -> a.getName().equals(name));
    }

    public void registerTimeFirmActivity(String employeeName, String firmActivityName, int hours, int minutes, int day, int month, int year) throws SystemAppException{
        Activity firmActivity = getFirmActivity(firmActivityName);
        Calendar date = SystemCalendar.getCalendar(day, month, year);
        if (!(hours >= 0 && minutes >= 0 )){
            throw new SystemAppException("Hours and minutes can't be negative");
        }
        firmActivity.registerTime(getEmployee(employeeName),hours,minutes,date);
    }

    public double getFirmActivityHours(String employeeName, String firmActivityName, int day, int month, int year) throws SystemAppException{
        Activity firmActivity = getFirmActivity(firmActivityName);
        Calendar date = SystemCalendar.getCalendar(day, month, year);
        return firmActivity.getHours(getEmployee(employeeName),date);
    }

    public void createActivity(String actor, String project, String activityName) throws SystemAppException {
        getProject(project).createActivity(actor, activityName);
    }

    private Project getProject(String name) throws SystemAppException {
        for (Project project : projects) {
            if (project.getName().equals(name)) {
                return project;
            }
        }
        throw new SystemAppException("Project " + name + " does not exist");
    }

    private Employee getEmployee(String name) throws SystemAppException {
        for (Employee employee : employees) {
            if (employee.name().equals(name)) {
                return employee;
            }
        }
        throw new SystemAppException("Employee " + name + " does not exist");
    }

    public boolean employeeExists(String name) {
        return employees.stream().anyMatch(e -> e.name().equals(name.toLowerCase()));
    }

    public void setProjectLeader(String actor, String project, String employeeName) throws SystemAppException {
        // TODO: this should be changed to use project IDs
        getProject(project).assignProjectLeader(actor, getEmployee(employeeName));
    }

    public Map<Integer, String> listProjects() {
        // This method only works when EVERY SINGLE project ID is unique
        Map<Integer, String> projectMap = new HashMap<>();
        for (Project project : this.projects) {
            projectMap.put(project.getId(), project.getName());
        }
        return projectMap;
    }

    public List<String> getProjectActivityList(String projectName) throws SystemAppException {
        return getProject(projectName).getActivityList();
    }

    public List<String> getEmployeeList() {
        List<String> employeeNames = new LinkedList<>();
        for (Employee employee : employees) {
            employeeNames.add(employee.name());
        }
        return employeeNames;
    }

    public String getProjectLeader(String projectName) throws SystemAppException {
        return getProject(projectName).getProjectLeader().name();
    }

    public Calendar getProjectStartDate(String projectName) throws SystemAppException {
        return getProject(projectName).getStartDate();
    }

    public Calendar getProjectEndDate(String projectName) throws SystemAppException {
        return getProject(projectName).getEndDate();
    }

    public String getProjectName(String projectName) throws SystemAppException {
        return getProject(projectName).getName();
    }

    public int getProjectId(String projectName) throws SystemAppException {
        return getProject(projectName).getId();
    }

    public String getProjectCustomer(String projectName) throws SystemAppException {
        return getProject(projectName).getCustomer();
    }

    public void setActivityName(String actor, String project, String activity, String name) throws SystemAppException {
        getProject(project).setActivityName(actor, activity, name);
    }

    public void setActivityStartWeek(String actor, String project, String activity, int week, int year) throws SystemAppException {
        Calendar startWeek = SystemCalendar.getCalendar(week, year);
        getProject(project).setActivityStartWeek(actor, activity, startWeek);
    }

    public void setActivityEndWeek(String actor, String project, String activity, int week, int year) throws SystemAppException {
        Calendar endWeek = SystemCalendar.getCalendar(week, year);
        getProject(project).setActivityEndWeek(actor, activity, endWeek);
    }

    public void setActivityEstimatedHours(String actor, String project, String activity, int hours) throws SystemAppException {
        getProject(project).setActivityEstimatedHours(actor, activity, hours);
    }

    public boolean activityExists(String project, String activity) throws SystemAppException {
        return getProject(project).activityExists(activity);
    }

    public Calendar getActivityStartWeek(String project, String activity) throws SystemAppException {
        return getProject(project).getActivityStartWeek(activity);
    }

    public Calendar getActivityEndWeek(String project, String activity) throws SystemAppException {
        return getProject(project).getActivityEndWeek(activity);
    }

    public int getActivityEstimatedHours(String project, String activity) throws SystemAppException {
        return getProject(project).getActivityEstimatedHours(activity);
    }

    public boolean hasActivity(String project, String activity) throws SystemAppException {
        return getProject(project).hasActivity(activity);
    }

    public void registerTimeToday(String project, String activity, String employeeName, int fullHours, int minutes) throws SystemAppException {
        getProject(project).registerTimeToday(activity, getEmployee(employeeName), fullHours, minutes);
    }

    public double getActivityHoursToday(String project, String activity, String employeeName) throws SystemAppException {
        return getProject(project).getActivityHoursToday(activity, getEmployee(employeeName));
    }

    public double getTodayHoursProject(String project, String employee)throws SystemAppException{
       double output = 0;
        for (String activity : getProject(project).getActivityList()){
              output += getActivityHoursToday(project, activity, employee);

        }
        return output;
    }

    public void registerTimeActivity(String employeeName, String project, String activity, int hours, int minutes, int day, int month, int year) throws SystemAppException{
        Calendar date = SystemCalendar.getCalendar(day, month, year);
        if (!(hours >= 0 && minutes >= 0 )){
            throw new SystemAppException("Hours and minutes can't be negative");
        }
        getProject(project).setTimeActivity(activity,getEmployee(employeeName),hours,minutes,date);
    }

    public double getActivityHours(String employeeName, String project, String activity, int day, int month , int year ) throws SystemAppException{
        Calendar date = SystemCalendar.getCalendar(day, month, year);
        return getProject(project).getActivityHours(activity,getEmployee(employeeName),date);
    }

    public double getActivityTotalHoursEmployee(String project, String activity, String employeeName) throws SystemAppException {
        return getProject(project).getActivityTotalHoursEmployee(activity, getEmployee(employeeName));
    }

    public void assignEmployee(String actor, String project, String activity, String employeeName) throws SystemAppException {
        if(assignedActivityAmount(employeeName, project, activity) < 20){
            getProject(project).assignEmployee(actor, activity, getEmployee(employeeName));
        } else {
            throw new SystemAppException("Too Many Activities Assigned To Employee");
        }
    }

    public int assignedActivityAmount(String employeeName, String projectName, String activityName) throws SystemAppException {
        Employee employee = getEmployee(employeeName);
        Calendar startWeek = getActivityStartWeek(projectName, activityName);
        Calendar endWeek = getActivityEndWeek(projectName, activityName);
        int sum = 0;
        for(Project project: projects) {
            sum += project.assignedActivityAmount(employee, startWeek, endWeek);
        }
        return sum;
    }

    public boolean hasEmployeeAssignedToActivity(String project, String activity, String employeeName) throws SystemAppException {
        return getProject(project).hasEmployeeAssignedToActivity(activity, getEmployee(employeeName));
    }

    public List<String> getAvailableEmployeesList() {
        ArrayList<String> availableEmployeeNames = new ArrayList<>(employees.stream().map(Employee::name).toList());
        for (Project project : projects) {
            availableEmployeeNames.removeAll(project.getOccupiedEmployees());
        }
        return availableEmployeeNames;
    }

    public int getProjectEstimatedHours(String projectName) throws SystemAppException {
        Project project = getProject(projectName);
        int sum = 0;
        for (String activity : project.getActivityList()) {
            sum += project.getActivityEstimatedHours(activity);
        }
        return sum;
    }

    public double getProjectTotalHours(String projectName) throws SystemAppException {
        Project project = getProject(projectName);
        double sum = 0;
        for (String activity : project.getActivityList()) {
            sum += project.getActivityTotalHours(activity);
        }
        return sum;
    }

//    checkWeeklyActivityAmount()
}
