package utill;

import transaction.UnitTransaction;
import yearly.report.YearlyReport;

import java.util.ArrayList;
import java.util.List;

public class ConverterFromListToObject {
    private String itemName;
    private boolean isExpense;
    private int quantity;
    private int unitPrice;

    public List<UnitTransaction> convertFromListToObjectTransactionsForMonth(List<String> lines) {

        List<UnitTransaction> transactionsForMonth = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {
            String[] lineContents = lines.get(i).split(",");
            itemName = lineContents[0];
            isExpense = Boolean.parseBoolean(lineContents[1]);
            quantity = Integer.parseInt(lineContents[2]);
            unitPrice = Integer.parseInt(lineContents[3]);
            transactionsForMonth.add(new UnitTransaction(itemName, isExpense, quantity, unitPrice));
        }
        return transactionsForMonth;
    }

    public List<YearlyReport> convertFromListToObjectOfYearReport(List<String> lines) {
        int numberOfMonth;
        int sumPerMonth;
        boolean isExpense;
        List<YearlyReport> processed = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {
            String[] lineContents = lines.get(i).split(",");
            numberOfMonth = Integer.parseInt(lineContents[0]);
            sumPerMonth = Integer.parseInt(lineContents[1]);
            isExpense = Boolean.parseBoolean(lineContents[2]);
            processed.add(new YearlyReport(numberOfMonth, sumPerMonth, isExpense));
        }
        return processed;
    }
}
