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
            // connection = DriverManager.getConnection(System.getenv("DATA_URL"), System.getenv("DATA_USER"), System.getenv("DATA_PASSWORD")); //for heroku
            connection = DriverManager.getConnection("jdbc:postgresql://ec2-54-156-53-71.compute-1.amazonaws.com:5432/d29ggoa2d1m003", "xhikaenmntzuze", "953cb11826eab3f9f5b610c50bd60e636d7086882f345f164084c180c7ed1d6b");
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

