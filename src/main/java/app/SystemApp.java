package app;

import java.util.*;

public class SystemApp {
    private List<Project> projects = new LinkedList<>();
    private List<Activity> firmActivityList = new ArrayList<>();
    private int projectIdCounter = 0;

    public static void main(String[] args) {
        System.out.println("Hello bitches: Software Huset A/S");
    }

    public void createProject(String name) throws SystemAppException {
        Project project = new Project(name, ++projectIdCounter);
        projects.add(project);
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

//    checkWeeklyActivityAmount()
}
