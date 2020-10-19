package main;


import data.User;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.logging.Logger;

/**
 *
 */

public class Model {
    private static Logger log = Logger.getLogger(Model.class.getName()); //логирование
    State state;
    CompileUsers compileUsers;

    public Model() {
        state = State.MENU;
        compileUsers = new CompileUsers();
    }

    public void updateState(Message message) {
        state = state.doSomething(message);
    }

    public void start(Update update) {
        log.info("--- Main.Start model ---");
        System.out.println("СООБЩЕНИЕ МОДЕЛИ --------------->" + update.getMessage().getText());
        if (update.getMessage().getText().equals("/start")) compileUsers.add(new User(update.getMessage()));
        compileUsers.compile(update.getMessage());
        // updateState(update.getMessage());
    }




    }

        /**
         * Если пользователь заходит первый раз, то просто добавляем его в БД
         */
       /* if (update.getMessage().getText().equals("/start")) {
            System.out.println("Дщдс");
            User user = new User(update.getMessage());
            user.setUser();
            users.add(user);
            if(postgresConnection.getUserStateToId(update.getMessage().getChatId()).equals("start")) command.menu(update.getMessage(), "лол");;

        }*/











