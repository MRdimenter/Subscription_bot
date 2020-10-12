package activity;

import command.Command;
import main.Keyboard;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.logging.Logger;

public class ActivityMenu implements Activity {
    private static Logger log = Logger.getLogger(ActivityMenu.class.getName()); //логирование
    private Message message;
    private Command command;

    public ActivityMenu(Message message){
        this.message = message;
        command = new Command();
    }

    @Override
    public void state() {
      //  command.menu(message);
        log.info("--- state Menu ---");
    }
}
