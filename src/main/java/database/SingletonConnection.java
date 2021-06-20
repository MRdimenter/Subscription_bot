package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class SingletonConnection {
    private static volatile SingletonConnection instance;
    private static Logger log = Logger.getLogger(SingletonConnection.class.getName()); //логирование
    private Connection connection;

    private SingletonConnection() {
        getConnection();
    }

    public static SingletonConnection getInstance() {
        SingletonConnection localInstance = instance;
        if (localInstance == null) {
            synchronized (SingletonConnection.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new SingletonConnection();
                    instance.getConnection();
                    instance.validConnection();
                }
            }
        }
        return localInstance;
    }


    public void getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(System.getenv("DATA_URL"), System.getenv("DATA_USER"), System.getenv("DATA_PASSWORD"));
        } catch (SQLException e) {
            log.severe("Не удалось загрузить Driver Manager");
        }

        this.connection = connection;

    }

    public Connection get() {
        return this.connection;
    }

    private void validConnection() {
        if (connection != null) log.info("- - - You successfully connected to database now - - -");
        else log.info("- - - Failed to make connection to database - - -");
    }

}

