package activity;

import ability.Button;
import command.Command;
import data.Subscribe;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.logging.Logger;

public class Stable implements Activity {
    private static Logger log = Logger.getLogger(Stable.class.getName()); //логирование
    private Message message;
    private Command command;
    Subscribe subscribe;

    public Stable(Message message, Subscribe subscribe) {
        this.message = message;
        command = new Command();
        this.subscribe = subscribe;

    }

    @Override
    public void state() {
        log.info("--- State Stable ---");


    }
}
