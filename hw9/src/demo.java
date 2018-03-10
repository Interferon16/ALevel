import java.io.*;
import java.util.Scanner;

public class demo {
    public static String read(String path) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");;
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        System.out.println(read("C:\\test.txt"));
    }
}

