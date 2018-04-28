package alevel;

import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;

public class DBManagerExcel<T> {
    private String url = "jdbc:mysql://localhost:3306/";
    private String db_name = "Excel";
    private String user = "root";
    private String password = "diafkon";
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
        String query = "CREATE TABLE IF NOT EXIST "+"`"+table_name+"` (" +
                "`id` INT NOT NULL auto_increment";
        executeUpdate(query);
    }
    public void addColumns(String table_name, int column_numbers,int last_column){
        String query = "ALTER TABLE `"+table_name+"` ";
        for(int i=0;i<column_numbers+1;i++){
            query+="ADD COLUMN `"+(last_column+1)+"` VARCHAR(255) NOT NULL";
            if(i<column_numbers){
                query+=",";
            }
        }
        query+=";";
        executeUpdate(query);
    }
    public void addRows(String[] row_data){

    }


}
