package main;

import data.User;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashSet;
import java.util.logging.Logger;

public class CompileUsers {
    private static Logger log = Logger.getLogger(CompileUsers.class.getName()); //логирование
    private HashSet<User> users;

    public CompileUsers() {
        users = new HashSet<>();
    }

    public void add(User user, Message message) {
        users.add(user);
    }


    public void compile(Message message) {
        for (User user : users) {
            if (message.getChatId() == user.getId()) user.updateState(message);
    }

    }


}

