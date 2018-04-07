import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class DBGenerate {
    private String path;
    private String[] csv_files;
    private String[] index_files;
    private String url = "jdbc:mysql://localhost:3306/";
    private String db_name = "temp";
    private String user = "root";
    private String password = "youshallnotpass16";
    private String full_url_to_db;


    public DBGenerate(String path) {
        this.path = path;
        String url_sufix = "?verifyServerCertificate=false&useSSL=true";
        full_url_to_db = url + db_name + url_sufix;
    }


    public DBGenerate(String path, String db_name) {
        this(path);
        this.db_name = db_name;
        if (db_name == "") {
            this.db_name = "temp";
        }
    }

    public DBGenerate(String path, String url, String db_name) {
        this(path, db_name);
        this.url = url;
    }

    public DBGenerate(String path, String url, String db_name, String user, String password) {
        this(path,url,db_name);
        this.user = user;
        this.password = password;
    }

    public void run() {
        DBFilesSearcher dbfiles = new DBFilesSearcher(path);
        csv_files = dbfiles.getCsv_files_path();
        index_files = dbfiles.getIndex_files_path();
        generateTablesFromCsv();
        createForeignKeysFromIndex();
    }

    private void generateTablesFromCsv() {
        ArrayList<String[]> table_body = new ArrayList<>();
        for (String path_to_csv : csv_files) {
            table_body.clear();

            File file = new File(path + "\\" + path_to_csv);
            String table_name = file.getName().replace(".csv", "");
            table_body = FileParcer.get(path + "\\" + path_to_csv, ",");

            String[] table_head = table_body.get(0);
            table_body.remove(0);
            table_body.trimToSize();
            createTableFromCsv(table_name, table_head);
            insertingFromCsvToDB(table_name, table_body);
        }
    }

    private void createTableFromCsv(String table_name, String[] table_head) {
        String query = generateQueryToCreateTable(table_name, table_head);

        try (Connection connection = DriverManager.getConnection(full_url_to_db, user, password);
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
            query_construct.append(s + " VARCHAR(100), ");
        }
        query_construct.append("PRIMARY KEY (" + primary_key + ")");
        query_construct.append(")");
        return query_construct.toString();
    }


    private void insertingFromCsvToDB(String table_name, ArrayList<String[]> table_body) {

        String query = generateQueryToInsertInTable(table_name, table_body);

        try (Connection connection = DriverManager.getConnection(full_url_to_db, user, password);
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

    private String generateQueryToInsertInTable(String table_name, ArrayList<String[]> table_body) {
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

    private void createForeignKeysFromIndex() {
        ArrayList<String[]> index_table = new ArrayList<>();
        for (String path_to_index : index_files) {
            index_table.clear();
            index_table = FileParcer.get(path + "\\" + path_to_index, " |\\(|\\)");
            insertIndexesToDB(index_table);
        }
    }

    private void insertIndexesToDB(ArrayList<String[]> index_table) {
        for (String[] line : index_table) {
            String query = "ALTER TABLE `" + line[0] + "` " +
                    "ADD CONSTRAINT `" + line[3] + "`" +
                    "  FOREIGN KEY (`" + line[3] + "`)" +
                    "  REFERENCES `" + line[2] + "` (`" + line[3] + "`);";
            try (Connection connection = DriverManager.getConnection(full_url_to_db, user, password);
                 Statement statement = connection.createStatement();) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


}




