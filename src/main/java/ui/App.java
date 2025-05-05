package ui;

import app.SystemApp;
import app.SystemAppException;

import java.util.Scanner;

public class App {

    private final SystemApp systemApp = new SystemApp();

    private void registerEmployee(Scanner arguments) {
        if (!arguments.hasNext()) {
            System.out.println("Usage: registerEmployee <name>");
            return;
        }
        String name = arguments.next();
        try {
            systemApp.registerEmployee(name);
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
        } catch (SystemAppException e) {
            System.out.println(e.getMessage());
        }
    }

    private void assignProjectLeader(Scanner arguments) {
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
                case "registeremployee":
                    registerEmployee(arguments);
                    break;
                case "createproject":
                    createProject(arguments);
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
