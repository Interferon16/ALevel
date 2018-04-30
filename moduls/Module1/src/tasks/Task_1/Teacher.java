package tasks.Task_1;

public class Teacher extends Person {

    public Teacher(){
        super();
    }
    public Teacher(String name, int age){
        super(name,age);
    }

    public void examStudent(Student student){
        Exam.start(student);
    }
}
