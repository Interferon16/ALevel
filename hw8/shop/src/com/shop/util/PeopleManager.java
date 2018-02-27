package com.shop.util;

import java.util.Random;

public class PeopleManager {

    public String nextName(){
        String nameList[]={"София", "Злата", "Ева", "Екатерина", "Анастасия", "Алиса", "Анна", "Елизавета", "Полина", "Карина",
                        "Артем", "Иван", "Максим", "Марк", "Дмитрий", "Михаил", "Александр", "Тимофей", "Владислав", "Матвей"};
        Random rnd = new Random();
        int iterrator = rnd.nextInt(nameList.length);
        String name = nameList[iterrator];
        return name;
    }

    public double nextMoney(){
        Random rnd = new Random();
        double money =(double) rnd.nextInt(1001)/10;
        return money;
    }

    public int nextAge(){
        Random rnd = new Random();
        int age =rnd.nextInt(64)+16;
        return age;
    }
}
