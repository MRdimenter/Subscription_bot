package main;

import ability.Button;
import command.Command;
import data.Subscribe;
import database.PostgresConnection;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.logging.Logger;

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
                postgresConnection.updateStateUserById(message.getChatId(), "SUBSCRIPTIONS");
                return SUBSCRIPTIONS;
            }

            if (message.getText().equals(Button.SETTINGS.get())) {
                command.getKeyboardSettings(message);
                //  System.out.println("Doing Something in Menu state and jumping to SUBSCRIBE, argument = " + message.getText());
                postgresConnection.updateStateUserById(message.getChatId(), "SETTINGS");
                return SETTINGS;
            }

            if (message.getText().equals(Button.STATISCTICS.get())) {
                postgresConnection.updateStateUserById(message.getChatId(), "STATISTICS");
                command.statistics(message);

                return STATISTICS;
            } else return this;
        }
    },

    /**
     * Раздел "Подписки"
     * 1) Добавить
     * 2) Удалить
     * 3) Показать
     * 4) Назад
     */
    SUBSCRIPTIONS {
        public State doSomething(Message message) {


            if (message.getText().equals(Button.ADD.get())) {//если клиент нажал кнопку добавить
                // System.out.println("Doing Something in ADDING state and jumping to  INPUT_SERVIE, argument = " + message.getText());
                command.sendMessage(message, "Напишите имя сервиса:");
                postgresConnection.updateStateUserById(message.getChatId(), "INPUT_SERVIE");

                return INPUT_SERVIE;
            }

            /**
             Удаление подписки
             */
            if (message.getText().equals(Button.DELETE.get())) {
                //  System.out.println("Редактировать");
                //   command.menu(message, postgresConnection.getUserStateToId(message.getChatId()));
                command.getKeyboardSubscription(message, postgresConnection.getSubscribeById(message.getChatId()));
                postgresConnection.updateStateUserById(message.getChatId(), "DELETE_SUBSCRIBE");
                return DELETE_SUBSCRIBE;
            }

            if (message.getText().equals(Button.SHOW.get())) {
                command.getKeyboardSubscription(message, postgresConnection.getSubscribeById(message.getChatId()));
            }

            if (message.getText().equals(Button.BACK.get())) {
                command.menu(message, "Окей");
                postgresConnection.updateStateUserById(message.getChatId(), "MENU");

                return MENU;
            }

            return this;
        }
    },

    /**
     * Раздел статистика
     */

    STATISTICS {
        public State doSomething(Message message) {


            if (message.getText().equals(Button.PER_MONTH.get())) {
                ArrayList<Subscribe> subscribes = postgresConnection.getStateSubscribeById(message.getChatId());
                command.MonthlyStatisticsOutput(message, subscribes);
                postgresConnection.updateStateUserById(message.getChatId(), "STATISTICS");

                return STATISTICS;
            }

            if (message.getText().equals(Button.PER_YEAR.get())) {
                ArrayList<Subscribe> subscribes = postgresConnection.getStateSubscribeById(message.getChatId());
                command.YearStatisticsOutput(message, subscribes);
                postgresConnection.updateStateUserById(message.getChatId(), "STATISTICS");

                return STATISTICS;
            }

            if (message.getText().equals(Button.PER_DAY.get())) {
                ArrayList<Subscribe> subscribes = postgresConnection.getStateSubscribeById(message.getChatId());
                command.DayStatisticsOutput(message, subscribes);
                postgresConnection.updateStateUserById(message.getChatId(), "STATISTICS");

                return STATISTICS;
            }

            if (message.getText().equals(Button.GENERAL.get())) {
                ArrayList<Subscribe> subscribes = postgresConnection.getStateSubscribeById(message.getChatId());
                command.AllStatisticsOutput(message, subscribes);
                postgresConnection.updateStateUserById(message.getChatId(), "STATISTICS");

                return STATISTICS;
            }

            if (message.getText().equals(Button.BACK.get())) {
                command.menu(message, "Окей");
                postgresConnection.updateStateUserById(message.getChatId(), "MENU");

                return MENU;
            } else return this;
        }
    },

    SETTINGS {
        public State doSomething(Message message) {
            if (message.getText().equals(Button.HELP.get())) {
                command.menu(message, "Пока что ничем не могу вам помочь!");
                //  System.out.println("Doing Something in Menu state and jumping to SUBSCRIBE, argument = " + message.getText());
                postgresConnection.updateStateUserById(message.getChatId(), "MENU");
                return MENU;
            }


            if (message.getText().equals(Button.BACK.get())) {
                //  System.out.println("Редактировать");
                //   command.menu(message, postgresConnection.getUserStateToId(message.getChatId()));

                command.menu(message, "Окей");
                postgresConnection.updateStateUserById(message.getChatId(), "MENU");

                return MENU;
            } else return this;
        }

    },


    INPUT_SERVIE {
        public State doSomething(Message message) {


            //  System.out.println("Doing Something in INPUT_SERVIE state and jumping to INPUT_BILLING, argument = " + message.getText());
            serviceName = command.nameService(message);

            command.sendMessage(message, "Напишите расчётный период\nНапример: 1 месяц, 3 месяца, 1 год");
            postgresConnection.updateStateUserById(message.getChatId(), "INPUT_BILLING");

            return INPUT_BILLING;
        }
    },

    INPUT_BILLING {
        public State doSomething(Message message) {


            //  System.out.println("Doing Something in INPUT_BILLING state and jumping to  MENU, argument = " + message.getText());
            billingPeriod = command.billingPeriod(message);
            command.sendMessage(message, "Напишите первый платеж:\nНапример:\nСегодня\n29 ионя\n31 (актуальный месяц)");

            postgresConnection.updateStateUserById(message.getChatId(), "FIRST_PAYMENT");

            return FIRST_PAYMENT;
        }

    },

    FIRST_PAYMENT {
        public State doSomething(Message message) {


            firstPayment = command.firstPayment(message);

            command.sendMessage(message, "Напишите сколько стоит подписка:");

            postgresConnection.updateStateUserById(message.getChatId(), "HOW_MUCH");

            return HOW_MUCH;
        }
    },

    HOW_MUCH {
        public State doSomething(Message message) {


            Subscribe subscribe = new Subscribe();

            subscribe.setNameService(serviceName);

            subscribe.setBillingPeriod(billingPeriod);
            if (subscribe.isWrong()) {
                command.subscriptionMessage(message, "Был неккоректно введён расчётный период. Попробуйте ещё раз!");
                return SUBSCRIPTIONS;
            }

            System.out.println("3");

            subscribe.setFirstPayment(firstPayment);
            if (subscribe.isWrong()) {
                command.subscriptionMessage(message, "Был неккоректно введён первый платёж. Попробуйте ещё раз!");
                return SUBSCRIPTIONS;
            }

            subscribe.setPrice(command.howMuchIs(message));
            if (subscribe.isWrong()) {
                command.subscriptionMessage(message, "Была неккоректно введена цена. Попробуйте ещё раз!");
                return SUBSCRIPTIONS;
            }

            subscribe.setUserId(message.getChatId());

            postgresConnection.addSubscribe(subscribe);
            command.menu(message, "Подписка добавлена");
            postgresConnection.updateStateUserById(message.getChatId(), "MENU");

            return MENU;
        }
    },


    DELETE_SUBSCRIBE {
        public State doSomething(Message message) {


            if (message.getText().equals(Button.BACK.get())) {
                command.subscriptionMessage(message, "Окей");
                postgresConnection.updateStateUserById(message.getChatId(), "SUBSCRIPTIONS");
                return SUBSCRIPTIONS;
            } else {
                postgresConnection.deleteSubscribeByName(message.getText());
                postgresConnection.updateStateUserById(message.getChatId(), "SUBSCRIPTIONS");
                command.subscriptionMessage(message, "Подписка " + message.getText() + " удалена");

                return SUBSCRIPTIONS;

            }


        }
    },


    FINAL {
        public State doSomething(Message message) {
            //    System.out.println("Doing Something in FINAL state and jumping to FINAL, argument = " + message.getText());
            return this;
        }
    };

    State() {

    }


    public static String serviceName;
    public static String billingPeriod;
    public static String firstPayment;
    Command command = new Command();
    PostgresConnection postgresConnection = new PostgresConnection();
    private static Logger log = Logger.getLogger(State.class.getName());

    public abstract State doSomething(Message message);
}
