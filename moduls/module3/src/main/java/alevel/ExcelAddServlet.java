package alevel;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@WebServlet("/exceladd")
public class ExcelAddServlet extends HttpServlet {

    DBManagerExcel dbManagerExcel = new DBManagerExcel();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder json = new StringBuilder();
        BufferedReader reader = request.getReader();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line).append('\n');
            }
        } finally {
            reader.close();
        }

        System.out.println("Get json: "+json);
        jsonToDB(json.toString());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String table_name=request.getParameter("table_name");
        try {
            HashMap<Integer,HashMap<String,String>> map= dbManagerExcel.getTableData(table_name);
            Gson gson = new Gson();
            String json = gson.toJson(map);
            response.setContentType("application/json");
            PrintWriter writer = response.getWriter();
            writer.println(json);
            writer.flush();
            writer.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void jsonToDB(String json){
        String table_name = "";
        String id = "";
        String data_formula = "";
        String value = "";

        Type type = new TypeToken<TreeMap<String, TreeMap<String, String>>>() {
        }.getType();
        TreeMap<String, TreeMap<String, String>> myMap = new Gson().fromJson(json, type);

        for (Map.Entry<String, TreeMap<String, String>> mapEntry : myMap.entrySet()) {
            int string_num = Integer.valueOf(mapEntry.getKey());
            for (Map.Entry<String, String> secEntrySet : mapEntry.getValue().entrySet()) {
                if (string_num == 1) {
                    if (secEntrySet.getKey().equals("data_formula")) {
                        table_name = secEntrySet.getValue();
                    }
                    dbManagerExcel.createTable(table_name);
                }
                if (secEntrySet.getKey().equals("id")) {
                    id = secEntrySet.getValue();
                }
                if (secEntrySet.getKey().equals("data_formula")) {
                    data_formula = secEntrySet.getValue();
                }
                if (secEntrySet.getKey().equals("value")) {
                    value = secEntrySet.getValue();
                }
            }
            dbManagerExcel.addRows(table_name, id, data_formula, value);
        }
    }
}
