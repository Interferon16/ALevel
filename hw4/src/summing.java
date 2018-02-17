public class summing {
    private static int sum,checker;

    public static int summline(int a){

        for(int i=0;i<1;i+=checker){
            int b=a%10;
            sum+=b;
            if(a<10){
                checker++;
                break;
            }
            a/=10;
        }
        return sum;
    }

}
