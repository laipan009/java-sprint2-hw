package monthly.report;

import transaction.UnitTransaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonthlyReport {
    private static Map<String, List<UnitTransaction>> unitTransactionsByMonth = new HashMap<>();

    public static Map<String, List<UnitTransaction>> getUnitTransactionsByMonth() {
        return unitTransactionsByMonth;
    }

    public static void setUnitTransactionsByMonth(Map<String, List<UnitTransaction>> transactions) {
        MonthlyReport.unitTransactionsByMonth = transactions;
    }
}
