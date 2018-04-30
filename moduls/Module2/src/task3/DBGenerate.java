package task3;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class DBGenerate {
    private String path;
    private String url = "jdbc:mysql://localhost:3306/temp?verifyServerCertificate=false&useSSL=true";
    private String user = "root";
    private String password = "youshallnotpass16";
    private String table_name = "Class";
    String[] table_head = {"Class_id", "Students", "Teacher"};


    public DBGenerate(String path) {
        this.path = path;
    }

    public void run() {
        dropTable();
        generateTablesFromCsv();
        new Workbench().run();
    }

    private void generateTablesFromCsv() {
        ArrayList<String[]> table_body = new ArrayList<>();
        table_body.clear();

        File file = new File(path);
        table_body = FileParcer.get(path, ",");

        createTableFromCsv();
        insertingFromCsvToDB(table_body);
    }

    private void createTableFromCsv() {
        String query = generateQueryToCreateTable(table_name, table_head);

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String generateQueryToCreateTable(String table_name, String[] table_head) {
        StringBuilder query_construct = new StringBuilder("CREATE TABLE " + table_name + "(");
        int column_count = 0;
        String primary_key = "";
        for (String s : table_head) {
            column_count++;
            if (column_count == 1) {
                primary_key = s;
            }
            query_construct.append(s + " VARCHAR(255), ");
        }
        query_construct.append("PRIMARY KEY (" + primary_key + ")");
        query_construct.append(")");
        return query_construct.toString();
    }


    private void insertingFromCsvToDB(ArrayList<String[]> table_body) {

        String query = generateQueryToInsertInTable(table_body);

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement prst = connection.prepareStatement(query);) {
            for (String[] array : table_body) {
                int pos = 0;
                for (String s : array) {
                    prst.setString(++pos, s);
                }
                prst.executeUpdate();
                prst.clearParameters();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String generateQueryToInsertInTable(ArrayList<String[]> table_body) {
        StringBuilder query_construct = new StringBuilder("INSERT INTO " + table_name + " VALUES (");
        String[] mask = table_body.get(0);
        int count = 0;
        for (String s : mask) {
            count++;
            if (count > 1) {
                query_construct.append(",");
            }
            query_construct.append(" ?");
        }
        query_construct.append(")");

        return query_construct.toString();
    }

    private void dropTable() {
        String query = "DROP TABLE IF EXISTS "+table_name+";";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}









