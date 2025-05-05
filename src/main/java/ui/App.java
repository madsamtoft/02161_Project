package ui;

import app.SystemApp;
import app.SystemAppException;

import java.util.Scanner;

public class App {

    private final SystemApp pma = new SystemApp();

    private void createProject(Scanner arguments) {
        if (!arguments.hasNext()) {
            System.out.println("Usage: createProject <name>");
            return;
        }
        String name = arguments.next();
        try {
            pma.createProject(name);
        } catch (SystemAppException e) {
            System.out.println(e.getMessage());
        }
    }

    public void launch() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("# ");
            String line = scanner.nextLine();
            Scanner lineScanner = new Scanner(line);
            String command = "";
            if (lineScanner.hasNext()) {
                command = lineScanner.next();
            }
            switch (command.toLowerCase()) {
                case "createproject":
                    createProject(lineScanner);
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
