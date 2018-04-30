import java.util.Comparator;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleNumbers {
    private CopyOnWriteArrayList<Integer> simple_numbers = new CopyOnWriteArrayList<>();
    private static int finished_threads=0;

    public static synchronized void threadIsFinishedReport() {
        finished_threads++;
    }

    public SimpleNumbers() {
        introduce();
    }

    private void introduce() {
        System.out.println("Enter max range from 1 to .. for searching simple numbers");
        Scanner scanner = new Scanner(System.in);
        int max_number = scanner.nextInt();
        System.out.println("Enter number of thread for searching");
        Scanner scanner1 = new Scanner(System.in);
        int thread_num = scanner1.nextInt();
        startThreads(max_number,thread_num);
    }


    private void startThreads(int max_number,int thread_num){
        int start=1;
        int step=max_number/thread_num;
        int finish=step;
        ExecutorService pool;
        pool= Executors.newFixedThreadPool(thread_num);
        for(int i =0;i<thread_num-1;i++){
            pool.execute(new IsSimpleToArray(start,finish));
            start+=step;
            finish+=step;
        }
        pool.execute(new IsSimpleToArray(start,max_number));
        while(finished_threads<thread_num){
            try {
                Thread.sleep(200);
            } catch (InterruptedException ignore) {}
        }
        sortArray();
        printArray();
        pool.shutdown();
    }

    private void sortArray(){
        simple_numbers.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1>o2){
                    return 1;
                }if (o1<o2){
                    return -1;
                }
                return 0;
            }
        });
    }

    public void printArray(){
        for(Integer i:simple_numbers){
            System.out.println(i);
        }
    }

    class IsSimpleToArray implements Runnable {
        private int start;
        private int finish;
        public IsSimpleToArray(int start,int finish){
            this.start=start;
            this.finish=finish;
        }

        @Override
        public void run() {
            IsSimple simple = new IsSimple();
            for(int i=start;i<finish;i++){
                if(simple.find(i)){
                    simple_numbers.add(i);
                }
            }
            SimpleNumbers.threadIsFinishedReport();
        }
    }

    class IsSimple {

        public boolean find(int number) {
            if (number < 2) {
                return false;
            }
            if (number == 2) {
                return true;
            }if(number%2==0){
                return false;
            }
            for (int i = 3; i < number; i+=2) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
