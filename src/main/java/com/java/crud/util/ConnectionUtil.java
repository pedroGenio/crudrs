package com.java.crud.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Main class for Connection
 */
public class ConnectionUtil {
    final static Log logger = LogFactory.getLog(ConnectionUtil.class);

    private static Connection connection;


    /**
     * Opens connection at DataSource with properties
     * @return
     */
    private static Connection openConnection() {
        Properties props = readDbProperties();

        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setServerName(props.getProperty("db.server"));
        ds.setDatabaseName(props.getProperty("db.database"));
        ds.setUser(props.getProperty("db.user"));
        ds.setPassword(props.getProperty("db.passwd"));

        DataSource dataSource = ds;
        try {
            connection = dataSource.getConnection();
////            Class.forName("org.postgresql.Driver");
////            connection = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.passwd"));
        } catch (SQLException e) {
            logger.error(e.getStackTrace());
            return null;
        }

        return connection;
    }

    /**
     * Returns connection opened if it's closed
     * @return connection
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        try {
            if (isClosed()) {
                connection = openConnection();
            }
        } catch (Exception e) {
            throw e;
        }
        return connection;
    }

    /**
     * Closes transaction and closes the database
     * @throws Exception
     */
    public static void autoCommitAndClose() throws Exception {
        try {
            getConnection().setAutoCommit(true);
            getConnection().close();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Closes the database
     * @throws Exception
     */
    public static void close() throws Exception {
        try {
            getConnection().close();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Verify if the connection is closed
     * @return
     * @throws Exception
     */
    public static boolean isClosed() throws Exception {
        try {
            return connection == null || connection.isClosed();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Get the property file for the database
     * @return
     */
    private static Properties readDbProperties() {

        Properties props = new Properties();

        try {
            props.load(ConnectionUtil.class.getResourceAsStream("/database.properties"));
        } catch (IOException ex) {
            logger.error("Sorry, something wrong!", ex);
        }

        return props;
    }
}



