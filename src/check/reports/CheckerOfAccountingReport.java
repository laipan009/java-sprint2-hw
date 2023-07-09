package check.reports;

import constant.YearlyReportConstant;
import monthly.report.MonthlyReport;
import transaction.UnitTransaction;
import yearly.report.YearlyReport;

import java.util.HashMap;
import java.util.List;

public class CheckerOfAccountingReport {
    public void checkOfMonthlyAndYearlyReportByEachMonth() {
        boolean successfully = true;
        for (int i = 0; i < MonthlyReport.getListOfListsOfUnitTransactionsByMonth().size(); i++) {
            List<UnitTransaction> listOfCurrentUnitTransactions = MonthlyReport.getListOfListsOfUnitTransactionsByMonth().get(i);

            int incomeOfCurrentMonth = getSumOfAllIncomesInListOfUnitTransactions(listOfCurrentUnitTransactions);
            int expenseOfCurrentMonth = getSumOfAllExpenseInListOfUnitTransactions(listOfCurrentUnitTransactions);

            YearlyReport currentYearlyReport = YearlyReportConstant.getYearlyReportList().get(i);

            if (currentYearlyReport.isExpenseOrIncome()) {
                successfully = isExpensesInCurrentMonthAndYearReportEquals(currentYearlyReport, expenseOfCurrentMonth, i);
            } else {
                successfully = isIncomeInCurrentMonthAndYearReportEquals(currentYearlyReport, incomeOfCurrentMonth, i);
            }
        }
        if (successfully) {
            System.out.println("Сверка прошла успешно!");
        }
    }

    private boolean isExpensesInCurrentMonthAndYearReportEquals(YearlyReport currentYearlyReport, int currentMonthExpense, int numberOfMonthInLoop) {
        if (currentYearlyReport.getSumPerMonth() != currentMonthExpense) {
            System.out.println("Обнаружено несоответствие в месяце " + (numberOfMonthInLoop + 1));
            return false;
        } else {
            return true;
        }
    }

    private boolean isIncomeInCurrentMonthAndYearReportEquals(YearlyReport currentYearlyReport, int currentMonthIncome, int numberOfMonthInLoop) {
        if (currentYearlyReport.getSumPerMonth() != currentMonthIncome) {
            System.out.println("Обнаружено несоответствие в месяце " + (numberOfMonthInLoop + 1));
            return false;
        } else {
            return true;
        }
    }

    private int getSumOfAllIncomesInListOfUnitTransactions(List<UnitTransaction> listOfUnitTransactions) {
        int sumOfAllIncomesInListOfUnitTransactions = 0;
        int multiplePriceAndQuantityOfUnitTransaction = 0;

        for (UnitTransaction unitTransactions : listOfUnitTransactions) {
            if (!unitTransactions.isExpense()) {
                multiplePriceAndQuantityOfUnitTransaction = unitTransactions.getUnitPrice() * unitTransactions.getQuantity();
                sumOfAllIncomesInListOfUnitTransactions += multiplePriceAndQuantityOfUnitTransaction;
            }
        }
        return sumOfAllIncomesInListOfUnitTransactions;
    }

    private int getSumOfAllExpenseInListOfUnitTransactions(List<UnitTransaction> listOfUnitTransactions) {
        int sumOfAllExpenseInListOfUnitTransactions = 0;
        int multiplePriceAndQuantityOfUnitTransaction = 0;

        for (UnitTransaction unitTransactions : listOfUnitTransactions) {
            if (unitTransactions.isExpense()) {
                multiplePriceAndQuantityOfUnitTransaction = unitTransactions.getUnitPrice() * unitTransactions.getQuantity();
                sumOfAllExpenseInListOfUnitTransactions += multiplePriceAndQuantityOfUnitTransaction;
            }
        }
        return sumOfAllExpenseInListOfUnitTransactions;
    }

    public void getMostProfitableProductInMonth(List<UnitTransaction> listOfUnitTransactions) {
        HashMap<String, Integer> mapOfTransactionsForMonth = new HashMap<>();
        String nameOfMostProfitableProduct = null;
        int sumOfMaxProfitableProduct = 0;

        for (UnitTransaction unitTransaction : listOfUnitTransactions) {
            if (!unitTransaction.isExpense()) {
                int multipleCountAndUnitPrice = unitTransaction.getQuantity() * unitTransaction.getUnitPrice();
                int currentOrDefaultProfit = mapOfTransactionsForMonth.getOrDefault(unitTransaction.getItemName(), 0);
                mapOfTransactionsForMonth.put(unitTransaction.getItemName(), currentOrDefaultProfit + multipleCountAndUnitPrice);
            }
        }

        for (String nameOfProduct : mapOfTransactionsForMonth.keySet()) {
            if (mapOfTransactionsForMonth.get(nameOfProduct) > sumOfMaxProfitableProduct) {
                sumOfMaxProfitableProduct = mapOfTransactionsForMonth.get(nameOfProduct);
                nameOfMostProfitableProduct = nameOfProduct;
            }
        }
        System.out.println("Самый прибыльный товар это " + nameOfMostProfitableProduct + " на сумму " + sumOfMaxProfitableProduct);
    }

