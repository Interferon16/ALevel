package Airplanes;

import java.io.Serializable;

public class MilitaryAircraft extends Aircraft implements Serializable {

    public MilitaryAircraft(){
        super();
        bullets=0;
    }
    public MilitaryAircraft(double speed,double vektor,double altitude, int bullets){
        super(speed,vektor,altitude);
        this.bullets=bullets;
    }

    private Object target;
    private int bullets;

    public boolean findTArget(){
        return true;
    }

    public boolean findTArget(Object o){
        return true;
    }

    public boolean shooting(Object o){
        bullets--;
        System.out.print("\nПИУ! ");
        return true;
    }

    public boolean shooting(Object o, int shots){
        System.out.println("");
        for(int i=0;i<=shots;i++){
            bullets--;
            System.out.print("ПИУ! ");
        }
        return true;
    }
}
