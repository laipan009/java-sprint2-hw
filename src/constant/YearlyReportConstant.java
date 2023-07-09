package constant;

import yearly.report.YearlyReport;

import java.util.ArrayList;
import java.util.List;

public class YearlyReportConstant {
    private static List<YearlyReport> yearlyReportList = new ArrayList<>();
    public static List<YearlyReport> getYearlyReportList() {
        return yearlyReportList;
    }
    public static void setYearlyReportList(List<YearlyReport> yearlyReportArray) {
        YearlyReportConstant.yearlyReportList = yearlyReportArray;
    }
}
