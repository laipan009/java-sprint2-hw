package constant;

import yearly.report.YearlyReport;

import java.util.ArrayList;
import java.util.List;

public class YearlyReportConstant {
    private static List<YearlyReport> yearlyReports = new ArrayList<>();
    public static List<YearlyReport> getYearlyReports() {
        return yearlyReports;
    }
    public static void setYearlyReports(List<YearlyReport> yearlyReportArray) {
        YearlyReportConstant.yearlyReports = yearlyReportArray;
    }
}
