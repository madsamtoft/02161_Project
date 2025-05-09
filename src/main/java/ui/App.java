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
        /*
        Project project;
        try {
            project = systemApp.getProject(projectName);
        } catch (Exception e) {
            System.out.println("Project \"" + projectName + "\" does not exist");
            return;
        }
         */
        try {
            systemApp.changeProjectCustomer(actor, projectName, customer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //System.out.println("User not authorized to add customer to project \"" + projectName + "\"");
            return;
        }
        System.out.println("Customer \"" + customer + "\" successfully added");
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
            systemApp.changeProjectName(actor, name, newName);
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
        if (!arguments.hasNextInt()) {
            System.out.println("Usage: changeProjectStartDate <project> <dd> <mm> <yyyy>");
            return;
        }
        int day = arguments.nextInt();
        if (!arguments.hasNextInt()) {
            System.out.println("Usage: changeProjectStartDate <project> <dd> <mm> <yyyy>");
            return;
        }
        int month = arguments.nextInt();
        if (!arguments.hasNextInt()) {
            System.out.println("Usage: changeProjectStartDate <project> <dd> <mm> <yyyy>");
            return;
        }
        int year = arguments.nextInt();
        Calendar date;
        try {
            date = CalendarConverter.getCalendar(day, month, year);
        } catch (Exception e) {
            System.out.println("Date format not valid. usage: <dd> <mm> <yyyy>");
            return;
        }
        try {
//            systemApp.changeProjectStartDate(actor, project, date);
            systemApp.changeProjectStartDate(actor, project, date);
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
        if (!arguments.hasNextInt()) {
            System.out.println("Usage: changeProjectEndDate <project> <dd> <mm> <yyyy>");
            return;
        }
        int day = arguments.nextInt();
        if (!arguments.hasNextInt()) {
            System.out.println("Usage: changeProjectEndDate <project> <dd> <mm> <yyyy>");
            return;
        }
        int month = arguments.nextInt();
        if (!arguments.hasNextInt()) {
            System.out.println("Usage: changeProjectEndDate <project> <dd> <mm> <yyyy>");
            return;
        }
        int year = arguments.nextInt();
        Calendar date;
        try {
            date = CalendarConverter.getCalendar(day, month, year);
        } catch (Exception e) {
            System.out.println("Date format not valid. usage: <dd> <mm> <yyyy>");
            return;
        }
        try {
            systemApp.changeProjectEndDate(actor, project, date);
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
            systemApp.assignProjectLeader(actor, project, employee);
            System.out.println("\"" + employee + "\" has been successfully assigned as Project Leader for the project \"" + project + "\"");
        } catch (SystemAppException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createFirmActivity(Scanner arguments) {
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

    public void registerHoursToFirmActivity(Scanner arguments) {
        if (!arguments.hasNext()){
            System.out.println("Usage: registerHoursToFirmActivity <employee> <firmActivityName> <hours> <minutes> <day>,<month> <year>");
            return;
        }
        String employee = arguments.next();
        if (!arguments.hasNext()){
            System.out.println("Usage: registerHoursToFirmActivity <employee> <firmActivityName> <hours> <minutes> <day>,<month> <year>");
            return;
        }
        String firmActivityName = arguments.next();
        if (!arguments.hasNext()){
            System.out.println("Usage: registerHoursToFirmActivity <employee> <firmActivityName> <hours> <minutes> <day>,<month> <year>");
            return;
        }
        int hours = arguments.nextInt();
        if (!arguments.hasNextInt()){
            System.out.println("Usage: registerHoursToFirmActivity <employee> <firmActivityName> <hours> <minutes> <day>,<month> <year>");
            return;
        }
        int minutes = arguments.nextInt();
        if (!arguments.hasNextInt()){
            System.out.println("Usage: registerHoursToFirmActivity <employee> <firmActivityName> <hours> <minutes> <day>,<month> <year>");
            return;
        }
        int day = arguments.nextInt();
        if (!arguments.hasNextInt()){
            System.out.println("Usage: registerHoursToFirmActivity <employee> <firmActivityName> <hours> <minutes> <day>,<month> <year>");
            return;
        }
        int month = arguments.nextInt();
        if (!arguments.hasNextInt()){
            System.out.println("Usage: registerHoursToFirmActivity <employee> <firmActivityName> <hours> <minutes> <day>,<month> <year>");
            return;
        }
        int year = arguments.nextInt();

        try {
            systemApp.registerTimeFirmActivity(employee,firmActivityName,hours,minutes,day,month,year);
            System.out.println(employee + " has registered " + hours + " and " + minutes + " to " + firmActivityName + " at " + day+ "/" + month + "/" + year);
        } catch (Exception e){
            System.out.println((e.getMessage()));
        }
    }

    private void checkRegisteredFirmActivity(Scanner arguments){
        if (!arguments.hasNext()){
            System.out.println("Usage: checkRegisteredFirmActivity <employee> <activityName> <day> <month> <year>");
            return;
        }
        String employee = arguments.next();
        if (!arguments.hasNext()){
            System.out.println("Usage: checkRegisteredFirmActivity <employee> <activityName> <day> <month> <year>");
            return;
        }
        String firmActivityName = arguments.next();
        if (!arguments.hasNextInt()){
            System.out.println("Usage: checkRegisteredFirmActivity <employee> <activityName> <day> <month> <year>");
            return;
        }
        int day = arguments.nextInt();
        if (!arguments.hasNextInt()){
            System.out.println("Usage: checkRegisteredFirmActivity <employee> <activityName> <day> <month> <year>");
            return;
        }
        int month = arguments.nextInt();
        if (!arguments.hasNextInt()){
            System.out.println("Usage: checkRegisteredFirmActivity <employee> <activityName> <day> <month> <year>");
            return;
        }
        int year = arguments.nextInt();
        try {
            double hours = systemApp.checkRegisteredFirmActivity(employee,firmActivityName,day,month,year);
            System.out.println(employee + " has registered " + hours + " to " + firmActivityName + " at " + day+ "/" + month + "/" + year);
        } catch (SystemAppException e){
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
            systemApp.createActivity(actor, project, activityName);
            System.out.println("Activity \"" + activityName + "\" successfully created in project \"" + project + "\"");
        } catch (SystemAppException e) {
            System.out.println(e.getMessage());
        }
    }

    private void assignEmployee(Scanner arguments) {
        if (!arguments.hasNext()) {
            System.out.println("Usage: assignEmployee <project> <activity> <employee>");
            return;
        }
        String project = arguments.next();
        if (!arguments.hasNext()) {
            System.out.println("Usage: assignEmployee <project> <activity> <employee>");
            return;
        }
        String activity = arguments.next();
        if (!arguments.hasNext()) {
            System.out.println("Usage: assignEmployee <project> <activity> <employee>");
            return;
        }
        String employee = arguments.next();
        try {
            systemApp.assignEmployeeToActivity(actor, project, activity, employee);
            System.out.println("Employee \"" + employee + "\" successfully assigned to activity \"" + activity + "\" in project \"" + project + "\"");
        } catch (SystemAppException e) {
            System.out.println(e.getMessage());
        }
    }

    private void changeActivityStartWeek(Scanner arguments) {
        if (!arguments.hasNext()) {
            System.out.println("Usage: changeActivityStartWeek <project> <activity> <week> <year>");
            return;
        }
        String project = arguments.next();
        if (!arguments.hasNext()) {
            System.out.println("Usage: changeActivityStartWeek <project> <activity> <week> <year>");
            return;
        }
        String activity = arguments.next();
        if (!arguments.hasNextInt()) {
            System.out.println("Usage: changeActivityStartWeek <project> <activity> <week> <year>");
            return;
        }
        int week = arguments.nextInt();
        if (!arguments.hasNextInt()) {
            System.out.println("Usage: changeActivityStartWeek <project> <activity> <week> <year>");
            return;
        }
        int year = arguments.nextInt();
        try {
            systemApp.setActivityStartWeek(actor, project, activity, week, year);
            System.out.println("End date successfully changed in activity \"" + activity + "\" in project \"" + project + "\"");
        }  catch (SystemAppException e) {
            System.out.println(e.getMessage());
        }
    }

    private void changeActivityEndWeek(Scanner arguments) {
        if (!arguments.hasNext()) {
            System.out.println("Usage: changeActivityEndWeek <project> <activity> <week> <year>");
            return;
        }
        String project = arguments.next();
        if (!arguments.hasNext()) {
            System.out.println("Usage: changeActivityEndWeek <project> <activity> <week> <year>");
            return;
        }
        String activity = arguments.next();
        if (!arguments.hasNextInt()) {
            System.out.println("Usage: changeActivityEndWeek <project> <activity> <week> <year>");
            return;
        }
        int week = arguments.nextInt();
        if (!arguments.hasNextInt()) {
            System.out.println("Usage: changeActivityEndWeek <project> <activity> <week> <year>");
            return;
        }
        int year = arguments.nextInt();
        try {
            systemApp.setActivityEndWeek(actor, project, activity, week, year);
            System.out.println("End date successfully changed in activity \"" + activity + "\" in project \"" + project + "\"");
        }  catch (SystemAppException e) {
            System.out.println(e.getMessage());
        }
    }

    private void registerTimeDaily(Scanner arguments){
        if (!arguments.hasNext()){
            System.out.println("Usage: registerTimeDaily <project> <activityName> <employee> <hours> <minutes>");
            return;
        }
        String project = arguments.next();
        if (!arguments.hasNext()){
            System.out.println("Usage: registerTimeDaily <project> <activityName> <employee> <hours> <minutes>");
            return;
        }
        String activityName = arguments.next();
        if (!arguments.hasNext()){
            System.out.println("Usage: registerTimeDaily <project> <activityName> <employee> <hours> <minutes>");
            return;
        }
        String employee = arguments.next();
        if (!arguments.hasNextInt()){
            System.out.println("Usage: registerTimeDaily <project> <activityName> <employee> <hours> <minutes>");
            return;
        }
        int hours = arguments.nextInt();
        if (!arguments.hasNextInt()){
            System.out.println("Usage: registerTimeDaily <project> <activityName> <employee> <hours> <minutes>");
            return;
        }
        int minutes = arguments.nextInt();
        try {
            systemApp.registerTimeDaily(project, activityName, employee, hours, minutes);
            double registered = systemApp.checkRegisteredTimeDaily(project, activityName, employee);
            System.out.println(registered + "hours have been registered to \"" + employee + "\" in activity" + activityName);
        } catch (SystemAppException e){
            System.out.println(e.getMessage());
        }
    }

    private void checkRegisteredTimeDaily(Scanner arguments){
        if (!arguments.hasNext()){
            System.out.println("Usage: checkRegisteredTimeDaily <project> <activityName> <employee>");
            return;
        }

        String project = arguments.next();
        if (!arguments.hasNext()){
            System.out.println("Usage: checkRegisteredTimeDaily <project> <activityName> <employee>");
            return;
        }
        String activityName = arguments.next();
        if (!arguments.hasNext()){
            System.out.println("Usage: checkRegisteredTimeDaily <project> <activityName> <employee>");
            return;
        }
        String employee = arguments.next();
        try {
            double hours = systemApp.checkRegisteredTimeDaily(project,activityName,employee);
            System.out.println(employee + " has registered " + hours + " to " + activityName);
        } catch (SystemAppException e){
            System.out.println(e.getMessage());
        }
    }

    private void registerTimeActivity(Scanner arguments){
        if(!arguments.hasNext()){
            System.out.println("Usage: registerTimeActivity <employee> <project> <activity> <hours> <minutes> <day> <month> <year>");
            return;
        }
        String employee = arguments.next();
        if(!arguments.hasNext()){
            System.out.println("Usage: registerTimeActivity <employee> <project> <activity> <hours> <minutes> <day> <month> <year>");
            return;
        }
        String project = arguments.next();
        if(!arguments.hasNext()){
            System.out.println("Usage: registerTimeActivity <employee> <project> <activity> <hours> <minutes> <day> <month> <year>");
            return;
        }
        String activity = arguments.next();
        if(!arguments.hasNextInt()){
            System.out.println("Usage: registerTimeActivity <employee> <project> <activity> <hours> <minutes> <day> <month> <year>");
            return;
        }
        int hours = arguments.nextInt();
        if(!arguments.hasNextInt()){
            System.out.println("Usage: registerTimeActivity <employee> <project> <activity> <hours> <minutes> <day> <month> <year>");
            return;
        }
        int minutes = arguments.nextInt();
        if(!arguments.hasNextInt()){
            System.out.println("Usage: registerTimeActivity <employee> <project> <activity> <hours> <minutes> <day> <month> <year>");
            return;
        }
        int day = arguments.nextInt();
        if(!arguments.hasNextInt()){
            System.out.println("Usage: registerTimeActivity <employee> <project> <activity> <hours> <minutes> <day> <month> <year>");
            return;
        }
        int month = arguments.nextInt();
        if(!arguments.hasNextInt()){
            System.out.println("Usage: registerTimeActivity <employee> <project> <activity> <hours> <minutes> <day> <month> <year>");
            return;
        }
        int year = arguments.nextInt();
        try{
            systemApp.registerTimeActivity(employee,project,activity,hours,minutes,day,month,year);
            double registered = systemApp.checkRegisteredActivity(employee,project,activity,day,month,year);
            System.out.println(employee + " has registered " + registered + "hours to " + activity + " at " + day+ "/" + month + "/" + year);
        } catch (SystemAppException e){
            System.out.println(e.getMessage());
        }
    }

    private void checkRegisteredTimeActivity(Scanner arguments){
        if(!arguments.hasNext()){
            System.out.println("Usage: checkRegisteredTimeActivity <employee> <project> <activity> <day> <month> <year>");
            return;
        }
        String employee = arguments.next();
        if(!arguments.hasNext()){
            System.out.println("Usage: checkRegisteredTimeActivity <employee> <project> <activity> <day> <month> <year>");
            return;
        }
        String project = arguments.next();
        if(!arguments.hasNext()){
            System.out.println("Usage: checkRegisteredTimeActivity <employee> <project> <activity> <day> <month> <year>");
            return;
        }
        String activity = arguments.next();
        if(!arguments.hasNextInt()){
            System.out.println("Usage: checkRegisteredTimeActivity <employee> <project> <activity> <day> <month> <year>");
            return;
        }
        int day = arguments.nextInt();
        if(!arguments.hasNextInt()){
            System.out.println("Usage: checkRegisteredTimeActivity <employee> <project> <activity> <day> <month> <year>");
            return;
        }
        int month = arguments.nextInt();
        if(!arguments.hasNextInt()){
            System.out.println("Usage: checkRegisteredTimeActivity <employee> <project> <activity> <day> <month> <year>");
            return;
        }
        int year = arguments.nextInt();
        try{
            double hours = systemApp.checkRegisteredActivity(employee,project,activity,day,month,year);
            System.out.println(employee + " has registered " + hours + " to " + activity + " at " + day+ "/" + month + "/" + year);
        } catch (SystemAppException e){
            System.out.println(e.getMessage());
        }
    }

    private void checkRegisteredTime(Scanner arguments) {
        if (!arguments.hasNext()) {
            System.out.println("Usage: checkRegisteredTime <project> <employee>");
            return;
        }
        String project = arguments.next();
        if (!arguments.hasNext()){
            System.out.println("Usage: checkRegisteredTime <project> <employee>");
            return;
        }
        String actor = arguments.next();
        try {
            System.out.println(systemApp.checkRegisteredTime(project,actor)+ "hours");


        } catch (SystemAppException e) {
            System.out.println(e.getMessage());
        }

    }

    public void setActivityEstimatedHours(Scanner arguments){
        if(!arguments.hasNext()){
            System.out.println("Usage: setActivityEstimatedHours <project> <activity> <hours>");
            return;
        }
        String project = arguments.next();
        if(!arguments.hasNext()){
            System.out.println("Usage: setActivityEstimatedHours <project> <activity> <hours>");
            return;
        }
        String activity = arguments.next();
        if(!arguments.hasNextInt()){
            System.out.println("Usage: setActivityEstimatedHours <project> <activity> <hours>");
            return;
        }
        int hours = arguments.nextInt();
        try{
            systemApp.setActivityEstimatedHours(actor,project,activity,hours);
            System.out.println("Estimated Hours set to \"" + hours + " hours for activity" + activity + "\" in project \"" + project + "\"");
        } catch (SystemAppException e){
            System.out.println(e.getMessage());
        }
    }

    private void listProjects() {
        Map<Integer, String> projects = systemApp.listProjects();
        int i = 1;
        for (int id : projects.keySet()) {
            System.out.printf("\tProject %2d: %s#%d\n", i, projects.get(id), id);
        }
    }

    private void listActivities(String projectName) {
        Project project;
        List<String> activities;
        try {
            activities = systemApp.listProjectActivities(projectName);
        } catch (Exception e) {
            System.out.println("Project \"" + projectName + "\" has no activities");
            return;
        }

        for (int i = 0; i < activities.size(); i++) {
            System.out.printf("\tActivity %2d: %s\n", (i+1), activities.get(i));
        }
    }

    private void listEmployees() {
        List<String> employees = systemApp.listEmployees();
        for (int i = 0; i < employees.size(); i++) {
            System.out.printf("\tEmployee %2d: %s\n", (i+1), employees.get(i));
        }
    }

    private void listAvailableEmployees() {
        List<String> availableEmployees = systemApp.getAvailableEmployees();
        for (int i = 0; i < availableEmployees.size(); i++) {
            System.out.printf("\tAvailable Employee %2d: %s\n", (i+1), availableEmployees.get(i));
        }
    }

    private void list(Scanner arguments) {
        if (!arguments.hasNext()){
            System.out.println("Usage:\n\tlist projects\n\tlist employees\n\tlist availableEmployees\n\tlist activities <project>");
            return;
        }
        switch (arguments.next().toLowerCase()) {
            case "projects":
                listProjects();
                break;
            case "employees":
                listEmployees();
                break;
            case "availableemployees":
                listAvailableEmployees();
                break;
            case "activities":
                if (!arguments.hasNext()) {
                    System.out.println("Usage: list activities <project>");
                    break;
                }
                listActivities(arguments.next());
                break;
            default:
                System.out.println("Usage:\n\tlist projects\n\tlist employees\n\tlist availableEmployees\n\tlist activities <project>");
        }
    }

    private void infoProject(String projectName) {
        String name;
        String id;
        try {
            name = systemApp.getProjectName(projectName);
            id = "" + systemApp.getProjectIds(projectName);
        } catch (SystemAppException e) {
            System.out.println(e.getMessage());
            return;
        }
        String leader;
        String customer;
        String startDate;
        String endDate;
        try {
            leader = systemApp.getProjectLeader(name);
        } catch (Exception e) {
            leader = "<none>";
        }
        try {
            customer = systemApp.getProjectCustomer(name);
            if (customer == null) {
                customer = "<none>";
            }
        } catch (Exception e) {
            customer = "<none>";
        }
        try {
            int startDateDay = systemApp.getProjectStartDate(name).get(Calendar.DAY_OF_MONTH);
            int startDateMonth = systemApp.getProjectStartDate(name).get(Calendar.MONTH) + 1;
            int startDateYear = systemApp.getProjectStartDate(name).get(Calendar.YEAR);
            startDate = startDateDay + "/" + startDateMonth + "/" + startDateYear;
        } catch (Exception e) {
            startDate = "<none>";
        }
        try {
            int endDateDay = systemApp.getProjectEndDate(name).get(Calendar.DAY_OF_MONTH);
            int endDateMonth = systemApp.getProjectEndDate(name).get(Calendar.MONTH) + 1;
            int endDateYear = systemApp.getProjectEndDate(name).get(Calendar.YEAR);
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

    private void generateReport(Scanner arguments){
        if(!arguments.hasNext()){
            System.out.println("Usage: generateReport <project>");
            return;
        }
        String projectName = arguments.next();
        double estimatedHours;
        try {
            estimatedHours = systemApp.getProjectEstimatedHours(projectName);
        } catch (SystemAppException e) {
            System.out.println(e.getMessage());
            return;
        }
        double totalHours;
        try {
            totalHours = systemApp.getProjectTotalHours(projectName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }


        System.out.println("[" + projectName + "]:");
        System.out.println("Estimated Hours.."+ estimatedHours);
        System.out.println("Total Hours......"+ totalHours);
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
                case "generatereport":
                    generateReport(arguments);
                    break;
                case "registeremployee":
                    registerEmployee(arguments);
                    break;
                case "checkregisteredtime":
                    checkRegisteredTime(arguments);
                    break;

                // Project commands
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

                // Firm activity commands
                case "createfirmactivity":
                    createFirmActivity(arguments);
                    break;
                case "registerhourstofirmactivity":
                    registerHoursToFirmActivity(arguments);
                    break;
                case "checkregisteredfirmactivity":
                    checkRegisteredFirmActivity(arguments);
                    break;

                // Activity commands
                case "createactivity":
                    createActivity(arguments);
                    break;
                case "assignemployee":
                    assignEmployee(arguments);
                    break;
                case "changeactivitystartweek":
                    changeActivityStartWeek(arguments);
                    break;
                case "changeactivityendweek":
                    changeActivityEndWeek(arguments);
                    break;
                case "registertimedaily":
                    registerTimeDaily(arguments);
                    break;
                case "checkregisteredtimedaily":
                    checkRegisteredTimeDaily(arguments);
                    break;
                case "registertimeactivity":
                    registerTimeActivity(arguments);
                    break;
                case "checkregisteredtimeactivity":
                    checkRegisteredTimeActivity(arguments);
                    break;
                case "setactivityestimatedhours":
                    setActivityEstimatedHours(arguments);
                    break;

                // Help commands
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
