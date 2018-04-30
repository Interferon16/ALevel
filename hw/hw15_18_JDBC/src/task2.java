/*
select em.first_name, em.last_name, sal.salary
from employees em
inner join salaries sal on (em.emp_no=sal.emp_no)
where sal.to_date like '2000%';

2) Записать в список зарплату за n-ный год первых ста сотрудников.
Сохранять зарплату и имя-фамилию сотрудника. Использовать PreparedStatement.

 */

import java.sql.*;
import java.util.ArrayList;

public class task2 {


    public static void main(String[] args) {

        String query = "select em.first_name, em.last_name, sal.salary " +
                "from employees em " +
                "inner join salaries sal on (em.emp_no=sal.emp_no) " +
                "where sal.to_date like ?";

        int count = 2000;
        String year = count+"%";


        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees", "root", "youshallnotpass16");
             PreparedStatement prst = connection.prepareStatement(query);){
            prst.setString(1,year);
            ResultSet resultSet = prst.executeQuery();

            ArrayList<DAO> employees = new ArrayList<>();

            for (int i = 0; i < 100; i++) {
                resultSet.next();
                String first_name=resultSet.getString("first_name");
                String last_name=resultSet.getString("last_name");
                int salary = resultSet.getInt("salary");
                DAO dao = new DAO();
                dao.setFirst_name(first_name);
                dao.setLast_name(last_name);
                dao.setSalary(salary);
                employees.add(dao);
            }
            for(DAO d:employees){
                System.out.print("name: "+d.getFirst_name()+" | soname: "+d.getLast_name()+" | Salary: "+d.getSalary()+"\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

