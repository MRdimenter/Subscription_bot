package database;


import data.Subscribe;
import data.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class PostgresConnection {
    private final static String ADD_USER = "insert into userpeople (id, firstName, lastName, userName) VALUES (?, ?,?,?)";
    private final static String IS_USER = "SELECT EXISTS(SELECT id FROM userpeople WHERE id = ?)";
    private final static String UPDATE_STATE = "update userpeople set state = ? where id = ?";
    private final static String GET_STATE = "update userpeople set state = ? where id = ?";
    private final static String ADD_SUBSCRIBE = "insert into subscribe (nameService, billingNumber, billingDate, firstPayment, price, idUser) VALUES ( ?, ?, ?,? ,?,?)";
    private static Logger log = Logger.getLogger(PostgresConnection.class.getName()); //логирование

    private PreparedStatement preparedStatement;



    public void setUserToDatabase(long id, String firstName, String lastName, String userName) {
            if(!isUser(id)) {
                try {
                    preparedStatement = SingletonConnection.getInstance().get().prepareStatement(ADD_USER);

                    preparedStatement.setLong(1, id);
                    preparedStatement.setString(2, firstName);
                    preparedStatement.setString(3, lastName);
                    preparedStatement.setString(4, userName);
                    preparedStatement.executeUpdate();

                } catch (SQLException e) {
                    log.severe("Ошибка PostgresConnection");
                }
            }
            else log.info("Пользователь уже существует");



    }






    //SELECT EXISTS(SELECT id FROM userpeople WHERE id = ?)
    public boolean isUser(long id) {
        try {
            preparedStatement = SingletonConnection.getInstance().get().prepareStatement(IS_USER);
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
            preparedStatement = SingletonConnection.getInstance().get().prepareStatement(IS_USER);
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

            preparedStatement = SingletonConnection.getInstance().get().prepareStatement(ADD_SUBSCRIBE);
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


}
