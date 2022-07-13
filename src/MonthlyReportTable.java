import java.util.ArrayList;

public class MonthlyReportTable {
    int month;
    ArrayList<MonthlyReportRow> rows;

    public MonthlyReportTable(int month, ArrayList<MonthlyReportRow> rows) {
        this.month = month;
        this.rows = rows;
    }

    int getTotalIncomeOrExpense(boolean isExpense) {
        int sum = 0;

        for (MonthlyReportRow row : rows) {
            if (row.isExpense == isExpense) {
                sum += row.sumOfOne * row.quantity;
            }
        }

        return sum;
    }

    MonthlyReportRow getMostIncomeOrExpenseProduct(boolean isExpense) {
        int max = 0;
        MonthlyReportRow product = null;

        for (MonthlyReportRow row : rows) {
            int currentIncome;

            if (row.isExpense == isExpense) {
                currentIncome = row.sumOfOne * row.quantity;

                if (currentIncome > max) {
                    max = currentIncome;
                    product = row;
                }
            }
        }

        return product;
    }
}
