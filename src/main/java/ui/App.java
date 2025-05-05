package ui;

import app.SystemApp;
import app.SystemAppException;
import java.util.Calendar;

import java.util.Scanner;

public class App {

    private final SystemApp systemApp = new SystemApp();

    private void createProject(Scanner arguments) {
        if (!arguments.hasNext()) {
            System.out.println("Usage: createProject <name>");
            return;
        }
        String name = arguments.next();
        try {
            systemApp.createProject(name);
        } catch (SystemAppException e) {
            System.out.println(e.getMessage());
        }
    }

    private void changeProjectName(Scanner arguments) {
        if (!arguments.hasNext()) {
            System.out.println("Usage: changeProjectName <project> <newName>");
            return;
        }
        String actor = "NULL"; //TODO Parse actor from arguments
        String name = arguments.next();
            if (!arguments.hasNext()) {
                System.out.println("Usage: changeProjectName <project> <newName>");
                return;
            }
        String newName = arguments.next();
        try {
            systemApp.changeProjectName(actor, name, newName);
        } catch (SystemAppException e) {
            System.out.println(e.getMessage());
        }
    }

    private void changeProjectStartDate(Scanner arguments) {
        if (!arguments.hasNext()) {
            System.out.println("Usage: changeProjectStartDate <project> <dd> <mm> <yyyy>");
            return;
        }
        String actor = "NULL"; //TODO Parse actor from arguments
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
            systemApp.changeProjectStartDate(actor, project, date);
        } catch (SystemAppException e) {
            System.out.println(e.getMessage());
        }
    }

    private void changeProjectEndDate(Scanner arguments) {
        if (!arguments.hasNext()) {
            System.out.println("Usage: changeProjectEndDate <project> <dd> <mm> <yyyy>");
            return;
        }
        String actor = "NULL"; //TODO Parse actor from arguments

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
            systemApp.changeProjectEndDate(actor, project, date);
        } catch (SystemAppException e) {
            System.out.println(e.getMessage());
        }
    }

    private void changeProjectLeader(Scanner arguments) {
        if (!arguments.hasNext()) {
            System.out.println("Usage: changeProjectLeader <project> <name>");
            return;
        }
        String actor = "NULL"; //TODO Parse actor from arguments
//        if (!arguments.hasNext()) {
//                System.out.println("Usage: changeProjectLeader <project> <name>");
//                return;}

        String project = arguments.next();
        if (!arguments.hasNext()) {
            System.out.println("Usage: changeProjectLeader <project> <name>");
            return;
        }
        String newProjectLeader = arguments.next();
        try {
            systemApp.changeProjectLeader(actor, project, newProjectLeader);
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
            systemApp.assignProjectLeader(project, employee);
        } catch (SystemAppException e) {
            System.out.println(e.getMessage());
        }
    }

    public void launch() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("# ");
            String line = scanner.nextLine();
            Scanner arguments = new Scanner(line);
            String command = "";
            if (arguments.hasNext()) {
                command = arguments.next();
            }
            switch (command.toLowerCase()) {
                case "createproject":
                    createProject(arguments);
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
                case "changeprojectleader":
                    changeProjectLeader(arguments);
                    break;
                case "assignprojectleader":
                    assignProjectLeader(arguments);
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Unknown command");
            }
        }
    }

    public static void main(String[] args) {
        App ui = new App();
        ui.launch();
    }
}
