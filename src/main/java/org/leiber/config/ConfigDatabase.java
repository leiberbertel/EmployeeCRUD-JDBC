package org.leiber.config;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class config to configure the connection to the database <br>
 * Created on 09/10/2024 at 09:20 p.m.
 *
 * @author Leiber Bertel
 */
public class ConfigDatabase {
    private static final Logger logger = Logger.getLogger(ConfigDatabase.class.getName());
    static Dotenv dotenv = Dotenv.load();
    static String jdbcUrl = "jdbc:mysql://localhost:" + dotenv.get("PORT") + "/" + dotenv.get("DATABASE");
    static String username = dotenv.get("USER");
    static String password = dotenv.get("PASSWORD");
    private static Connection connection;

    private ConfigDatabase() {
    }

    /**
     * Performs the connection to the MySQL db, using the Singleton pattern. <br>
     * Created on 09/10/2024 at 9:30 p.m.
     *
     * @author Leiber Bertel
     */
    public static Connection getInstance() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(jdbcUrl, username, password);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error, unable to connect to the db", e);
            }
        }
        return connection;
    }

}
