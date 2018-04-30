package task3;

import java.util.ArrayList;

abstract class FileParcer {
    private static ArrayList<String[]> tablesBody = new ArrayList<>();

    public static ArrayList<String[]> get(String path) {
        return get(path,",");
    }

    public static ArrayList<String[]> get(String path,String splitter) {
        MyTextFileReader reader = new MyTextFileReader(path);
        tablesBody.clear();
        for (String s : reader) {
            tablesBody.add(s.split(splitter));
        }
        return tablesBody;
    }

}