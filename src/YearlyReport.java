import java.io.File;
import java.util.ArrayList;

public class YearlyReport {
    int year;
    ArrayList<YearlyReportRow> rows = new ArrayList<>();

    YearlyReport(int year, String path) {
        this.year = year;

        String filePath = path + File.separator + "y." + year + ".csv";
        String content = FileReader.readFile(filePath);

        if (content != null) {
            String[] lines = content.split(System.lineSeparator());

            for (int i = 1; i < lines.length; i++) {
                String line = lines[i];
                String[] data = line.split(",");

                int month = Integer.parseInt(data[0]);
                int amount = Integer.parseInt(data[1]);
                boolean isExpense = Boolean.parseBoolean(data[2]);

                rows.add(new YearlyReportRow(month, amount, isExpense));
            }

            System.out.println("Отчет за " + year + " год успешно считан.");
        }
    }

    void getGeneralInfo() {
        if (rows.size() == 0) {
            System.out.println("Годовой отчет отсутствует.");
            return;
        }
        System.out.println("Год: " + year);

        System.out.println("Прибыль:");
        for (int i = 0; i < rows.size(); i += 2) {
            int month = rows.get(i).month;
            System.out.println("\t" + month + ": " + calculateProfitByMonth(month));
        }

        System.out.println("Средний расход за все месяцы в году: " + getAverageIncomeOrExpense(true));
        System.out.println("Средний доход за все месяцы в году: " + getAverageIncomeOrExpense(false));
    }

    private int calculateProfitByMonth(int month) {
        int expenses = 0;
        int income = 0;

        for (YearlyReportRow row : rows) {
            if (row.month == month) {
                if (row.isExpense) {
                    expenses += row.amount;
                } else {
                    income += row.amount;
                }
            }
        }

        return income - expenses;
    }

    private double getAverageIncomeOrExpense(boolean isExpense) {
        double sum = 0;
        int count = 0;

        for (YearlyReportRow row : rows) {
            if (row.isExpense == isExpense) {
                count++;
                sum += row.amount;
            }
        }

        return sum / count;
    }
}
