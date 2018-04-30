package Airplanes;

public class PassengerAircraft extends TransportAircraft {

    public PassengerAircraft(){
        super();
        number_of_planting_places=0;
        number_of_passengers=0;
    }

    public PassengerAircraft(double speed,double vektor,double altitude,int mass,int fuelmass,int number_of_planting_places){
        super(speed,vektor,altitude,mass,fuelmass);
        this.number_of_planting_places=number_of_planting_places;
        number_of_passengers=0;
    }

    private int number_of_planting_places;
    private int number_of_passengers;

    public boolean loadPassengers(){
        number_of_passengers++;
        return true;
    }
    public boolean loadPassengers(int number){
        number_of_passengers+=number;
        return true;
    }

    public boolean unLoadPassengers(){
        number_of_passengers--;
        return true;
    }
    public boolean unLoadPassengers(int number){
        number_of_passengers-=number;
        return true;
    }

    public void setNumber_of_passengers(int number_of_passengers) {
        this.number_of_passengers = number_of_passengers;
    }

    public int getNumber_of_planting_places() {
        return number_of_planting_places;
    }

    public int getNumber_of_passengers() {
        return number_of_passengers;
    }
}
