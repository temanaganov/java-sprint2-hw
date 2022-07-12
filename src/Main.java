import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MonthlyReport monthlyReport = null;
        YearlyReport yearlyReport = null;

        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1" -> monthlyReport = readMonthlyReport();
                case "2" -> yearlyReport = readYearlyReport();
                case "3" -> compareReports(yearlyReport, monthlyReport);
                case "4" -> getMonthlyReportInfo(monthlyReport);
                case "5" -> getYearlyReportInfo(yearlyReport);
                case "exit" -> {
                    System.out.println("Программа завершена.");
                    return;
                }
                default -> System.out.println("Такой команды нет.");
            }
        }
    }

    public static MonthlyReport readMonthlyReport() {
        int year = 2021;
        return new MonthlyReport(year);
    }

    public static YearlyReport readYearlyReport() {
        int year = 2021;
        return new YearlyReport(year);
    }

    public static void compareReports(YearlyReport yearlyReport, MonthlyReport monthlyReport) {
        if (monthlyReport == null) System.out.println("Месячные отчеты отсутствуют.");
        if (yearlyReport == null) System.out.println("Годовой отчет отсутствует.");
        if (monthlyReport == null || yearlyReport == null) return;

        for (YearlyReportRow row : yearlyReport.rows) {
            boolean isMatches;

            if (row.isExpense) {
                isMatches = row.amount == monthlyReport.getTotalExpenseByMonth(row.month);
            } else {
                isMatches = row.amount == monthlyReport.getTotalIncomeByMonth(row.month);
            }

            if (!isMatches) {
                System.out.println("Месячный отчет за " + row.month + "-й месяц не совпадает с годовым.");

            }
        }

        System.out.println("Сверка отчетов завершена.");
    }

    public static void getMonthlyReportInfo(MonthlyReport report) {
        if (report == null) {
            System.out.println("Месячные отчеты отсутствуют.");
            return;
        }

        report.getGeneralInfo();
    }

    public static void getYearlyReportInfo(YearlyReport report) {
        if (report == null) {
            System.out.println("Годовой отчет отсутствуют.");
            return;
        }

        report.getGeneralInfo();
    }

    public static void printMenu() {
        String[] pointsOfMenu = {
                "Считать все месячные отчёты",
                "Считать годовой отчёт",
                "Сверить отчёты",
                "Вывести информацию о всех месячных отчётах",
                "Вывести информацию о годовом отчёте"
        };

        for (int i = 0; i < pointsOfMenu.length; i++) {
            System.out.println((i + 1) + ". " + pointsOfMenu[i]);
        }

        System.out.println("exit – выход");
    }
}
