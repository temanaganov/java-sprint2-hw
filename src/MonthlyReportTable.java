import java.util.ArrayList;

public class MonthlyReportTable {
    int month;
    ArrayList<MonthlyReportRow> rows;

    public MonthlyReportTable(int month, ArrayList<MonthlyReportRow> rows) {
        this.month = month;
        this.rows = rows;
    }

    int getTotalIncome() {
        int sum = 0;

        for (MonthlyReportRow row : rows) {
            if (!row.isExpense) {
                sum += row.sumOfOne * row.quantity;
            }
        }

        return sum;
    }

    int getTotalExpense() {
        int sum = 0;

        for (MonthlyReportRow row : rows) {
            if (row.isExpense) {
                sum += row.sumOfOne * row.quantity;
            }
        }

        return sum;
    }

    MonthlyReportRow getMostIncomeProduct() {
        int max = 0;
        MonthlyReportRow product = null;

        for (MonthlyReportRow row : rows) {
            int currentIncome;

            if (!row.isExpense) {
                currentIncome = row.sumOfOne * row.quantity;

                if (currentIncome > max) {
                    max = currentIncome;
                    product = row;
                }
            }
        }

        return product;
    }

    MonthlyReportRow getMostExpenseProduct() {
        int max = 0;
        MonthlyReportRow product = null;

        for (MonthlyReportRow row : rows) {
            int currentIncome;

            if (row.isExpense) {
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
