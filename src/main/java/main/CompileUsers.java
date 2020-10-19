package main;

import data.User;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

public class CompileUsers {
    List<User> users;

    public CompileUsers() {
        users = new ArrayList<>();
    }

    public void add(User user) { users.add(user);}

    public void compile(Message message) {
        for(User user : users){ if(message.getChatId() == user.getId()) user.updateState(message);

    }
}}
