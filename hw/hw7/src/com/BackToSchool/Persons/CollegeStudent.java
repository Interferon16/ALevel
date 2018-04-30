package com.BackToSchool.Persons;

public class CollegeStudent extends Student{

    public CollegeStudent(String name,int age,String gender,String idNumber,double gpa,int year,String major){
        super(name,age,gender,idNumber,gpa);
        this.year=year;
        this.major=major;
    }
    private int year;
    private String major;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String toString(){
        return "Name: "+getName()+" | age: "+getAge()+" | Gender: "+getGender()+" | Student id: "+getIdNumber()+" | GPA: "+getGpa()+" | Year: "+getYear()+" | Major: "+getMajor();
    }
}
