package task4;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class utils {

    public static String resultSetToString(ResultSet resultset) throws SQLException {
        StringBuffer result = new StringBuffer("");
        ResultSetMetaData md = resultset.getMetaData();
        int column_count = md.getColumnCount();
        int row_count = 0;
        while (resultset.next()) {
            row_count++;
            if (row_count == 1) {
                for (int i = 1; i <= column_count; i++) {
                    result.append(md.getColumnName(i) + " ");
                }
                result.append("\n");
            }

            for (int i = 1; i <= column_count; i++) {
                if (i > 1) result.append(" ");
                String columnValue = resultset.getString(i);
                result.append(columnValue);
            }
            result.append("\n");
        }
        return result.toString();
    }
}
