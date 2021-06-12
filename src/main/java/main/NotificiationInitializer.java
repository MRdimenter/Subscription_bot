package main;

import data.Subscribe;
import database.PostgresConnection;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class NotificiationInitializer extends Thread {
    PostgresConnection postgresConnection = new PostgresConnection();
    SimpleDateFormat formatForDateNow = new SimpleDateFormat("k");
    Date date = new Date();
    Timer timer = new Timer();
    TimerTask sometask = new TimerTask() {

        /**
         * Запуск таймера
         */
        @Override
        public void run() {

            ArrayList arrayList = postgresConnection.getUsersId();

            for (int i = 0; i < arrayList.size(); i++) {
                notification((long) arrayList.get(i));
            }


        }
    };

    /**
     * Запуск потока
     */

    @Override
    public void run() {
        timer.schedule(sometask, 0l, 1000 * 60 * 60); //синхронизация запускается один раз в час

    }


    public String notification(Long id) {


        String text = "";
        ArrayList<Subscribe> subs = postgresConnection.getStateSubscribeById(id);


        for (int i = 0; i < subs.size(); i++) {

            if (subs.get(i).getFirstPaymentTime() == Integer.parseInt(formatForDateNow.format(date.getTime())) && calulateBilling(subs.get(i))) {
                text += String.format("Ваша подписка: \"%s\" истечёт через 24 часа\n", subs.get(i).getNameService());
                sendNotification(id, text);
            }


        }

        return text;

    }

    /**
     * Считаем когда конкретно отправлять уведомление в зависимости от расчётного пероида
     */

    public boolean calulateBilling(Subscribe subscribe) {
        try {
            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");

            String startTime = subscribe.getFirstPayment().toString();
            String endTime = LocalDate.now().toString();

            Date start = myFormat.parse(startTime);
            Date end = myFormat.parse(endTime);

            if (subscribe.getBillingDate().equals("month") && getDifferenceDays(start, end) == (subscribe.getBillingNumber() * 30 - 1))
                return true;

            if (subscribe.getBillingDate().equals("year") && getDifferenceDays(start, end) == (subscribe.getBillingNumber() * 365) - 1)
                return true;

            if (subscribe.getBillingDate().equals("day") && getDifferenceDays(start, end) == subscribe.getBillingNumber() - 1)
                return true;

       /* if(subscribe.getBillingDate().equals("month")
                && setLocalDate(subscribe.getFirstPayment().toString()).getMonth().plus(subscribe.getBillingNumber()) == LocalDate.now().getMonth()
                && setLocalDate(subscribe.getFirstPayment().toString()).getDayOfMonth() == LocalDate.now().getDayOfMonth()) return true;

        if(subscribe.getBillingDate().equals("year")
                && setLocalDate(subscribe.getFirstPayment().toString()).plusYears(subscribe.getBillingNumber()).getYear() == LocalDate.now().getYear()
                && setLocalDate(subscribe.getFirstPayment().toString()).getDayOfMonth() == LocalDate.now().getDayOfMonth()
                && setLocalDate(subscribe.getFirstPayment().toString()).getMonth() == LocalDate.now().getMonth() ) return true;*/

            // if(subscribe.getBillingDate().equals("day")
            //       && setLocalDate(subscribe.getFirstPayment().toString()).plusDays(subscribe.getBillingNumber()).getDayOfMonth() == LocalDate.now().getDayOfMonth()) return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;

    }

    public LocalDate setLocalDate(String date) {
        return LocalDate.of(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5, 7)), Integer.parseInt(date.substring(8, 10)));
    }


    /**
     * Отправка уведомления
     */

    public void sendNotification(long id, String message) {

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .version(HttpClient.Version.HTTP_2)
                .build();

        UriBuilder builder = UriBuilder
                .fromUri("https://api.telegram.org")
                .path("/{token}/sendMessage")
                .queryParam("chat_id", id)
                .queryParam("text", message);

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(builder.build("bot" + "1236836643:AAG5WGEaULc1R72Q2roDGcswJx55N-TMyxc"))
                .timeout(Duration.ofSeconds(5))
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * Метод для определния разницы между датами в часах.
     */

    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

}
