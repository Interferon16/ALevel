package task4;


import java.sql.*;

import static task4.utils.resultSetToString;

public class SocialNetworkControl {
    private String url = "jdbc:mysql://localhost:3306/";
    private String db_name = "social_network";
    private String user = "root";
    private String password = "youshallnotpass16";
    private String full_url_to_db;
    private String url_sufix = "?verifyServerCertificate=false&useSSL=true";


    public SocialNetworkControl() {
        full_url_to_db = url + db_name + url_sufix;
    }

    public SocialNetworkControl(String url, String db_name, String user, String password, boolean sufix) {
        this.url = url;
        this.db_name = db_name;
        this.user = user;
        this.password = password;

        if (sufix) {
            full_url_to_db = url + db_name + url_sufix;
        } else {
            full_url_to_db = url + db_name;
        }
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

    private void executeQuery(String query) {
        try (Connection con = DriverManager.getConnection(full_url_to_db, user, password);
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(query);) {

            String result = resultSetToString(resultSet);
            System.out.println(result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addPerson(String name, String lastname) {
        String query = "INSERT INTO " + db_name + ".entity VALUES (null); ";
        int gen_id = executeUpdate(query);
        query = "INSERT INTO " + db_name + ".person VALUES (" + gen_id + ",'" + name + "','" + lastname + "');";
        executeUpdate(query);

    }


    public void addCommentToEntity(int idFromPerson, int idToEntity, String text) {
        String query = "INSERT INTO " + db_name + ".entity VALUES (null); ";
        int gen_id = executeUpdate(query);
        query = "INSERT INTO " + db_name + ".comments VALUES("+ gen_id +"," + idFromPerson + "," + idToEntity + ",'" + text + "');";
        executeUpdate(query);
    }

    public void addFoto(int idFromPerson, String title, String url) {
        String query = "INSERT INTO " + db_name + ".entity VALUES (null); ";
        int gen_id = executeUpdate(query);
        query = "INSERT INTO " + db_name + ".foto VALUES(" + gen_id + "," + idFromPerson + ",'" + title + "','" + url + "');";
        executeUpdate(query);
    }

    public void like(int idFromPerson, int idToEntity) {
        String query = "call like_from_person_to_entity_create_delete(" + idFromPerson + "," + idToEntity + ");";
        executeUpdate(query);
    }

    public void countLikesOfEntity(int idEntity) {
        String query = "SELECT COUNT(person.id) AS 'likes' FROM person " +
                "INNER JOIN likes ON(likes.id_from_person=person.id) " +
                "where likes.id_to_entity=" + idEntity + ";";

        executeQuery(query);

    }

    public void listWhoLikeTheEntity(int idEntity) {
        String query = "SELECT person.first_name, person.last_name FROM person " +
                "INNER JOIN likes ON(likes.id_from_person=person.id) " +
                "where likes.id_to_entity=" + idEntity + ";";

        executeQuery(query);
    }


}
