package org.leiber.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.commons.dbcp2.BasicDataSource;
import org.leiber.utils.Constants.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
    private static BasicDataSource poolConnection;

    private ConfigDatabase() {
    }

    /**
     * Configures and returns the connection pool. <br>
     * Created on 19/10/2024 at 8:51 p.m.
     *
     * @returns BasicDataSource
     * @author Leiber Bertel
     */
    public static BasicDataSource getInstance() {
        if (poolConnection == null) {
            try {
                poolConnection = new BasicDataSource();
                poolConnection.setUrl(jdbcUrl);
                poolConnection.setUsername(username);
                poolConnection.setPassword(password);

                poolConnection.setInitialSize(MagicNumber.THREE);
                poolConnection.setMinIdle(MagicNumber.THREE);
                poolConnection.setMaxIdle(MagicNumber.TEN);
                poolConnection.setMaxTotal(MagicNumber.TEN);

            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error, unable to connect to the db", e);
            }
        }
        return poolConnection;
    }

    /**
     * Gets the connection from the connection pool. <br>
     * Created on 19/10/2024 at 8:50 p.m.
     *
     * @returns Connection
     * @author Leiber Bertel
     */
    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }

}
