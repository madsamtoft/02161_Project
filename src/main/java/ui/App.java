package ui;

import app.*;

import java.util.*;

import java.util.Scanner;

public class App {

    private final SystemApp systemApp = new SystemApp();
    private String actor = "";

    private void createEmployee(Scanner arguments) {
        if (!arguments.hasNext()) {
            System.out.println("Usage: createEmployee <name>");
            return;
        }
        String name = arguments.next();
        try {
            systemApp.createEmployee(name);
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

    private void setProjectCustomer(Scanner arguments) {
        if (!arguments.hasNext()) {
            System.out.println("Usage: setProjectCustomer <project> <customer>");
            return;
        }
        String projectName = arguments.next();
        if (!arguments.hasNext()) {
            System.out.println("Usage: setProjectCustomer <project> <customer>");
            return;
        }
        String customer = arguments.next();
        try {
            systemApp.setProjectCustomer(actor, projectName, customer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Customer for project \"" + projectName + "\" successfully set to \"" + customer + "\"");

    }

    private void setProjectName(Scanner arguments) {
        if (!arguments.hasNext()) {
            System.out.println("Usage: setProjectName <project> <newName>");
            return;
        }
        String name = arguments.next();
        if (!arguments.hasNext()) {
            System.out.println("Usage: setProjectName <project> <newName>");
            return;
        }
        String newName = arguments.next();
        try {
            systemApp.setProjectName(actor, name, newName);
            System.out.println("Name for project \"" + name + "\" successfully set to \"" + newName + "\"");
        } catch (SystemAppException e) {
            System.out.println(e.getMessage());
        }
    }

    private void setProjectStartDate(Scanner arguments) {
        if (!arguments.hasNext()) {
            System.out.println("Usage: setProjectStartDate <project> <dd> <mm> <yyyy>");
            return;
        }
        String project = arguments.next();
        if (!arguments.hasNextInt()) {
            System.out.println("Usage: setProjectStartDate <project> <dd> <mm> <yyyy>");
            return;
        }
        int day = arguments.nextInt();
        if (!arguments.hasNextInt()) {
            System.out.println("Usage: setProjectStartDate <project> <dd> <mm> <yyyy>");
            return;
        }
        int month = arguments.nextInt();
        if (!arguments.hasNextInt()) {
            System.out.println("Usage: setProjectStartDate <project> <dd> <mm> <yyyy>");
            return;
        }
        int year = arguments.nextInt();
        Calendar date;
        try {
            date = SystemCalendar.getCalendar(day, month, year);
        } catch (Exception e) {
            System.out.println("Date format not valid. usage: <dd> <mm> <yyyy>");
            return;
        }
        try {
            systemApp.setProjectStartDate(actor, project, date);
            System.out.println("Start date for project \"" + project + "\" successfully set to \"" + day + "/" + month + "/" + year + "\"");
        } catch (SystemAppException e) {
            System.out.println(e.getMessage());
        }
    }

    private void setProjectEndDate(Scanner arguments) {
        if (!arguments.hasNext()) {
            System.out.println("Usage: setProjectEndDate <project> <dd> <mm> <yyyy>");
            return;
        }

        String project = arguments.next();
        if (!arguments.hasNextInt()) {
            System.out.println("Usage: setProjectEndDate <project> <dd> <mm> <yyyy>");
            return;
        }
        int day = arguments.nextInt();
        if (!arguments.hasNextInt()) {
            System.out.println("Usage: setProjectEndDate <project> <dd> <mm> <yyyy>");
            return;
        }
        int month = arguments.nextInt();
        if (!arguments.hasNextInt()) {
            System.out.println("Usage: setProjectEndDate <project> <dd> <mm> <yyyy>");
            return;
        }
        int year = arguments.nextInt();
        Calendar date;
        try {
            date = SystemCalendar.getCalendar(day, month, year);
        } catch (Exception e) {
            System.out.println("Date format not valid. usage: <dd> <mm> <yyyy>");
            return;
        }
        try {
            systemApp.setProjectEndDate(actor, project, date);
            System.out.println("End date for project \"" + project + "\" successfully set to \"" + day + "/" + month + "/" + year + "\"");

        } catch (SystemAppException e) {
            System.out.println(e.getMessage());
        }
    }

    private void setProjectLeader(Scanner arguments){
        if (!arguments.hasNext()) {
            System.out.println("Usage: setProjectLeader <project> <employee>");
            return;
        }
        String project = arguments.next();
        if (!arguments.hasNext()) {
            System.out.println("Usage: setProjectLeader <project> <employee>");
            return;
        }
        String employee = arguments.next();
        try {
            systemApp.setProjectLeader(actor, project, employee);
            System.out.println("Project leader for project \"" + project + "\" successfully set to \"" + employee + "\"");
        } catch (SystemAppException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createFirmActivity(Scanner arguments) {
        if (!arguments.hasNext()){
            System.out.println("Usage: createFirmActivity <activity>");
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

    public void registerTimeFirmActivity(Scanner arguments) {
        if (!arguments.hasNext()){
            System.out.println("Usage: registerTimeFirmActivity <employee> <firmActivity> <hours> <minutes> <day> <month> <year>");
            return;
        }
        String employee = arguments.next();
        if (!arguments.hasNext()){
            System.out.println("Usage: registerTimeFirmActivity <employee> <firmActivity> <hours> <minutes> <day> <month> <year>");
            return;
        }
        String firmActivityName = arguments.next();
        if (!arguments.hasNext()){
            System.out.println("Usage: registerTimeFirmActivity <employee> <firmActivity> <hours> <minutes> <day> <month> <year>");
            return;
        }
        int hours = arguments.nextInt();
        if (!arguments.hasNextInt()){
            System.out.println("Usage: registerTimeFirmActivity <employee> <firmActivity> <hours> <minutes> <day> <month> <year>");
            return;
        }
        int minutes = arguments.nextInt();
        if (!arguments.hasNextInt()){
            System.out.println("Usage: registerTimeFirmActivity <employee> <firmActivity> <hours> <minutes> <day> <month> <year>");
            return;
        }
        int day = arguments.nextInt();
        if (!arguments.hasNextInt()){
            System.out.println("Usage: registerTimeFirmActivity <employee> <firmActivity> <hours> <minutes> <day> <month> <year>");
            return;
        }
        int month = arguments.nextInt();
        if (!arguments.hasNextInt()){
            System.out.println("Usage: registerTimeFirmActivity <employee> <firmActivity> <hours> <minutes> <day> <month> <year>");
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

    private void getFirmActivityHours(Scanner arguments){
        if (!arguments.hasNext()){
            System.out.println("Usage: getFirmActivityHours <employee> <activity> <day> <month> <year>");
            return;
        }
        String employee = arguments.next();
        if (!arguments.hasNext()){
            System.out.println("Usage: getFirmActivityHours <employee> <activity> <day> <month> <year>");
            return;
        }
        String firmActivityName = arguments.next();
        if (!arguments.hasNextInt()){
            System.out.println("Usage: getFirmActivityHours <employee> <activity> <day> <month> <year>");
            return;
        }
        int day = arguments.nextInt();
        if (!arguments.hasNextInt()){
            System.out.println("Usage: getFirmActivityHours <employee> <activity> <day> <month> <year>");
            return;
        }
        int month = arguments.nextInt();
        if (!arguments.hasNextInt()){
            System.out.println("Usage: getFirmActivityHours <employee> <activity> <day> <month> <year>");
            return;
        }
        int year = arguments.nextInt();
        try {
            double hours = systemApp.getFirmActivityHours(employee,firmActivityName,day,month,year);
            System.out.println(employee + " has registered " + hours + " hours to firm activity \"" + firmActivityName + "\" at " + day + "/" + month + "/" + year);
        } catch (SystemAppException e){
            System.out.println(e.getMessage());
        }
    }

    private void createActivity(Scanner arguments) {
        if (!arguments.hasNext()){
            System.out.println("Usage: createActivity <project> <activity>");
            return;
        }
        String project = arguments.next();
        if (!arguments.hasNext()){
            System.out.println("Usage: createActivity <project> <activity>");
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
            systemApp.assignEmployee(actor, project, activity, employee);
            System.out.println("Employee \"" + employee + "\" successfully assigned to activity \"" + activity + "\" in project \"" + project + "\"");
        } catch (SystemAppException e) {
            System.out.println(e.getMessage());
        }
    }

    private void setActivityStartWeek(Scanner arguments) {
        if (!arguments.hasNext()) {
            System.out.println("Usage: setActivityStartWeek <project> <activity> <week> <year>");
            return;
        }
        String project = arguments.next();
        if (!arguments.hasNext()) {
            System.out.println("Usage: setActivityStartWeek <project> <activity> <week> <year>");
            return;
        }
        String activity = arguments.next();
        if (!arguments.hasNextInt()) {
            System.out.println("Usage: setActivityStartWeek <project> <activity> <week> <year>");
            return;
        }
        int week = arguments.nextInt();
        if (!arguments.hasNextInt()) {
            System.out.println("Usage: setActivityStartWeek <project> <activity> <week> <year>");
            return;
        }
        int year = arguments.nextInt();
        try {
            systemApp.setActivityStartWeek(actor, project, activity, week, year);
            System.out.println("Start Week for activity \"" + activity + "\" successfully set to " + week + " in project \"" + project + "\"");
        }  catch (SystemAppException e) {
            System.out.println(e.getMessage());
        }
    }

    private void setActivityEndWeek(Scanner arguments) {
        if (!arguments.hasNext()) {
            System.out.println("Usage: setActivityEndWeek <project> <activity> <week> <year>");
            return;
        }
        String project = arguments.next();
        if (!arguments.hasNext()) {
            System.out.println("Usage: setActivityEndWeek <project> <activity> <week> <year>");
            return;
        }
        String activity = arguments.next();
        if (!arguments.hasNextInt()) {
            System.out.println("Usage: setActivityEndWeek <project> <activity> <week> <year>");
            return;
        }
        int week = arguments.nextInt();
        if (!arguments.hasNextInt()) {
            System.out.println("Usage: setActivityEndWeek <project> <activity> <week> <year>");
            return;
        }
        int year = arguments.nextInt();
        try {
            systemApp.setActivityEndWeek(actor, project, activity, week, year);
            System.out.println("End Week for activity \"" + activity + "\" successfully set to " + week + " in project \"" + project + "\"");
        }  catch (SystemAppException e) {
            System.out.println(e.getMessage());
        }
    }

    private void registerTimeToday(Scanner arguments){
        if (!arguments.hasNext()){
            System.out.println("Usage: registerTimeToday <project> <activity> <employee> <hours> <minutes>");
            return;
        }
        String project = arguments.next();
        if (!arguments.hasNext()){
            System.out.println("Usage: registerTimeToday <project> <activity> <employee> <hours> <minutes>");
            return;
        }
        String activityName = arguments.next();
        if (!arguments.hasNext()){
            System.out.println("Usage: registerTimeToday <project> <activity> <employee> <hours> <minutes>");
            return;
        }
        String employee = arguments.next();
        if (!arguments.hasNextInt()){
            System.out.println("Usage: registerTimeToday <project> <activity> <employee> <hours> <minutes>");
            return;
        }
        int hours = arguments.nextInt();
        if (!arguments.hasNextInt()){
            System.out.println("Usage: registerTimeToday <project> <activity> <employee> <hours> <minutes>");
            return;
        }
        int minutes = arguments.nextInt();
        try {
            systemApp.registerTimeToday(project, activityName, employee, hours, minutes);
            double registered = systemApp.getActivityHoursToday(project, activityName, employee);
            System.out.println(registered + " hours have been registered to \"" + employee + "\" in activity \"" + activityName + "\"");
        } catch (SystemAppException e){
            System.out.println(e.getMessage());
        }
    }

    private void getActivityHoursToday(Scanner arguments){
        if (!arguments.hasNext()){
            System.out.println("Usage: getActivityHoursToday <project> <activity> <employee>");
            return;
        }

        String project = arguments.next();
        if (!arguments.hasNext()){
            System.out.println("Usage: getActivityHoursToday <project> <activity> <employee>");
            return;
        }
        String activityName = arguments.next();
        if (!arguments.hasNext()){
            System.out.println("Usage: getActivityHoursToday <project> <activity> <employee>");
            return;
        }
        String employee = arguments.next();
        try {
            double hours = systemApp.getActivityHoursToday(project,activityName,employee);
            System.out.println(employee + " has registered " + hours + " hours to " + activityName);
        } catch (SystemAppException e){
            System.out.println(e.getMessage());
        }
    }

    private void registerTime(Scanner arguments){
        if(!arguments.hasNext()){
            System.out.println("Usage: registerTime <employee> <project> <activity> <hours> <minutes> <day> <month> <year>");
            return;
        }
        String employee = arguments.next();
        if(!arguments.hasNext()){
            System.out.println("Usage: registerTime <employee> <project> <activity> <hours> <minutes> <day> <month> <year>");
            return;
        }
        String project = arguments.next();
        if(!arguments.hasNext()){
            System.out.println("Usage: registerTime <employee> <project> <activity> <hours> <minutes> <day> <month> <year>");
            return;
        }
        String activity = arguments.next();
        if(!arguments.hasNextInt()){
            System.out.println("Usage: registerTime <employee> <project> <activity> <hours> <minutes> <day> <month> <year>");
            return;
        }
        int hours = arguments.nextInt();
        if(!arguments.hasNextInt()){
            System.out.println("Usage: registerTime <employee> <project> <activity> <hours> <minutes> <day> <month> <year>");
            return;
        }
        int minutes = arguments.nextInt();
        if(!arguments.hasNextInt()){
            System.out.println("Usage: registerTime <employee> <project> <activity> <hours> <minutes> <day> <month> <year>");
            return;
        }
        int day = arguments.nextInt();
        if(!arguments.hasNextInt()){
            System.out.println("Usage: registerTime <employee> <project> <activity> <hours> <minutes> <day> <month> <year>");
            return;
        }
        int month = arguments.nextInt();
        if(!arguments.hasNextInt()){
            System.out.println("Usage: registerTime <employee> <project> <activity> <hours> <minutes> <day> <month> <year>");
            return;
        }
        int year = arguments.nextInt();
        try{
            systemApp.registerTimeActivity(employee,project,activity,hours,minutes,day,month,year);
            double registered = systemApp.getActivityHours(employee,project,activity,day,month,year);
            System.out.println(employee + " has registered " + registered + " hours to " + activity + " at " + day+ "/" + month + "/" + year);
        } catch (SystemAppException e){
            System.out.println(e.getMessage());
        }
    }

    private void getActivityHours(Scanner arguments){
        if(!arguments.hasNext()){
            System.out.println("Usage: getActivityHours <employee> <project> <activity> <day> <month> <year>");
            return;
        }
        String employee = arguments.next();
        if(!arguments.hasNext()){
            System.out.println("Usage: getActivityHours <employee> <project> <activity> <day> <month> <year>");
            return;
        }
        String project = arguments.next();
        if(!arguments.hasNext()){
            System.out.println("Usage: getActivityHours <employee> <project> <activity> <day> <month> <year>");
            return;
        }
        String activity = arguments.next();
        if(!arguments.hasNextInt()){
            System.out.println("Usage: getActivityHours <employee> <project> <activity> <day> <month> <year>");
            return;
        }
        int day = arguments.nextInt();
        if(!arguments.hasNextInt()){
            System.out.println("Usage: getActivityHours <employee> <project> <activity> <day> <month> <year>");
            return;
        }
        int month = arguments.nextInt();
        if(!arguments.hasNextInt()){
            System.out.println("Usage: getActivityHours <employee> <project> <activity> <day> <month> <year>");
            return;
        }
        int year = arguments.nextInt();
        try{
            double hours = systemApp.getActivityHours(employee,project,activity,day,month,year);
            System.out.println(employee + " has registered " + hours + " hours to " + activity + " at " + day+ "/" + month + "/" + year);
        } catch (SystemAppException e){
            System.out.println(e.getMessage());
        }
    }

    private void getTodayHoursProject(Scanner arguments) {
        if (!arguments.hasNext()) {
            System.out.println("Usage: getTodayHoursProject <project> <employee>");
            return;
        }
        String project = arguments.next();
        if (!arguments.hasNext()){
            System.out.println("Usage: getTodayHoursProject <project> <employee>");
            return;
        }
        String actor = arguments.next();
        try {
            System.out.println(systemApp.getTodayHoursProject(project,actor)+ " hours");


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
            System.out.println("Estimated Hours for activity \"" + activity + "\" successfully set to \"" + hours + "\" in project \"" + project + "\"");

        } catch (SystemAppException e){
            System.out.println(e.getMessage());
        }
    }

    private void listProjects() {
        Map<Integer, String> projects = systemApp.listProjects();
        int i = 1;
        if (projects.isEmpty()){
            System.out.println("There are no projects");
        }
        for (int id : projects.keySet()) {
            System.out.printf("\tProject %2d: %s#%d\n", i, projects.get(id), id);
        }
    }

    private void listActivities(String projectName) {
        List<String> activities;
        try {
            activities = systemApp.getProjectActivityList(projectName);
        } catch (Exception e) {
            System.out.println("Project \"" + projectName + "\" has no activities");
            return;
        }
        if (activities.isEmpty()){
            System.out.println("Project \"" + projectName + "\" has no activities");
        }

        for (int i = 0; i < activities.size(); i++) {
            System.out.printf("\tActivity %2d: %s\n", (i+1), activities.get(i));
        }
    }

    private void listEmployees() {
        List<String> employees = systemApp.getEmployeeList();
        for (int i = 0; i < employees.size(); i++) {
            System.out.printf("\tEmployee %2d: %s\n", (i+1), employees.get(i));
        }
    }

    private void listAvailableEmployees() {
        List<String> availableEmployees = systemApp.getAvailableEmployeesList();

        if (availableEmployees.isEmpty()){
            System.out.println("There are no available employees");
        }

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
            id = "" + systemApp.getProjectId(projectName);
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

    private void helpBase() {
        System.out.println("General system commands:");
        System.out.println("\texit  -  exit the program");
        System.out.println("\tlogin <user>  -  log into the system as <user>");
        System.out.println("\tlogout  -  log out the current user");
        System.out.println("\tcreateEmployee <employee>  -  register <employee> in the system");
        System.out.println("\tlist projects  -  list all projects in the SystemApp");
        System.out.println("\tlist activities <project>  -  list all activities in given <project>");
        System.out.println("\tlist employees  -  list all employees registered in the system");
        System.out.println("\thelp  -  display this menu");
        System.out.println();
        System.out.println("Further help:\n\thelp project\n\thelp activity\n\thelp firmActivity");
    }

    private void helpProject() {
        System.out.println("General project commands:");
        System.out.println("\tinfo <project>  -  display general information about");
        System.out.println("\tcreateProject <project>  -  create a new project with name <project>");
        System.out.println("\tgenerateReport <project>  -  generate report for <project>");
        System.out.println();
        System.out.println("Only when logged in as project leader (or none is assigned):");
        System.out.println("\tsetProjectCustomer <project> <customer>  -  set the customer of <project> to <customer>");
        System.out.println("\tsetProjectName <project> <newName>  -  set the project name of <project> to <newName>");
        System.out.println("\tsetProjectStartDate <project> <dd> <mm> <yyyy>  -  set the start date of <project>");
        System.out.println("\tsetProjectEndDate <project> <dd> <mm> <yyyy>  -  set the end date of <project>");
        System.out.println("\tsetProjectLeader <project> <employee>  -  set the project leader of <project> to <employee>");
    }

    private void helpActivity() {
        System.out.println("General activity commands:");
        System.out.println("\tregisterTimeToday <project> <activity> <employee> <hours> <minutes>  -  register time that <employee> worked on <activity> today");
        System.out.println("\tregisterTime <employee> <project> <activity> <hours> <minutes> <day> <month> <year>  -  register time that <employee> worked on <activity> on a specific day");
        System.out.println("\tgetActivityHoursToday <project> <activity> <employee>  -  check how many hours <employee> has worked on <activity> today");
        System.out.println("\tgetActivityHours <employee> <project> <activity> <day> <month> <year>  -  check how many hours <employee> has worked on <activity> on a specific day");
        System.out.println();
        System.out.println("Only when logged in as project leader (or none is assigned):");
        System.out.println("\tcreateActivity <project> <activity>  -  create new <activity> in <project>");
        System.out.println("\tassignEmployee <project> <activity> <employee>  -  assign <employee> to <activity>");
        System.out.println("\tsetActivityStartWeek <project> <activity> <week> <year>  -  set start week of <activity>");
        System.out.println("\tsetActivityEndWeek <project> <activity> <week> <year>  -  set end week of <activity>");
        System.out.println("\tsetActivityEstimatedHours <project> <activity> <hours>  -  set the estimated hours of <activity>");
    }

    private void helpFirmActivity() {
        System.out.println("General firm activity commands:");
        System.out.println("\tcreateFirmActivity <firmActivity>  -  create new <firmActivity> in the system");
        System.out.println("\tregisterTimeFirmActivity <employee> <firmActivity> <hours> <minutes> <day> <month> <year>  -  register time for <employee> in <firmActivity> on a specific day");
        System.out.println("\tgetFirmActivityHours <employee> <firmActivity> <day> <month> <year>  -  check the time registered for <employee> in <firmActivity> on a specific day");
    }

    private void help(Scanner arguments) {
        if (!arguments.hasNext()) {
            helpBase();
            return;
        }
        String arg = arguments.next();
        switch (arg.toLowerCase()) {
            case "project":
                helpProject();
                break;
            case "activity":
                helpActivity();
                break;
            case "firmactivity":
                helpFirmActivity();
                break;
            default:
                System.out.println("Invalid argument. Usage:\n\thelp project\n\thelp activity\n\thelp firmActivity");
                break;
        }
    }

    public void launch() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the time registration and project management system\n -- by Softwarehuset A/S --");
        helpBase();
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
                case "createemployee":
                    createEmployee(arguments);
                    break;
                case "gettodayhoursproject":
                    getTodayHoursProject(arguments);
                    break;

                // Project commands
                case "createproject":
                    createProject(arguments);
                    break;
                case "setprojectcustomer":
                    setProjectCustomer(arguments);
                    break;
                case "setprojectname":
                    setProjectName(arguments);
                    break;
                case "setprojectstartdate":
                    setProjectStartDate(arguments);
                    break;
                case "setprojectenddate":
                    setProjectEndDate(arguments);
                    break;
                case "setprojectleader":
                    setProjectLeader(arguments);
                    break;
                case "generatereport":
                    generateReport(arguments);
                    break;

                // Firm activity commands
                case "createfirmactivity":
                    createFirmActivity(arguments);
                    break;
                case "registertimefirmactivity":
                    registerTimeFirmActivity(arguments);
                    break;
                case "getfirmactivityhours":
                    getFirmActivityHours(arguments);
                    break;

                // Activity commands
                case "createactivity":
                    createActivity(arguments);
                    break;
                case "assignemployee":
                    assignEmployee(arguments);
                    break;
                case "setactivitystartweek":
                    setActivityStartWeek(arguments);
                    break;
                case "setactivityendweek":
                    setActivityEndWeek(arguments);
                    break;
                case "registertimetoday":
                    registerTimeToday(arguments);
                    break;
                case "getactivityhourstoday":
                    getActivityHoursToday(arguments);
                    break;
                case "registertime":
                    registerTime(arguments);
                    break;
                case "getactivityhours":
                    getActivityHours(arguments);
                    break;
                case "setactivityestimatedhours":
                    setActivityEstimatedHours(arguments);
                    break;

                // Help commands
                case "help":
                    help(arguments);
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
                    System.out.println("Exiting system...");
                    return;
                default:
                    System.out.println("Unknown command: " + command + "\ntype \"help\" for further information");
            }
        }
    }

    public static void main(String[] args) {
        App ui = new App();
        ui.launch();
    }
}
