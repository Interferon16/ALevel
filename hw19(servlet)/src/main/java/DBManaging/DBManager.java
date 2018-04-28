package DBManaging;

import com.sun.rowset.CachedRowSetImpl;
import entitys.User;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;


public class DBManager<T> {
    private String url = "jdbc:mysql://localhost:3306/";
    private String db_name = "site";
    private String user = "root";
    private String password = "youshallnotpass16";
    private String full_url_to_db;
    private String url_sufix = "?verifyServerCertificate=false&useSSL=true";


    public DBManager() {
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

    public int addUserToDB(String name,String email,String pass){
        String query = "INSERT INTO " + db_name + ".user VALUES (" + null + ",'" + name + "','" + email + "','" + pass + "');";
        return executeUpdate(query);
    }

    public boolean isAlreadyExists(String idx, String idx_name){
        boolean result = false;
        String query = "select count(id) as `count` From "+db_name+".`user` where `"+idx+"`='"+idx_name+"'";
        CachedRowSet rowSet = executeQuery(query);
        try {
            rowSet.beforeFirst();
            while(rowSet.next()){
                if(rowSet.getInt("count")>0){
                    result=true;
                }else result=false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public T getUserInfo(int id){
        String query = "SELECT*FROM `"+db_name+"`.`user` where `id`="+id+";";
        CachedRowSet rowSet=executeQuery(query);
        User user = null;
        try {
            rowSet.beforeFirst();
            rowSet.next();
            user = new User(rowSet.getInt("id"),rowSet.getString("name"),rowSet.getString("email"),rowSet.getString("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (T)user;
    }

}
