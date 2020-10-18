package main;

import activity.*;
import command.Command;
import data.Subscribe;
import data.User;
import database.PostgresConnection;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 */

public class Model  {
    private static Logger log = Logger.getLogger(Model.class.getName()); //логирование
   // private Update update;
    private ActivityBot activityBot = new ActivityBot();
    private List<User> users = new ArrayList<>();

  //  Command command = new Command();
    public Model() {


        }


    public void start(Update update) {
        log.info("--- Main.Start model ---");
        System.out.println("СООБЩЕНИЕ МОДЕЛИ --------------->" + update.getMessage().getText());
        //activityBot = new ActivityBot(update.getMessage());
        activityBot.setMessage(update.getMessage());
        activityBot.setActivity(new ActivityStart(update.getMessage()));
        activityBot.state();
        activityBot.changeActivity();
        activityBot.state();
        activityBot.changeActivity();
        activityBot.state();
        activityBot.changeActivity();
        activityBot.state();
    }

        /**
         * Если пользователь заходит первый раз, то просто добавляем его в БД
         *//*
         if(update.getMessage().getText().equals("/start")) {
             System.out.println("Дщдс");
         User user = new User(update.getMessage());
         user.setUser();
         users.add(user);
         //if(postgresConnection.getUserStateToId(update.getMessage().getChatId()).equals("start")) command.menu(update.getMessage(), "Добро пожаловать!");
*/

    }








