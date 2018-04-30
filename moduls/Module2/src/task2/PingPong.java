/*
2. MULTI-THREADING Игра "пинг-понг".
ПК пишет "bamm" и у пользователя есть 2 секунды на ввод ответа (любой ввод в консоль).
Игра продолжиться, пока пользователь не проиграет потоку.
*/

package task2;

import java.util.Scanner;

public class PingPong {
    static boolean capture = true;

    public static void run() {
        System.out.println("Ping-Pong game");
        Scanner scanner = new Scanner(System.in);
            System.out.println("Tape \"start\" to begin or exit");
            String command = scanner.next();

            switch(command){
                case "start": startGame();break;
                case "exit" : System.exit(0);
                default:
                    System.out.println("select command");
            }
    }

    synchronized public static void tossBall() {
        capture = false;
    }

    public static boolean isCapture() {
        return capture;
    }

    private static void startGame(){
        Thread thread = new Thread(new ball());
        thread.start();
        Scanner scanner = new Scanner(System.in);
        while(thread.isAlive()){
            if(scanner.next()!=""){
                capture=true;
            }

        }
    }

}
