import task1.TestNewArrayList;
import task2.PingPong;
import task3.DBGenerate;
import task3.Workbench;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Task number");
        Scanner scanner = new Scanner(System.in);
        int task_num=scanner.nextInt();
        switch(task_num){
            case 1:
                TestNewArrayList.run();break;
            case 2:
                PingPong.run();break;
            case 3:
                System.out.println("Enter path to Class.csv");
                String path = scanner.next().replace("\\","\\\\")
                                            .replace("/","\\\\");
                new DBGenerate(path).run();break;
            case 4:
                break;
        }
    }
}
