package main;

import activity.*;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.logging.Logger;

/**
 *
 */

public class Model extends Start {
    private static Logger log = Logger.getLogger(Model.class.getName()); //логирование
    private Update update;


    private ActivityBot activityBot;

    public Model(Update update) {
            log.info("--- Main.Start model ---");
            this.update = update;
            //chatCommand = new ChatCommand();
            //user = new User(update.getMessage());

            //chatCommand.startDialog(update.getMessage(),user);
        activityBot = new ActivityBot(update.getMessage());
        activityBot.setActivity(new ActivityStart(update.getMessage()));
        activityBot.state();
        activityBot.changeActivity();
        activityBot.state();
        activityBot.changeActivity();
        activityBot.state();
        activityBot.changeActivity();
        activityBot.state();
        activityBot.changeActivity();
        }





    }

