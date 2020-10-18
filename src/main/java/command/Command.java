package command;

import ability.Button;
import data.Subscribe;
import database.PostgresConnection;
import com.vdurmont.emoji.EmojiParser;
import main.Keyboard;
import main.Start;
import data.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Command extends Start {
    private static Logger log = Logger.getLogger(Command.class.getName()); //логирование


    public Command() {

    }


    /**
     * Метод для стартового диалога
     */

    public void start(Message message, User user) {

        if (message.getText().equals("/start")) {
            sendMessage(message, "Добро пожаловать " + user.getFirstNameAndLastName() + EmojiParser.parseToUnicode(":relaxed:") + "\nЯ создан что-бы отслеживать ваши платные подписки " + EmojiParser.parseToUnicode(":euro:") + EmojiParser.parseToUnicode(":euro:") + EmojiParser.parseToUnicode(":euro:"), Keyboard.menu());
            log.info("Сообщение пользователю отправлено");
        }

    }


    /**
     * Метод для вывода главного меню
     */
    public void menu(Message message, String text) {
        sendMessage(message, text ,Keyboard.menu());
        System.out.println("Menu");
    }


    public void subscription(Message message) {
        if (message.getText().equals(Button.SUBSCRIPTION.get())) {
            log.info("--- Подписки ---");
            sendMessage(message, "Добавьте или редактируете свои подписочные сервисы", Keyboard.subscribe());
        }
    }


    public void instal(Message message) {

            log.info("--- Добавление подписки ---");
            sendMessage(message, "Напишите какой сервис следует добавить:");

    }

    public String outsub (Message message) {
            String out = message.getText();
            log.info("Подписка: " + out);
            sendMessage(message, "Напишите расчётный период: Например, 1 месяц");
            return message.getText();


    }

    public String billing(Message message) {
            log.info("--- Расчётный период ---");
            //sendMessage(message, "Напишите расчётный период: Например, 1 месяц");
            String out = message.getText();
            log.info("Период платежей: " + out);
            return out;
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

    public void sendMessage(Message message, List<KeyboardRow> keyboardRowList) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        // sendMessage.setReplyToMessageId(message.getMessageId()); //Если необходимо сделать реплай
        setButtons(sendMessage, keyboardRowList);
       // sendMessage.setText("Отлично :)");

        try {
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
        replyKeyboardMarkup.setKeyboard(keyboardRowList);


    }



}
