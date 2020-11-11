package command;

import data.Subscribe;
import data.SubscribeState;

import java.time.YearMonth;
import java.util.ArrayList;

public class StatisticsManager {


    //сколько всего в месяц выходит
    public SubscribeState MonthlyStatisticsCalculator(ArrayList<Subscribe> subscribes) {
        SubscribeState subscribeState = new SubscribeState();
        for (Subscribe subscribe : subscribes) {
            subscribeState.setSubscribeState(subscribe.getNameService(), sumSubscribeOnMonth(subscribe));
        }


        return subscribeState;
    }


    //Вывод статистики за месяц


    public double sumSubscribeOnMonth(Subscribe subscribe) {
        double total = 0;
        String[] firstPayArray = subscribe.getFirstPayment().toString().split("-");
        int daysInMonth = YearMonth.of(Integer.parseInt(firstPayArray[0]), Integer.parseInt(firstPayArray[1])).lengthOfMonth();
        switch (subscribe.getBillingDate()) {
            case "year":
                total = (double) (subscribe.getPrice() / subscribe.getBillingNumber()) / 12;
                break;
            case "day":
                total = (double) (subscribe.getPrice() * daysInMonth) / subscribe.getBillingNumber();
                break;
            case "month":
                total = (double) subscribe.getPrice() / subscribe.getBillingNumber(); //каждые price в месяц
        }

        return total;


    }

}
