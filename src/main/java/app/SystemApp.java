package app;

import java.util.*;

public class SystemApp {
    private List<Project> projects = new LinkedList<>();
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

//    checkWeeklyActivityAmount()
}
