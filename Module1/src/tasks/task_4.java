package tasks;

/*
Не работает
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class task_4 {
    private static String[]passwords = new String[10];
    private static String[] tempstring;

    public void run(String path) throws IOException {
         BufferedReader reader = new BufferedReader(new FileReader(path));
         String s =reader.readLine();
         tempstring=s.split(",");

    }

    public static String[] scanFile(BufferedReader read){

    }
}
