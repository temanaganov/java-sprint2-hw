public class YearlyReportRow {
    int month;
    int amount;
    boolean isExpense;

    public YearlyReportRow(int month, int amount, boolean isExpenses) {
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpenses;
    }
}
