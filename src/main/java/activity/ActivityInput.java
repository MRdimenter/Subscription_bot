package activity;

import command.Command;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.logging.Logger;

public class ActivityInput implements Activity {
    private static Logger log = Logger.getLogger(ActivityInput.class.getName()); //логирование
    private Message message;
    private Command command;

    public ActivityInput(Message message){
        this.message = message;
        command = new Command();
    }

    @Override
    public void state() {
        command.outsub(message);
        log.info("--- state Input ---");
    }
}
