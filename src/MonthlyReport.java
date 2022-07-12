import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    FileReader reader = new FileReader();
    HashMap<Integer, MonthlyReportTable> months;

    public MonthlyReport(int year) {
        months = new HashMap<>();

        for (int i = 1; i <= 12; i++) {
            String month = String.format("%02d", i);
            String path = "resources/m." + year + month + ".csv";
            String content = reader.readFile(path);

            if (content != null) {
                ArrayList<MonthlyReportRow> rows = new ArrayList<>();
                String[] lines = content.split(System.lineSeparator());

                for (int j = 1; j < lines.length; j++) {
                    String line = lines[j];
                    String[] data = line.split(",");

                    String name = data[0];
                    boolean isExpense = Boolean.parseBoolean(data[1]);
                    int quantity = Integer.parseInt(data[2]);
                    int sumOfOne = Integer.parseInt(data[3]);

                    rows.add(new MonthlyReportRow(name, isExpense, quantity, sumOfOne));
                }

                months.put(i, new MonthlyReportTable(i, rows));
                System.out.println("Отчет за " + i + " месяц успешно считан.");
            }
        }
    }

    int getTotalIncomeByMonth(int month) {
        return months.get(month).getTotalIncome();
    }

    int getTotalExpenseByMonth(int month) {
        return months.get(month).getTotalExpense();
    }

    void getGeneralInfo() {
        String formatString = "%-15s%-50s%-50s%n";
        System.out.printf(formatString, "Номер месяца", "Самый прибыльный товар", "Самая большая трата");

        for (MonthlyReportTable month : months.values()) {
            MonthlyReportRow mostIncomeProduct = month.getMostIncomeProduct();
            MonthlyReportRow mostExpensesProduct = month.getMostExpenseProduct();
            System.out.printf(
                    formatString,
                    month.month,
                    mostIncomeProduct.name + ": " + mostIncomeProduct.sumOfOne * mostIncomeProduct.quantity,
                    mostExpensesProduct.name + ": " + mostExpensesProduct.sumOfOne * mostExpensesProduct.quantity
            );
        }
    }
}
