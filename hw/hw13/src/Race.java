import java.util.ArrayList;

public class Race {

    private static int place = 0;

    public static synchronized int getNextPlace() {
        return ++place;
    }

    public static void startRace() throws InterruptedException {
        System.out.println("Race is started, ALL BETS ARE OFF");
        ArrayList<Thread> HorseRace= new ArrayList<>();
        String[] names = {
                "Jack",
                "Bill",
                "Sem",
                "Bob",
                "Elly",
                "Kriss",
                "Barney",
                "Coddy",
                "Piper",
                "Lizzy"};

        for(int i=0;i<names.length;i++){
            HorseRace.add(new Thread(new Horse(),names[i]+" №"+(i+1)));
        }
        for(Thread h: HorseRace){
            h.start();
        }
        for(Thread h: HorseRace){
            h.join();
        }
    }
}
