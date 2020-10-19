package main;

import ability.Button;
import command.Command;
import data.Subscribe;
import org.telegram.telegrambots.meta.api.objects.Message;

public enum State {
    MENU {
        State doSomething(String aParameter, Message message) {
            if (aParameter.equals("/start")) {
                command.menu(message, "Вы можете выбрать что угодно");
            } if (aParameter.equals("Подписки")) {
                command.subscription(message);
                System.out.println("Doing Something in Menu state and jumping to SUBSCRIBE, argument = " + aParameter);
                return ADDING;
            }
            else return this;
        }
    },

 /*   SUBSCRIBE {
        State doSomething(String aParameter, Message message) {
            System.out.println("Doing Something in SUBSCRIBE state and jumping to ADDING, argument = " + aParameter);
            return ADDING;
        }
    },*/

    ADDING {
        State doSomething(String aParameter, Message message) {
            if (aParameter.equals("Добавить")) {//если клиент нажал кнопку добавить
                System.out.println("Doing Something in ADDING state and jumping to  INPUT_SERVIE, argument = " + aParameter);
                command.instal(message);
                return INPUT_SERVIE;
            }

            if (aParameter.equals("Редактировать")) {
                System.out.println("Редактировать");
                command.menu(message, "Основное меню");
                return MENU;
            } else {
                System.out.println("Повторяем");
                return this;
            }
        }
    },

    INPUT_SERVIE {
        State doSomething(String aParameter, Message message) {
            System.out.println("Doing Something in INPUT_SERVIE state and jumping to INPUT_BILLING, argument = " + aParameter);
            command.outsub(message);
            return INPUT_BILLING;
        }
    },

    INPUT_BILLING {
        State doSomething(String aParameter, Message message) {
            System.out.println("Doing Something in INPUT_BILLING state and jumping to  FINAL, argument = " + aParameter);
            command.billing(message);
            return MENU;
        }
    },

    FINAL {
        State doSomething(String aParameter, Message message) {
            System.out.println("Doing Something in FINAL state and jumping to FINAL, argument = " + aParameter);
            return this;
        }
    };

    Command command = new Command();
    abstract State doSomething(String aParameter, Message message);
}
