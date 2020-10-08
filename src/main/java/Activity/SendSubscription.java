package Activity;

import Main.ChatCommand;
import Main.User;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.logging.Logger;

public class SendSubscription implements Activity {

    private static Logger log = Logger.getLogger(SendSubscription.class.getName()); //логирование
    private Message message;
    private ChatCommand chatCommand;
    private User user;

    public SendSubscription(Message message) {
        this.message = message;
        chatCommand = new ChatCommand();
        user = new User(message);
    }

    @Override
    public void state() {
        log.info("--- State SendSub ---");
        chatCommand.sendSub(message);


    }
}
