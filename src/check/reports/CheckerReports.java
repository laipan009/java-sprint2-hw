package check.reports;

import constant.YearlyReportConstant;
import monthly.report.MonthlyReport;
import transaction.UnitTransaction;
import yearly.report.YearlyReport;

import java.util.HashMap;
import java.util.List;

public class CheckerReports {
    public void checkMonthlyAndYearlyReport() {
        boolean successfully = true;
        for (int i = 0; i < MonthlyReport.getUnitTransactionsByMonth().size(); i++) {
            List<UnitTransaction> unitTransactions = MonthlyReport.getUnitTransactionsByMonth().get(i);

            int incomeOfCurrentMonth = getSumOfAllIncomes(unitTransactions);
            int expenseOfCurrentMonth = getSumOfAllExpense(unitTransactions);

            YearlyReport currentYearlyReport = YearlyReportConstant.getYearlyReports().get(i);

            if (currentYearlyReport.isExpense()) {
                successfully = isExpensesReportsEquals(currentYearlyReport, expenseOfCurrentMonth, i);
            } else {
                successfully = isIncomesReportsEquals(currentYearlyReport, incomeOfCurrentMonth, i);
            }
        }
        if (successfully) {
            System.out.println("Сверка прошла успешно!");
        }
    }

    private boolean isExpensesReportsEquals(YearlyReport currentYearlyReport, int currentMonthExpense, int numberOfMonthInLoop) {
        if (currentYearlyReport.getSumPerMonth() != currentMonthExpense) {
            System.out.println("Обнаружено несоответствие в месяце " + (numberOfMonthInLoop + 1));
            return false;
        } else {
            return true;
        }
    }

    private boolean isIncomesReportsEquals(YearlyReport currentYearlyReport, int currentMonthIncome, int numberOfMonthInLoop) {
        if (currentYearlyReport.getSumPerMonth() != currentMonthIncome) {
            System.out.println("Обнаружено несоответствие в месяце " + (numberOfMonthInLoop + 1));
            return false;
        } else {
            return true;
        }
    }

    private int getSumOfAllIncomes(List<UnitTransaction> listOfUnitTransactions) {
        int sumOfAllIncomes = 0;
        int multiplePriceAndQuantity;

        for (UnitTransaction unitTransaction : listOfUnitTransactions) {
            if (!unitTransaction.isExpense()) {
                multiplePriceAndQuantity = unitTransaction.getUnitPrice() * unitTransaction.getQuantity();
                sumOfAllIncomes += multiplePriceAndQuantity;
            }
        }
        return sumOfAllIncomes;
    }

    private int getSumOfAllExpense(List<UnitTransaction> listOfUnitTransactions) {
        int sumOfAllExpense = 0;
        int multiplePriceAndQuantity;

        for (UnitTransaction unitTransaction : listOfUnitTransactions) {
            if (unitTransaction.isExpense()) {
                multiplePriceAndQuantity = unitTransaction.getUnitPrice() * unitTransaction.getQuantity();
                sumOfAllExpense += multiplePriceAndQuantity;
            }
        }
        return sumOfAllExpense;
    }

    public void getMostProfitableProductInMonth(List<UnitTransaction> listOfUnitTransactions) {
        HashMap<String, Integer> transactionsForMonth = new HashMap<>();
        String nameOfMostProfitableProduct = null;
        int sumOfMaxProfitableProduct = 0;

        for (UnitTransaction unitTransaction : listOfUnitTransactions) {
            if (!unitTransaction.isExpense()) {
                int multipleCountAndUnitPrice = unitTransaction.getQuantity() * unitTransaction.getUnitPrice();
                int currentOrDefaultProfit = transactionsForMonth.getOrDefault(unitTransaction.getItemName(), 0);
                transactionsForMonth.put(unitTransaction.getItemName(), currentOrDefaultProfit + multipleCountAndUnitPrice);
            }
        }

        for (String nameOfProduct : transactionsForMonth.keySet()) {
            if (transactionsForMonth.get(nameOfProduct) > sumOfMaxProfitableProduct) {
                sumOfMaxProfitableProduct = transactionsForMonth.get(nameOfProduct);
                nameOfMostProfitableProduct = nameOfProduct;
            }
        }
        System.out.println("Самый прибыльный товар это " + nameOfMostProfitableProduct + " на сумму " + sumOfMaxProfitableProduct);
    }

