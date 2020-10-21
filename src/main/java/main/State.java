package main;

import ability.Button;
import command.Command;
import data.Subscribe;
import database.PostgresConnection;
import org.telegram.telegrambots.meta.api.objects.Message;

public enum State {

    /**
     * Меню приложения
     * 1) Подписки
     * 2) Статистика
     * 3) Настройки
     */
    MENU {
        public State doSomething(Message message) {
            if (message.getText().equals("/start")) {
                command.menu(message, "Добро пожаловать! Теперь вы можете отслеживать ваши платные подписки в любое время :)");
                postgresConnection.setUserToDatabase(message.getChatId(), message.getFrom().getFirstName(), message.getFrom().getLastName(), message.getFrom().getUserName());
            }

            if (message.getText().equals(Button.SUBSCRIPTION.get())) {
                command.subscription(message);
                //  System.out.println("Doing Something in Menu state and jumping to SUBSCRIBE, argument = " + message.getText());
                return SUBSCRIPTIONS;
            } else return this;
        }
    },

    /**
     * Раздел "Подписки"
     * 1) Добавить
     * 2) Удалить
     * 3) Назад
     */
    SUBSCRIPTIONS {
        public State doSomething(Message message) {
            if (message.getText().equals(Button.ADD.get())) {//если клиент нажал кнопку добавить
                // System.out.println("Doing Something in ADDING state and jumping to  INPUT_SERVIE, argument = " + message.getText());
                command.sendMessage(message, "Напишите имя сервиса:");
                return INPUT_SERVIE;
            }

            if (message.getText().equals(Button.DELETE.get())) {
                //  System.out.println("Редактировать");
                //   command.menu(message, postgresConnection.getUserStateToId(message.getChatId()));
                return MENU;
            }

            if (message.getText().equals(Button.BACK.get())) {
                command.menu(message, "Окей");
                return MENU;
            }

            return this;
        }
    },

    INPUT_SERVIE {
        public State doSomething(Message message) {
            //  System.out.println("Doing Something in INPUT_SERVIE state and jumping to INPUT_BILLING, argument = " + message.getText());
            subscribe.setNameService(command.nameService(message));
            command.sendMessage(message, "Напишите расчётный период\nНапример: 1 месяц, 3 месяца, 1 год");
            return INPUT_BILLING;
        }
    },

    INPUT_BILLING {
        public State doSomething(Message message) {
            //  System.out.println("Doing Something in INPUT_BILLING state and jumping to  MENU, argument = " + message.getText());
            subscribe.setBillingPeriod(command.billingPeriod(message));
            command.sendMessage(message, "Напишите первый платеж:\nНапример:\nСегодня\n29 ионя\n31 (актуальный месяц)");

            return FIRST_PAYMENT;
        }

    },

    FIRST_PAYMENT {
        public State doSomething(Message message) {
            subscribe.setFirstPayment(command.firstPayment(message));
            command.sendMessage(message, "Напишите сколько стоит подписка:");

            return HOW_MUCH;
        }
    },

    HOW_MUCH {
        public State doSomething(Message message) {
            subscribe.setPrice(command.howMuchIs(message));
            subscribe.setUserId(message.getChatId());
            postgresConnection.addSubscribe(subscribe);
            command.menu(message, "Подписка добавлена");

            return MENU;
        }
    },

    FINAL {
        public State doSomething(Message message) {
            //    System.out.println("Doing Something in FINAL state and jumping to FINAL, argument = " + message.getText());
            return this;
        }
    };

    Subscribe subscribe = new Subscribe();
    Command command = new Command();
    PostgresConnection postgresConnection = new PostgresConnection();

    public abstract State doSomething(Message message);
}