/*
select employees.first_name
from employees
where employees.last_name like 'K%';


3) Сохранить в список имена всех сотрудников, фамилия которых начинается с произвольной буквы/букв (метод принимает параметр “Начало фамилии”).
Использовать PreparedStatement.

 */


import java.sql.*;
import java.util.Scanner;

public class task3 {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first char or sequence of chars to search through database employees with lastname starting with them");
        String charset = scanner.next()+"%";

        String query =
                "select employees.first_name, employees.last_name " +
                "from employees " +
                "where employees.last_name like ? ;";

        Connection connection=null;
        PreparedStatement prst=null;
        ResultSet resultset=null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees?verifyServerCertificate=false&useSSL=true", "root", "youshallnotpass16");
            prst = connection.prepareStatement(query);
            prst.setString(1, charset);
            resultset = prst.executeQuery();

            printResultSet(resultset);

        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                prst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                resultset.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
    private static void printResultSet(ResultSet resultset) throws SQLException {
        ResultSetMetaData rsmd = resultset.getMetaData();
        int column_count = rsmd.getColumnCount();
        int row_count=0;
        while (resultset.next()) {
            row_count++;
            if(row_count==1){
                for (int i = 1; i <= column_count; i++) {
                    System.out.print(rsmd.getColumnName(i)+" | ");
                }
                System.out.println("");
            }

            for (int i = 1; i <= column_count; i++) {
                if (i > 1) System.out.print(" | ");
                String columnValue = resultset.getString(i);
                System.out.print(columnValue);
            }
            System.out.println("");
        }
    }
}

