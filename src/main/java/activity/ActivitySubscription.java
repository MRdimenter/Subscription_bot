package activity;

import command.Command;
import data.User;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.logging.Logger;

public class ActivitySubscription implements Activity {

    private static Logger log = Logger.getLogger(ActivitySubscription.class.getName()); //логирование
    private Message message;
    private Command command;
    private User user;

    public ActivitySubscription(Message message) {
        this.message = message;
        command = new Command();
        user = new User(message);
    }

    @Override
    public void state() {
        log.info("--- State subscription ---");
        command.subscription(message);


    }
}
