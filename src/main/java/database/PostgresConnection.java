package database;


import data.Subscribe;
import data.User;

import java.sql.*;
import java.util.logging.Logger;

public class PostgresConnection {
    private final static String ADD_USER = "insert into userpeople (id, firstName, lastName, userName, state) VALUES (?, ?,?,?,?)";
    private final static String OUT_STATE = "select state from userpeople where id = ?";
    private final static String IS_USER = "SELECT EXISTS(SELECT id FROM userpeople WHERE id = ?)";
    private final static String UPDATE_STATE = "update userpeople set state = ? where id = ?";
    private final static String GET_STATE = "update userpeople set state = ? where id = ?";
    private final static String ADD_SUBSCRIBE = "insert into subscribe (nameService, billingPeriod, firstPayment, price, idUser) VALUES ( ?, ?, ?,? ,?)";
    private static Logger log = Logger.getLogger(PostgresConnection.class.getName()); //логирование
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;


    public PostgresConnection() {
        //connection = SingletonConnection.getInstance().getConnection();
        // validConnection();

    }

    public PostgresConnection(String s) {
        //   connection = SingletonConnection.getInstance().getConnection();
        // validConnection();

    }


    private Connection getConnection() {
        try {
            // connection = DriverManager.getConnection(System.getenv("DATA_URL"), System.getenv("DATA_USER"), System.getenv("DATA_PASSWORD")); //for heroku
            connection = DriverManager.getConnection("jdbc:postgresql://ec2-54-156-53-71.compute-1.amazonaws.com:5432/d29ggoa2d1m003", "xhikaenmntzuze", "953cb11826eab3f9f5b610c50bd60e636d7086882f345f164084c180c7ed1d6b");
            this.statement = connection.createStatement();
        } catch (SQLException e) {
            log.severe("Не удалось загрузить Driver Manager");
            return null;
        }
        return connection;

    }

    private void validConnection() {
        if(connection != null) log.info("- - - You successfully connected to database now - - -");
        else log.info("- - - Failed to make connection to database - - -");
    }


    public void setUserToDatabase(long id, String firstName, String lastName, String userName) {
            if(!isUser(id)) {
                try {
                    preparedStatement = SingletonConnection.getInstance().get().prepareStatement(ADD_USER);

                    preparedStatement.setLong(1, id);
                    preparedStatement.setString(2, firstName);
                    preparedStatement.setString(3, lastName);
                    preparedStatement.setString(4, userName);
                    preparedStatement.setString(5, "start");
                    preparedStatement.executeUpdate();

                } catch (SQLException e) {
                    log.severe("Ошибка PostgresConnection");
                }
            }
            else log.info("Пользователь уже существует");



    }

    public String getUserStateToId(long id) {
        String state = "";
        try {
            preparedStatement = SingletonConnection.getInstance().get().prepareStatement(OUT_STATE);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println(state);
               state =  resultSet.getString("state");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return state;
    }

    public void setUserStateToId(long id, String state) {
        try {
            preparedStatement = SingletonConnection.getInstance().get().prepareStatement(UPDATE_STATE);
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, state);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
            // preparedStatement.setLong(1, 2);
            preparedStatement.setString(1, subscribe.getNameService());
            preparedStatement.setString(2, subscribe.getBillingPeriod());
            preparedStatement.setDate(3, (Date) subscribe.getFirstPayment());
            preparedStatement.setInt(4, subscribe.getPrice());
            preparedStatement.setLong(5, subscribe.getUserId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}