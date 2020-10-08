package Activity;

import org.telegram.telegrambots.meta.api.objects.Message;

public class BotState {
    Activity activity;
    private Message message;

    public BotState(Message message) {
        this.message = message;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void changeActivity() {
        if(activity instanceof Welcome) setActivity(new AddSubscription(message));
        else if(activity instanceof AddSubscription) setActivity(new SendSubscription(message));
        else if(activity instanceof SendSubscription) setActivity(new Stable());

    }

    public void state() {
        activity.state();
    }
}
