package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        String url = properties.getProperty("connection.url");
        String login = properties.getProperty("connection.username");
        String password = properties.getProperty("connection.password");
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void executeStatement(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable(String tableName) {
        String sql = String.format("CREATE TABLE IF NOT EXISTS %s();", tableName);
        executeStatement(sql);
    }

    public void dropTable(String tableName) {
        String sql = String.format("DROP TABLE %s;", tableName);
        executeStatement(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format("ALTER TABLE %s ADD %s %s;", tableName, columnName, type);
        executeStatement(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format("ALTER TABLE %s DROP COLUMN %s;", tableName, columnName);
        executeStatement(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format("ALTER TABLE %s RENAME COLUMN %s TO %s;", tableName, columnName, newColumnName);
        executeStatement(sql);
    }


    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = Class.forName("org.postgresql.Driver").getClassLoader().getResourceAsStream("app.properties")) {
            if (in == null) {
                throw new RuntimeException("Property file not found");
            }
            config.load(in);
        }
        try (TableEditor tableEditor = new TableEditor(config)) {
            tableEditor.createTable("new_table");
            tableEditor.addColumn("new_table", "name", "VARCHAR(255)");
            System.out.println(tableEditor.getTableScheme("new_table"));
            tableEditor.renameColumn("new_table", "name", "color");
            System.out.println(tableEditor.getTableScheme("new_table"));
            tableEditor.dropColumn("new_table", "color");
            System.out.println(tableEditor.getTableScheme("new_table"));
            tableEditor.dropTable("new_table");
        }
    }
}