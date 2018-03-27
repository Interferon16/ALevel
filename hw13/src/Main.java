import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Chosse the task");
        System.out.print("1 - race \n" +
                        "2 - finding simple numbers \n");
        Scanner scanner = new Scanner(System.in);
        int tasknumber = scanner.nextInt();
        switch (tasknumber){
            case 1:
                Race.startRace();break;
            case 2:
                SimpleNumbers numbers = new SimpleNumbers();break;
            default:
                System.out.println("Wrong task");
        }
    }
}
