package Activity;

import Main.ChatCommand;
import Main.User;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.logging.Logger;

public class Welcome implements Activity {
    private static Logger log = Logger.getLogger(Welcome.class.getName()); //логирование
    private Message message;
    private ChatCommand chatCommand;
    private User user;

    public Welcome(Message message) {
        this.message = message;
        chatCommand = new ChatCommand();
        user = new User(message);

    }

    @Override
    public void state() {
        chatCommand.startDialog(message, user);
        log.info("--- state welcome");
    }


}
