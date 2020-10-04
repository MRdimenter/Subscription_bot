package Database;

import java.sql.*;
import java.util.logging.Logger;

public class PostgresConnection {
    private final static String ADD_USER = "insert into userpeople (id, name) VALUES (?, ?)";
    private static Logger log = Logger.getLogger(PostgresConnection.class.getName()); //логирование
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;


    public PostgresConnection() {
       connection = getConnection();
       validConnection();

    }





    private Connection getConnection() {
        try {
            connection = DriverManager.getConnection(System.getenv("DATA_URL"), System.getenv("DATA_USER"), System.getenv("DATA_PASSWORD"));
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


    public void setUserToDatabase(long id, String name) {
        try {
            preparedStatement = connection.prepareStatement(ADD_USER);

            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            log.severe("Ошибка PostgresConnection");
        }
    }
}
