package command;

import ability.Icon;
import data.Subscribe;
import data.SubscribeState;
import main.Keyboard;
import main.Start;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Command extends Start {
    private static Logger log = Logger.getLogger(Command.class.getName()); //логирование
    private StatisticsManager statisticsManager = new StatisticsManager();

    /**
     * Метод для вывода главного меню
     */
    public void menu(Message message, String text) {
        sendMessage(message, text, Keyboard.menu());
    }

    public void subscription(Message message) {
        sendMessage(message, "Добавьте или редактируете свои подписочные сервисы", Keyboard.subscribe());
    }


    public void subscriptionMessage(Message message, String text) {
        sendMessage(message, text, Keyboard.subscribe());
    }


    public void statistics(Message message) {
        sendMessage(message, "Выбор статистики", Keyboard.statistics());
    }

    public void getKeyboardSubscription(Message message, ArrayList<String> subKeyboard) {

        sendMessage(message, "Ваши подписки", Keyboard.keyboardViewSubscribe(subKeyboard));
    }


    /**
     *
     */


    public void getKeyboardSettings(Message message) {
        sendMessage(message, "Настройка бота! В будущем будет доступна интеграция с сервисом Notion.", Keyboard.settings());
    }


    public String nameService(Message message) {
        return message.getText();


    }

    public String billingPeriod(Message message) {
        log.info("--- Расчётный период ---");
        //sendMessage(message, "Напишите расчётный период: Например, 1 месяц");
        String out = message.getText();
        log.info("Период платежей: " + out);
        return out;
    }


    /**
     * Первый платеж
     * Сколько стоит подписка
     */

    public String firstPayment(Message message) {
        return message.getText();
    }

    public String howMuchIs(Message message) {
        log.info("Сколько стоит подписка:" + message.getText());
        return message.getText();
    }


    /**
     * Метод для получения статистики за месяц
     */

    public void MonthlyStatisticsOutput(Message message, ArrayList<Subscribe> subscribes) {
        StringBuilder text = new StringBuilder("Статистика за месяц " + Icon.STATIC.get() + "\n\n");
        SubscribeState subscribeState = statisticsManager.MonthlyStatisticsCalculator(subscribes);
        Map<String, Double> state = subscribeState.getSubscribeState();
        for (Map.Entry<String, Double> item : state.entrySet()) {
            text.append(item.getKey()).append(" : ").append(textBold(String.format("%.1f", item.getValue()))).append("\n");

        }
        text.append("\n*Всего: ").append(String.format("%.1f", subscribeState.getTotal())).append("*");
        sendMessage(message, text.toString(), Keyboard.statistics());

    }

    /**
     * Метод для получения статистики за год
     */

    public void YearStatisticsOutput(Message message, ArrayList<Subscribe> subscribes) {
        StringBuilder text = new StringBuilder("Статистика за год " + Icon.STATIC.get() + "\n\n");
        SubscribeState subscribeState = statisticsManager.YearStatisticsCalculator(subscribes);
        Map<String, Double> state = subscribeState.getSubscribeState();
        for (Map.Entry<String, Double> item : state.entrySet()) {
            text.append(item.getKey()).append(" : ").append(textBold(String.format("%.1f", item.getValue()))).append("\n");

        }
        text.append("\n*Всего: ").append(String.format("%.1f", subscribeState.getTotal())).append("*");
        sendMessage(message, text.toString(), Keyboard.statistics());
    }

    /**
     * Метод для получения статистики за день
     */

    public void DayStatisticsOutput(Message message, ArrayList<Subscribe> subscribes) {
        StringBuilder text = new StringBuilder("Статистика за день " + Icon.STATIC.get() + "\n\n");
        SubscribeState subscribeState = statisticsManager.DayStatisticsCalculator(subscribes);
        Map<String, Double> state = subscribeState.getSubscribeState();
        for (Map.Entry<String, Double> item : state.entrySet()) {
            text.append(item.getKey()).append(" : ").append(textBold(String.format("%.1f", item.getValue()))).append("\n");

        }
        text.append("\n*Всего: ").append(String.format("%.1f", subscribeState.getTotal())).append("*");
        sendMessage(message, text.toString(), Keyboard.statistics());
    }

    /**
     * Метод для получения общей статистики
     */

    public void AllStatisticsOutput(Message message, ArrayList<Subscribe> subscribes) {
        StatisticsManager statisticsManager = new StatisticsManager();
        ArrayList<SubscribeState> subscribeStates = new ArrayList<>();

        subscribeStates.add(statisticsManager.YearStatisticsCalculator(subscribes)); /** за год  */
        subscribeStates.add(statisticsManager.MonthlyStatisticsCalculator(subscribes)); /** за месяц  */
        subscribeStates.add(statisticsManager.DayStatisticsCalculator(subscribes)); /** за день  */

        int inc = 0;
        StringBuilder text = new StringBuilder();
        for (SubscribeState st : subscribeStates) {
            Map<String, Double> state = st.getSubscribeState();
            if (inc == 0) sendMessage(message, textBold("Статистика за год:"));
            if (inc == 1) sendMessage(message, textBold("Статистика за месяц:"));
            if (inc == 2) sendMessage(message, textBold("Статистика за день:"));
            inc++;
            for (Map.Entry<String, Double> item : state.entrySet()) {
                text.append(item.getKey()).append(" : ").append(String.format("%.1f", item.getValue()).toString()).append("\n");


            }
            sendMessage(message, text + "\n" + "Всего: " + st.getTotal());
            text = new StringBuilder();

        }


    }


    /**
     * Метод для отправки сообщения пользователю
     */

    public void sendMessage(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        // sendMessage.setReplyToMessageId(message.getMessageId()); //Если необходимо сделать реплай
        sendMessage.setText(text);

        try {
            execute(sendMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();

        }
    }

    public void sendMessage(Message message, String text, List<KeyboardRow> keyboardRowList) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        // sendMessage.setReplyToMessageId(message.getMessageId()); //Если необходимо сделать реплай
        sendMessage.setText(text);

        try {
            setButtons(sendMessage, keyboardRowList);
            execute(sendMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();

        }
    }

    public void setButtons(SendMessage sendMessage, List<KeyboardRow> keyboardRowList) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(); //инициалзиация клавиатуры
        sendMessage.setReplyMarkup(replyKeyboardMarkup); //устанавливаем разметку
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true); //адаптивность
        replyKeyboardMarkup.setOneTimeKeyboard(true); //скрытие клавиутары после нажатия
        // Создаем список строк клавиатуры
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

    }


    /**
     * Метод для жирного текста
     */

    private String textBold(String text) {
        return "*" + text + "*";
    }

}
