package date;

import java.time.LocalDate;
import java.time.Month;

public class TemporaryManager {


    public LocalDate firstPaymentToLocalDate(String first) {
        String[] date = first.toLowerCase().split(" ");


        if (checkingTodayInMessage(date[0])) return LocalDate.now();
        if (checkingNumbersInMonth(Integer.parseInt(date[0])))
            return LocalDate.of(LocalDate.now().getYear(), getMonthFromRusMonths(date[1]), Integer.parseInt(date[0]));

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
