package Main;

import Activity.Activity;
import Activity.BotState;
import Activity.Welcome;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.logging.Logger;

/**
 *
 */

public class Model extends Start {
    private static Logger log = Logger.getLogger(Model.class.getName()); //логирование
    private Update update;
    private ChatCommand chatCommand;
    private User user;

    private BotState botState;

    public Model(Update update) {
            log.info("--- Main.Start model ---");
            this.update = update;
            //chatCommand = new ChatCommand();
            //user = new User(update.getMessage());

            //chatCommand.startDialog(update.getMessage(),user);
        botState = new BotState(update.getMessage());
        botState.setActivity(new Welcome(update.getMessage()));
        botState.state();
        botState.changeActivity();
        botState.state();
        }





    }

