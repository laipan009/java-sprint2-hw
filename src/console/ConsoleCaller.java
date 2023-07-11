package console;

import console.manager.ConsoleManager;

import java.util.Scanner;

public class ConsoleCaller {
    Scanner scanner = new Scanner(System.in);
    ConsoleManager consoleManager = new ConsoleManager();

    public void getConsole() {
        while (true) {
            consoleManager.printMenu();
            var userInput = scanner.nextLine().trim();
            switch (userInput) {
                case "1":
                    consoleManager.readMonthReports();
                    break;
                case "2":
                    consoleManager.readYearReport();
                    break;
                case "3":
                    consoleManager.callCheckerReports();
                    break;
                case "4":
                    consoleManager.printInfoForMonthReport();
                    break;
                case "5":
                    consoleManager.printInfoForYearReport();
                    break;
                case "exit":
                    System.out.println("Пока!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Такой команды нет");
            }
        }
    }
}
