import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Select action: \n" +
                "1 - add database from csv files and indexes \n" +
                "2 - start handmade workbench \n");


        switch(scanner.nextInt()){
            case 1:
                new DBGenerate("K:\\Home_Works\\db\\db").run();break;
            case 2:
                new Workbench().run();break;
            default:
                System.out.println("No such action");
        }
    }
}
