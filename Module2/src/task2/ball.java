package task2;

public class ball implements Runnable {
    @Override
    public void run() {
        while(true){
            PingPong.tossBall();
            System.out.println("bamm");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignore) {}
            if(!PingPong.isCapture()){
                System.out.println("You lose! :(");
                System.exit(0);
            }
        }
    }
}
