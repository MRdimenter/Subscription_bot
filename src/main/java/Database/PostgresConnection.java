package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class PostgresConnection {
    private static Logger log = Logger.getLogger(PostgresConnection.class.getName()); //логирование
    private Connection connection;


    public PostgresConnection() {
       connection = getConnection();
       validConnection();

    }





    private Connection getConnection() {
        try {
            connection = DriverManager.getConnection(System.getenv("DATA_URL"), System.getenv("DATA_USER"), System.getenv("DATA_PASSWORD"));
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

}
