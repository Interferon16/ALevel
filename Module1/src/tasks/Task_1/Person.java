package tasks.Task_1;

import tasks.Task_1.utils.PeopleManager;

public class Person {
    public Person(){
        PeopleManager person = new PeopleManager();
        name=person.nextName();
        age=person.nextAge();
    }
    public Person(String name, int age){
        this.name=name;
        this.age=age;
    }
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
