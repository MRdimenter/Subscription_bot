package main;

import data.User;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashSet;
import java.util.logging.Logger;

public class CompileUsers {
    private static Logger log = Logger.getLogger(CompileUsers.class.getName()); //логирование
    HashSet<User> users;
    // PostgresConnection connection = new PostgresConnection("COMPILE");

    public CompileUsers() {
        users = new HashSet<>();
    }

    public void add(User user, Message message) {
        if (users.add(user)) System.out.println("Юзер добавлен");
        ;

    }

    public void compile(Message message) {
        for (User user : users) {
            if (message.getChatId() == user.getId()) user.updateState(message);


        }

    }
}

