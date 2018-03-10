import java.io.*;
import java.util.Scanner;

public class demo {
    public static String read(String path) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }finally {
            try {
                if(reader!=null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Поток закрыт");
            System.exit(0);
        }
        StringBuilder stringBuilder = new StringBuilder();

        String currentString;
        try {
            while ((currentString = reader.readLine()) != null) {
                stringBuilder.append(currentString);
                stringBuilder.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(read("C:\\test1.txt"));
    }
}

