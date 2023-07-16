package check.reports;

import constant.YearlyReportConstant;
import monthly.report.MonthlyReport;
import transaction.UnitTransaction;
import utill.Months;
import yearly.report.YearlyReport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckerReports {
    public void checkMonthlyAndYearlyReport() {
        boolean successfully = true;
        for (String monthName : MonthlyReport.getUnitTransactionsByMonth().keySet()) {
            List<UnitTransaction> unitTransactions = MonthlyReport.getUnitTransactionsByMonth().get(monthName);

            int incomeOfCurrentMonth = getSumOfAllIncomes(unitTransactions);
            int expenseOfCurrentMonth = getSumOfAllExpense(unitTransactions);

            int numberMonth;
            numberMonth = Months.changeMonthNameToNumber(monthName);

            YearlyReport currentYearlyReport = YearlyReportConstant.getYearlyReports().get(numberMonth-1);

            if (currentYearlyReport.isExpense()) {
                successfully = isExpensesReportsEquals(currentYearlyReport, expenseOfCurrentMonth, monthName);
            } else {
                successfully = isIncomesReportsEquals(currentYearlyReport, incomeOfCurrentMonth, monthName);
            }
        }
        if (successfully) {
            System.out.println("Сверка прошла успешно!");
        }
    }

    private boolean isExpensesReportsEquals(YearlyReport currentYearlyReport, int currentMonthExpense, String monthName) {
        if (currentYearlyReport.getSumPerMonth() != currentMonthExpense) {
            System.out.println("Обнаружено несоответствие в месяце " + monthName);
            return false;
        } else {
            return true;
        }
    }

    private boolean isIncomesReportsEquals(YearlyReport currentYearlyReport, int currentMonthIncome, String monthName) {
        if (currentYearlyReport.getSumPerMonth() != currentMonthIncome) {
            System.out.println("Обнаружено несоответствие в месяце " + monthName);
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
        System.out.println("Самая большая трата это " + nameOfMostExpensesProduct + " на сумму " + sumOfMostExpensesProduct);
    }

    public void getProfitForEachMonth(List <YearlyReport> yearlyReport) {
        HashMap<Integer, Integer> yearlyReportHashMap = new HashMap<>();
        for (YearlyReport currentYearlyReport : yearlyReport) {
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
            System.out.println("Прибыль по месяцу " + Months.changeNumberToMonthName(numberOfMonth) + " составила " + yearlyReportHashMap.get(numberOfMonth));
        }
    }

    public void printAverageExpenseInYear(Map<String, List<UnitTransaction>> transactions) {
        int countOfAllExpenses = 0;
        int sumOfAllExpenses = 0;
        for (List<UnitTransaction> listOfUnitTransactions : transactions.values()) {
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

    public void printAverageIncomeInYear(Map<String, List<UnitTransaction>> transactions) {
        int countOfAllIncome = 0;
        int sumOfAllIncomes = 0;
        for (List<UnitTransaction> listOfUnitTransactions : transactions.values()) {
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
