package com.BackToSchool.Persons;

public class Student extends Person{

    public Student(String name,int age,String gender,String idNumber, double gpa){
        super(name,age,gender);
        this.gpa=gpa;
        this.idNumber=idNumber;
    }

    private double gpa;
    private String idNumber;

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String toString(){
        return "Name: "+getName()+" | age: "+getAge()+" | Gender: "+getGender()+" | Student id: "+getIdNumber()+" | GPA: "+getGpa();
    }
}

