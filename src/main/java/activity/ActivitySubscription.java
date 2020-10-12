package activity;


import command.Command;
import data.Subscribe;
import org.telegram.telegrambots.meta.api.objects.Message;
import java.util.logging.Logger;

public class ActivitySubscription  implements Activity {

    private static Logger log = Logger.getLogger(ActivitySubscription.class.getName()); //логирование
    private Message message;
    private Command command;
    Subscribe subscribe;

    public ActivitySubscription(Message message, Subscribe subscribe) {
        this.message = message;
        command = new Command();
        this.subscribe = subscribe;

    }

    @Override
    public void state() {
        log.info("--- State subscription ---");
        if(message.getText().equals("Подписки")) {
            command.subscription(message);


        }
        else if(message.getText().equals("Добавить")) command.instal(message);
        else if(subscribe.getName() == null || subscribe.getName().equals("")) subscribe.setName(command.outsub(message));
        else if(subscribe.getBilling() == null || subscribe.getBilling().equals("")) subscribe.setBilling(command.billing(message));
        else command.menu(message, "- - Подписка добавлена - -");

        System.out.println("BILLING: " + subscribe.getBilling());

    }

    public Subscribe getSubscribe() {
        return subscribe;
    }
}
