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
        System.out.println("__________________ НОВЫЙ АПДЕЙТ ______________________");
        System.out.println("СООБЩЕНИЕ ----------> " + update.getMessage().getText());

       model.start(update);
    }


    @Override
    public String getBotToken() {return "1236836643:AAG5WGEaULc1R72Q2roDGcswJx55N-TMyxc"; }


    @Override
    public String getBotUsername() { return "lotsubbot"; }


}
