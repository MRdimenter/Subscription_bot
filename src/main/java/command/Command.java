package command;

import main.Keyboard;
import main.Start;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Command extends Start {
    private static Logger log = Logger.getLogger(Command.class.getName()); //логирование




    /**
     * Метод для вывода главного меню
     */
    public void menu(Message message, String text) {
        sendMessage(message, text, Keyboard.menu());
    }

    public void subscription(Message message) {
        sendMessage(message, "Добавьте или редактируете свои подписочные сервисы", Keyboard.subscribe());
    }

    public void getKeyboardSubscription(Message message, ArrayList<String> subKeyboard) {

        sendMessage(message, "Ваши подписки", Keyboard.keyboardViewSubscribe(subKeyboard));
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

    public int howMuchIs(Message message) {
        log.info("Сколько стоит подписка:" + message.getText());
        return Integer.parseInt(message.getText());
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


}
