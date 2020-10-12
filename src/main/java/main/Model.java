package main;

import activity.*;
import data.Subscribe;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.logging.Logger;

/**
 *
 */

public class Model  {
    private static Logger log = Logger.getLogger(Model.class.getName()); //логирование
   // private Update update;
    private int i = 1;
    private ActivityBot activityBot = new ActivityBot();

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
    }
}

