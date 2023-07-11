package console.manager;

import check.reports.CheckerReports;
import constant.YearlyReportConstant;
import monthly.report.MonthlyReport;
import transaction.UnitTransaction;
import utill.FileReader;
import utill.ReportMapper;

import java.util.ArrayList;
import java.util.List;

public class ConsoleManager {
    private boolean isMonthlyReportRead;
    private boolean isYearlyReportRead;
    FileReader fileReader = new FileReader();
    ReportMapper reportMapper = new ReportMapper();
    CheckerReports checkerReports = new CheckerReports();

    public void readMonthReports() {
        List<List<UnitTransaction>> transactions = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            ArrayList<String> lines = fileReader.readFileContents("m.20210" + i + ".csv");
            transactions.add(reportMapper.convertToMonthReport(lines));
            MonthlyReport.setUnitTransactionsByMonth(transactions);
        }
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
            for (int i = 0; i < MonthlyReport.getUnitTransactionsByMonth().size(); i++) {
                List<UnitTransaction> transactionsCurrentMonth = MonthlyReport.getUnitTransactionsByMonth().get(i);
                switch (i) {
                    case 0:
                        System.out.println("Месяц - Январь");
                        checkerReports.getMostProfitableProductInMonth(transactionsCurrentMonth);
                        checkerReports.getMostExpensesProductInMonth(transactionsCurrentMonth);
                        break;
                    case 1:
                        System.out.println("Месяц - Февраль");
                        checkerReports.getMostProfitableProductInMonth(transactionsCurrentMonth);
                        checkerReports.getMostExpensesProductInMonth(transactionsCurrentMonth);
                        break;
                    case 2:
                        System.out.println("Месяц - Март");
                        checkerReports.getMostProfitableProductInMonth(transactionsCurrentMonth);
                        checkerReports.getMostExpensesProductInMonth(transactionsCurrentMonth);
                        break;
                }
            }
        } else {
            System.out.println("Считайте сначало годовой и месячные отчеты.");
        }
    }
    public void printInfoForYearReport() {
        List<List<UnitTransaction>> transactionsByMonth = MonthlyReport.getUnitTransactionsByMonth();
        System.out.println("Год - 2021");
        checkerReports.getProfitForEachMonth(YearlyReportConstant.getYearlyReports());
        checkerReports.printAverageExpenseInYear(transactionsByMonth);
        checkerReports.printAverageIncomeInYear(transactionsByMonth);
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


