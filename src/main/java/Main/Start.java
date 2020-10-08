package Main;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.logging.Logger;

public class Start extends TelegramLongPollingBot {
    private static Logger log = Logger.getLogger(Start.class.getName()); //логирование

    public static void main(String[] args) {


        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {
            telegramBotsApi.registerBot(new Start());
            log.info("----Main.Start bot----");
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
        Model model = new Model(update);
    }


    @Override
    public String getBotToken() {return System.getenv("BOT_TOKEN"); }


    @Override
    public String getBotUsername() { return "lotsubbot"; }


}
