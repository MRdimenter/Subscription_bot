package data;

import date.MonthsRus;

import java.time.LocalDate;
import java.time.Month;

public class Subscribe {
    private long id;
    private String nameService;
    private String billingPeriod;
    private String firstPayment;
    private int Price;
    private long userId;

    public Subscribe() {

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public String getBillingPeriod() {
        return billingPeriod;
    }

    public void setBillingPeriod(String billingPeriod) {
        this.billingPeriod = billingPeriod;
    }

    public String getFirstPayment() {
        return firstPayment;
    }

    public void setFirstPayment(String firstPayment) {
        String[] totime = firstPayment.split(" ");

        for (int i = 1; i <= 12; i++) {
            if (totime[1].startsWith(MonthsRus.of(i).name().substring(0, 3))) System.out.println(Month.of(i).name());
            //else System.out.println(MonthsRus.of(i).name());
        }

        LocalDate localDate = LocalDate.of(LocalDate.now().getYear(), Month.MARCH, 2);
        this.firstPayment = firstPayment;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public static void main(String[] args) {
        Subscribe subscribe = new Subscribe();

        subscribe.setFirstPayment("29 декабря");
    }
}
