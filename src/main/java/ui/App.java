package ui;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("# ");
            String line = scanner.nextLine();
            Scanner lineScanner = new Scanner(line);
            String command = lineScanner.next();
            switch (command.toLowerCase()) {
                case "exit":
                    return;
                default:
                    System.out.println("Unknown command");
            }
        }
    }
}
