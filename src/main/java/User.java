import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class User {
    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private Message message;


    public User (Update update) {
        message = update.getMessage();
        id = message.getChatId();
        firstName  = message.getFrom().getFirstName();
        lastName = message.getFrom().getLastName();
        userName = message.getFrom().getUserName();
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {

        return firstName != null ? firstName : "null";
    }

    public String getLastName() {
        return lastName != null ? lastName : "null";
    }

    public String getUserName() {
        return userName != null ? userName : "null";
    }

    public String getFirstNameAndLastName() {
        if(lastName != null) return firstName + " " + lastName;
        else return firstName;
    }
}
