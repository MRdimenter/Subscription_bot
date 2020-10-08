import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.logging.Logger;

/**
 *
 */

public class Model extends Start  {
    private static Logger log = Logger.getLogger(Start.class.getName()); //логирование
    private Update update;
    private ChatCommand chatCommand;
    private User user;


    public Model(Update update) {
            log.info("--- Start model ---");
            this.update = update;
            chatCommand = new ChatCommand();
            user = new User(update.getMessage());

            chatCommand.startDialog(update.getMessage(),user);
        }





    }

