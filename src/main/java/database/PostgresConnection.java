package database;


import ability.SqlRequests;
import data.Subscribe;
import data.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class PostgresConnection {
    private static Logger log = Logger.getLogger(PostgresConnection.class.getName()); //логирование
    private PreparedStatement preparedStatement;


    public void setUserToDatabase(long id, String firstName, String lastName, String userName) {
        if (!isUser(id)) {
            try {
                preparedStatement = SingletonConnection.getInstance().get().prepareStatement(SqlRequests.ADD_USER.get());
                preparedStatement.setLong(1, id);
                preparedStatement.setString(2, firstName);
                preparedStatement.setString(3, lastName);
                preparedStatement.setString(4, userName);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                log.severe("Ошибка PostgresConnection");
            }
        } else log.info("Пользователь уже существует");


    }


    //SELECT EXISTS(SELECT id FROM userpeople WHERE id = ?)
    public boolean isUser(long id) {
        try {
            preparedStatement = SingletonConnection.getInstance().get().prepareStatement(SqlRequests.IS_USER.get());
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                return resultSet.getBoolean("exists"); //tck
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public User getUser(long id) {
        try {
            preparedStatement = SingletonConnection.getInstance().get().prepareStatement(SqlRequests.IS_USER.get());
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) ;

            return new User(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void addSubscribe(Subscribe subscribe) {
        try {

            preparedStatement = SingletonConnection.getInstance().get().prepareStatement(SqlRequests.ADD_SUBSCRIBE.get());
            preparedStatement.setString(1, subscribe.getNameService());
            preparedStatement.setInt(2, subscribe.getBillingNumber());
            preparedStatement.setString(3, subscribe.getBillingDate());
            preparedStatement.setDate(4, new java.sql.Date(Date.valueOf(subscribe.getFirstPayment()).getTime()));
            preparedStatement.setInt(5, subscribe.getPrice());
            preparedStatement.setLong(6, subscribe.getUserId());
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Вывод всех подписок по ID пользователя
     */
    public ArrayList<String> getSubscribeById(long id) {
        try {
            ArrayList<String> subscribeList = new ArrayList<>();
            preparedStatement = SingletonConnection.getInstance().get().prepareStatement(SqlRequests.OUT_SUBSCRIBE_BY_IdUser.get());
            preparedStatement.setLong(1, id);


            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) subscribeList.add(resultSet.getString("nameService"));
            return subscribeList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public ArrayList<Subscribe> getStateSubscribeById(long id) {
        try {
            ArrayList<Subscribe> subscribes = new ArrayList<>();
            Subscribe subscribe;
            preparedStatement = SingletonConnection.getInstance().get().prepareStatement(SqlRequests.OUT_STATE_SUBSCRIBE_BY_IdUser.get());
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                subscribe = new Subscribe();
                subscribe.setNameService(resultSet.getString(1));
                subscribe.setBillingNumber(resultSet.getInt(2));
                subscribe.setBillingDate(resultSet.getString(3));
                subscribe.setFirstPaymentForNormalizeDate(resultSet.getString(4));
                subscribe.setPrice(String.valueOf(resultSet.getInt(5)));
                subscribes.add(subscribe);
            }

            return subscribes;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        PostgresConnection postgresConnection = new PostgresConnection();

        System.out.println(postgresConnection.getStateUserById(123456));

        postgresConnection.setStateUserById(2, "NEOK");
        System.out.println(postgresConnection.getStateUserById(2));
    }


    /**
     * Вывод статуса из БД
     */

    public String getStateUserById(long id) {
        try {
            String state = "";
            preparedStatement = SingletonConnection.getInstance().get().prepareStatement(SqlRequests.OUT_STATUS.get());
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                state = resultSet.getString(1);
            }

            return state;

        } catch (SQLException e) {
            System.out.println("Ошибка");
        }
        return null;
    }

    /**
     * Обновление статуса в Бд
     */

    public void updateStateUserById(long id, String state) {
        try {
            preparedStatement = SingletonConnection.getInstance().get().prepareStatement(SqlRequests.UPDATE_STATUS.get());
            preparedStatement.setString(1, state);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            log.severe("Ошибка PostgresConnection");
        }
    }

    /**
     * Ввод статуса в БД
     */

    public void setStateUserById(long id, String state) {
        try {
            preparedStatement = SingletonConnection.getInstance().get().prepareStatement(SqlRequests.SET_STATUS.get());
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, state);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            log.severe("Ошибка PostgresConnection");
        }

    }


    /**
     * Удаление подписки у пользователя
     */

    public void deleteSubscribeByName(String nameservice) {
        try {
            preparedStatement = SingletonConnection.getInstance().get().prepareStatement(SqlRequests.DELETE_SUBSCRIBE.get());
            preparedStatement.setString(1, nameservice);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

