package com.BackToSchool;

import com.BackToSchool.Persons.CollegeStudent;
import com.BackToSchool.Persons.Person;
import com.BackToSchool.Persons.Student;
import com.BackToSchool.Persons.Teacher;

public class main {

    public static void main(String[] args) {
        Person hoy = new Person("Yura Klinskih",20,"M");
        Person lindsey = new Student("Lindsey Stirling",22,"F","am886543",4.1);
        Person tarja = new Teacher("Tarja Turunen",30,"F",2500,"English");
        Person valera = new CollegeStudent("Valeriy Kipelov",24,"M","af457623",4.1,2,"Biology");


        System.out.println(hoy.toString());
        System.out.println(lindsey.toString());
        System.out.println(tarja.toString());
        System.out.println(valera.toString());

    }
}
