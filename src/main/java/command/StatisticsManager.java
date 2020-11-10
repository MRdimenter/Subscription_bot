package command;

import java.time.YearMonth;

public class StatisticsManager {


    //сколько всего в месяц выходит
    public double calculate() {
        return 0;
    }


    //Вывод статистики за месяц

    /**
     * @param price         - Cколько стоит подписка
     * @param billingDate   - платить каждый день/месяц/год
     * @param billingNumber - сколько раз в месяц/год/день платить
     */
    public double showStateOnMonth(int billingNumber, String billingDate, int price, String firstPayment) {
        double total = 0;
        String[] firstPayArray = firstPayment.split("-");
        int daysInMonth = YearMonth.of(Integer.parseInt(firstPayArray[0]), Integer.parseInt(firstPayArray[1])).lengthOfMonth();
        switch (billingDate) {
            case "year":
                total = (double) (price / billingNumber) / 12;
                break;
            case "day":
                total = (double) (price * daysInMonth) / billingNumber;
                break;
            case "month":
                total = (double) price / billingNumber; //каждые price в месяц
        }

        return total;


    }

    public static void main(String[] args) {
        //      System.out.println(Month.of(2).length(2020));
        //System.out.println(YearMonth.of(2020, 11).lengthOfMonth());
        System.out.println(String.format("%.2f", new StatisticsManager().showStateOnMonth(2, "month", 150, "2020-11-29"))); //каждые 2 месяца по 150
        System.out.println(String.format("%.2f", new StatisticsManager().showStateOnMonth(2, "year", 1000, "2020-11-29"))); //каждые 2 года по 1000
        System.out.println(String.format("%.2f", new StatisticsManager().showStateOnMonth(1, "day", 1, "2020-11-29"))); //каждые 30 дней по 1 рублю


    }
}
