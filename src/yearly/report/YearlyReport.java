package yearly.report;

public class YearlyReport {
    private int numberOfMonth;
    private int sumPerMonth;
    private boolean isExpense;

    public YearlyReport(int numberOfMonth, int sumPerMonth, boolean isExpense) {
        this.numberOfMonth = numberOfMonth;
        this.sumPerMonth = sumPerMonth;
        this.isExpense = isExpense;
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

    public boolean isExpense() {
        return isExpense;
    }

    public void setExpense(boolean expense) {
        this.isExpense = expense;
    }

    public YearlyReport() {

    }
}
