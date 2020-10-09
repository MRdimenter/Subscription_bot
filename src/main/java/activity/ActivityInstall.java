package activity;

import command.Command;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.logging.Logger;

/**
 * Активити для добавления подписки
 */

public class ActivityInstall implements Activity {
    private static Logger log = Logger.getLogger(ActivityInstall.class.getName()); //логирование
    private Message message;
    private Command command;

    public ActivityInstall(Message message){
        this.message = message;
        command = new Command();
    }


    @Override
    public void state() {
        command.instal(message);
        log.info("--- state Install ---");
    }
}
