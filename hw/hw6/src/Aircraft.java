public class Aircraft {

    public Aircraft(){
        speed=0;
        vektor=0;
        altitude=0;
        range=0;
    }

    public Aircraft(double speed,double vektor,double altitude){
        this.speed=speed;
        this.vektor=vektor;
        this.altitude=altitude;
        range=0;
    }

    private int range;
    private double speed;
    private double vektor;
    private double altitude;

    public void takeoff(){
        System.out.println("Взлетаем!");
    }
    public int move(int range){
        System.out.println("Летим!");
        this.range+=range;
        return range;
    }
    public void landing(){
        System.out.println("Садимся!");
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setVektor(double vektor) {
        this.vektor = vektor;
    }

    public double getSpeed() {
        return speed;
    }

    public double getVektor() {
        return vektor;
    }

    public double getAltitude() {
        return altitude;
    }
}
