package print;

import check.reports.CheckerOfAccountingReport;
import constant.YearlyReportConstant;
import monthly.report.MonthlyReport;
import transaction.UnitTransaction;

import java.util.List;

public class PrinterOfInfo {
    CheckerOfAccountingReport checkerOfAccountingReport = new CheckerOfAccountingReport();
    public void printInfoForMonthReport() {
        for (int i = 0; i < MonthlyReport.getListOfListsOfUnitTransactionsByMonth().size(); i++) {
            List<UnitTransaction> listOfUnitTransactionsOfCurrentMonth = MonthlyReport.getListOfListsOfUnitTransactionsByMonth().get(i);
            switch (i) {
                case 0:
                    System.out.println("Месяц - Январь");
                    checkerOfAccountingReport.getMostProfitableProductInMonth(listOfUnitTransactionsOfCurrentMonth);
                    checkerOfAccountingReport.getMostExpensesProductInMonth(listOfUnitTransactionsOfCurrentMonth);
                    break;
                case 1:
                    System.out.println("Месяц - Февраль");
                    checkerOfAccountingReport.getMostProfitableProductInMonth(listOfUnitTransactionsOfCurrentMonth);
                    checkerOfAccountingReport.getMostExpensesProductInMonth(listOfUnitTransactionsOfCurrentMonth);
                    break;
                case 2:
                    System.out.println("Месяц - Март");
                    checkerOfAccountingReport.getMostProfitableProductInMonth(listOfUnitTransactionsOfCurrentMonth);
                    checkerOfAccountingReport.getMostExpensesProductInMonth(listOfUnitTransactionsOfCurrentMonth);
                    break;
            }
        }
    }
    public void printInfoForYearReport() {
        List<List<UnitTransaction>> listOfListsOfUnitTransactionsByMonth = MonthlyReport.getListOfListsOfUnitTransactionsByMonth();
        System.out.println("Год - 2021");
        checkerOfAccountingReport.getProfitForEachMonth(YearlyReportConstant.getYearlyReportList());
        checkerOfAccountingReport.printAverageExpenseTransactionInYear(listOfListsOfUnitTransactionsByMonth);
        checkerOfAccountingReport.printAverageIncomeTransactionInYear(listOfListsOfUnitTransactionsByMonth);
    }

}
