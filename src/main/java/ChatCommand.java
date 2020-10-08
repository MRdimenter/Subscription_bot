import Database.PostgresConnection;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.logging.Logger;

public class ChatCommand extends Start {
    private static Logger log = Logger.getLogger(ChatCommand.class.getName()); //логирование
    private Update update;
    private Message message;
    private PostgresConnection postgres;
   // User user = new User(update);

    public ChatCommand(Update update) {
        this.update = update;
        this.message = update.getMessage();

    }

    public ChatCommand() {}

    /**
     * Метод для стартового диалога
     */

    public void startDialog(Message message, User user) {

        switch (message.getText()) {
            case "/start":
               
                sendMessage(message, "Добро пожаловать " + user.getFirstNameAndLastName() + " ! Я создан что-бы отслеживать ваши платные подписки!");
                log.info("Сообщение пользователю отправлено");
                postgres = new PostgresConnection();
               // postgres.setUserToDatabase(user.getId(),user.getFirstName(), user.getLastName(), user.getUserName());
                break;
        }

    }


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

}
