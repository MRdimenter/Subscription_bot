package command;

import data.Subscribe;
import data.SubscribeState;

import java.time.YearMonth;
import java.util.ArrayList;

public class StatisticsManager {


    //сколько всего в месяц
    public SubscribeState MonthlyStatisticsCalculator(ArrayList<Subscribe> subscribes) {
        SubscribeState subscribeState = new SubscribeState();
        for (Subscribe subscribe : subscribes) {
            subscribeState.setSubscribeState(subscribe.getNameService(), sumSubscribeOnMonth(subscribe));
        }


        return subscribeState;
    }

    //сколько всего в год
    public SubscribeState YearStatisticsCalculator(ArrayList<Subscribe> subscribes) {
        SubscribeState subscribeState = new SubscribeState();
        for (Subscribe subscribe : subscribes) {
            subscribeState.setSubscribeState(subscribe.getNameService(), sumSubscribeOnYear(subscribe));
        }


        return subscribeState;
    }

    //сколько всего в день
    public SubscribeState DayStatisticsCalculator(ArrayList<Subscribe> subscribes) {
        SubscribeState subscribeState = new SubscribeState();
        for (Subscribe subscribe : subscribes) {
            subscribeState.setSubscribeState(subscribe.getNameService(), sumSubscribeOnDay(subscribe));
        }


        return subscribeState;
    }


    //Вывод статистики за месяц
    private double sumSubscribeOnMonth(Subscribe subscribe) {
        double total = 0;
        String[] firstPayArray = subscribe.getFirstPayment().toString().split("-");

        switch (subscribe.getBillingDate()) {
            case "year":
                total = (double) (subscribe.getPrice() / subscribe.getBillingNumber()) / 12;
                break;
            case "day":
                total = (double) (subscribe.getPrice() * daysInMonth()) / subscribe.getBillingNumber();
                break;
            case "month":
                total = (double) subscribe.getPrice() / subscribe.getBillingNumber(); //каждые price в месяц
        }

        return total;


    }

    private double sumSubscribeOnYear(Subscribe subscribe) {
        double total = 0;
        String[] firstPayArray = subscribe.getFirstPayment().toString().split("-");

        switch (subscribe.getBillingDate()) {
            case "year":
                total = (double) subscribe.getPrice() / subscribe.getBillingNumber();
                break;
            case "day":
                total = (double) daysInYear() * subscribe.getPrice() / subscribe.getBillingNumber();
                ;                 //(subscribe.getPrice() *  daysInMonth()) / subscribe.getBillingNumber();
                break;
            case "month":
                total = (double) (subscribe.getPrice() * 12) / subscribe.getBillingNumber(); //каждые price в месяц

        }

        return total;
    }

    private double sumSubscribeOnDay(Subscribe subscribe) {
        double total = 0;
        String[] firstPayArray = subscribe.getFirstPayment().toString().split("-");

        switch (subscribe.getBillingDate()) {
            case "year":
                total = (double) subscribe.getPrice() / daysInYear() / subscribe.getBillingNumber();
                break;
            case "day":
                total = (double) subscribe.getPrice() / subscribe.getBillingNumber();
                break;
            case "month":
                total = (double) subscribe.getPrice() / daysInMonth() / subscribe.getBillingNumber();//каждые price в месяц

        }

        return total;
    }


    /**
     * Метод возвращает кол-во дней в месяце
     */
    private int daysInMonth() {
        return YearMonth.now().lengthOfMonth();
    }

    private int daysInYear() {
        return YearMonth.now().lengthOfYear();
    }



}
