import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import java.util.Properties;

public class Start extends TelegramLongPollingBot {
    //private static Logger log = Logger.getLogger(Start.class.getName()); //логирование

    private static String BOT_NAME;

    public static void main(String[] args) {


        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {
            telegramBotsApi.registerBot(new Start());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

    }


    /**
     * Метод для приема сообщений.
     * @param update Содержит сообщение от пользователя.
     */

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();



            switch (message.getText()) {
                case "/help" : sendMessage(message, "привет");


        }

        System.out.println(message.getText());
        System.out.println(update.getMessage());

      //  log.fine(update.getMessage().getText().toString());
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


    @Override
    public String getBotToken() {
        return "1236836643:AAG5WGEaULc1R72Q2roDGcswJx55N-TMyxc";
    }


    @Override
    public String getBotUsername() {
         return BOT_NAME;
    }
}
