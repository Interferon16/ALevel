package tasks;

/*
Не работает
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Task_4 {

    public static void run(String path) throws IOException {
        HashMap<String,Integer>passwords=start(path);
        Map<String, Integer> final_pass = sort(passwords);
        print(final_pass);

    }

    private static HashMap<String,Integer> start(String path) throws IOException {
        HashMap<String, Integer> passwords = new LinkedHashMap<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден");
            try {
                reader.close();
            } catch (NullPointerException s) {
                System.out.println("потоки закрыты");
            }
            System.exit(0);
        }
        while (reader.ready()) {
            String s = reader.readLine();
            String[] tempstring = s.split(":");
            if (passwords.get(tempstring[1]) == null) {
                passwords.put(tempstring[1], 1);
            } else {
                passwords.put(tempstring[1], passwords.get(tempstring[1]) + 1);
            }
        }

        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("потоки закрыты");
        }
        return passwords;
    }

    private static void print(Map<String, Integer> final_pass) {

        int count = 0;
        for (Map.Entry temp : final_pass.entrySet()) {
            System.out.print(++count + " Пароль | " + temp.getKey());
            System.out.println(" | Используется раз : " + temp.getValue());
            if (count > 9) {
                break;
            }
        }
    }


    private static Map<String, Integer> sort(HashMap passwords) {
        List<Map.Entry<String, Integer>> list =
                new LinkedList<>(passwords.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        Map<String, Integer> final_pass = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            final_pass.put(entry.getKey(), entry.getValue());
        }
        return final_pass;

    }


}

