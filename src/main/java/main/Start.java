package main;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.logging.Logger;

public class Start extends TelegramLongPollingBot {
    private static Logger log = Logger.getLogger(Start.class.getName()); //логирование
    private Model model = new Model();

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        new NotificiationInitializer().start(); //Запускаем поток для Нотификаций

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
        model.start(update);
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
