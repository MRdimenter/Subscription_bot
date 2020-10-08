package Main;

import Database.PostgresConnection;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public  class ChatCommand extends Start {
    private static Logger log = Logger.getLogger(ChatCommand.class.getName()); //логирование
    private PostgresConnection postgres;


    public ChatCommand() {

    }



    /**
     * Метод для стартового диалога
     */

    public void startDialog(Message message, User user) {


                sendMessage(message, "Добро пожаловать " + user.getFirstNameAndLastName() + " ! Я создан что-бы отслеживать ваши платные подписки!");
                log.info("Сообщение пользователю отправлено");
                postgres = new PostgresConnection();
               // postgres.setUserToDatabase(user.getId(),user.getFirstName(), user.getLastName(), user.getUserName());

        }



   public void addSub(Message message) {
        sendMessage(message,"Добавьте подписку за которой вы хотите следить:");
        String sub = message.getText();
        log.info("Пользователь " + message.getChatId() + "хочет следить за подпиской: " + sub);
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
            setButtons(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();

        }
    }

    public void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(); //инициалзиация клавиатуры
        sendMessage.setReplyMarkup(replyKeyboardMarkup); //устанавливаем разметку
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true); //адаптивность
        replyKeyboardMarkup.setOneTimeKeyboard(false); //скрытие клавиутары после нажатия

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow keyboardRowFirst = new KeyboardRow();
        keyboardRowFirst.add(new KeyboardButton("Добавить"));
        keyboardRowFirst.add(new KeyboardButton("Просмотреть"));

        keyboardRows.add(keyboardRowFirst);


    }

}
