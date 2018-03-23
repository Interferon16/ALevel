package Airplanes;

public class TransportAircraft extends Aircraft{

    public TransportAircraft(){
        super();
        mass=0;
        fuelmass=0;
    }
    public TransportAircraft(double speed,double vektor,double altitude,int mass,int fuelmass){
        super(speed,vektor,altitude);
        this.mass=mass;
        this.fuelmass=fuelmass;
    }
    private int mass;
    private int fuelmass;

    public void fuelConsumption(){

    }

    public int getMass() {
        return mass;
    }

    public int getFuelmass() {
        return fuelmass;
    }
}
