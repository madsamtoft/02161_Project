package app;

import java.util.*;

public class SystemApp {
    private List<Project> projects = new LinkedList<>();
    private List<Activity> firmActivityList = new ArrayList<>();
    private int projectIdCounter = 0;
    private List<Employee> employees = new ArrayList<>();

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

    public void changeProjectLeader(String actor, String name, String newProjectLeader) throws SystemAppException {
        getProject(name).assignProjectLeader(getEmployee(actor));
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
        if (firmActivityExists(activityName)) {
            throw new SystemAppException("Firm Activity Name already taken");
        } else {
            firmActivityList.add(new Activity(activityName));
        }
    }

    private boolean firmActivityExists(String activityName) {
        try {
            getFirmActivity(activityName);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    public Activity getFirmActivity(String activityName) throws SystemAppException{
        for (Activity activity : firmActivityList) {
            if (activityName.equals(activity.getName())) {
                return activity;
            }
        }
        throw new SystemAppException("No such firm activity found");
    }
    
    private Project getProject(String name) throws SystemAppException {
        for (Project project : projects) {
            if (project.getName().equals(name)) {
                return project;
            }
        }
        throw new SystemAppException("Project does not exist");
    }

    private Employee getEmployee(String name) throws SystemAppException {
        for (Employee employee : employees) {
            if (employee.name().equals(name)) {
                return employee;
            }
        }
        throw new SystemAppException("Employee does not exist");
    }

    public void assignProjectLeader(String project, String employee) throws SystemAppException {
        // TODO: this should be changed to use project IDs
        getProject(project).assignProjectLeader(getEmployee(employee));
    }

//    checkWeeklyActivityAmount()
}
