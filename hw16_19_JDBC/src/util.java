import java.util.Random;

public class util {

    public static void main(String[] args) {
        Random rnd = new Random();

        for(int i =0;i<1000;i++){
            //System.out.println("(null"+","+(rnd.nextInt(1000)+1)+","+"\'http:\\\\random.pic.com\\"+(rnd.nextInt(999999)+1)+".jpg\')"+(i<999?",":";"));
            System.out.println("(null"+","+(rnd.nextInt(1000)+1)+","+(rnd.nextInt(1000)+1)+","+"\'randomtext"+(rnd.nextInt(999999)+1)+"\')"+(i<999?",":";"));
        }
    }
}
