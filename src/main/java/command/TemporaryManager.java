package command;

import ability.MonthsRus;
import pair.Pair;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;
import java.util.logging.Logger;

public class TemporaryManager {
    private static Logger log = Logger.getLogger(TemporaryManager.class.getName());


    public Pair<Integer, String> parseBillingPeriod(String billing) {
        String[] arrayBilling = billing.toLowerCase().split(" ");

        /**
         * Проверка на корректные данные
         */

        if (arrayBilling.length != 2) return new Pair<>(0, "wrong");


        if (arrayBilling[1].startsWith("мес"))
            return new Pair(Integer.parseInt(arrayBilling[0]), "month"); //Example: 3 месяца
        if (arrayBilling[1].startsWith("год") || arrayBilling[1].startsWith("лет")) // Example: 1 год
            return new Pair(Integer.parseInt(arrayBilling[0]), "year"); //5 дней
        if (arrayBilling[1].startsWith("д")) return new Pair(Integer.parseInt(arrayBilling[0]), "day");

        log.info("Были неверно введены значения платежного периода");
        return new Pair<>(0, "wrong");
    }


    /**
     * Получение объекта LocaleDate из строки firstPayment, для последующей передачи даты в БД
     */

    public LocalDate firstPaymentToLocalDate(String first) {

        String[] date = first.toLowerCase().split(" ");
        if (checkingTodayInMessage(date[0])) {
            return LocalDate.now();
        }


        try {
            if (date.length == 1 && checkingNumbersInMonth(Integer.parseInt(date[0]), LocalDate.now().getMonth())) {
                return LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), Integer.parseInt(date[0]));
            }

            if (date.length == 2 && checkingNumbersInMonth(Integer.parseInt(date[0]), getMonthFromRusMonths(date[1])))
                return LocalDate.of(LocalDate.now().getYear(), Objects.requireNonNull(getMonthFromRusMonths(date[1])), Integer.parseInt(date[0]));


            else
                return LocalDate.parse("2000-01-01"); /** Используем стандартную дату что бы потом проверить на ошибку и избежать исключение NullPointerException*/

        } catch (NumberFormatException e) {
            return LocalDate.parse("2000-01-01");
        }
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

    private boolean checkingNumbersInMonth(int number, Month month) {
        if (month == null) return false;
        return number > 0 && number <= month.maxLength();
    }


    /**
     * Если пользователь пишет сообщение "Сегодня" выводим актуальную дату
     */
    private boolean checkingTodayInMessage(String today) {
        return today.equals("сегодня");
    }

    public static void main(String[] args) {
        TemporaryManager temporaryManager = new TemporaryManager();
        System.out.println(temporaryManager.getMonthFromRusMonths("ионя"));
        System.out.println(temporaryManager.firstPaymentToLocalDate("вооп имои").toString());
    }

}
