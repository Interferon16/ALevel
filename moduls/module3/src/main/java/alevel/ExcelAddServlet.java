package alevel;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/exceladd")
public class ExcelAddServlet extends HttpServlet {

    DBManagerExcel dbManagerExcel = new DBManagerExcel();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String table_name = request.getParameter("table_name");
        int column_number = Integer.valueOf(request.getParameter("column_number"));
        String row_data = request.getParameter("row_data");


        if(!dbManagerExcel.isTableExist(table_name)){
            dbManagerExcel.createTable(table_name);
            dbManagerExcel.addColumns(table_name,column_number,0);
        }


    }
}
