package main;

import ability.Button;
import command.Command;
import org.telegram.telegrambots.meta.api.objects.Message;

public enum State {

    MENU {
        public State doSomething(Message message) {
            if (message.getText().equals("/start")) {
                command.menu(message, "Вы можете выбрать что угодно");
            }
            if (message.getText().equals(Button.SUBSCRIPTION.get())) {
                command.subscription(message);
                System.out.println("Doing Something in Menu state and jumping to SUBSCRIBE, argument = " + message.getText());
                return ADDING;
            } else return this;
        }
    },

    ADDING {
        public State doSomething(Message message) {
            if (message.getText().equals(Button.ADD.get())) {//если клиент нажал кнопку добавить
                System.out.println("Doing Something in ADDING state and jumping to  INPUT_SERVIE, argument = " + message.getText());
                command.instal(message);
                return INPUT_SERVIE;
            }

            if (message.getText().equals(Button.EDIT.get())) {
                System.out.println("Редактировать");
                command.menu(message, "Получилось");
                return MENU;
            } else return this;
        }
    },

    INPUT_SERVIE {
        public State doSomething(Message message) {
            System.out.println("Doing Something in INPUT_SERVIE state and jumping to INPUT_BILLING, argument = " + message.getText());
            command.outsub(message);
            return INPUT_BILLING;
        }
    },

    INPUT_BILLING {
        public State doSomething(Message message) {
            System.out.println("Doing Something in INPUT_BILLING state and jumping to  MENU, argument = " + message.getText());
            command.billing(message);
            command.menu(message, "Подписка добавлена");
            return MENU;
        }
    },

    FINAL {
        public State doSomething(Message message) {
            System.out.println("Doing Something in FINAL state and jumping to FINAL, argument = " + message.getText());
            return this;
        }
    };


    Command command = new Command();

    public abstract State doSomething(Message message);
}
