package console;

import check.reports.CheckerOfAccountingReport;
import constant.YearlyReportConstant;
import monthly.report.MonthlyReport;
import print.PrinterOfInfo;
import transaction.UnitTransaction;
import utill.ConverterFromListToObject;
import utill.FileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleForManage {
    boolean isMonthlyReportRead = false;
    boolean isYearlyReportRead = false;
    FileReader fileReader = new FileReader();
    Scanner scanner = new Scanner(System.in);
    ConverterFromListToObject converterFromListToObject = new ConverterFromListToObject();
    CheckerOfAccountingReport checkerOfAccountingReport = new CheckerOfAccountingReport();
    PrinterOfInfo printerOfInfo = new PrinterOfInfo();

    public void getConsoleForManageAccounting() {
        while (true) {
            printMenu();
            var userInput = scanner.nextLine().trim();
            switch (userInput) {
                case "1":
                    List<List<UnitTransaction>> transactions = new ArrayList<>();
                    for (int i = 1; i < 4; i++) {
                        ArrayList<String> lines = fileReader.readFileContents("m.20210" + i + ".csv");
                        transactions.add(converterFromListToObject.convertFromListToObjectTransactionsForMonth(lines));
                    }
                    MonthlyReport.setListOfListsOfUnitTransactionsByMonth(transactions);
                    System.out.println("Месячные отчеты считаны!");
                    isMonthlyReportRead = true;
                    break;
                case "2":
                    ArrayList<String> lines = fileReader.readFileContents("y.2021.csv");
                    YearlyReportConstant.setYearlyReportList(converterFromListToObject.convertFromListToObjectOfYearReport(lines));
                    System.out.println("Годовой отчет считан!");
                    isYearlyReportRead = true;
                    break;
                case "3":
                    if (isMonthlyReportRead && isYearlyReportRead) {
                        checkerOfAccountingReport.checkOfMonthlyAndYearlyReportByEachMonth();
                    } else {
                        System.out.println("Считайте сначало годовой и месячные отчеты.");
                    }
                    break;
                case "4":
                    if (isMonthlyReportRead && isYearlyReportRead) {
                        printerOfInfo.printInfoForMonthReport();
                    } else {
                        System.out.println("Считайте сначало годовой и месячные отчеты.");
                    }
                    break;
                case "5":
                    if (isMonthlyReportRead && isYearlyReportRead) {
                        printerOfInfo.printInfoForYearReport();
                    } else {
                        System.out.println("Считайте сначало годовой и месячные отчеты.");
                    }
                    break;
                case "0":
                    System.out.println("Пока!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Такой команды нет");
            }
        }
    }
    private static void printMenu() {
        List<String> linesOfMenu = new ArrayList<>();
        linesOfMenu.add("Выберите что Вы хотите сделать?");
        linesOfMenu.add("1 - Считать все месячные отчёты.");
        linesOfMenu.add("2 - Считать годовой отчёт.");
        linesOfMenu.add("3 - Сверить отчёты.");
        linesOfMenu.add("4 - Вывести информацию обо всех месячных отчётах.");
        linesOfMenu.add("5 - Вывести информацию о годовом отчёте.");
        linesOfMenu.add("0 - Закрыть программу.");
        for (String lineOfMenu : linesOfMenu) {
            System.out.println(lineOfMenu);
        }
    }
}
