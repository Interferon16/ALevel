package tasks.Task_1;

import java.util.Random;

public class Facultet {

    public static String choise(){
        Random rnd = new Random();
        int a = rnd.nextInt(5);
        String facultet;
        switch (a){
            case 1:facultet="MechMath";break;
            case 2:facultet="OrganicChemistri";break;
            case 3:facultet="IT_Technology";break;
            case 4:facultet="Filosofy";break;
            default:facultet="Filosofy";break;
        }
        return facultet;

    }

}
