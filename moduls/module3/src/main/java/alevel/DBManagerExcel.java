package alevel;

import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;
import java.util.HashMap;

public class DBManagerExcel<T> {
    private String url = "jdbc:mysql://localhost:3306/";
    private String db_name = "excel";
    private String user = "root";
    private String password = "youshallnotpass16";
    private String full_url_to_db;
    private String url_sufix = "?verifyServerCertificate=false&useSSL=true";


    public DBManagerExcel() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        full_url_to_db = url + db_name + url_sufix;
    }

    private int executeUpdate(String query) {
        int generated_id = 0;
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            con = DriverManager.getConnection(full_url_to_db, user, password);
            statement = con.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                generated_id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return generated_id;
    }

    private CachedRowSet executeQuery(String query) {
        CachedRowSet rowSet = null;
        try (Connection con = DriverManager.getConnection(full_url_to_db, user, password);
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(query);) {
            rowSet = new CachedRowSetImpl();
            rowSet.populate(resultSet);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowSet;
    }



    public boolean isTableExist(String table_name){
        Connection con=null;
        ResultSet resultSet;
        boolean result=false;
        try {
            DatabaseMetaData metadata = con.getMetaData();
            resultSet = metadata.getTables(null, null, table_name, null);
            if(resultSet.next()){
                result=true;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    public void createTable(String table_name){
        String query = "DROP TABLE IF EXISTS `"+db_name+"`.`"+table_name+"`;";
        executeUpdate(query);
        query = "CREATE TABLE IF NOT EXISTS `"+db_name+"`.`"+table_name+"` (" +
                "`id` VARCHAR(30) NOT NULL ," +
                "`data_formula` VARCHAR(255)," +
                "`value` VARCHAR(255))";
        executeUpdate(query);
    }


    public void addRows(String table_name, String id, String data_formula, String value){
        String query = "INSERT INTO `"+db_name+"`.`"+table_name+"` VALUES ('"+id+"','"+data_formula+"','"+value+"');";
        executeUpdate(query);
    }

    public HashMap getTableData(String table_name) throws SQLException {
        HashMap<String, HashMap<String, String>> rowMap = new HashMap<>();
        String query = "SELECT * FROM `"+db_name+"`.`"+table_name+"`;";
        CachedRowSet rowSet=executeQuery(query);
        ResultSetMetaData md = rowSet.getMetaData();
        int count=0;
        while(rowSet.next()){
            HashMap<String,String> row = new HashMap<>();
            for(int i =1;i<=md.getColumnCount();i++){
                row.put(md.getColumnLabel(i),rowSet.getString(i));
            }
            rowMap.put(String.valueOf(++count),row);
        }
        return rowMap;
    }


}
