package tasks.Task_1;

import java.util.Random;

public class Exam {

    public static void start(Student student){
        Random rnd = new Random();
        student.examScors(rnd.nextInt(5)+1,rnd.nextInt(5)+1,rnd.nextInt(5)+1,rnd.nextInt(5)+1);
    }
}
