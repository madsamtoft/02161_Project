package ui;

import app.*;

import java.util.*;

import java.util.Scanner;

public class App {

    private final SystemApp systemApp = new SystemApp();
    private String actor = "";

    private void registerEmployee(Scanner arguments) {
        if (!arguments.hasNext()) {
            System.out.println("Usage: registerEmployee <name>");
            return;
        }
        String name = arguments.next();
        try {
            systemApp.registerEmployee(name);
            System.out.println("Employee \"" + name + "\" successfully registered");
        } catch (SystemAppException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createProject(Scanner arguments) {
        if (!arguments.hasNext()) {
            System.out.println("Usage: createProject <name>");
            return;
        }
        String name = arguments.next();
        try {
            systemApp.createProject(name);
            System.out.println("Project \"" + name + "\" successfully created");
        } catch (SystemAppException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addCustomer(Scanner arguments) {
        if (!arguments.hasNext()) {
            System.out.println("Usage: addCustomer <project> <customer>");
            return;
        }
        String projectName = arguments.next();
        if (!arguments.hasNext()) {
            System.out.println("Usage: addCustomer <project> <customer>");
            return;
        }
        String customer = arguments.next();
        Project project;
        try {
            project = systemApp.getProject(projectName);
        } catch (Exception e) {
            System.out.println("Project \"" + projectName + "\" does not exist");
            return;
        }
        try {
            project.setCustomer(actor, customer);
        } catch (Exception e) {
            System.out.println("User not authorized to add customer to project \"" + projectName + "\"");
            return;
        }
    }

    private void changeProjectName(Scanner arguments) {
        if (!arguments.hasNext()) {
            System.out.println("Usage: changeProjectName <project> <newName>");
            return;
        }
        String name = arguments.next();
        if (!arguments.hasNext()) {
            System.out.println("Usage: changeProjectName <project> <newName>");
            return;
        }
        String newName = arguments.next();
        try {
            systemApp.getProject(name).setName(actor, newName);
            System.out.println("Project name for \"" + name + "\" successfully changed to \"" + newName + "\"");
        } catch (SystemAppException e) {
            System.out.println(e.getMessage());
        }
    }

    private void changeProjectStartDate(Scanner arguments) {
        if (!arguments.hasNext()) {
            System.out.println("Usage: changeProjectStartDate <project> <dd> <mm> <yyyy>");
            return;
        }
        String project = arguments.next();
        if (!arguments.hasNext()) {
            System.out.println("Usage: changeProjectStartDate <project> <dd> <mm> <yyyy>");
            return;
        }
        String day = arguments.next();
        if (!arguments.hasNext()) {
            System.out.println("Usage: changeProjectStartDate <project> <dd> <mm> <yyyy>");
            return;
        }
        String month = arguments.next();
        if (!arguments.hasNext()) {
            System.out.println("Usage: changeProjectStartDate <project> <dd> <mm> <yyyy>");
            return;
        }
        String year = arguments.next();
        try {
            int int_day = Integer.parseInt(day);
            int int_month = Integer.parseInt(month);
            int int_year = Integer.parseInt(year);
        }
        catch (NumberFormatException e) {
            System.out.println("Date format not valid. usage: <dd> <mm> <yyyy>");
        }
        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
        date.set(Calendar.MONTH, Integer.parseInt(month) - 1); // Months are 0-based in Calendar
        date.set(Calendar.YEAR, Integer.parseInt(year));
        try {
//            systemApp.changeProjectStartDate(actor, project, date);
            systemApp.getProject(project).setStartDate(actor, date);
            System.out.println("Start date successfully changed in project \"" + project + "\"");
        } catch (SystemAppException e) {
            System.out.println(e.getMessage());
        }
    }

    private void changeProjectEndDate(Scanner arguments) {
        if (!arguments.hasNext()) {
            System.out.println("Usage: changeProjectEndDate <project> <dd> <mm> <yyyy>");
            return;
        }

        String project = arguments.next();
        if (!arguments.hasNext()) {
            System.out.println("Usage: changeProjectEndDate <project> <dd> <mm> <yyyy>");
            return;
        }
        String day = arguments.next();
        if (!arguments.hasNext()) {
            System.out.println("Usage: changeProjectEndDate <project> <dd> <mm> <yyyy>");
            return;
        }
        String month = arguments.next();
        if (!arguments.hasNext()) {
            System.out.println("Usage: changeProjectEndDate <project> <dd> <mm> <yyyy>");
            return;
        }
        String year = arguments.next();
        try {
            int int_day = Integer.parseInt(day);
            int int_month = Integer.parseInt(month);
            int int_year = Integer.parseInt(year);
        }
        catch (NumberFormatException e) {
            System.out.println("Date format not valid. usage: <dd> <mm> <yyyy>");
        }
        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
        date.set(Calendar.MONTH, Integer.parseInt(month) - 1); // Months are 0-based in Calendar
        date.set(Calendar.YEAR, Integer.parseInt(year));
        try {
//            systemApp.changeProjectEndDate(actor, project, date);
            systemApp.getProject(project).setEndDate(actor, date);
            System.out.println("End date successfully changed in project \"" + project + "\"");
        } catch (SystemAppException e) {
            System.out.println(e.getMessage());
        }
    }



    private void assignProjectLeader(Scanner arguments){
        if (!arguments.hasNext()) {
            System.out.println("Usage: assignProjectLeader <project> <name>");
            return;
        }
        String project = arguments.next();
        if (!arguments.hasNext()) {
            System.out.println("Usage: assignProjectLeader <project> <name>");
            return;
        }
        String employee = arguments.next();
        try {
//            systemApp.assignProjectLeader(actor, project, employee);
            systemApp.getProject(project).assignProjectLeader(actor, systemApp.getEmployee(employee));
            System.out.println("\"" + employee + "\" has been successfully assigned as Project Leader for the project \"" + project + "\"");
        } catch (SystemAppException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createActivity(Scanner arguments) {
        if (!arguments.hasNext()){
            System.out.println("Usage: createActivity <projectName> <activityName>");
            return;
        }
        String project = arguments.next();
        if (!arguments.hasNext()){
            System.out.println("Usage: createActivity <projectName> <activityName>");
            return;
        }
        String activityName = arguments.next();
        try {
//            systemApp.createActivity(actor, projectName, activityName);
            systemApp.getProject(project).createActivity(actor, activityName);
            System.out.println("Activity \"" + activityName + "\" successfully created in project \"" + project + "\"");
        } catch (SystemAppException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createFirmActivity(Scanner arguments) {
        if (!arguments.hasNext()){
            System.out.println("Usage: createFirmActivity <activityName>");
            return;
        }
        String activityName = arguments.next();
        try {
            systemApp.createFirmActivity(activityName);
            System.out.println("Firm activity \"" + activityName + "\" successfully created");
        } catch (SystemAppException e){
            System.out.println(e.getMessage());
        }
    }

    private void listProjects() {
        List<Project> projects = systemApp.getProjects();
        for (int i = 0; i < projects.size(); i++) {
            System.out.printf("\tProject %2d: %s#%d\n", (i+1), projects.get(i).getName(), projects.get(i).getId());
        }
    }

    private void listActivities(String projectName) {
        Project project;
        List<Activity> activities;
        try {
            project = systemApp.getProject(projectName);
        } catch (Exception e) {
            System.out.println("Project \"" + projectName + "\" does not exist");
            return;
        }

        try {
            activities = project.getActivities();
        } catch (Exception e) {
            System.out.println("Project \"" + projectName + "\" has no activities");
            return;
        }

        for (int i = 0; i < activities.size(); i++) {
            System.out.printf("\tActivity %2d: %s\n", (i+1), activities.get(i).getName());
        }
    }

    private void listEmployees() {
        List<Employee> employees = systemApp.getEmployees();
        for (int i = 0; i < employees.size(); i++) {
            System.out.printf("\tEmployee %2d: %s\n", (i+1), employees.get(i).name());
        }
    }

    private void list(Scanner arguments) {
        if (!arguments.hasNext()){
            System.out.println("Usage:\n\tlist projects\n\tlist employees\n\tlist activities <project>");
            return;
        }
        String next = arguments.next().toLowerCase();
        if (next.equals("projects")) {
            listProjects();
            return;
        } else if (next.equals("employees")) {
            listEmployees();
            return;
        } else if (next.equals("activities")) {
            listActivities(arguments.next());
            return;
        } else {
            System.out.println("Usage: list projects OR list activities <project>");
            return;
        }
    }

    private void infoProject(String projectName) {
        Project project;
        try {
            project = systemApp.getProject(projectName);
        } catch (SystemAppException e) {
            System.out.println(e.getMessage());
            return;
        }
        String name = project.getName();
        String id = "" + project.getId();
        String leader;
        String customer;
        String startDate;
        String endDate;
        try {
            leader = project.getProjectLeader().name();
        } catch (Exception e) {
            leader = "<none>";
        }
        try {
            customer = project.getCustomer();
            if (customer == null) {
                customer = "<none>";
            }
        } catch (Exception e) {
            customer = "<none>";
        }
        try {
            int startDateDay = project.getStartDate().get(Calendar.DAY_OF_MONTH);
            int startDateMonth = project.getStartDate().get(Calendar.MONTH) + 1;
            int startDateYear = project.getStartDate().get(Calendar.YEAR);
            startDate = startDateDay + "/" + startDateMonth + "/" + startDateYear;
        } catch (Exception e) {
            startDate = "<none>";
        }
        try {
            int endDateDay = project.getEndDate().get(Calendar.DAY_OF_MONTH);
            int endDateMonth = project.getEndDate().get(Calendar.MONTH) + 1;
            int endDateYear = project.getEndDate().get(Calendar.YEAR);
            endDate = endDateDay + "/" + endDateMonth + "/" + endDateYear;
        } catch (Exception e) {
            endDate = "<none>";
        }
        System.out.println("Project Name...." + name);
        System.out.println("Project ID......" + id);
        System.out.println("Project Leader.." + leader);
        System.out.println("Customer........" + customer);
        System.out.println("Start date......" + startDate);
        System.out.println("End date........" + endDate);
        System.out.println("Activities:");
        listActivities(projectName);
    }

    private void info(Scanner arguments) {
        if (!arguments.hasNext()){
            System.out.println("Usage: info <project>");
            return;
        }
        String projectName = arguments.next();
        infoProject(projectName);
    }

    public void login(Scanner arguments) {
        if (!arguments.hasNext()){
            System.out.println("Usage: login <name>");
            return;
        }
        String name = arguments.next();
        if (systemApp.employeeExists(name)) {
            actor = name;
        } else {
            System.out.println("Employee " + name + " does not exist");
        }
    }

    public void launch() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (!actor.isEmpty()) {
                System.out.print("(" + actor + ") ");
            }
            System.out.print("# ");
            String line = scanner.nextLine();
            Scanner arguments = new Scanner(line);
            String command = "";
            if (arguments.hasNext()) {
                command = arguments.next();
            }
            switch (command.toLowerCase()) {
                case "registeremployee":
                    registerEmployee(arguments);
                    break;
                case "createproject":
                    createProject(arguments);
                    break;
                case "addcustomer":
                    addCustomer(arguments);
                    break;
                case "changeprojectname":
                    changeProjectName(arguments);
                    break;
                case "changeprojectstartdate":
                    changeProjectStartDate(arguments);
                    break;
                case "changeprojectenddate":
                    changeProjectEndDate(arguments);
                    break;
                case "assignprojectleader":
                    assignProjectLeader(arguments);
                    break;
                case "createactivity":
                    createActivity(arguments);
                    break;
                case "createfirmactivity":
                    createFirmActivity(arguments);
                    break;
                case "list":
                    list(arguments);
                    break;
                case "info":
                    info(arguments);
                    break;

                // UI specific commands:
                case "login":
                    login(arguments);
                    break;
                case "logout":
                    actor = "";
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Unknown command: " + command);
            }
        }
    }

    public static void main(String[] args) {
        App ui = new App();
        ui.launch();
    }
}
