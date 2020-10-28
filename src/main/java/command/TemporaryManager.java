package command;

import ability.MonthsRus;
import pair.Pair;

import java.time.LocalDate;
import java.time.Month;
import java.util.logging.Logger;

public class TemporaryManager {
    private static Logger log = Logger.getLogger(TemporaryManager.class.getName());


    public Pair<Integer, String> parseBillingPeriod(String billing) {
        String[] arrayBilling = billing.toLowerCase().split(" ");
        if (arrayBilling[1].startsWith("мес")) return new Pair(Integer.parseInt(arrayBilling[0]), "month"); //Example: 3 месяца
        if (arrayBilling[1].startsWith("год") || arrayBilling[1].startsWith("лет")) // Example: 1 год
            return new Pair(Integer.parseInt(arrayBilling[0]), "year"); //5 дней
        if (arrayBilling[1].startsWith("д")) return new Pair(Integer.parseInt(arrayBilling[0]), "day");

        log.info("Были неверно введены значения платежного периода");
        return new Pair<>(null, null);
    }


    /**
     * Получение объекта LocaleDate из строки firstPayment, для последующей передачи даты в БД
     */

    public LocalDate firstPaymentToLocalDate(String first) {
        String[] date = first.toLowerCase().split(" ");
        if (checkingTodayInMessage(date[0])) {
            return LocalDate.now();
        }

        Month month = getMonthFromRusMonths(date[1]);


        if (checkingNumbersInMonth(Integer.parseInt(date[0])) && !(month == null))
            return LocalDate.of(LocalDate.now().getYear(), month, Integer.parseInt(date[0]));
        log.info("Были неверно введены значения первого платежа");
        return null;

    }

    /**
     * Получение типа Month из сообщения на русском
     */

    private Month getMonthFromRusMonths(String month) {
        for (int i = 1; i <= 12; i++)
            if (month.startsWith(MonthsRus.of(i).name())) return Month.valueOf(Month.of(i).name());
        return null;
    }

    /**
     * Проверка на правильное количество чисел в месяце
     * Num > 0 and num <= 31
     */

    private boolean checkingNumbersInMonth(int number) {
        return number > 0 && number <= 31;
    }


    /**
     * Если пользователь пишет сообщение "Сегодня" выводим актуальную дату
     */
    private boolean checkingTodayInMessage(String today) {
        return today.equals("сегодня");
    }


}
