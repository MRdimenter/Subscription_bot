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
     *               
     */

    public void onUpdateReceived(Update update) {
        ChatCommand chatCommand = new ChatCommand(update);
    }


    @Override
    public String getBotToken() {
        return System.getenv("BOT_TOKEN");
    }


    @Override
    public String getBotUsername() {
        return System.getenv("BOT_NAME");
    }


}
