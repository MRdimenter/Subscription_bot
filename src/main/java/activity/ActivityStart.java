package activity;

import command.Command;
import data.User;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.logging.Logger;

public class ActivityStart implements Activity {
    private static Logger log = Logger.getLogger(ActivityStart.class.getName()); //логирование
    private Message message;
    private Command command;
    private User user;

    public ActivityStart(Message message) {
        this.message = message;
        command = new Command();
        user = new User(message);

    }

    @Override
    public void state() {
        command.start(message, user);
        user.setUser();
        log.info("--- state welcome");
    }


}
