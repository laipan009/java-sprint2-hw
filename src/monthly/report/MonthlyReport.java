package monthly.report;

import transaction.UnitTransaction;

import java.util.ArrayList;
import java.util.List;

public class MonthlyReport {
    private static List<List<UnitTransaction>> unitTransactionsByMonth = new ArrayList<>();

    public static List<List<UnitTransaction>> getUnitTransactionsByMonth() {
        return unitTransactionsByMonth;
    }

    public static void setUnitTransactionsByMonth(List<List<UnitTransaction>> transactions) {
        MonthlyReport.unitTransactionsByMonth = transactions;
    }
}