    public void getMostExpensesProductInMonth(List<UnitTransaction> listOfUnitTransactions) {
        HashMap<String, Integer> transactionsForMonth = new HashMap<>();
        String nameOfMostExpensesProduct = null;
        int sumOfMostExpensesProduct = 0;

        for (UnitTransaction unitTransaction : listOfUnitTransactions) {
            if (unitTransaction.isExpense()) {
                int multipleCountAndUnitPrice = unitTransaction.getQuantity() * unitTransaction.getUnitPrice();
                int currentOrDefaultExpense = transactionsForMonth.getOrDefault(unitTransaction.getItemName(), 0);
                transactionsForMonth.put(unitTransaction.getItemName(), currentOrDefaultExpense + multipleCountAndUnitPrice);
            }
        }

        for (String nameOfProduct : transactionsForMonth.keySet()) {
            if (transactionsForMonth.get(nameOfProduct) > sumOfMostExpensesProduct) {
                sumOfMostExpensesProduct = transactionsForMonth.get(nameOfProduct);
                nameOfMostExpensesProduct = nameOfProduct;
            }
        }
        System.out.println("Самый большая трата это " + nameOfMostExpensesProduct + " на сумму " + sumOfMostExpensesProduct);
    }

    public void getProfitForEachMonth(List<YearlyReport> listYearlyReports) {
        HashMap<Integer, Integer> yearlyReportHashMap = new HashMap<>();
        for (YearlyReport currentYearlyReport : listYearlyReports) {
            if (currentYearlyReport.isExpense()) {
                int currentValue = yearlyReportHashMap.getOrDefault(currentYearlyReport.getNumberOfMonth(), 0);
                int residualCurrentAndExpenseInReport = currentValue - currentYearlyReport.getSumPerMonth();
                yearlyReportHashMap.put(currentYearlyReport.getNumberOfMonth(), residualCurrentAndExpenseInReport);
            } else {
                int currentValue = yearlyReportHashMap.getOrDefault(currentYearlyReport.getNumberOfMonth(), 0);
                int sumCurrentAndProfitInReport = currentValue + currentYearlyReport.getSumPerMonth();
                yearlyReportHashMap.put(currentYearlyReport.getNumberOfMonth(), sumCurrentAndProfitInReport);
            }
        }
        for (Integer numberOfMonth : yearlyReportHashMap.keySet()) {
            System.out.println("Прибыль по месяцу " + numberOfMonth + " составила " + yearlyReportHashMap.get(numberOfMonth));
        }
    }

    public void printAverageExpenseInYear(List<List<UnitTransaction>> listOfListsTransactions) {
        int countOfAllExpenses = 0;
        int sumOfAllExpenses = 0;
        for (List<UnitTransaction> listOfUnitTransactions : listOfListsTransactions) {
            sumOfAllExpenses += getSumOfAllExpense(listOfUnitTransactions);
            countOfAllExpenses += getCountOfAllExpenses(listOfUnitTransactions);
        }
        double averageExpensesTransactionInYear = (double) sumOfAllExpenses / countOfAllExpenses;
        System.out.println("Средняя расходная транзакция в году равна: " + averageExpensesTransactionInYear);
    }

    private int getCountOfAllExpenses(List<UnitTransaction> listOfUnitTransaction) {
        int countOfAllExpenses = 0;
        for (UnitTransaction unitTransaction : listOfUnitTransaction) {
            if (unitTransaction.isExpense()) {
                countOfAllExpenses++;
            }
        }
        return countOfAllExpenses;
    }

    public void printAverageIncomeInYear(List<List<UnitTransaction>> listOfArraysTransactions) {
        int countOfAllIncome = 0;
        int sumOfAllIncomes = 0;
        for (List<UnitTransaction> listOfUnitTransactions : listOfArraysTransactions) {
            sumOfAllIncomes += getSumOfAllIncomes(listOfUnitTransactions);
            countOfAllIncome += getCountOfAllIncome(listOfUnitTransactions);
        }
        double averageProfitTransactionInYear = (double) sumOfAllIncomes / countOfAllIncome;
        System.out.println("Средняя доходная транзакция в году равна: " + averageProfitTransactionInYear);
    }

    private int getCountOfAllIncome(List<UnitTransaction> listOfUnitTransaction) {
        int countOfAllIncome = 0;
        for (UnitTransaction unitTransaction : listOfUnitTransaction) {
            if (!unitTransaction.isExpense()) {
                countOfAllIncome++;
            }
        }
        return countOfAllIncome;
    }
}
