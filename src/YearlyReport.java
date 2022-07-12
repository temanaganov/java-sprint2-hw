import java.util.ArrayList;

public class YearlyReport {
    int year;
    ArrayList<YearlyReportRow> rows = new ArrayList<>();
    FileReader reader = new FileReader();

    YearlyReport(int year) {
        this.year = year;

        String content = reader.readFile("resources/y." + year + ".csv");
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

    void getGeneralInfo() {
        System.out.println("Год: " + year);

        System.out.println("Прибыль:");
        for (int i = 0; i < rows.size(); i += 2) {
            int month = rows.get(i).month;
            System.out.println("\t" + month + ": " + calculateProfitByMonth(month));
        }

        System.out.println("Средний расход за все месяцы в году: " + getAverageExpenses());
        System.out.println("Средний доход за все месяцы в году: " + getAverageIncome());
    }

    int calculateProfitByMonth(int month) {
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

    double getAverageExpenses() {
        double sum = 0;
        int count = 0;

        for (YearlyReportRow row : rows) {
            if (row.isExpense) {
                count++;
                sum += row.amount;
            }
        }

        return sum / count;
    }

    double getAverageIncome() {
        double sum = 0;
        int count = 0;

        for (YearlyReportRow row : rows) {
            if (!row.isExpense) {
                count++;
                sum += row.amount;
            }
        }

        return sum / count;
    }
}
