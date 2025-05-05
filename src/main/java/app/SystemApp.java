package app;

import java.util.*;

public class SystemApp {
    private List<Project> projects = new LinkedList<>();
    private List<Activity> firmActivityList = new ArrayList<>();
    private int projectIdCounter = 0;
    private List<Employee> employees = new ArrayList<>();

    public SystemApp() {
        employees.add(new Employee("huba"));
    }

    public static void main(String[] args) {
        System.out.println("Hello bitches: Software Huset A/S");
    }

    public void createProject(String name) throws SystemAppException {
        Project project = new Project(name, ++projectIdCounter);
        projects.add(project);
    }

    public void changeProjectName( String actor, String name, String newName) throws SystemAppException {

        getProject(name).setName(getEmployee(actor), newName);
    }

    public void changeProjectStartDate(String actor, String name, Calendar startDate) throws SystemAppException {
        getProject(name).setStartDate(getEmployee(actor), startDate);
    }

    public void changeProjectEndDate(String actor, String name, Calendar endDate) throws SystemAppException {
        getProject(name).setEndDate(getEmployee(actor), endDate);
    }


    public void registerEmployee(String name) throws SystemAppException {
        if (name.length() > 4 || !name.toLowerCase().matches("[a-z]+")) {
            throw new SystemAppException("Employee username must be up to 1-4 letters");
        }
        if (employeeExists(name)) {
            throw new SystemAppException("An emp½loyee with that username already exists");
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

    public Activity getFirmActivity(String name) throws SystemAppException{
        for (Activity activity : firmActivityList) {
            if (name.equals(activity.getName())) {
                return activity;
            }
        }
        throw new SystemAppException("Firm activity " + name + " does not exist");
    }

    public void createActivity(String actor, String project, String activityName) throws SystemAppException {
        Employee allegedProjectLeader;
        try {
            allegedProjectLeader = getEmployee(actor);
        } catch (SystemAppException e) {
            allegedProjectLeader = null;
        }
        getProject(project).createActivity(allegedProjectLeader, activityName);
    }

    public Project getProject(String name) throws SystemAppException {
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
        getProject(project).assignProjectLeader(getEmployee(actor), getEmployee(employee));
    }

    public List<Project> getProjects() {
        return this.projects;
    }

    public List<Employee> getEmployees() {
        return this.employees;
    }

//    checkWeeklyActivityAmount()
}
