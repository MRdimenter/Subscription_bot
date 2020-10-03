import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.io.FileInputStream;

import java.util.Properties;
import java.util.logging.Logger;

public class Start extends TelegramLongPollingBot {
    private static Logger log = Logger.getLogger(Start.class.getName()); //логирование
    private static String BOT_TOKEN;
    private static String BOT_NAME;

    public static void main(String[] args) {
        readConfig();

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
     *
     * @param update Содержит сообщение от пользователя.
     */

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();

        switch (message.getText()) {
            case "/help":
                sendMessage(message, "привет");
                break;
            case "/start":
                sendMessage(message, "Добро пожаловать!");
                break;

        }


          log.fine(update.getMessage().getText().toString());
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
        return System.getenv("BOT_TOKEN");
    }


    @Override
    public String getBotUsername() {
        return System.getenv("BOT_NAME");
    }

    /**
     * Method for reading config.properties
     */

    private static void readConfig() {
        FileInputStream fileInputStream;
        Properties properties = new Properties();

        try {
            fileInputStream = new FileInputStream("src/main/resources/config.properties");
            properties.load(fileInputStream);
            BOT_NAME = properties.getProperty("BOT_NAME");
            BOT_TOKEN = properties.getProperty("BOT_TOKEN");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
