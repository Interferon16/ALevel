package tasks.Task_1;

import java.util.Scanner;

public class EntryExams {

    public static void start(){
        System.out.println("Введите количество студентов на экзамене");
        Scanner scanner = new Scanner(System.in);
        int student_num=scanner.nextInt();
        Teacher teacher = new Teacher();
        System.out.println("Экзамен принимает преподаватель - " +teacher.getName());

        for(int i=0;i<student_num;i++){
            Student student = new Student();
            teacher.examStudent(student);
            student.shooseFacultet();
            System.out.println("###########################################");
            System.out.println("Абитуриент " + student.getName()+" | "+student.getAge()+" лет здал экзаменны со следующим результатом:");
            System.out.println("Математика : "+student.getMath_score());
            System.out.println("Химия : "+student.getChem_score());
            System.out.println("Иностранный язык : "+student.getLanguage_score());
            System.out.println("Компьютерные науки : "+student.getIt_score());
            System.out.println("Средняя оценка составила :"+student.getAverage_rating());
            if(student.getAverage_rating()>3){
                System.out.println("Абитуриент " + student.getName()+" | "+student.getAge()+" лет ЗАЧИСЛЕН на факультет - "+student.getFacultet());
            }else{
                System.out.println("Абитуриент " + student.getName()+" | "+student.getAge()+" лет НЕ ЗАЧИСЛЕН на факультет - "+student.getFacultet());
            }
            System.out.println("###########################################");

        }
    }
}
