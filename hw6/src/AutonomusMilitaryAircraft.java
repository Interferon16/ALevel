public class AutonomusMilitaryAircraft extends MilitaryAircraft {

    public AutonomusMilitaryAircraft(){
        super();
        range_of_control=0;
        range_from_base=0;
    }
    public AutonomusMilitaryAircraft(double speed,double vektor,double altitude, int bullets,int range_of_control){
        super(speed,vektor,altitude,bullets);
        this.range_of_control=range_of_control;
        range_from_base=0;
    }

    private int range_from_base;
    private int range_of_control;

    @Override
    public int move(int range) {
        range_from_base+=super.move(range);
        return range;
    }

    public int getRange_from_base() {
        return range_from_base;
    }

    public int getRange_of_control() {
        return range_of_control;
    }

    public void setRange_of_control(int range_of_control) {
        this.range_of_control = range_of_control;
    }
}
