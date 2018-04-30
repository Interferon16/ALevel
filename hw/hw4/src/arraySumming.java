public class arraySumming {

    public static int summUpperGeneralLine(int[][] m) {
        int summ=0;
        int length = m.length;
        for (int i=0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (m[i][j] >= 0) {
                    summ += m[i][j];
                }
            }
        }
        return summ;
    }

    public static int[] maxCompositionLine(int[][] m) {
        int result[] = new int[2];
        int composition1, composition2 = -1, count = 0;
        for (int i = 0; i < m.length; i++) {
            composition1=1;
            for (int j = 0; j < m[i].length; j++) {
                if(m[i][j]==0){
                    m[i][j]=1;
                }
                composition1 *= Math.abs(m[i][j]);
            }
            if (composition1 > composition2) {
                count = i+1;
                composition2 = composition1;

            }
        }
        result[0] = count;
        result[1] = composition2;
        return result;
    }
}
