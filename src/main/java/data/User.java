package data;

import database.PostgresConnection;
import main.Start;
import main.State;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;

public class User extends Start {
    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private List<Subscribe> subscribes = new ArrayList<>();
    PostgresConnection postgresConnection = new PostgresConnection();
    private State state;
    //private Message message;


    public User(Message message) {
        id = message.getChatId();
        firstName = message.getFrom().getFirstName();
        lastName = message.getFrom().getLastName();
        userName = message.getFrom().getUserName();
        state = State.MENU;
    }

    public User(Long id) {
        this.id = id;
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

    public void setUser() { ;
        postgresConnection.setUserToDatabase(id, firstName, lastName, userName);
    }

    public void setUserStateToId(String state) {
        postgresConnection.setUserStateToId(id, state);
    }

    public String getUserStateToId() {
        return postgresConnection.getUserStateToId(id);
    }


    public void updateState(Message message) {
        System.out.println("User: " + message.getChatId());
        state = state.doSomething(message);
    }


}
