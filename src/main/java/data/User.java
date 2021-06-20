package data;

import database.PostgresConnection;
import main.Start;
import main.State;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User extends Start {
    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private List<Subscribe> subscribes = new ArrayList<>();
    PostgresConnection postgresConnection = new PostgresConnection();
    private State state;


    public User(Message message) {
        id = message.getChatId();
        firstName = message.getFrom().getFirstName();
        lastName = message.getFrom().getLastName();
        userName = message.getFrom().getUserName();
        /**
         * Проверка на статус при отключении
         */
        String status = postgresConnection.getStateUserById(message.getChatId());
        if (status.equals("")) {
            postgresConnection.setStateUserById(message.getChatId(), "MENU");
            state = State.MENU;
        } else state = State.valueOf(postgresConnection.getStateUserById(message.getChatId()));

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
        if (lastName != null) return firstName + " " + lastName;
        else return firstName;
    }

    public void setUser() {
       // postgresConnection.setUserToDatabase(id, firstName, lastName, userName);
    }



    public void updateState(Message message) {
        System.out.println("User: " + message.getChatId());
        state = state.doSomething(message);
    }


    /**
     * Переопределяем Hashcode для сравнения объектов User
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
