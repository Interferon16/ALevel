import java.util.Random;

public class Horse implements Runnable {

    public void run() {
        Random rnd = new Random();
        int race_lenght = 1000;
        int horse_distance = 0;
        while (horse_distance <= race_lenght) {
            long st = System.currentTimeMillis();
            int rand_sleep = rnd.nextInt(100) + 301;
            int rand_distance = rnd.nextInt(20) + 31;
            horse_distance+=rand_distance;
            try {
                Thread.sleep(rand_sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int place = Race.getNextPlace();
        System.out.print("\nHorse - " + Thread.currentThread().getName() + " is finished by number " + place);
        System.out.print(place == 1 ? " - we have a WINNER!!!" : "");
    }
}


