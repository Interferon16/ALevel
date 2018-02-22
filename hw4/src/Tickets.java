public class tickets {
    private static int LTCounts=0,a,b;

    public static int LuckyTicketsCount(int x){
        for(int i=0;i<=999;i++){
            for(int j=0;j<=999;j++){
                int a=i/100;
                int b=(i/10)%10;
                int c=i%10;
                int d=j/100;
                int e=(j/10)%10;
                int f=j%10;
                //a=summing.summline(i);
                //b=summing.summline(j);
                //if(a==b){
                if((a+b+c)==(d+e+f)){
                    LTCounts++;
                }
            }
        }
        return LTCounts;
    }

}
