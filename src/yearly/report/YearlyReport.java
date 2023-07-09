package yearly.report;

public class YearlyReport {
    private int numberOfMonth;
    private int sumPerMonth;
    private boolean isExpenseOrIncome;
    public YearlyReport(int numberOfMonth, int sumPerMonth, boolean isExpenseOrIncome) {
        this.numberOfMonth = numberOfMonth;
        this.sumPerMonth = sumPerMonth;
        this.isExpenseOrIncome = isExpenseOrIncome;
    }

    public int getNumberOfMonth() {
        return numberOfMonth;
    }

    public void setNumberOfMonth(int numberOfMonth) {
        this.numberOfMonth = numberOfMonth;
    }

    public int getSumPerMonth() {
        return sumPerMonth;
    }

    public void setSumPerMonth(int sumPerMonth) {
        this.sumPerMonth = sumPerMonth;
    }

    public boolean isExpenseOrIncome() {
        return isExpenseOrIncome;
    }

    public void setExpenseOrIncome(boolean expenseOrIncome) {
        this.isExpenseOrIncome = expenseOrIncome;
    }

    public YearlyReport() {

    }
}
