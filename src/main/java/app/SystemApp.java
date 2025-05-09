package app;

import java.util.*;

public class SystemApp {
    private List<Project> projects = new LinkedList<>();
    private List<Activity> firmActivityList = new ArrayList<>();
    private int projectIdCounter = 1;
    private int currentYear = 0;
    private List<Employee> employees = new ArrayList<>();

    public SystemApp() {
        employees.add(new Employee("huba"));
    }

    private boolean projectExists(String name) {
        return projects.stream().anyMatch(p -> p.getName().equals(name.toLowerCase()));
    }

    private int getNewProjectId() {
        if (currentYear != CalendarConverter.getCurrentYear()) {
            projectIdCounter = 1;
            currentYear = CalendarConverter.getCurrentYear();
        }

        return (currentYear % 100) * 1000 + projectIdCounter++;
    }

    public void createProject(String name) throws SystemAppException {
        if (projectExists(name))  {
            throw new SystemAppException("Project with that name already exists");
        }
        projects.add(new Project(name, getNewProjectId()));
    }

    public void changeProjectName( String actor, String project, String name) throws SystemAppException {
        try  {
            getProject(name);
        }
        catch(Exception e){
            getProject(project).setName(actor, name);
        }
        throw new SystemAppException("Project with that name already exists");

    }

    public void changeProjectStartDate(String actor, String project, Calendar startDate) throws SystemAppException {
        getProject(project).setStartDate(actor, startDate);
    }

    public void changeProjectEndDate(String actor, String project, Calendar endDate) throws SystemAppException {
        getProject(project).setEndDate(actor, endDate);
    }

    public void changeProjectCustomer(String actor, String project, String customer) throws SystemAppException {
        getProject(project).setCustomer(actor, customer);
    }


    public void registerEmployee(String name) throws SystemAppException {
        if (name.length() > 4 || !name.toLowerCase().matches("[a-z]+")) {
            throw new SystemAppException("Employee username must be 1-4 letters");
        }
        if (employeeExists(name)) {
            throw new SystemAppException("An employee with that username already exists");
        }
        employees.add(new Employee(name.toLowerCase()));
    }

    public List<Integer> getProjectIds(String name) {
        List<Integer> ids = new LinkedList<>();
        for (Project project : projects) {
            String projectName = project.getName();
            if (name.equals(projectName)) {
                ids.add(project.getId());
            }
        }
        return ids;
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

    public boolean firmActivityExists(String name) {
        return firmActivityList.stream().anyMatch(a -> a.getName().equals(name));
    }

    public void registerTimeFirmActivity(String employee, String firmActivityName, int hours, int minutes, int day, int month,int year) throws SystemAppException{
        Activity firmActivity = getFirmActivity(firmActivityName);
        Calendar date = CalendarConverter.getCalendar(day, month, year);
        firmActivity.registerTime(getEmployee(employee),hours,minutes,date);
    }

    public double checkRegisteredFirmActivity(String employee, String firmActivityName, int day, int month, int year) throws SystemAppException{
        Activity firmActivity = getFirmActivity(firmActivityName);
        Calendar date = CalendarConverter.getCalendar(day, month, year);
        return firmActivity.checkRegistered(getEmployee(employee),date);
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

    public void assignProjectLeader(String actor, String project, String employee) throws SystemAppException {
        // TODO: this should be changed to use project IDs
        getProject(project).assignProjectLeader(actor, getEmployee(employee));
    }


    public Map<Integer, String> listProjects() {
        // This method only works when EVERY SINGLE project ID is unique
        Map<Integer, String> projectMap = new HashMap<>();
        for (Project project : this.projects) {
            projectMap.put(project.getId(), project.getName());
        }
        return projectMap;
    }

    public List<String> listProjectActivities(String projectName) throws SystemAppException {
        return getProject(projectName).listActivities();
    }

    public List<String> listEmployees() {
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

    public String getProjectCustomer(String projectName) throws SystemAppException {
        return getProject(projectName).getCustomer();
    }

    public void setActivityName(String actor, String project, String activity, String name) throws SystemAppException {
        getProject(project).setActivityName(actor, activity, name);
    }

    public void setActivityStartWeek(String actor, String project, String activity, int week, int year) throws SystemAppException {
        Calendar startWeek = CalendarConverter.getCalendar(week, year);
        getProject(project).setActivityStartWeek(actor, activity, startWeek);
    }

    public void setActivityEndWeek(String actor, String project, String activity, int week, int year) throws SystemAppException {
        Calendar endWeek = CalendarConverter.getCalendar(week, year);
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

    public void registerTimeDaily(String project, String activity, String employee, int fullHours, int minutes) throws SystemAppException {
        getProject(project).registerTimeDaily(activity, getEmployee(employee), fullHours, minutes);
    }

    public double checkRegisteredTimeDaily(String project, String activity, String employee) throws SystemAppException {
        return getProject(project).checkRegisteredDaily(activity, getEmployee(employee));
    }
    public double checkRegisteredTime(String project,String actor)throws SystemAppException{
       double output = 0;
        for (String activity : getProject(project).listActivities()){
              output += checkRegisteredTimeDaily(project, activity, actor);

        }
        return output;

    }

    public void registerTimeActivity(String employee, String project, String activity, int hours, int minutes, int day, int month, int year) throws SystemAppException{
        Calendar date = CalendarConverter.getCalendar(day, month, year);
        getProject(project).registerTimeActivity(activity,getEmployee(employee),hours,minutes,date);
    }

    public double checkRegisteredActivity(String employee,String project, String activity, int day, int month , int year ) throws SystemAppException{
        Calendar date = CalendarConverter.getCalendar(day, month, year);
        return getProject(project).checkRegisteredActivity(activity,getEmployee(employee),date);
    }

    public double checkRegisteredTotalActivity(String project, String activity, String employee) throws SystemAppException {
        return getProject(project).checkRegisteredTotalActivity(activity, getEmployee(employee));
    }


    public void assignEmployeeToActivity(String actor, String project, String activity, String employee) throws SystemAppException {
        getProject(project).assignEmployeeToActivity(actor, activity, getEmployee(employee));
    }

    public boolean hasEmployeeAssignedToActivity(String project, String activity, String employee) throws SystemAppException {
        return getProject(project).hasEmployeeAssignedToActivity(activity, getEmployee(employee));
    }

//    checkWeeklyActivityAmount()
}
