package main;


import data.User;
import database.PostgresConnection;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.logging.Logger;


public class Model {
    private static Logger log = Logger.getLogger(Model.class.getName()); //логирование
    State state;
    CompileUsers compileUsers;
    PostgresConnection postgresConnection = new PostgresConnection();
    public Model() {
        state = State.MENU;
        compileUsers = new CompileUsers();
    }

    public void start(Update update) {
        log.info("--- Main.Start model ---");
        log.info("СООБЩЕНИЕ МОДЕЛИ --------------->\" + update.getMessage().getText()");

        /**
         * NOTIFICATION
         * LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), Integer.parseInt(date[0]))
         */


        if (update.getMessage().getText().equals("/start") || !postgresConnection.getStateUserById(update.getMessage().getChatId()).equals(""))
            compileUsers.add(new User(update.getMessage()), update.getMessage());
        compileUsers.compile(update.getMessage());
        // updateState(update.getMessage());
    }




    }











