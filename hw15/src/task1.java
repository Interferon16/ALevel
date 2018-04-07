/*
select employees.first_name, employees.last_name, employees.hire_date from employees.employees;

1) Записать в список первых 200 сотрудников. Сотрудник имеет имя, фамилию, дату найма.

 */

import java.sql.*;
import java.util.ArrayList;

public class task1 {


    public static void main(String[] args) {
        String query = "SELECT employees.first_name, employees.last_name, employees.hire_date from employees";


        int count = 0;

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees", "root", "youshallnotpass16");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            ArrayList<DAO> employees = new ArrayList<>();

            for (int i = 0; i < 200; i++) {
                resultSet.next();
                String first_name=resultSet.getString("first_name");
                String last_name=resultSet.getString("last_name");
                Date date = resultSet.getDate("hire_date");
                DAO dao = new DAO(first_name,last_name,date);
                employees.add(dao);
            }
            for(DAO d:employees){
                System.out.print("name: "+d.getFirst_name()+" | soname: "+d.getLast_name()+"Hire date: "+d.getHire_date()+"\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

