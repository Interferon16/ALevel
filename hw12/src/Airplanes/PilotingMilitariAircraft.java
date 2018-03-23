package Airplanes;

public class PilotingMilitariAircraft extends MilitaryAircraft {

    public PilotingMilitariAircraft(){
        super();
        crew_number=0;
    }
    public PilotingMilitariAircraft(double speed,double vektor,double altitude, int bullets, int crew_number){
        super(speed,vektor,altitude,bullets);
        this.crew_number=crew_number;

    }

    private int crew_number;

    public boolean ejection(){
        if(crew_number==0){
            return false;
        }
        crew_number--;
        System.out.println("Катапультирование");
        return true;
    }

    public int getCrew_number() {
        return crew_number;
    }

    public void setCrew_number(int crew_number) {
        this.crew_number = crew_number;

    }
}
