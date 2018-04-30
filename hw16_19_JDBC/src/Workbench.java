import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Workbench {

    private String url = "jdbc:mysql://localhost:3306/";
    private String db_name = "employees";
    private String user = "root";
    private String password = "youshallnotpass16";
    private String full_url_to_db;

    public Workbench(String url, String db_name, String user, String password) {
        this.url = url;
        this.db_name = db_name;
        this.user = user;
        this.password = password;
    }

    public Workbench() {
        this("jdbc:mysql://localhost:3306/", "temp", "root", "youshallnotpass16");
        this.full_url_to_db = url + db_name + "?verifyServerCertificate=false&useSSL=true";
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("\n" +
                    "Select an action: \n" +
                    "1 - execute sql file with query \n" +
                    "2 - execute query to DB from here \n" +
                    "3 - exit from workbench \n");
            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    executeScriptsFromFile();
                    break;
                case 2:
                    executeQueryFromConsole();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("No such action");
                    break;
            }
        }
    }

    private void executeScriptsFromFile() {
        ArrayList<String[]> script_file = new ArrayList<>();
        script_file.clear();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter path to file with sql scripts");
        String path = scanner.next();
        path.replace("\\", "\\\\")
                .replace("/", "\\\\");
        StringBuilder query = new StringBuilder("");
        for (String s : new MyTextFileReader(path)) {
            query.append(s);
        }
        String result=executeQuery(query.toString());
        resultPrint(result);

    }

    private void executeQueryFromConsole() {
        System.out.println("Enter sql query");
        Scanner scanner = new Scanner(System.in);
        String query = scanner.next();
        String result = executeQuery(query);
        resultPrint(result);
    }

    private String executeQuery(String query) {
        String result = "";
        try (Connection connection = DriverManager.getConnection(full_url_to_db, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultset = statement.executeQuery(query);) {

            result=resultSetToString(resultset);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String resultSetToString(ResultSet resultset) throws SQLException {
        StringBuffer result = new StringBuffer("");
        ResultSetMetaData md = resultset.getMetaData();
        int column_count = md.getColumnCount();
        int row_count = 0;
        while (resultset.next()) {
            row_count++;
            if (row_count == 1) {
                for (int i = 1; i <= column_count; i++) {
                    result.append(md.getColumnName(i) + " | ");
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

    private void resultPrint(String result){
        Scanner scanner = new Scanner(System.in);
        System.out.println("*************RESULT-START****************");
        System.out.println(result);
        System.out.println("*************RESULT-END******************");
        boolean answer = true;
        while(answer){
            System.out.println("\nSave result in the file?  yes/no?");
            String select = scanner.next();
            switch(select){
                case "yes":
                    answer=false;
                    saveResultToFile(result);break;
                case "no":
                    answer=false;break;
                default:
                    System.out.println("Please answer");
            }
        }
    }
    private void saveResultToFile(String result){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter path to directory whe save result file");
        String path = scanner.next().replace("\\","\\\\")
                                    .replace("/","\\\\");
        ResultSaver saver = new ResultSaver(path,result);
        System.out.println("File successfully created - "+saver.getFileName());

    }
}

