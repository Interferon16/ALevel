package tasks.Task_1;

import tasks.Task_1.Person;

public class Student extends Person {
    public Student(){
        super();
    }
    public Student(String name,int age){
        super(name,age);
    }

    private String facultet;

    public void shooseFacultet(){
        facultet=Facultet.choise();
    }

    private int math_score,chem_score,language_score,it_score;
    private double average_rating;

    public void examScors(int math, int chem, int language,int it){
        this.math_score=math;
        this.chem_score=chem;
        this.language_score=language;
        this.it_score=it;
    }

    public String getFacultet() {
        return facultet;
    }

    public int getMath_score() {
        return math_score;
    }

    public int getChem_score() {
        return chem_score;
    }

    public int getLanguage_score() {
        return language_score;
    }

    public int getIt_score() {
        return it_score;
    }

    public double getAverage_rating() {
        average_rating=(double)(math_score+chem_score+language_score+it_score)/4;
        return average_rating;
    }
}
