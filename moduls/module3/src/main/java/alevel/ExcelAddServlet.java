package alevel;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/exceladd")
public class ExcelAddServlet extends HttpServlet {

    DBManagerExcel dbManagerExcel = new DBManagerExcel();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } finally {
            reader.close();
        }

        System.out.println(sb);

        Type type = new TypeToken<HashMap<String, HashMap<String,String>>>(){}.getType();
        HashMap<String, Map<String,String>> myMap = new Gson().fromJson(sb.toString(), type);

        for(Map.Entry<String, Map<String,String>> mapEntry:myMap.entrySet()){
            System.out.println(" - "+mapEntry.getKey());
            for(Map.Entry<String,String> secEntrySet:mapEntry.getValue().entrySet()){
                System.out.println("    - "+secEntrySet.getKey()+"  :  "+secEntrySet.getValue());
            }
        }

    }
}
