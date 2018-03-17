package com.java.crud.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    final static Log logger = LogFactory.getLog(ConnectionFactory.class);

    private static final Connection connection = openConnection();


    /**
     * @return
     */
    private static Connection openConnection() {

        logger.debug("-------- PostgreSQL JDBC Connection Testing ------------");

        Properties props = readProperties();

        Connection connection;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.passwd"));
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e.getStackTrace());
            return null;
        }

        return connection;

    }

    public static Connection getConnection() {
        return connection;
    }

    public static ResultSet query(String sql) throws Exception {
        try {
            return getConnection().prepareStatement(sql).executeQuery();
        } catch (Exception e) {
            throw e;
        }
    }

    private static Properties readProperties() {

        Properties props = new Properties();
//        Path myPath = Paths.get("src/main/resources/database.properties");

        try {
            props.load(ConnectionFactory.class.getResourceAsStream("/database.properties"));
//            BufferedReader bf = Files.newBufferedReader(myPath, StandardCharsets.UTF_8);
//
//            props.load(bf);
        } catch (IOException ex) {
            logger.error("Sorry, something wrong!", ex);
        }

        return props;
    }
}



