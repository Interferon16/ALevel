package task3;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Workbench {

    private String url_to_DB;
    private String db_name;
    private String user;
    private String password;
    private String full_db_url;

    public Workbench(String url_to_DB, String db_name, String user, String password) {
        this.url_to_DB = url_to_DB;
        this.db_name = db_name;
        this.user = user;
        this.password = password;
    }

    public Workbench() {
        this("jdbc:mysql://localhost:3306/", "temp", "root", "youshallnotpass16");
        this.full_db_url=url_to_DB + db_name + "?verifyServerCertificate=false&useSSL=true";
    }

    public void run() {
        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("*******************************");
            System.out.println("Enter class name for query to database");
            String query = generateQuery(scanner.next());
            String result = executeQuery(query);
            if(result.equals("")){
                System.out.println("Such class not found");
            }else{
                System.out.println(result);
            }
        }
    }


    private String generateQuery(String text){
        String query =
                "SELECT * FROM "+db_name+".Class "+
                "WHERE Class_id = '"+text+"';";
        return query;
    }

    private String executeQuery(String query) {
        String result="";
        try (Connection connection = DriverManager.getConnection(full_db_url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultset = statement.executeQuery(query);) {

            result = printResultSetTable(resultset);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String printResultSetTable(ResultSet resultset) throws SQLException {
        StringBuilder result = new StringBuilder("");
        ResultSetMetaData md = resultset.getMetaData();
        int column_count = md.getColumnCount();
        int row_count=0;
        while (resultset.next()) {
            row_count++;
            if(row_count==1){
                for (int i = 1; i <=column_count; i++) {
                    result.append(md.getColumnName(i)+" | ");
                }
                result.append("\n");
            }

            for (int i = 1; i <= column_count; i++) {
                if (i > 1) result.append(" | ");
                String columnValue = resultset.getString(i);
                result.append(columnValue);
            }
            result.append("\n");
        }
        return result.toString();
    }
}