    public void getMostExpensesProductInMonth(List<UnitTransaction> listOfUnitTransactions) {
        HashMap<String, Integer> mapOfTransactionsForMonth = new HashMap<>();
        String nameOfMostExpensesProduct = null;
        int sumOfMostExpensesProduct = 0;

        for (UnitTransaction unitTransaction : listOfUnitTransactions) {
            if (unitTransaction.isExpense()) {
                int multipleCountAndUnitPrice = unitTransaction.getQuantity() * unitTransaction.getUnitPrice();
                int currentOrDefaultExpense = mapOfTransactionsForMonth.getOrDefault(unitTransaction.getItemName(), 0);
                mapOfTransactionsForMonth.put(unitTransaction.getItemName(), currentOrDefaultExpense + multipleCountAndUnitPrice);
            }
        }

        for (String nameOfProduct : mapOfTransactionsForMonth.keySet()) {
            if (mapOfTransactionsForMonth.get(nameOfProduct) > sumOfMostExpensesProduct) {
                sumOfMostExpensesProduct = mapOfTransactionsForMonth.get(nameOfProduct);
                nameOfMostExpensesProduct = nameOfProduct;
            }
        }
        System.out.println("Самый большая трата это " + nameOfMostExpensesProduct + " на сумму " + sumOfMostExpensesProduct);
    }

    public void getProfitForEachMonth(List<YearlyReport> listYearlyReports) {
        HashMap<Integer, Integer> yearlyReportHashMap = new HashMap<>();
        for (YearlyReport yearlyReport : listYearlyReports) {
            if (yearlyReport.isExpenseOrIncome()) {
                int currentOrDefaultExpense = yearlyReportHashMap.getOrDefault(yearlyReport.getNumberOfMonth(), 0);
                int residualCurrentAndExpenseInReport = currentOrDefaultExpense - yearlyReport.getSumPerMonth();
                yearlyReportHashMap.put(yearlyReport.getNumberOfMonth(), residualCurrentAndExpenseInReport);
            } else {
                int currentOrDefaultProfit = yearlyReportHashMap.getOrDefault(yearlyReport.getNumberOfMonth(), 0);
                int sumCurrentAndProfitInReport = currentOrDefaultProfit + yearlyReport.getSumPerMonth();
                yearlyReportHashMap.put(yearlyReport.getNumberOfMonth(), sumCurrentAndProfitInReport);
            }
        }
        for (Integer numberOfMonth : yearlyReportHashMap.keySet()) {
            System.out.println("Прибыль по месяцу " + numberOfMonth + " составила " + yearlyReportHashMap.get(numberOfMonth));
        }
    }

    public void printAverageExpenseTransactionInYear(List<List<UnitTransaction>> listOfListsTransactions) {
        int countOfAllExpensesTransactionsInList = 0;
        int sumOfAllExpensesInList = 0;
        for (List<UnitTransaction> listOfUnitTransactions : listOfListsTransactions) {
            sumOfAllExpensesInList += getSumOfAllExpenseInListOfUnitTransactions(listOfUnitTransactions);
            countOfAllExpensesTransactionsInList += getCountOfAllExpensesTransactionsInList(listOfUnitTransactions);
        }
        double averageExpensesTransactionInYear = (double) sumOfAllExpensesInList / countOfAllExpensesTransactionsInList;
        System.out.println("Средняя расходная транзакция в году равна: " + averageExpensesTransactionInYear);
    }

    private int getCountOfAllExpensesTransactionsInList(List<UnitTransaction> listOfUnitTransaction) {
        int countOfAllExpensesTransactionsInList = 0;
        for (UnitTransaction unitTransaction : listOfUnitTransaction) {
            if (unitTransaction.isExpense()) {
                countOfAllExpensesTransactionsInList++;
            }
        }
        return countOfAllExpensesTransactionsInList;
    }

    public void printAverageIncomeTransactionInYear(List<List<UnitTransaction>> listOfArraysTransactions) {
        int countOfAllIncomeTransactionsInList = 0;
        int sumOfAllIncomesInList = 0;
        for (List<UnitTransaction> listOfUnitTransactions : listOfArraysTransactions) {
            sumOfAllIncomesInList += getSumOfAllIncomesInListOfUnitTransactions(listOfUnitTransactions);
            countOfAllIncomeTransactionsInList += getCountOfAllIncomeTransactionsInList(listOfUnitTransactions);
        }
        double averageProfitTransactionInYear = (double) sumOfAllIncomesInList / countOfAllIncomeTransactionsInList;
        System.out.println("Средняя доходная транзакция в году равна: " + averageProfitTransactionInYear);
    }

    private int getCountOfAllIncomeTransactionsInList(List<UnitTransaction> listOfUnitTransaction) {
        int countOfAllIncomeTransactionsInList = 0;
        for (UnitTransaction unitTransaction : listOfUnitTransaction) {
            if (!unitTransaction.isExpense()) {
                countOfAllIncomeTransactionsInList++;
            }
        }
        return countOfAllIncomeTransactionsInList;
    }


}
