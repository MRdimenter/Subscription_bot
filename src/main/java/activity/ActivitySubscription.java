package activity;

import ability.Button;
import command.Command;
import data.Subscribe;
import data.User;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.logging.Logger;

public class ActivitySubscription implements Activity {

    private static Logger log = Logger.getLogger(ActivitySubscription.class.getName()); //логирование
    private Message message;
    private Command command;
    private User user;
    Subscribe subscribe = new Subscribe();

    public ActivitySubscription(Message message) {
        this.message = message;
        command = new Command();
        user = new User(message);
    }

    @Override
    public void state() {
        log.info("--- State subscription ---");
        if(message.getText().equals("Подписки")) {
            command.subscription(message);


        }
        else if(message.getText().equals("Добавить")) command.instal(message);
        else if(subscribe.getName() == null || subscribe.getName().equals("")) subscribe.setName(command.outsub(message));
        else  command.billing(message);

    }
}
