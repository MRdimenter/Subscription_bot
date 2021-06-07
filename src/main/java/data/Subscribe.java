package data;

import command.TemporaryManager;
import pair.Pair;

import java.time.LocalDate;

public class Subscribe {
    private long id;
    private String nameService;
    private int billingNumber;
    private String billingDate;
    private LocalDate firstPayment;
    private int Price;
    private long userId;
    private TemporaryManager temporaryManager = new TemporaryManager();

    public int getWrong() {
        return wrong;
    }

    public void setWrong(int wrong) {
        this.wrong = wrong;
    }

    private int wrong = 0;

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


    //C помошью billingPeriod мы получаем billingNumber и billingDate
    public void setBillingPeriod(String billingPeriod) {
        Pair<Integer, String> pair = temporaryManager.parseBillingPeriod(billingPeriod);
        this.billingNumber = pair.getFirst();
        this.billingDate = pair.getSecond();
    }

    public LocalDate getFirstPayment() {
        return firstPayment;
    }

    public void setFirstPayment(String firstPayment) {
        this.firstPayment = temporaryManager.firstPaymentToLocalDate(firstPayment);
        if (this.firstPayment == null || this.firstPayment.equals("")) wrong++;
        System.out.println(wrong);
    }

    public void setFirstPaymentForNormalizeDate(String firstPayment) {
        String[] data = firstPayment.split("-");
        this.firstPayment = LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]));
    }

    public void setBillingNumber(int billingNumber) {
        this.billingNumber = billingNumber;
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

    public int getBillingNumber() {
        return billingNumber;
    }

    public String getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(String billingDate) {
        this.billingDate = billingDate;
    }

    @Override
    public String toString() {
        return "Subscribe{" +
                "id=" + id +
                ", nameService='" + nameService + '\'' +
                ", billingNumber=" + billingNumber +
                ", billingDate='" + billingDate + '\'' +
                ", firstPayment=" + firstPayment +
                ", Price=" + Price +
                ", userId=" + userId +
                ", temporaryManager=" + temporaryManager +
                '}';
    }
}

