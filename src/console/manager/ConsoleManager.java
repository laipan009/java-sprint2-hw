package console.manager;

import check.reports.CheckerReports;
import constant.YearlyReportConstant;
import monthly.report.MonthlyReport;
import transaction.UnitTransaction;
import utill.FileReader;
import utill.Months;
import utill.ReportMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsoleManager {
    private boolean isMonthlyReportRead;
    private boolean isYearlyReportRead;
    FileReader fileReader = new FileReader();
    ReportMapper reportMapper = new ReportMapper();
    CheckerReports checkerReports = new CheckerReports();

    public void readMonthReports() {
        Map<String, List<UnitTransaction>> transactions = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            ArrayList<String> lines = fileReader.readFileContents("m.20210" + (i+1) + ".csv");
            transactions.put(Months.changeNumberToMonthName(i), reportMapper.convertToMonthReport(lines));
        }
        MonthlyReport.setUnitTransactionsByMonth(transactions);
        System.out.println("Месячные отчеты считаны!");
        isMonthlyReportRead = true;
    }

    public void readYearReport() {
        ArrayList<String> lines = fileReader.readFileContents("y.2021.csv");
        YearlyReportConstant.setYearlyReports(reportMapper.convertToYearReport(lines));
        System.out.println("Годовой отчет считан!");
        isYearlyReportRead = true;
    }

    public void callCheckerReports() {
        if (isMonthlyReportRead && isYearlyReportRead) {
            checkerReports.checkMonthlyAndYearlyReport();
        } else {
            System.out.println("Считайте сначало годовой и месячные отчеты.");
        }
    }

    public void printInfoForMonthReport() {
        if (isMonthlyReportRead && isYearlyReportRead) {
            Map<String, List<UnitTransaction>> transactions = MonthlyReport.getUnitTransactionsByMonth();
            for (String monthName: transactions.keySet()) {
                System.out.println("Месяц - " + monthName);
                checkerReports.getMostProfitableProductInMonth(transactions.get(monthName));
                checkerReports.getMostExpensesProductInMonth(transactions.get(monthName));
            }
        } else {
            System.out.println("Считайте сначало годовой и месячные отчеты.");
        }
    }
    public void printInfoForYearReport() {
        if (isMonthlyReportRead && isYearlyReportRead) {
            Map<String, List<UnitTransaction>> transactions = MonthlyReport.getUnitTransactionsByMonth();
            System.out.println("Год - 2021");
            checkerReports.getProfitForEachMonth(YearlyReportConstant.getYearlyReports());
            checkerReports.printAverageExpenseInYear(transactions);
            checkerReports.printAverageIncomeInYear(transactions);
        }  else {
            System.out.println("Считайте сначало годовой и месячные отчеты.");
        }
    }
    public void printMenu() {
        List<String> linesOfMenu = new ArrayList<>();
        linesOfMenu.add("Выберите что Вы хотите сделать?");
        linesOfMenu.add("1 - Считать все месячные отчёты.");
        linesOfMenu.add("2 - Считать годовой отчёт.");
        linesOfMenu.add("3 - Сверить отчёты.");
        linesOfMenu.add("4 - Вывести информацию обо всех месячных отчётах.");
        linesOfMenu.add("5 - Вывести информацию о годовом отчёте.");
        linesOfMenu.add("\"exit\" - Закрыть программу.");
        for (String lineOfMenu : linesOfMenu) {
            System.out.println(lineOfMenu);
        }
    }

}


