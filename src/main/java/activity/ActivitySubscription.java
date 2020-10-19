package activity;


import ability.Button;
import command.Command;
import data.Subscribe;
import data.User;
import org.telegram.telegrambots.meta.api.objects.Message;
import java.util.logging.Logger;

public class ActivitySubscription  implements Activity {

    private static Logger log = Logger.getLogger(ActivitySubscription.class.getName()); //логирование
    private Message message;
    private Command command;
    Subscribe subscribe;
    private User user;

    public ActivitySubscription(Message message, Subscribe subscribe) {
        this.message = message;
        command = new Command();
        this.subscribe = subscribe;
        this.user = new User(message);

    }

    @Override
    public void state() {

        log.info("--- State subscription ---");
        if(message.getText().equals("Подписки")) {
            command.subscription(message);



        }
        if(message.getText().equals("Добавить")) {
            command.instal(message);
            //subscribe.flagactivity = true;
            user.setUserStateToId("addingsub");
            return;

        }

        if(user.getUserStateToId().equals("addingsub")) {
            if((subscribe.getName() == null || subscribe.getName().equals("")) ) subscribe.setName(command.outsub(message));
            else if((subscribe.getBilling() == null || subscribe.getBilling().equals(""))) {
                subscribe.setBilling(command.billing(message));
                command.menu(message, "Подписка добавлена");
            }

            System.out.println("NAME: " + subscribe.getName());
            System.out.println("BILLING: " + subscribe.getBilling());
        }




    }


    public Subscribe getSubscribe() {
        return subscribe;
    }
}
