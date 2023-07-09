package monthly.report;

import transaction.UnitTransaction;

import java.util.ArrayList;
import java.util.List;

public class MonthlyReport {
    private static List<List<UnitTransaction>> listOfListsOfUnitTransactionsByMonth = new ArrayList<>();

    public static List<List<UnitTransaction>> getListOfListsOfUnitTransactionsByMonth() {
        return listOfListsOfUnitTransactionsByMonth;
    }

    public static void setListOfListsOfUnitTransactionsByMonth(List<List<UnitTransaction>> transactions) {
        MonthlyReport.listOfListsOfUnitTransactionsByMonth = transactions;
    }
}
