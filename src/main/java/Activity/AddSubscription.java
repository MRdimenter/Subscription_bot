package Activity;

import Main.ChatCommand;
import Main.Start;
import Main.User;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.logging.Logger;

public class AddSubscription implements Activity {
    private static Logger log = Logger.getLogger(AddSubscription.class.getName()); //логирование
    private Message message;
    private ChatCommand chatCommand;
    private User user;

    public AddSubscription(Message message){
        this.message = message;
        chatCommand = new ChatCommand();
        user = new User(message);
    }

    @Override
    public void state() {
        chatCommand.addSub(message);
        log.info("--- state addsubscription");
    }
}
