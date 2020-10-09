package activity;

import org.telegram.telegrambots.meta.api.objects.Message;

public class ActivityBot {
    private Activity activity;
    private Message message;

    public ActivityBot(Message message) {
        this.message = message;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void changeActivity() {
        if(activity instanceof ActivityStart) setActivity(new ActivityMenu(message));
        else if(activity instanceof ActivityMenu) setActivity(new ActivitySubscription(message));
        else if(activity instanceof ActivitySubscription) setActivity(new ActivityInstall(message));
        else if(activity instanceof ActivityInstall) setActivity(new ActivityInput(message));
        else if(activity instanceof ActivityInput) setActivity(new Stable());

    }

    public void state() {
        activity.state();
    }
}
