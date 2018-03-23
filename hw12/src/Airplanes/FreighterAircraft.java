package Airplanes;

public class FreighterAircraft extends TransportAircraft {

    public FreighterAircraft(){
        super();
        carrying_capacity=0;
        load_mass=0;
    }

    public FreighterAircraft(double speed, double vektor, double altitude, int mass, int fuelmass,int carrying_capacity){
        super(speed,vektor,altitude,mass,fuelmass);
        this.carrying_capacity=carrying_capacity;
        this.load_mass=load_mass=0;
    }

    private int carrying_capacity;
    private int load_mass;

    @Override
    public int getMass(){
        return super.getMass()+load_mass;
    }

    public void loading(int mass){

    }

    public void unLoading(int mass){

    }

    public int getCarrying_capacity() {
        return carrying_capacity;
    }

    public int getLoad_mass() {
        return load_mass;
    }
}
